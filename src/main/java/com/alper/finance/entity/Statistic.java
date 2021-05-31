package com.alper.finance.entity;

public class Statistic {

    private long totalAsset;

    private long monthlySavingAverage;

    private long readyMoney; //Retirement money excluded 

    public long getTotalAsset() {
        return totalAsset;
    }

    public long getMonthlySavingAverage() {
        return monthlySavingAverage;
    }

    public void setMonthlySavingAverage(long monthlySavingAverage) {
        this.monthlySavingAverage = monthlySavingAverage;
    }

    public long getReadyMoney() {
        return readyMoney;
    }

    public void setReadyMoney(long readyMoney) {
        this.readyMoney = readyMoney;
    }

    public void setTotalAsset(long totalAsset) {
        this.totalAsset = totalAsset;
    }
}
