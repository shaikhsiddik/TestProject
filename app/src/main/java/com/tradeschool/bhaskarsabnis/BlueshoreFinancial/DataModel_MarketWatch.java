package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

public class DataModel_MarketWatch {
    private String scrip;
    private double ltp;
    private double value;

    public String getScrip() {
        return scrip;
    }

    public void setScrip(String product) {
        this.scrip = product;
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
}
