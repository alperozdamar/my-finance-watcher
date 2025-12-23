package com.alper.finance.service;

import com.alper.finance.entity.NetWorthCategory;
import com.alper.finance.entity.NetWorthEntry;
import com.alper.finance.entity.NetWorthSnapshot;

import java.time.LocalDate;
import java.util.List;

public interface NetWorthService {

    List<NetWorthCategory> getCategories(int userId);

    NetWorthCategory addCategory(NetWorthCategory category);

    void deleteCategory(int categoryId);

    List<NetWorthEntry> getEntries(int userId);

    NetWorthEntry saveEntry(NetWorthEntry entry, int categoryId);

    void deleteEntry(int entryId);

    /**
     * Copy the latest dated entries to a target date to quickly snapshot net worth.
     */
    void snapshotLatestEntries(int userId, LocalDate targetDate, String noteSuffix);

    List<NetWorthSnapshot> getSnapshots(int userId);

    NetWorthSnapshot saveSnapshot(NetWorthSnapshot snapshot);

    void deleteSnapshot(int snapshotId);
}


