package com.viet.pennyplanner.budget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.*;

@Repository

public class JdbcClientBudgetRepository {
    private static final Logger log = LoggerFactory.getLogger(JdbcClientBudgetRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientBudgetRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }
    public List<Budget> findAll() {
        return jdbcClient.sql("select * from budgets")
                .query(Budget.class)
                .list();
    }
    public Optional<Budget> findById(String id){
        return jdbcClient.sql("select * from budgets where id = :id")
                .param("id", id)
                .query(Budget.class)
                .optional();
    }
    public void create(Budget budget) {
        var updated = jdbcClient.sql("INSERT INTO Budgets(id,budgetName,totalIncome,totalExpense,startDate,endDate,savingsGoal,currentBalance,status, notifications) values(?,?,?,?,?,?,?,?,?,?)")
                .params(List.of(budget.id(),budget.budgetName(),budget.totalIncome(),budget.totalExpense(),budget.startDate(),budget.endDate(),budget.savingsGoal(),
                        budget.currentBalance(),budget.status(),budget.notifications()))
                .update();

        Assert.state(updated == 1, "Failed to create budget " );
    }

    public void update(Budget budget, String id) {
        // SQL statement to update a budget
        var updated = jdbcClient.sql("UPDATE Budgets SET budgetName = ?, totalIncome = ?, totalExpense = ?, startDate = ?, endDate = ?, savingsGoal = ?, currentBalance = ?, status = ?, notifications = ? WHERE id = ?")
                .params(List.of(budget.budgetName(), budget.totalIncome().toString(), budget.totalExpense().toString(), budget.startDate().toString(), budget.endDate().toString(), budget.savingsGoal().toString(), budget.currentBalance().toString(), budget.status(), budget.notifications(), id))
                .update();

        // Assert to ensure that exactly one record was updated
        Assert.state(updated == 1, "Failed to update Budget " + budget.budgetName());
    }

    public void delete(String id) {
        // Assume 'id' is a String; adjust the type if necessary (e.g., to UUID or Integer)
        var updated = jdbcClient.sql("DELETE FROM Budgets WHERE id = :id")
                .param("id", id)  // Ensures the parameter is correctly associated with the query
                .update();

        // Assert to ensure that exactly one record was deleted
        Assert.state(updated == 1, "Failed to delete Budget with ID " + id);
    }
    public int count(){
        return jdbcClient.sql("select * from budgets").query().listOfRows().size();
    }
    public void saveAll(List<Budget> budgets){
        budgets.stream().forEach(this::create);

    }


}
