package com.obcodes.bankaccountmanagementsystem;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a banking transaction with details
 * @author Obakeng Phale
 */
public class Transaction {
    private final String transactionId;
    private final long accountNumber;
    private final String transactionType;
    private final double amount;
    private final double balanceAfter;
    private final Date timestamp;
    private final String description;
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public Transaction(long accountNumber, String transactionType, 
                      double amount, double balanceAfter, String description) {
        this.transactionId = generateTransactionId();
        this.accountNumber = accountNumber;
        this.transactionType = validateTransactionType(transactionType);
        this.amount = validateAmount(amount);
        this.balanceAfter = balanceAfter;
        this.timestamp = new Date();
        this.description = (description != null) ? description.trim() : "";
    }
    
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis() + (int)(Math.random() * 1000);
    }
    
    private String validateTransactionType(String type) {
        if (type == null || type.trim().isEmpty()) {
            return "UNKNOWN";
        }
        String upperType = type.toUpperCase();
        if (upperType.equals("DEPOSIT") || upperType.equals("WITHDRAWAL") || 
            upperType.equals("TRANSFER_IN") || upperType.equals("TRANSFER_OUT")) {
            return upperType;
        }
        return "UNKNOWN";
    }
    
    private double validateAmount(double amount) {
        if (amount < 0) {
            return 0.0;
        }
        if (amount > 10000000) {
            return 10000000.0;
        }
        return Math.round(amount * 100.0) / 100.0;
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
        return new Date(timestamp.getTime());
    }
    
    public String getDescription() {
        return description;
    }
    
    public void displayTransaction() {
        System.out.printf("%-15s %-12s %-15s R%-12.2f R%-12.2f %-20s %-30s%n",
            transactionId,
            DATE_FORMAT.format(timestamp),
            transactionType,
            amount,
            balanceAfter,
            accountNumber,
            description);
    }
    
    public String getFormattedTimestamp() {
        return DATE_FORMAT.format(timestamp);
    }
    
    @Override
    public String toString() {
        return String.format("Transaction[ID: %s, Type: %s, Amount: R%.2f, Date: %s]",
            transactionId, transactionType, amount, getFormattedTimestamp());
    }
}