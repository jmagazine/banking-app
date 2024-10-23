package com.example.banking.account.investment;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STOCK")
public class Stock extends Investment {

    private String symbol;

    private String series;

    private int open;

    private int high;
    private int low;
    private int close;
    private int last;

    private int prevClose;

    private int totalTradedQuantity; // total traded quantity
    private int totalTradedValue; // total traded value


    public Stock() {
    }

    public Stock(Long id, String symbol, String series, int open, int high, int low, int close, int last, int prevClose, int totalTradedQuantity, int totalTradedValue) {
        super(id);
        this.symbol = symbol;
        this.series = series;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.last = last;
        this.prevClose = prevClose;
        this.totalTradedQuantity = totalTradedQuantity;
        this.totalTradedValue = totalTradedValue;
    }

    public Stock(String symbol, String series, int open, int high, int low, int close, int last, int prevClose, int totalTradedQuantity, int totalTradedValue) {
        super();
        this.symbol = symbol;
        this.series = series;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.last = last;
        this.prevClose = prevClose;
        this.totalTradedQuantity = totalTradedQuantity;
        this.totalTradedValue = totalTradedValue;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getClose() {
        return close;
    }

    public void setClose(int close) {
        this.close = close;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(int prevClose) {
        this.prevClose = prevClose;
    }

    public int getTotalTradedQuantity() {
        return totalTradedQuantity;
    }

    public void setTotalTradedQuantity(int totalTradedQuantity) {
        this.totalTradedQuantity = totalTradedQuantity;
    }

    public int getTotalTradedValue() {
        return totalTradedValue;
    }

    public void setTotalTradedValue(int totalTradedValue) {
        this.totalTradedValue = totalTradedValue;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", series='" + series + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", last=" + last +
                ", prevClose=" + prevClose +
                ", totalTradedQuantity=" + totalTradedQuantity +
                ", totalTradedValue=" + totalTradedValue +
                '}';
    }
}
