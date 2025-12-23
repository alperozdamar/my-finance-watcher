package com.alper.finance.controller;

import com.alper.finance.entity.NetWorthCategory;
import com.alper.finance.entity.NetWorthEntry;
import com.alper.finance.entity.NetWorthType;
import com.alper.finance.entity.NetWorthSnapshot;
import com.alper.finance.service.NetWorthService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

@Controller
@RequestMapping("/networth")
public class NetWorthController {

    private static final int DEFAULT_USER_ID = 1;

    private final NetWorthService netWorthService;

    public NetWorthController(NetWorthService netWorthService) {
        this.netWorthService = netWorthService;
    }

    @GetMapping
    public String viewNetWorth(Model model) {
        List<NetWorthCategory> categories = netWorthService.getCategories(DEFAULT_USER_ID);
        List<NetWorthEntry> entries = netWorthService.getEntries(DEFAULT_USER_ID);
        List<NetWorthSnapshot> snapshots = netWorthService.getSnapshots(DEFAULT_USER_ID);

        NavigableMap<LocalDate, Totals> totalsByDate = new TreeMap<>();
        for (NetWorthEntry entry : entries) {
            LocalDate date = toLocalDate(entry.getEntryDate());
            Totals totals = totalsByDate.computeIfAbsent(date, d -> new Totals());
            if (entry.getCategory().getType() == NetWorthType.ASSET) {
                totals.assets += entry.getAmount();
            } else {
                totals.liabilities += entry.getAmount();
            }
        }

        List<String> labels = new ArrayList<>();
        List<Double> assets = new ArrayList<>();
        List<Double> liabilities = new ArrayList<>();
        List<Double> netTotals = new ArrayList<>();
        totalsByDate.forEach((date, totals) -> {
            labels.add(date.toString());
            assets.add(totals.assets);
            liabilities.add(totals.liabilities);
            netTotals.add(totals.assets - totals.liabilities);
        });

        Totals latest = totalsByDate.isEmpty() ? new Totals() : totalsByDate.get(totalsByDate.lastKey());

        List<NetWorthSnapshot> snapshotAsc = new ArrayList<>(snapshots);
        Collections.reverse(snapshotAsc); // ensure oldest -> newest so chart runs left->right

        List<String> snapLabels = new ArrayList<>();
        List<Double> snapAssets = new ArrayList<>();
        List<Double> snapLiabilities = new ArrayList<>();
        List<Double> snapNet = new ArrayList<>();
        for (NetWorthSnapshot snap : snapshotAsc) {
            LocalDate d = toLocalDate(snap.getSnapshotDate());
            snapLabels.add(d.toString());
            snapAssets.add(snap.getAssets());
            snapLiabilities.add(snap.getLiabilities());
            snapNet.add(snap.getNetWorth());
        }

        model.addAttribute("categories", categories);
        model.addAttribute("entries", entries);
        model.addAttribute("snapshots", snapshots);
        model.addAttribute("labels", labels);
        model.addAttribute("assets", assets);
        model.addAttribute("liabilities", liabilities);
        model.addAttribute("netTotals", netTotals);
        model.addAttribute("snapLabels", snapLabels);
        model.addAttribute("snapAssets", snapAssets);
        model.addAttribute("snapLiabilities", snapLiabilities);
        model.addAttribute("snapNet", snapNet);
        model.addAttribute("latestAssets", latest.assets);
        model.addAttribute("latestLiabilities", latest.liabilities);
        model.addAttribute("latestNetWorth", latest.assets - latest.liabilities);

        return "networth/net-worth";
    }

    @PostMapping("/category")
    public String addCategory(@RequestParam String name,
                              @RequestParam NetWorthType type) {
        NetWorthCategory category = new NetWorthCategory();
        category.setName(name);
        category.setType(type);
        category.setUserId(DEFAULT_USER_ID);
        netWorthService.addCategory(category);
        return "redirect:/networth";
    }

    @PostMapping("/entry")
    public String addEntry(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                           @RequestParam int categoryId,
                           @RequestParam double amount,
                           @RequestParam(required = false) String note) {
        NetWorthEntry entry = new NetWorthEntry();
        entry.setUserId(DEFAULT_USER_ID);
        entry.setEntryDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        entry.setAmount(amount);
        entry.setNote(note);
        netWorthService.saveEntry(entry, categoryId);
        return "redirect:/networth";
    }

    @PostMapping("/entry/{id}/delete")
    public String deleteEntry(@PathVariable int id) {
        netWorthService.deleteEntry(id);
        return "redirect:/networth";
    }

    @PostMapping("/snapshot")
    public String snapshotLatest(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                 @RequestParam(required = false) String note) {
        netWorthService.snapshotLatestEntries(DEFAULT_USER_ID, date, note);
        return "redirect:/networth";
    }

    @PostMapping("/quick-snapshot")
    public String quickSnapshot(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                @RequestParam(required = false) String note) {
        NavigableMap<LocalDate, Totals> totalsByDate = new TreeMap<>();
        List<NetWorthEntry> entries = netWorthService.getEntries(DEFAULT_USER_ID);
        for (NetWorthEntry entry : entries) {
            LocalDate d = toLocalDate(entry.getEntryDate());
            Totals totals = totalsByDate.computeIfAbsent(d, k -> new Totals());
            if (entry.getCategory().getType() == NetWorthType.ASSET) {
                totals.assets += entry.getAmount();
            } else {
                totals.liabilities += entry.getAmount();
            }
        }
        if (totalsByDate.isEmpty()) {
            return "redirect:/networth";
        }
        Totals latest = totalsByDate.get(totalsByDate.lastKey());
        NetWorthSnapshot snapshot = new NetWorthSnapshot();
        snapshot.setUserId(DEFAULT_USER_ID);
        snapshot.setSnapshotDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        snapshot.setAssets(latest.assets);
        snapshot.setLiabilities(latest.liabilities);
        snapshot.setNetWorth(latest.assets - latest.liabilities);
        snapshot.setNote(note);
        netWorthService.saveSnapshot(snapshot);
        return "redirect:/networth";
    }

    @PostMapping("/snapshot/{id}/delete")
    public String deleteSnapshot(@PathVariable int id) {
        netWorthService.deleteSnapshot(id);
        return "redirect:/networth";
    }

    private LocalDate toLocalDate(Date date) {
        if (date == null) {
            return LocalDate.now();
        }
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static class Totals {
        double assets = 0;
        double liabilities = 0;
    }
}


