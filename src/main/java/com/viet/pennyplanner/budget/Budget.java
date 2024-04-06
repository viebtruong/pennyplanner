package com.viet.pennyplanner.budget;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
public record Budget(String budgetName,
                     BigDecimal totalIncome,
                     BigDecimal totalExpense,
                     Map<String, Category> categories,
                     LocalDate startDate,
                     LocalDate endDate,
                     BigDecimal savingsGoal,
                     BigDecimal currentBalance,
                     List<Transaction> transactions,
                     String status,
                     boolean notifications) {

}
