package com.alper.finance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "asset")
public class Asset {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "month")
    private int month;

    @Column(name = "boa")
    private int boa;

    @Column(name = "chase")
    private int chase;

    @Column(name = "hsbc_tr")
    private int hsbcTr;

    @Column(name = "stock")
    private int stock;

    @Column(name = "ret_401k")
    private int ret401k;

    @Column(name = "ret_tur")
    private int retTur;

    @Column(name = "difference")
    private int difference;

    @Column(name = "total")
    private int total;

    // define constructors
    public Asset() {
    }

    public Asset(int id, int month, int boa, int chase, int hsbcTr, int stock, int ret401k, int retTur, int total) {
        this.id = id;
        this.month = month;
        this.boa = boa;
        this.chase = chase;
        this.hsbcTr = hsbcTr;
        this.stock = stock;
        this.ret401k = ret401k;
        this.retTur = retTur;
        this.total = total;
    }


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }


    // define getter/setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getBoa() {
        return boa;
    }


    public void setBoa(int boa) {
        this.boa = boa;
    }


    public int getChase() {
        return chase;
    }


    public void setChase(int chase) {
        this.chase = chase;
    }


    public int getHsbcTr() {
        return hsbcTr;
    }


    public void setHsbcTr(int hsbcTr) {
        this.hsbcTr = hsbcTr;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getRet401k() {
        return ret401k;
    }

    public void setRet401k(int ret401k) {
        this.ret401k = ret401k;
    }

    public int getRetTur() {
        return retTur;
    }

    public void setRetTur(int retTur) {
        this.retTur = retTur;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", month=" + month +
                ", boa=" + boa +
                ", chase=" + chase +
                ", hsbcTr=" + hsbcTr +
                ", stock=" + stock +
                ", ret401k=" + ret401k +
                ", retTur=" + retTur +
                ", difference=" + difference +
                ", total=" + total +
                '}';
    }
}











