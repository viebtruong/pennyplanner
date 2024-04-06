package com.viet.pennyplanner.budget;

import java.math.BigDecimal;

public record Category(
                       String categoryID,
                       String categoryName,
                       BigDecimal budgetedAmount,
                       BigDecimal spentAmount) {
    // Additional methods or validation can go here
}