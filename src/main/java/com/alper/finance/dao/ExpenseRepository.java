package com.alper.finance.dao;

import com.alper.finance.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByUserIdAndYear(int userId, int year);
    List<Expense> findByUserIdAndYearAndCategoryId(int userId, int year, int categoryId);
} 