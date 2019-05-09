package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

public class DataModel {
    private String scrip;
    private int qty;
    private double ltp;
    private double value;
    private double avgPrice;
    private String stocks;

    public String getScrip() {
        return scrip;
    }

    public void setScrip(String product) {
        this.scrip = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getLtp() {
        return ltp;
    }

    public void setLtp(double ltp) {
        this.ltp = ltp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getStocks() {
        return stocks;
    }

    public void setStocks(String stocks) {
        this.stocks = stocks;
    }
}
