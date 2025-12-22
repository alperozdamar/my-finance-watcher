package com.alper.finance.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "net_worth_entry")
public class NetWorthEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private NetWorthCategory category;

    @Column(name = "amount")
    private double amount;

    @Column(name = "entry_date")
    @Temporal(TemporalType.DATE)
    private Date entryDate;

    @Column(name = "note")
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public NetWorthCategory getCategory() {
        return category;
    }

    public void setCategory(NetWorthCategory category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}


