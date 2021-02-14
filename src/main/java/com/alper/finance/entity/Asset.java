package com.alper.finance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "asset")
public class Asset {

    // define fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

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

    public Asset(int id, Date date, int boa, int chase, int hsbcTr, int stock, int ret401k, int retTur, int total) {
        this.id = id;
        this.date = date;
        this.boa = boa;
        this.chase = chase;
        this.hsbcTr = hsbcTr;
        this.stock = stock;
        this.ret401k = ret401k;
        this.retTur = retTur;
        this.total = total;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", date=" + date +
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











