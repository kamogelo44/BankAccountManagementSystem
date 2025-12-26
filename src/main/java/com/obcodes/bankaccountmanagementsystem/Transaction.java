package com.obcodes.bankaccountmanagementsystem;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a banking transaction with details
 * @author Obakeng Phale
 */
public class Transaction {
    private String transactionId;
    private long accountNumber;
    private String transactionType; // DEPOSIT, WITHDRAWAL, TRANSFER
    private double amount;
    private double balanceAfter;
    private Date timestamp;
    private String description;
    
    // Date formatter for display
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    // Constructor
    public Transaction(long accountNumber, String transactionType, 
                      double amount, double balanceAfter, String description) {
        this.transactionId = generateTransactionId();
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = new Date();
        this.description = description;
    }
    
    // Generate unique transaction ID
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis() + (int)(Math.random() * 1000);
    }
    
    // Getters
    public String getTransactionId() {
        return transactionId;
    }
    
    public long getAccountNumber() {
        return accountNumber;
    }
    
    public String getTransactionType() {
        return transactionType;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public double getBalanceAfter() {
        return balanceAfter;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    
    public String getDescription() {
        return description;
    }
    
    // Display transaction details
    public void displayTransaction() {
        System.out.println(String.format("%-15s %-12s %-15s R%-12.2f R%-12.2f %-20s %-30s",
            transactionId,
            dateFormat.format(timestamp),
            transactionType,
            amount,
            balanceAfter,
            accountNumber,
            description));
    }
    
    // Get formatted timestamp
    public String getFormattedTimestamp() {
        return dateFormat.format(timestamp);
    }
}