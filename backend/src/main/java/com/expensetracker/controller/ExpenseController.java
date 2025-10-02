package com.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.expensetracker.auth.SuccessResponse;
import com.expensetracker.controller.entity.ExpenseRequest;
import com.expensetracker.expense.Expense;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.service.ExpenseService;
import com.expensetracker.user.User;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createExpense(@Valid @RequestBody ExpenseRequest expenseRequest, @AuthenticationPrincipal UserDetails userDetails) {
        Expense createdExpense = expenseService.createExpense(expenseRequest, userDetails.getUsername());

        return ResponseEntity.ok(
            new SuccessResponse<>(createdExpense)
        );
    }

    @GetMapping
    public ResponseEntity<?> getUserExpenses(@AuthenticationPrincipal UserDetails userDetails) {
        List<Expense> expenses = expenseService.getExpensesForUser(userDetails.getUsername());
        return ResponseEntity.ok(
            new SuccessResponse<>(expenses)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable UUID id, @RequestBody ExpenseRequest expenseRequest, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository
            .findByEmail(userDetails.getUsername())
            .orElseThrow();

        Expense updatedExpense = expenseService.updateExpense(id, expenseRequest, user.getId());
        return ResponseEntity.ok(
            new SuccessResponse<>(updatedExpense)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable UUID id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository
            .findByEmail(userDetails.getUsername())
            .orElseThrow();
        
        expenseService.deleteExpense(id, user.getId());
        return ResponseEntity.noContent().build();
    }
}
