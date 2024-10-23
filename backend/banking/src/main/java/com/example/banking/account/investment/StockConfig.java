package com.example.banking.account.investment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Configuration
public class StockConfig {

    private Stock getStockFromLine(String line){
        // SYMBOL,SERIES,OPEN,HIGH,LOW,CLOSE,LAST,PREVCLOSE,TOTTRDQTY,TOTTRDVAL
        String[] fields = line.split(",");
        String symbol = fields[0];
        String series = fields[1];
        int open = (int)((Float.parseFloat(fields[2]))*100);
        int high = (int)((Float.parseFloat(fields[3]))*100);
        int low = (int)((Float.parseFloat(fields[4]))*100);
        int close = (int)((Float.parseFloat(fields[5]))*100);
        int last = (int)((Float.parseFloat(fields[6]))*100);
        int prevClose = (int)((Float.parseFloat(fields[7]))*100);
        int totalTradedQuantity = (int) Float.parseFloat(fields[8]);
        int totalTradedValue = (int) Float.parseFloat(fields[9]);

        return new Stock(symbol, series, open, high, low, close, last, prevClose, totalTradedQuantity, totalTradedValue);





    }
    @Bean
    CommandLineRunner stockCommandLineRunner(InvestmentRepository investmentRepository){
        return args ->{
            List<Stock> stocks = new ArrayList<>();
            try (Scanner scanner = new Scanner(new File("src/main/resources/data/stock_data.csv"))) {
                System.out.println(scanner.nextLine());
                int i = 0;
                while (scanner.hasNextLine() && i < 100) {
                    stocks.add(getStockFromLine(scanner.nextLine()));
                    i+=1;
                }
            }
            investmentRepository.saveAll(stocks);

        };
    }
}
