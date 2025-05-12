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
    private ExpenseRepository expenseRepository;
    @Autowired
    private IncomeRepository incomeRepository;

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
    public List<Expense> getExpenses(int userId, int year) {
        return expenseRepository.findByUserIdAndYear(userId, year);
    }
    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    @Override
    public void deleteExpense(int expenseId) {
        expenseRepository.deleteById(expenseId);
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
    public List<Income> getIncomes(int userId, int year) {
        return incomeRepository.findByUserIdAndYear(userId, year);
    }
    @Override
    public Income saveIncome(Income income) {
        return incomeRepository.save(income);
    }
    @Override
    public void deleteIncome(int incomeId) {
        incomeRepository.deleteById(incomeId);
    }
} 