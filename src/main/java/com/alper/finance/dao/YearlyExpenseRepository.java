package com.alper.finance.dao;

import com.alper.finance.entity.YearlyExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface YearlyExpenseRepository extends JpaRepository<YearlyExpense, Integer> {
    List<YearlyExpense> findByUserIdAndYear(int userId, int year);
    List<YearlyExpense> findByUserIdAndYearAndCategoryId(int userId, int year, int categoryId);
} 