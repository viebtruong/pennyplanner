package com.viet.pennyplanner.budget;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
public record Budget(
        @Id
                    String id,
        @NotEmpty
                    String budgetName,
        BigDecimal totalIncome,
        BigDecimal totalExpense,
        //Map<String, Category> categories,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal savingsGoal,
        BigDecimal currentBalance,
        //List<Transaction> transactions,
        String status,
        boolean notifications,
        @Version
        Integer version) {

}
