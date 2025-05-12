package com.alper.finance.controller;

import com.alper.finance.entity.ExpenseCategory;
import com.alper.finance.entity.IncomeCategory;
import com.alper.finance.entity.YearlyExpense;
import com.alper.finance.entity.YearlyIncome;
import com.alper.finance.service.ExpenseIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/yearly-tracker")
public class YearlyExpenseIncomeController {

    @Autowired
    private ExpenseIncomeService expenseIncomeService;

    @GetMapping
    public String viewYearlyTracker(Model model, @RequestParam(defaultValue = "2023") int year) {
        // Fetch expense and income categories for the given year
        List<ExpenseCategory> expenseCategories = expenseIncomeService.getExpenseCategories(1, year); // Assuming userId=1 for now
        List<IncomeCategory> incomeCategories = expenseIncomeService.getIncomeCategories(1, year);

        // Fetch yearly expenses and incomes
        List<YearlyExpense> yearlyExpenses = expenseIncomeService.getYearlyExpenses(1, year);
        List<YearlyIncome> yearlyIncomes = expenseIncomeService.getYearlyIncomes(1, year);

        // Map categoryId to category name for Thymeleaf
        Map<Integer, String> expenseCategoryNames = expenseCategories.stream()
            .collect(Collectors.toMap(ExpenseCategory::getId, ExpenseCategory::getName));
        Map<Integer, String> incomeCategoryNames = incomeCategories.stream()
            .collect(Collectors.toMap(IncomeCategory::getId, IncomeCategory::getName));

        double totalIncome = yearlyIncomes.stream().mapToDouble(YearlyIncome::getAmount).sum();
        double totalExpense = yearlyExpenses.stream().mapToDouble(YearlyExpense::getAmount).sum();
        double netDiff = totalIncome - totalExpense;

        model.addAttribute("year", year);
        model.addAttribute("expenseCategories", expenseCategories);
        model.addAttribute("incomeCategories", incomeCategories);
        model.addAttribute("yearlyExpenses", yearlyExpenses);
        model.addAttribute("yearlyIncomes", yearlyIncomes);
        model.addAttribute("expenseCategoryNames", expenseCategoryNames);
        model.addAttribute("incomeCategoryNames", incomeCategoryNames);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("netDiff", netDiff);

        return "yearly-expense-income";
    }

    @PostMapping("/expense-category")
    public String addExpenseCategory(
        @RequestParam String name,
        @RequestParam int year
    ) {
        ExpenseCategory category = new ExpenseCategory();
        category.setName(name);
        category.setUserId(1); // hardcoded userId
        category.setYear(year);
        expenseIncomeService.addExpenseCategory(category);
        return "redirect:/yearly-tracker?year=" + year;
    }

    @PostMapping("/expense-category/{id}")
    public String deleteExpenseCategory(@PathVariable int id, @RequestParam int year) {
        expenseIncomeService.deleteExpenseCategory(id);
        return "redirect:/yearly-tracker?year=" + year;
    }

    @PostMapping("/income-category")
    public String addIncomeCategory(
        @RequestParam String name,
        @RequestParam int year
    ) {
        IncomeCategory category = new IncomeCategory();
        category.setName(name);
        category.setUserId(1); // hardcoded userId
        category.setYear(year);
        expenseIncomeService.addIncomeCategory(category);
        return "redirect:/yearly-tracker?year=" + year;
    }

    @PostMapping("/income-category/{id}")
    public String deleteIncomeCategory(@PathVariable int id, @RequestParam int year) {
        expenseIncomeService.deleteIncomeCategory(id);
        return "redirect:/yearly-tracker?year=" + year;
    }

    @PostMapping("/yearly-expense")
    public String saveYearlyExpense(
        @RequestParam int year,
        @RequestParam int categoryId,
        @RequestParam double amount
    ) {
        YearlyExpense expense = new YearlyExpense();
        expense.setUserId(1); // hardcoded
        expense.setYear(year);
        expense.setCategoryId(categoryId);
        expense.setAmount(amount);
        expenseIncomeService.saveYearlyExpense(expense);
        return "redirect:/yearly-tracker?year=" + year;
    }

    @PostMapping("/yearly-expense/{id}")
    public String deleteYearlyExpense(@PathVariable int id, @RequestParam int year) {
        expenseIncomeService.deleteYearlyExpense(id);
        return "redirect:/yearly-tracker?year=" + year;
    }

    @PostMapping("/yearly-income")
    public String saveYearlyIncome(
        @RequestParam int year,
        @RequestParam int categoryId,
        @RequestParam double amount
    ) {
        YearlyIncome income = new YearlyIncome();
        income.setUserId(1); // hardcoded
        income.setYear(year);
        income.setCategoryId(categoryId);
        income.setAmount(amount);
        expenseIncomeService.saveYearlyIncome(income);
        return "redirect:/yearly-tracker?year=" + year;
    }

    @PostMapping("/yearly-income/{id}")
    public String deleteYearlyIncome(@PathVariable int id, @RequestParam int year) {
        expenseIncomeService.deleteYearlyIncome(id);
        return "redirect:/yearly-tracker?year=" + year;
    }
} 