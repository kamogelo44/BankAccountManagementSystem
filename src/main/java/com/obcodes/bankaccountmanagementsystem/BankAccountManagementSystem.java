package com.obcodes.bankaccountmanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class to run the banking system
 * @author Obakeng Phale
 */
public class BankAccountManagementSystem {
    
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        displayWelcomeBanner();
        
        // Initialize with some test accounts
        initializeTestAccounts();
        
        boolean running = true;
        
        while(running) {
            Menu.displayMainMenu();
            int choice = getIntInput("Enter your choice (1-7): ");
            
            switch(choice) {
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    viewAllAccounts();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    checkAccountBalance();
                    break;
                case 6:
                    transferMoney();  // NEW: Day 3 Feature
                    break;
                case 7:
                    System.out.println("\n" + "=".repeat(45));
                    System.out.println("  Thank you for using our banking system!");
                    System.out.println("               Goodbye! 👋");
                    System.out.println("=".repeat(45));
                    running = false;
                    break;
                default:
                    System.out.println("\n✗ Invalid choice. Please select 1-7.");
            }
        }
        
        scanner.close();
    }
    
    // Display welcome banner
    private static void displayWelcomeBanner() {
        System.out.println("\n" + "⭐".repeat(50));
        System.out.println("       BANK ACCOUNT MANAGEMENT SYSTEM");
        System.out.println("⭐".repeat(50));
        System.out.println("\nA Java OOP Project by Obakeng Phale");
        System.out.println("Day 3: Transfer Functionality Implemented");
    }
    
    // Initialize with some test accounts
    private static void initializeTestAccounts() {
        accounts.add(new Account("John Doe", 5000.00));
        accounts.add(new Account("Jane Smith", 10000.00));
        accounts.add(new Account("Bob Johnson", 7500.00));
        accounts.add(new Account("Alice Brown", 12000.00));
        System.out.println("\n✓ 4 test accounts initialized");
    }
    
    // Method to create a new account
    private static void createNewAccount() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("      CREATE NEW ACCOUNT");
        System.out.println("=".repeat(40));
        
        System.out.print("Enter account holder name: ");
        scanner.nextLine(); // Clear buffer
        String name = scanner.nextLine();
        
        if(name.trim().isEmpty()) {
            System.out.println("✗ Account holder name cannot be empty");
            return;
        }
        
        double initialDeposit = getDoubleInput("Enter initial deposit amount: R");
        
        if(initialDeposit < 0) {
            System.out.println("✗ Initial deposit cannot be negative");
            return;
        }
        
        Account newAccount = new Account(name, initialDeposit);
        accounts.add(newAccount);
        
        System.out.println("\n" + "✓".repeat(40));
        System.out.println("      ACCOUNT CREATED SUCCESSFULLY!");
        System.out.println("✓".repeat(40));
        System.out.println("Account Number: " + newAccount.getAccountNumber());
        System.out.println("Holder Name: " + newAccount.getHolderName());
        System.out.println("Initial Balance: R" + String.format("%.2f", newAccount.getBalance()));
    }
    
    // Method to view all accounts
    private static void viewAllAccounts() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                      ALL ACCOUNTS");
        System.out.println("=".repeat(60));
        
        if(accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        
        System.out.println("Total Accounts: " + accounts.size());
        System.out.println("-".repeat(60));
        System.out.println(String.format("%-15s %-20s %-12s", 
            "Account No.", "Holder Name", "Balance"));
        System.out.println("-".repeat(60));
        
        for(Account acc : accounts) {
            acc.displayAccountSummary();
        }
        
        System.out.println("-".repeat(60));
        
        // Calculate and display total bank balance
        double totalBalance = 0;
        for(Account acc : accounts) {
            totalBalance += acc.getBalance();
        }
        System.out.println("Total Bank Balance: R" + String.format("%.2f", totalBalance));
    }
    
    // Method to deposit money
    private static void depositMoney() {
        System.out.println("\n" + "=".repeat(35));
        System.out.println("         DEPOSIT MONEY");
        System.out.println("=".repeat(35));
        
        Account account = findAccountByNumber();
        if(account == null) {
            System.out.println("✗ Account not found");
            return;
        }
        
        System.out.println("Account Holder: " + account.getHolderName());
        System.out.println("Current Balance: R" + String.format("%.2f", account.getBalance()));
        
        double amount = getDoubleInput("\nEnter deposit amount: R");
        account.deposit(amount);
    }
    
    // Method to withdraw money
    private static void withdrawMoney() {
        System.out.println("\n" + "=".repeat(35));
        System.out.println("        WITHDRAW MONEY");
        System.out.println("=".repeat(35));
        
        Account account = findAccountByNumber();
        if(account == null) {
            System.out.println("✗ Account not found");
            return;
        }
        
        System.out.println("Account Holder: " + account.getHolderName());
        System.out.println("Current Balance: R" + String.format("%.2f", account.getBalance()));
        
        double amount = getDoubleInput("\nEnter withdrawal amount: R");
        account.withdraw(amount);
    }
    
    // Method to check account balance
    private static void checkAccountBalance() {
        System.out.println("\n" + "=".repeat(35));
        System.out.println("     CHECK ACCOUNT BALANCE");
        System.out.println("=".repeat(35));
        
        Account account = findAccountByNumber();
        if(account == null) {
            System.out.println("✗ Account not found");
            return;
        }
        
        account.checkBalance();
    }
    
    // NEW: Method to transfer money between accounts - DAY 3 FEATURE
    private static void transferMoney() {
        System.out.println("\n" + "=".repeat(45));
        System.out.println("         TRANSFER MONEY");
        System.out.println("=".repeat(45));
        
        System.out.println("\n--- SENDER ACCOUNT ---");
        Account sender = findAccountByNumber();
        if(sender == null) {
            System.out.println("✗ Sender account not found");
            return;
        }
        
        System.out.println("\n--- RECIPIENT ACCOUNT ---");
        Account recipient = findAccountByNumber();
        if(recipient == null) {
            System.out.println("✗ Recipient account not found");
            return;
        }
        
        // Check if sender and recipient are the same
        if(sender.getAccountNumber() == recipient.getAccountNumber()) {
            System.out.println("✗ Cannot transfer to the same account");
            return;
        }
        
        System.out.println("\n" + "-".repeat(45));
        System.out.println("TRANSFER DETAILS:");
        System.out.println("-".repeat(45));
        System.out.println("Sender: " + sender.getHolderName() + 
                          " (Acc: " + sender.getAccountNumber() + ")");
        System.out.println("  Current Balance: R" + String.format("%.2f", sender.getBalance()));
        System.out.println("-".repeat(45));
        System.out.println("Recipient: " + recipient.getHolderName() + 
                          " (Acc: " + recipient.getAccountNumber() + ")");
        System.out.println("  Current Balance: R" + String.format("%.2f", recipient.getBalance()));
        System.out.println("-".repeat(45));
        
        double amount = getDoubleInput("\nEnter transfer amount: R");
        
        // Confirm transfer
        System.out.print("\nConfirm transfer? (yes/no): ");
        scanner.nextLine(); // Clear buffer
        String confirmation = scanner.nextLine().toLowerCase();
        
        if(confirmation.equals("yes") || confirmation.equals("y")) {
            sender.transfer(recipient, amount);
        } else {
            System.out.println("✗ Transfer cancelled by user");
        }
    }
    
    // Helper method to find account by number
    private static Account findAccountByNumber() {
        System.out.print("Enter account number: ");
        
        while(!scanner.hasNextLong()) {
            System.out.println("✗ Please enter a valid account number (12 digits)");
            System.out.print("Enter account number: ");
            scanner.next();
        }
        
        long accountNumber = scanner.nextLong();
        
        for(Account acc : accounts) {
            if(acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        
        System.out.println("✗ Account with number " + accountNumber + " not found");
        return null;
    }
    
    // Helper method to get integer input
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while(!scanner.hasNextInt()) {
            System.out.println("✗ Please enter a valid number");
            System.out.print(prompt);
            scanner.next();
        }
        int value = scanner.nextInt();
        return value;
    }
    
    // Helper method to get double input
    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while(!scanner.hasNextDouble()) {
            System.out.println("✗ Please enter a valid amount");
            System.out.print(prompt);
            scanner.next();
        }
        double value = scanner.nextDouble();
        return value;
    }
}