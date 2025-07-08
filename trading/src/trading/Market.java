/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trading;

/**
 *
 * @author ritik
 */
import java.util.*;

public class Market {
    private Map<String, Stock> stocks = new HashMap<>();

    public void addStock(Stock stock) {
        stocks.put(stock.getSymbol(), stock);
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public void updatePrices() {
        for (Stock stock : stocks.values()) {
            stock.updatePrice();
        }
    }

    public Collection<Stock> getAllStocks() {
        return stocks.values();
    }

    public void displayMarket() {
        System.out.println("\n--- Market Data ---");
        for (Stock stock : getAllStocks()) {
            System.out.printf("%s (%s): ₹%.2f\n", stock.getName(), stock.getSymbol(), stock.getPrice());
        }
    }
}
