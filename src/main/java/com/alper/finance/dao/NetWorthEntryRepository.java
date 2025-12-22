package com.alper.finance.dao;

import com.alper.finance.entity.NetWorthEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface NetWorthEntryRepository extends JpaRepository<NetWorthEntry, Integer> {
    List<NetWorthEntry> findByUserIdOrderByEntryDateDesc(int userId);
    List<NetWorthEntry> findByUserIdAndEntryDate(int userId, Date entryDate);
}


