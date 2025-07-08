/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trading;

/**
 *
 * @author ritik
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Market market = new Market();
        market.addStock(new Stock("TCS", "Tata Consultancy Services", 3500));
        market.addStock(new Stock("INFY", "Infosys", 1400));
        market.addStock(new Stock("RELI", "Reliance", 2700));

        User user = new User("ritika", 10000);
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Show Portfolio");
            System.out.println("5. Update Prices");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;
                case 2:
                    System.out.print("Enter symbol to buy: ");
                    String buySym = sc.next();
                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();
                    Stock buyStock = market.getStock(buySym);
                    if (buyStock != null) {
                        user.buyStock(buySym, buyQty, buyStock.getPrice());
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter symbol to sell: ");
                    String sellSym = sc.next();
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();
                    Stock sellStock = market.getStock(sellSym);
                    if (sellStock != null) {
                        user.sellStock(sellSym, sellQty, sellStock.getPrice());
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 4:
                    user.showPortfolio();
                    break;
                case 5:
                    market.updatePrices();
                    System.out.println("Prices updated.");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}