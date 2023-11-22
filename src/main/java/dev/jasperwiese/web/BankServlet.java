package dev.jasperwiese.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jasperwiese.context.Application;
import dev.jasperwiese.context.TransactionApplicationConfiguration;
import dev.jasperwiese.model.Transaction;
import dev.jasperwiese.service.TransactionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


public class BankServlet extends HttpServlet {

    private TransactionService transactionService;

    private ObjectMapper objectMapper;

    public void init() throws ServletException {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(TransactionApplicationConfiguration.class);
        this.transactionService = ctx.getBean(TransactionService.class);
        this.objectMapper = ctx.getBean(ObjectMapper.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getRequestURI().equalsIgnoreCase("/")){
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().print(
                    """
                            <html>
                            <body>
                            <h1> basicTransactions REST API</h1>\s
                            <h2> USE THE FOLLOWING ENDPOINTS:</h2>\s
                            <h3> GET METHODS </h3>\s
                            <p> api/v1/transactions -> returns a list of all of the transactions</p>\s
                            <h3> POST METHODS </h3>\s
                            <p> api/v1/transaction -> JSON of keys amount<String>, reference<String>\s
                            </body>
                            </html>
                    """

            );
        } else if (req.getRequestURI().equalsIgnoreCase("/transactions")) {
            resp.setContentType("application/json; charset=UTF-8");
            List<Transaction> transactions = transactionService.findAll();
            resp.getWriter().print(objectMapper.writeValueAsString(transactions));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equalsIgnoreCase("/transaction")) {
            StringBuffer reqBody = new StringBuffer();
            String line = null;
            BufferedReader reader = req.getReader();
            while((line = reader.readLine()) != null)
                reqBody.append(line);

            JSONObject jsonObject = new JSONObject(reqBody.toString());

            Integer amount = jsonObject.getInt("amount");
            String reference = jsonObject.getString("reference");
            System.out.println(amount);
            System.out.println(reference);
            Transaction transaction = transactionService.create(amount, reference);
            System.out.println(transaction);
            resp.setContentType("application/json; charset=UTF-8");
            String json = objectMapper.writeValueAsString(transaction);

            resp.getWriter().print(json);
        }
    }
}
