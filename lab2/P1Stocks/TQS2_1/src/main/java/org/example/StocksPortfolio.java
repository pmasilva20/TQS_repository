package org.example;

import java.util.ArrayList;

public class StocksPortfolio {
    private String name;
    private IStockMarket market;
    private ArrayList<Stock> stocksGotten;

    public StocksPortfolio(IStockMarket market) {
        this.market = market;
        this.stocksGotten = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IStockMarket getMarket() {
        return market;
    }

    public void setMarket(IStockMarket market) {
        this.market = market;
    }

    public double getTotalValue(){
        double totalVal = 0;
        for(Stock stock : this.stocksGotten){
            totalVal+= market.getPrice(stock.getName()) * stock.getQuantity();
        }
        return totalVal;
    };
    public void addStock(Stock stock){
        this.stocksGotten.add(stock);
    };
}
