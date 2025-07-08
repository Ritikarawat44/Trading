/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trading;

/**
 *
 * @author ritik
 */
import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private double balance;
    private Map<String, Integer> portfolio = new HashMap<>();

    public User(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    public void buyStock(String symbol, int quantity, double price) {
        double cost = price * quantity;
        if (balance >= cost) {
            portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
            balance -= cost;
            System.out.println("Bought " + quantity + " of " + symbol);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void sellStock(String symbol, int quantity, double price) {
        if (portfolio.containsKey(symbol) && portfolio.get(symbol) >= quantity) {
            portfolio.put(symbol, portfolio.get(symbol) - quantity);
            balance += quantity * price;
            System.out.println("Sold " + quantity + " of " + symbol);
        } else {
            System.out.println("Not enough stocks.");
        }
    }

    public void showPortfolio() {
        System.out.println("\n--- Portfolio ---");
        for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Balance: ₹" + balance);
    }

    public double getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }
}