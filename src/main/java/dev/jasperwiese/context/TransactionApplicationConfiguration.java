package dev.jasperwiese.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.jasperwiese.model.Transaction;
import dev.jasperwiese.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionApplicationConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
    @Bean
    public TransactionService transactionService() {
        return new TransactionService();
    }
}
