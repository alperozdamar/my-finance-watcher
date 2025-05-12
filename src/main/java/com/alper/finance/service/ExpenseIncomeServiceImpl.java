package com.alper.finance.service;

import com.alper.finance.dao.*;
import com.alper.finance.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseIncomeServiceImpl implements ExpenseIncomeService {
    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;
    @Autowired
    private IncomeCategoryRepository incomeCategoryRepository;
    @Autowired
    private YearlyExpenseRepository yearlyExpenseRepository;
    @Autowired
    private YearlyIncomeRepository yearlyIncomeRepository;

    // Expense
    @Override
    public List<ExpenseCategory> getExpenseCategories(int userId, int year) {
        return expenseCategoryRepository.findByUserIdAndYear(userId, year);
    }
    @Override
    public ExpenseCategory addExpenseCategory(ExpenseCategory category) {
        return expenseCategoryRepository.save(category);
    }
    @Override
    public void deleteExpenseCategory(int categoryId) {
        expenseCategoryRepository.deleteById(categoryId);
    }
    @Override
    public List<YearlyExpense> getYearlyExpenses(int userId, int year) {
        return yearlyExpenseRepository.findByUserIdAndYear(userId, year);
    }
    @Override
    public YearlyExpense saveYearlyExpense(YearlyExpense expense) {
        return yearlyExpenseRepository.save(expense);
    }
    @Override
    public void deleteYearlyExpense(int expenseId) {
        yearlyExpenseRepository.deleteById(expenseId);
    }

    // Income
    @Override
    public List<IncomeCategory> getIncomeCategories(int userId, int year) {
        return incomeCategoryRepository.findByUserIdAndYear(userId, year);
    }
    @Override
    public IncomeCategory addIncomeCategory(IncomeCategory category) {
        return incomeCategoryRepository.save(category);
    }
    @Override
    public void deleteIncomeCategory(int categoryId) {
        incomeCategoryRepository.deleteById(categoryId);
    }
    @Override
    public List<YearlyIncome> getYearlyIncomes(int userId, int year) {
        return yearlyIncomeRepository.findByUserIdAndYear(userId, year);
    }
    @Override
    public YearlyIncome saveYearlyIncome(YearlyIncome income) {
        return yearlyIncomeRepository.save(income);
    }
    @Override
    public void deleteYearlyIncome(int incomeId) {
        yearlyIncomeRepository.deleteById(incomeId);
    }
} 