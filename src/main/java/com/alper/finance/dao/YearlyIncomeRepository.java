package com.alper.finance.dao;

import com.alper.finance.entity.YearlyIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface YearlyIncomeRepository extends JpaRepository<YearlyIncome, Integer> {
    List<YearlyIncome> findByUserIdAndYear(int userId, int year);
    List<YearlyIncome> findByUserIdAndYearAndCategoryId(int userId, int year, int categoryId);
} 