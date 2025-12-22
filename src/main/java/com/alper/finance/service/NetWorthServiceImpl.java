package com.alper.finance.service;

import com.alper.finance.dao.NetWorthCategoryRepository;
import com.alper.finance.dao.NetWorthEntryRepository;
import com.alper.finance.dao.NetWorthSnapshotRepository;
import com.alper.finance.entity.NetWorthCategory;
import com.alper.finance.entity.NetWorthEntry;
import com.alper.finance.entity.NetWorthSnapshot;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NetWorthServiceImpl implements NetWorthService {

    private final NetWorthCategoryRepository categoryRepository;
    private final NetWorthEntryRepository entryRepository;
    private final NetWorthSnapshotRepository snapshotRepository;

    public NetWorthServiceImpl(NetWorthCategoryRepository categoryRepository,
                               NetWorthEntryRepository entryRepository,
                               NetWorthSnapshotRepository snapshotRepository) {
        this.categoryRepository = categoryRepository;
        this.entryRepository = entryRepository;
        this.snapshotRepository = snapshotRepository;
    }

    @Override
    public List<NetWorthCategory> getCategories(int userId) {
        return categoryRepository.findByUserIdOrderByNameAsc(userId);
    }

    @Override
    @Transactional
    public NetWorthCategory addCategory(NetWorthCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<NetWorthEntry> getEntries(int userId) {
        return entryRepository.findByUserIdOrderByEntryDateDesc(userId);
    }

    @Override
    @Transactional
    public NetWorthEntry saveEntry(NetWorthEntry entry, int categoryId) {
        NetWorthCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId));
        entry.setCategory(category);
        return entryRepository.save(entry);
    }

    @Override
    @Transactional
    public void deleteEntry(int entryId) {
        entryRepository.deleteById(entryId);
    }

    @Override
    @Transactional
    public void snapshotLatestEntries(int userId, LocalDate targetDate, String noteSuffix) {
        List<NetWorthEntry> existingOnTargetDate = entryRepository.findByUserIdAndEntryDate(
                userId,
                java.sql.Date.valueOf(targetDate)
        );
        if (!existingOnTargetDate.isEmpty()) {
            return; // avoid duplicates if already snapped for that date
        }

        List<NetWorthEntry> allEntries = entryRepository.findByUserIdOrderByEntryDateDesc(userId);
        if (allEntries.isEmpty()) {
            return;
        }

        LocalDate latestDate = allEntries.get(0).getEntryDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        List<NetWorthEntry> latestEntries = allEntries.stream()
                .filter(e -> e.getEntryDate()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                        .isEqual(latestDate))
                .collect(Collectors.toList());

        for (NetWorthEntry entry : latestEntries) {
            NetWorthEntry clone = new NetWorthEntry();
            clone.setUserId(entry.getUserId());
            clone.setCategory(entry.getCategory());
            clone.setAmount(entry.getAmount());
            clone.setEntryDate(java.sql.Date.valueOf(targetDate));
            String originalNote = entry.getNote();
            String suffix = (noteSuffix == null || noteSuffix.isEmpty()) ? "" : " " + noteSuffix.trim();
            clone.setNote((originalNote == null ? "" : originalNote) + suffix);
            entryRepository.save(clone);
        }
    }

    @Override
    public List<NetWorthSnapshot> getSnapshots(int userId) {
        return snapshotRepository.findByUserIdOrderBySnapshotDateDesc(userId);
    }

    @Override
    @Transactional
    public NetWorthSnapshot saveSnapshot(NetWorthSnapshot snapshot) {
        return snapshotRepository.save(snapshot);
    }

    @Override
    @Transactional
    public void deleteSnapshot(int snapshotId) {
        snapshotRepository.deleteById(snapshotId);
    }
}


