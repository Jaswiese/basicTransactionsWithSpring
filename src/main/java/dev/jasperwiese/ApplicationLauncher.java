package dev.jasperwiese;

import dev.jasperwiese.web.BankServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class ApplicationLauncher {
    public static void main(String[] args) throws LifecycleException {
        int serverPort = 8080;
        String portProperty = System.getProperty("server.port");
        if(portProperty != null) {
            serverPort = Integer.parseInt(portProperty);
        }
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(serverPort);
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);
        Wrapper servlet = Tomcat.addServlet(ctx, "BankServlet", new BankServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        tomcat.start();
    }
}
