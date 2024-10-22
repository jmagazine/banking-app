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
}
