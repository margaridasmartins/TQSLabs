package ex1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class StockPortfolioTest
{

    @Test
    public void getTotalValue()
    {
        IStockMarket mockedMarket = mock(IStockMarket.class);
        Stockportfolio sp = new Stockportfolio();
        sp.setMarketService(mockedMarket);

        sp.addStock(new Stock( "IBM", 3));
        sp.addStock(new Stock( "NOS", 10));

        when(mockedMarket.getPrice("IBM")).thenReturn(15.0);
        when(mockedMarket.getPrice("NOS")).thenReturn(5.5);
        
        double totalValue = sp.getTotalValue();

        assertEquals( totalValue,100 );
        assertThat(totalValue, is(100.0));

        verify(mockedMarket).getPrice("IBM");
        verify(mockedMarket).getPrice("NOS");
    }

   

}