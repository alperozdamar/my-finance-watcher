package com.alper.finance.service;

import com.alper.finance.entity.ExpenseCategory;
import com.alper.finance.entity.IncomeCategory;
import com.alper.finance.entity.YearlyExpense;
import com.alper.finance.entity.YearlyIncome;

import java.util.List;

public interface ExpenseIncomeService {
    // Expense
    List<ExpenseCategory> getExpenseCategories(int userId, int year);
    ExpenseCategory addExpenseCategory(ExpenseCategory category);
    void deleteExpenseCategory(int categoryId);

    List<YearlyExpense> getYearlyExpenses(int userId, int year);
    YearlyExpense saveYearlyExpense(YearlyExpense expense);
    void deleteYearlyExpense(int expenseId);

    // Income
    List<IncomeCategory> getIncomeCategories(int userId, int year);
    IncomeCategory addIncomeCategory(IncomeCategory category);
    void deleteIncomeCategory(int categoryId);

    List<YearlyIncome> getYearlyIncomes(int userId, int year);
    YearlyIncome saveYearlyIncome(YearlyIncome income);
    void deleteYearlyIncome(int incomeId);
} 