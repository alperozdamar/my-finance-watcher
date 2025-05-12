package com.alper.finance.entity;

import javax.persistence.*;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "year")
    private int year;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "amount")
    private double amount;

    public Expense() {}

    public Expense(int userId, int year, int categoryId, double amount) {
        this.userId = userId;
        this.year = year;
        this.categoryId = categoryId;
        this.amount = amount;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
} 