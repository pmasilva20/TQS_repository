package org.example;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StocksPortfolioTest {


    @org.junit.jupiter.api.Test
    void getTotalValue() {
        IStockMarket market = mock(IStockMarket.class);

        StocksPortfolio portfolio = new StocksPortfolio(market);

        when(market.getPrice("EBAY")).thenReturn( 4.0);
        when(market.getPrice("MSFT")).thenReturn( 1.5);

        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("MSFT", 4));
        double result = portfolio.getTotalValue();

        //With Hamcrest
        assertThat(result, is(14.0));
        //assertEquals(14.0, result);
    }
}