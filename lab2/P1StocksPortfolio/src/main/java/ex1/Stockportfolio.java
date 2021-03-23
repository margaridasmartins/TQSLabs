package ex1;

import java.util.ArrayList;

import java.util.List;

public class Stockportfolio {

    private String name;
    private IStockMarket market;
    private List<Stock> stocks = new ArrayList<Stock>();

    public IStockMarket getMarketService() {
        return market;
    }

    public void setMarketService(IStockMarket market) {
        this.market=market;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public double getTotalValue(){
        double total=0;
        for (Stock st : stocks) {
            total+= market.getPrice(st.getName())*st.getQuantity();
        }
        return total;
    }
    public void addStock(Stock s) {
        this.stocks.add(s);
    }

}