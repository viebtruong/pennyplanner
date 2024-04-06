package com.viet.pennyplanner.budget;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(
                          String transactionID,
                          LocalDate date,
                          BigDecimal amount,
                          String category,
                          String description) {
    // Additional methods or validation can go here
}