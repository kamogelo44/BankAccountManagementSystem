package com.obcodes.bankaccountmanagementsystem;

import java.util.Random;

/**
 * Represents a bank account with basic operations
 * @author Obakeng Phale
 */
public class Account {
    private long accountNumber;
    private String holderName;
    private double balance;
    
    // Constructor
    public Account(String holderName, double initialDeposit) {
        this.accountNumber = generateAccountNumber();
        this.holderName = holderName;
        this.balance = initialDeposit;
    }
    
    // Getters and Setters (Encapsulation)
    public long getAccountNumber() {
        return accountNumber;
    }
    
    public String getHolderName() {
        return holderName;
    }
    
    public void setHolderName(String holderName) {
        if(holderName != null && !holderName.trim().isEmpty()) {
            this.holderName = holderName;
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    // Account number generation
    private long generateAccountNumber() {
        Random random = new Random();
        // Generate 12-digit account number
        long min = 100000000000L;
        long max = 999999999999L;
        return min + (long)(random.nextDouble() * (max - min));
    }
    
    // Deposit method
    public boolean deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
    
    // Withdraw method stub (to be implemented fully later)
    public boolean withdraw(double amount) {
        // Basic validation
        if(amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    // Display account info
    public void displayAccountInfo() {
        System.out.println("\n=== Account Information ===");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: R" + String.format("%.2f", balance));
        System.out.println("===========================\n");
    }
}