package com.alper.finance.dao;

import com.alper.finance.entity.NetWorthCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NetWorthCategoryRepository extends JpaRepository<NetWorthCategory, Integer> {
    List<NetWorthCategory> findByUserIdOrderByNameAsc(int userId);
}


