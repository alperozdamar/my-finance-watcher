package com.alper.finance.dao;

import com.alper.finance.entity.NetWorthSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NetWorthSnapshotRepository extends JpaRepository<NetWorthSnapshot, Integer> {
    List<NetWorthSnapshot> findByUserIdOrderBySnapshotDateDesc(int userId);
}


