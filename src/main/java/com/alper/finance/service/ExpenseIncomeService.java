package com.alper.finance.service;

import com.alper.finance.entity.ExpenseCategory;
import com.alper.finance.entity.IncomeCategory;
import com.alper.finance.entity.Expense;
import com.alper.finance.entity.Income;

import java.util.List;

public interface ExpenseIncomeService {
    // Expense
    List<ExpenseCategory> getExpenseCategories(int userId, int year);
    ExpenseCategory addExpenseCategory(ExpenseCategory category);
    void deleteExpenseCategory(int categoryId);

    List<Expense> getExpenses(int userId, int year);
    Expense saveExpense(Expense expense);
    void deleteExpense(int expenseId);

    // Income
    List<IncomeCategory> getIncomeCategories(int userId, int year);
    IncomeCategory addIncomeCategory(IncomeCategory category);
    void deleteIncomeCategory(int categoryId);

    List<Income> getIncomes(int userId, int year);
    Income saveIncome(Income income);
    void deleteIncome(int incomeId);
} 