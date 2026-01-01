package com.obcodes.bankaccountmanagementsystem;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a bank account with complete operations
 * @author Obakeng Phale
 */
public class Account {
    // Constants for validation limits
    private static final double MAX_DEPOSIT = 1000000.00;
    private static final double MAX_WITHDRAWAL = 50000.00;
    private static final double MAX_TRANSFER = 100000.00;
    private static final double MIN_BALANCE = 0.00;
    
    private final long accountNumber;
    private String holderName;
    private double balance;
    private final ArrayList<Transaction> transactionHistory;
    
    /**
     * Constructor for creating a new account
     */
    public Account(String holderName, double initialDeposit) {
        validateHolderName(holderName);
        
        this.accountNumber = generateAccountNumber();
        this.holderName = holderName.trim();
        this.transactionHistory = new ArrayList<>();
        
        if (initialDeposit > 0) {
            this.balance = validateAmount(initialDeposit);
            addTransaction("DEPOSIT", initialDeposit, "Initial deposit");
        } else {
            this.balance = MIN_BALANCE;
        }
    }
    
    /**
     * Validates account holder name
     */
    private void validateHolderName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder name cannot be empty");
        }
        if (name.trim().length() < 2) {
            throw new IllegalArgumentException("Account holder name must be at least 2 characters");
        }
    }
    
    /**
     * Validates monetary amounts
     */
    private double validateAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        return Math.round(amount * 100.0) / 100.0;
    }
    
    /**
     * Generates a random 12-digit account number
     */
    private long generateAccountNumber() {
        Random random = new Random();
        long min = 100000000000L;
        long max = 999999999999L;
        return min + (long)(random.nextDouble() * (max - min + 1));
    }
    
    /**
     * Helper method to add transaction to history
     */
    private void addTransaction(String type, double amount, String description) {
        Transaction transaction = new Transaction(
            accountNumber, 
            type, 
            amount, 
            balance, 
            description
        );
        transactionHistory.add(transaction);
    }
    
    // Getters and Setters
    public long getAccountNumber() {
        return accountNumber;
    }
    
    public String getHolderName() {
        return holderName;
    }
    
    public void setHolderName(String holderName) {
        validateHolderName(holderName);
        String oldName = this.holderName;
        this.holderName = holderName.trim();
        addTransaction("ACCOUNT_UPDATE", 0.0, 
            String.format("Name changed from '%s' to '%s'", oldName, this.holderName));
    }
    
    public double getBalance() {
        return balance;
    }
    
    public ArrayList<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
    
    /**
     * Deposits money into account
     */
    public boolean deposit(double amount) {
        try {
            double validatedAmount = validateAmount(amount);
            
            if (validatedAmount <= 0) {
                System.out.println("[ERROR] Deposit failed: Amount must be greater than 0");
                return false;
            }
            
            if (validatedAmount > MAX_DEPOSIT) {
                System.out.printf("[ERROR] Deposit failed: Amount exceeds maximum deposit limit of R%,.2f%n", MAX_DEPOSIT);
                return false;
            }
            
            balance += validatedAmount;
            System.out.printf("[SUCCESS] Deposit successful: R%,.2f%n", validatedAmount);
            System.out.printf("[INFO] New balance: R%,.2f%n", balance);
            addTransaction("DEPOSIT", validatedAmount, "Cash deposit");
            return true;
            
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Deposit failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Withdraws money from account
     */
    public boolean withdraw(double amount) {
        try {
            double validatedAmount = validateAmount(amount);
            
            if (validatedAmount <= 0) {
                System.out.println("[ERROR] Withdrawal failed: Amount must be greater than 0");
                return false;
            }
            
            if (validatedAmount > MAX_WITHDRAWAL) {
                System.out.printf("[ERROR] Withdrawal failed: Amount exceeds maximum withdrawal limit of R%,.2f%n", MAX_WITHDRAWAL);
                return false;
            }
            
            if (validatedAmount > balance) {
                System.out.println("[ERROR] Withdrawal failed: Insufficient funds");
                System.out.printf("[INFO] Current balance: R%,.2f%n", balance);
                System.out.printf("[INFO] Attempted withdrawal: R%,.2f%n", validatedAmount);
                System.out.printf("[INFO] Shortfall: R%,.2f%n", (validatedAmount - balance));
                return false;
            }
            
            balance -= validatedAmount;
            System.out.printf("[SUCCESS] Withdrawal successful: R%,.2f%n", validatedAmount);
            System.out.printf("[INFO] Remaining balance: R%,.2f%n", balance);
            addTransaction("WITHDRAWAL", validatedAmount, "Cash withdrawal");
            return true;
            
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Withdrawal failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Transfers money to another account
     */
    public boolean transfer(Account recipient, double amount) {
        if (recipient == null) {
            System.out.println("[ERROR] Transfer failed: Recipient account does not exist");
            return false;
        }
        
        if (this.accountNumber == recipient.accountNumber) {
            System.out.println("[ERROR] Transfer failed: Cannot transfer to same account");
            return false;
        }
        
        try {
            double validatedAmount = validateAmount(amount);
            
            if (validatedAmount <= 0) {
                System.out.println("[ERROR] Transfer failed: Amount must be greater than 0");
                return false;
            }
            
            if (validatedAmount > MAX_TRANSFER) {
                System.out.printf("[ERROR] Transfer failed: Amount exceeds maximum transfer limit of R%,.2f%n", MAX_TRANSFER);
                return false;
            }
            
            System.out.println("\n==========================================");
            System.out.println("        TRANSFER CONFIRMATION");
            System.out.println("==========================================");
            System.out.printf("From: %s (Account: %d)%n", holderName, accountNumber);
            System.out.printf("To: %s (Account: %d)%n", recipient.holderName, recipient.accountNumber);
            System.out.printf("Amount: R%,.2f%n", validatedAmount);
            System.out.printf("Sender Current Balance: R%,.2f%n", balance);
            System.out.printf("Recipient Current Balance: R%,.2f%n", recipient.balance);
            System.out.println("==========================================");
            
            // First withdraw from this account
            if (this.withdraw(validatedAmount)) {
                // Then deposit to recipient account
                if (recipient.deposit(validatedAmount)) {
                    // Add transfer transaction for both accounts
                    String senderDesc = String.format("Transfer to %s (Account: %d)", 
                        recipient.holderName, recipient.accountNumber);
                    String receiverDesc = String.format("Transfer from %s (Account: %d)", 
                        holderName, accountNumber);
                    
                    addTransaction("TRANSFER_OUT", validatedAmount, senderDesc);
                    recipient.addTransaction("TRANSFER_IN", validatedAmount, receiverDesc);
                    
                    System.out.println("\n[SUCCESS] TRANSFER COMPLETED SUCCESSFULLY!");
                    System.out.println("==========================================");
                    System.out.printf("Sender New Balance: R%,.2f%n", balance);
                    System.out.printf("Recipient New Balance: R%,.2f%n", recipient.balance);
                    return true;
                } else {
                    // If deposit fails, refund the withdrawal
                    this.deposit(validatedAmount);
                    System.out.println("[ERROR] Transfer failed: Could not complete recipient deposit");
                    return false;
                }
            }
            
            System.out.println("[ERROR] Transfer failed: Insufficient funds or other error");
            return false;
            
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Transfer failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Displays account balance
     */
    public void checkBalance() {
        System.out.println("\n==========================================");
        System.out.println("          ACCOUNT BALANCE");
        System.out.println("==========================================");
        System.out.printf("Account Number: %d%n", accountNumber);
        System.out.printf("Holder Name: %s%n", holderName);
        System.out.printf("Current Balance: R%,.2f%n", balance);
        System.out.println("==========================================");
    }
    
    /**
     * Displays complete transaction history
     */
    public void displayTransactionHistory() {
        System.out.println("\n==================================================");
        System.out.printf("    TRANSACTION HISTORY - %s%n", holderName.toUpperCase());
        System.out.printf("    Account: %d%n", accountNumber);
        System.out.println("==================================================");
        
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found for this account.");
            System.out.println("==================================================");
            return;
        }
        
        System.out.printf("%-15s %-20s %-15s %-12s %-12s %-20s %-30s%n",
            "Transaction ID", "Date & Time", "Type", "Amount", "Balance", "Account", "Description");
        System.out.println("--------------------------------------------------");
        
        for (Transaction transaction : transactionHistory) {
            transaction.displayTransaction();
        }
        
        System.out.println("--------------------------------------------------");
        System.out.printf("Total Transactions: %d%n", transactionHistory.size());
        System.out.printf("Current Balance: R%,.2f%n", balance);
        System.out.println("==================================================");
    }
    
    /**
     * Displays account information
     */
    public void displayAccountInfo() {
        System.out.println("\n==========================================");
        System.out.println("        ACCOUNT INFORMATION");
        System.out.println("==========================================");
        System.out.printf("Account Number: %d%n", accountNumber);
        System.out.printf("Holder Name: %s%n", holderName);
        System.out.printf("Current Balance: R%,.2f%n", balance);
        System.out.printf("Total Transactions: %d%n", transactionHistory.size());
        
        // Show last transaction if available
        if (!transactionHistory.isEmpty()) {
            Transaction lastTransaction = transactionHistory.get(transactionHistory.size() - 1);
            System.out.printf("Last Transaction: %s - R%,.2f%n", 
                lastTransaction.getTransactionType(), lastTransaction.getAmount());
        }
        
        System.out.println("==========================================");
    }
    
    /**
     * Displays account summary for list views
     */
    public void displayAccountSummary() {
        System.out.printf("%-15d %-25s R%,-15.2f %-10d%n", 
            accountNumber, holderName, balance, transactionHistory.size());
    }
    
    /**
     * Returns string representation of account
     */
    @Override
    public String toString() {
        return String.format("Account[Number: %d, Holder: %s, Balance: R%.2f, Transactions: %d]",
            accountNumber, holderName, balance, transactionHistory.size());
    }
}