/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trading;

/**
 *
 * @author ritik
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp extends JFrame {
    private Market market;
    private User user;

    private JTextArea marketArea;
    private JTextField symbolField, quantityField;
    private JButton buyButton, sellButton, updatePricesButton, showPortfolioButton;

    public MainApp() {
        market = new Market();
        market.addStock(new Stock("TCS", "Tata Consultancy Services", 3500));
        market.addStock(new Stock("INFY", "Infosys", 1400));
        market.addStock(new Stock("RELI", "Reliance", 2700));

        user = new User("ritika", 10000);

        setTitle("Stock Trading Platform");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        marketArea = new JTextArea();
        marketArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(marketArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Stock Symbol:"));
        symbolField = new JTextField();
        inputPanel.add(symbolField);
        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buyButton = new JButton("Buy");
        sellButton = new JButton("Sell");
        updatePricesButton = new JButton("Update Prices");
        showPortfolioButton = new JButton("Show Portfolio");

        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(updatePricesButton);
        buttonPanel.add(showPortfolioButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addListeners();
        refreshMarket();
        setVisible(true);
    }

    private void addListeners() {
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String symbol = symbolField.getText().toUpperCase();
                int qty = Integer.parseInt(quantityField.getText());
                Stock stock = market.getStock(symbol);
                if (stock != null) {
                    user.buyStock(symbol, qty, stock.getPrice());
                    refreshMarket();
                } else {
                    showMessage("Stock not found.");
                }
            }
        });

        sellButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String symbol = symbolField.getText().toUpperCase();
                int qty = Integer.parseInt(quantityField.getText());
                Stock stock = market.getStock(symbol);
                if (stock != null) {
                    user.sellStock(symbol, qty, stock.getPrice());
                    refreshMarket();
                } else {
                    showMessage("Stock not found.");
                }
            }
        });

        updatePricesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                market.updatePrices();
                refreshMarket();
            }
        });

        showPortfolioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                user.showPortfolio();
            }
        });
    }

    private void refreshMarket() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(user.getUsername()).append(" | Balance: ₹").append(user.getBalance()).append("\n\n");
        for (Stock stock : market.getAllStocks()) {
            sb.append(stock.getName()).append(" (").append(stock.getSymbol()).append("): ₹").append(String.format("%.2f", stock.getPrice())).append("\n");
        }
        marketArea.setText(sb.toString());
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public static void main(String[] args) {
        new MainApp();
    }
}