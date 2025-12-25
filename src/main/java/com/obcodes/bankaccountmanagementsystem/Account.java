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
        if(initialDeposit > 0) {
            this.balance = initialDeposit;
        } else {
            this.balance = 0.0;
        }
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
            System.out.println("✓ Deposit successful: R" + String.format("%.2f", amount));
            return true;
        }
        System.out.println("✗ Deposit failed: Invalid amount");
        return false;
    }
    
    // Withdraw method
    public boolean withdraw(double amount) {
        if(amount <= 0) {
            System.out.println("✗ Withdrawal failed: Amount must be positive");
            return false;
        }
        
        if(amount > balance) {
            System.out.println("✗ Withdrawal failed: Insufficient funds");
            System.out.println("  Current balance: R" + String.format("%.2f", balance));
            System.out.println("  Attempted withdrawal: R" + String.format("%.2f", amount));
            return false;
        }
        
        balance -= amount;
        System.out.println("✓ Withdrawal successful: R" + String.format("%.2f", amount));
        System.out.println("  Remaining balance: R" + String.format("%.2f", balance));
        return true;
    }
    
    // Check balance method
    public void checkBalance() {
        System.out.println("\n=== Account Balance ===");
        System.out.println("Account: " + accountNumber);
        System.out.println("Holder: " + holderName);
        System.out.println("Balance: R" + String.format("%.2f", balance));
        System.out.println("=======================\n");
    }
    
    // COMPLETE TRANSFER METHOD - DAY 3 IMPLEMENTATION
    public boolean transfer(Account recipient, double amount) {
        if(recipient == null) {
            System.out.println("✗ Transfer failed: Recipient account does not exist");
            return false;
        }
        
        if(this.accountNumber == recipient.accountNumber) {
            System.out.println("✗ Transfer failed: Cannot transfer to same account");
            return false;
        }
        
        if(amount <= 0) {
            System.out.println("✗ Transfer failed: Amount must be positive");
            return false;
        }
        
        System.out.println("\n=== TRANSFER INITIATED ===");
        System.out.println("From: " + this.holderName + " (Acc: " + this.accountNumber + ")");
        System.out.println("To: " + recipient.holderName + " (Acc: " + recipient.accountNumber + ")");
        System.out.println("Amount: R" + String.format("%.2f", amount));
        System.out.println("=".repeat(30));
        
        // First withdraw from this account
        if(this.withdraw(amount)) {
            // Then deposit to recipient account
            if(recipient.deposit(amount)) {
                System.out.println("\n✓ TRANSFER COMPLETED SUCCESSFULLY");
                System.out.println("=".repeat(35));
                System.out.println("Sender (" + this.holderName + "):");
                System.out.println("  New Balance: R" + String.format("%.2f", this.balance));
                System.out.println("-".repeat(35));
                System.out.println("Recipient (" + recipient.holderName + "):");
                System.out.println("  New Balance: R" + String.format("%.2f", recipient.balance));
                System.out.println("=".repeat(35));
                return true;
            } else {
                // If deposit fails, refund the withdrawal
                this.deposit(amount);
                System.out.println("✗ Transfer failed: Could not complete recipient deposit");
                return false;
            }
        }
        
        System.out.println("✗ Transfer failed: Insufficient funds or other error");
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
    
    // NEW: Display account summary (for lists)
    public void displayAccountSummary() {
        System.out.println(String.format("%-15s %-20s R%-12.2f", 
            accountNumber, holderName, balance));
    }
}