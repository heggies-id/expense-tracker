package com.expensetracker.repository;

import com.expensetracker.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    List<Expense> findByUserIdOrderByDateDesc(UUID userId);
    Optional<Expense> findByIdAndUserId(UUID id, UUID userId);
}
