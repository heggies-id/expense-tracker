package com.expensetracker.service;

import com.expensetracker.controller.entity.ExpenseRequest;
import com.expensetracker.expense.Expense;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Expense> getExpensesForUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return expenseRepository.findByUserIdOrderByDateDesc(user.getId());
    }

    public Expense createExpense(ExpenseRequest expenseRequest, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        Expense expense = new Expense();
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(expenseRequest.getCategory());
        expense.setDate(expenseRequest.getDate());
        expense.setUser(user);

        return expenseRepository.save(expense);
    }

    public Expense updateExpense(UUID expenseId, ExpenseRequest expenseRequest, UUID userId) {
        Expense existingExpense = expenseRepository.findByIdAndUserId(expenseId, userId)
                .orElseThrow(() -> new RuntimeException("Expense not found or user does not have permission"));

        existingExpense.setDescription(expenseRequest.getDescription());
        existingExpense.setAmount(expenseRequest.getAmount());
        existingExpense.setCategory(expenseRequest.getCategory());
        existingExpense.setDate(expenseRequest.getDate());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(UUID expenseId, UUID userId) {
        Expense expense = expenseRepository.findByIdAndUserId(expenseId, userId)
                .orElseThrow(() -> new RuntimeException("Expense not found or user does not have permission"));

        expenseRepository.delete(expense);
    }
}
