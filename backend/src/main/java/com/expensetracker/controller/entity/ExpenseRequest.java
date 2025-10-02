package com.expensetracker.controller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequest {
    @Positive(message = "Must be greater than 0")
    private BigDecimal amount;

    private String description;
    private String category;
    private LocalDate date;
}
