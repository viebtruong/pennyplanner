package com.viet.pennyplanner.budget;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class BudgetJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(BudgetJsonDataLoader.class);
    private final JdbcClientBudgetRepository budgetRepository;
    private final ObjectMapper objectMapper;


    public BudgetJsonDataLoader(ObjectMapper objectMapper, JdbcClientBudgetRepository budgetRepository) {
        this.objectMapper = objectMapper;
        this.budgetRepository = budgetRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(budgetRepository.count());
        if(budgetRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/budgets.json")) {
                Budgets allBudgets = objectMapper.readValue(inputStream, Budgets.class);
                log.info("Reading {} budgets from JSON data and saving to in-memory collection.", allBudgets.budgets().size());
                budgetRepository.saveAll(allBudgets.budgets());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading Budgets from JSON data because the collection contains data.");
        }
    }
}
