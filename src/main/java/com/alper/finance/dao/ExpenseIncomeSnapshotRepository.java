package com.alper.finance.dao;

import com.alper.finance.entity.ExpenseIncomeSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseIncomeSnapshotRepository extends JpaRepository<ExpenseIncomeSnapshot, Integer> {
    List<ExpenseIncomeSnapshot> findByUserIdOrderBySnapshotDateDesc(int userId);
}


