package com.alper.finance.dao;

import com.alper.finance.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
    List<Income> findByUserIdAndYear(int userId, int year);
    List<Income> findByUserIdAndYearAndCategoryId(int userId, int year, int categoryId);
} 