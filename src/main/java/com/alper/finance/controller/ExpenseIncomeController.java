package com.alper.finance.controller;

import com.alper.finance.entity.ExpenseCategory;
import com.alper.finance.entity.IncomeCategory;
import com.alper.finance.entity.Expense;
import com.alper.finance.entity.Income;
import com.alper.finance.service.ExpenseIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tracker")
public class ExpenseIncomeController {

    @Autowired
    private ExpenseIncomeService expenseIncomeService;

    @GetMapping
    public String viewTracker(Model model, @RequestParam(required = false) Integer year) {
        if (year == null) {
            year = java.time.Year.now().getValue();
        }
        // Fetch expense and income categories for the given year
        List<ExpenseCategory> expenseCategories = expenseIncomeService.getExpenseCategories(1, year); // Assuming userId=1 for now
        List<IncomeCategory> incomeCategories = expenseIncomeService.getIncomeCategories(1, year);

        // Fetch expenses and incomes
        List<Expense> expenses = expenseIncomeService.getExpenses(1, year);
        List<Income> incomes = expenseIncomeService.getIncomes(1, year);

        // Map categoryId to category name for Thymeleaf
        Map<Integer, String> expenseCategoryNames = expenseCategories.stream()
            .collect(Collectors.toMap(ExpenseCategory::getId, ExpenseCategory::getName));
        Map<Integer, String> incomeCategoryNames = incomeCategories.stream()
            .collect(Collectors.toMap(IncomeCategory::getId, IncomeCategory::getName));

        double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double netDiff = totalIncome - totalExpense;

        model.addAttribute("year", year);
        model.addAttribute("expenseCategories", expenseCategories);
        model.addAttribute("incomeCategories", incomeCategories);
        model.addAttribute("Expenses", expenses);
        model.addAttribute("Incomes", incomes);
        model.addAttribute("expenseCategoryNames", expenseCategoryNames);
        model.addAttribute("incomeCategoryNames", incomeCategoryNames);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("netDiff", netDiff);

        return "expense-income";
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
        return "redirect:/tracker?year=" + year;
    }

    @PostMapping("/expense-category/{id}")
    public String deleteExpenseCategory(@PathVariable int id, @RequestParam int year) {
        expenseIncomeService.deleteExpenseCategory(id);
        return "redirect:/tracker?year=" + year;
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
        return "redirect:/tracker?year=" + year;
    }

    @PostMapping("/income-category/{id}")
    public String deleteIncomeCategory(@PathVariable int id, @RequestParam int year) {
        expenseIncomeService.deleteIncomeCategory(id);
        return "redirect:/tracker?year=" + year;
    }

    @PostMapping("/expense")
    public String saveExpense(
        @RequestParam int year,
        @RequestParam int categoryId,
        @RequestParam double amount
    ) {
        Expense expense = new Expense();
        expense.setUserId(1); // hardcoded
        expense.setYear(year);
        expense.setCategoryId(categoryId);
        expense.setAmount(amount);
        expenseIncomeService.saveExpense(expense);
        return "redirect:/tracker?year=" + year;
    }

    @PostMapping("/expense/{id}")
    public String deleteExpense(@PathVariable int id, @RequestParam int year) {
        expenseIncomeService.deleteExpense(id);
        return "redirect:/tracker?year=" + year;
    }

    @PostMapping("/income")
    public String saveIncome(
        @RequestParam int year,
        @RequestParam int categoryId,
        @RequestParam double amount
    ) {
        Income income = new Income();
        income.setUserId(1); // hardcoded
        income.setYear(year);
        income.setCategoryId(categoryId);
        income.setAmount(amount);
        expenseIncomeService.saveIncome(income);
        return "redirect:/tracker?year=" + year;
    }

    @PostMapping("/income/{id}")
    public String deleteIncome(@PathVariable int id, @RequestParam int year) {
        expenseIncomeService.deleteIncome(id);
        return "redirect:/tracker?year=" + year;
    }
} 