package com.alper.finance.dao;

import com.alper.finance.entity.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Integer> {
    List<IncomeCategory> findByUserIdAndYear(int userId, int year);
} 