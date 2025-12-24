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
        System.out.println("==================================");
        System.out.println("   BANK ACCOUNT MANAGEMENT SYSTEM");
        System.out.println("==================================");
        System.out.println("\nWelcome! Starting the system...");
        
        // Initialize with some test accounts
        initializeTestAccounts();
        
        boolean running = true;
        
        while(running) {
            Menu.displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
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
                    System.out.println("\nThank you for using our banking system!");
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\n✗ Invalid choice. Please select 1-6.");
            }
        }
        
        scanner.close();
    }
    
    // Initialize with some test accounts
    private static void initializeTestAccounts() {
        accounts.add(new Account("John Doe", 5000.00));
        accounts.add(new Account("Jane Smith", 10000.00));
        accounts.add(new Account("Bob Johnson", 7500.00));
        System.out.println("\n✓ 3 test accounts initialized");
    }
    
    // Method to create a new account
    private static void createNewAccount() {
        System.out.println("\n=== CREATE NEW ACCOUNT ===");
        
        System.out.print("Enter account holder name: ");
        scanner.nextLine(); // Clear buffer
        String name = scanner.nextLine();
        
        double initialDeposit = getDoubleInput("Enter initial deposit amount: R");
        
        if(initialDeposit < 0) {
            System.out.println("✗ Initial deposit cannot be negative");
            return;
        }
        
        Account newAccount = new Account(name, initialDeposit);
        accounts.add(newAccount);
        
        System.out.println("\n✓ Account created successfully!");
        System.out.println("Account Number: " + newAccount.getAccountNumber());
        System.out.println("Holder Name: " + newAccount.getHolderName());
        System.out.println("Initial Balance: R" + String.format("%.2f", newAccount.getBalance()));
    }
    
    // Method to view all accounts
    private static void viewAllAccounts() {
        System.out.println("\n=== ALL ACCOUNTS ===");
        
        if(accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        
        System.out.println("Total Accounts: " + accounts.size());
        System.out.println("-----------------------------------");
        
        for(int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            System.out.println("Account #" + (i+1));
            System.out.println("  Number: " + acc.getAccountNumber());
            System.out.println("  Holder: " + acc.getHolderName());
            System.out.println("  Balance: R" + String.format("%.2f", acc.getBalance()));
            System.out.println("-----------------------------------");
        }
    }
    
    // Method to deposit money
    private static void depositMoney() {
        System.out.println("\n=== DEPOSIT MONEY ===");
        
        Account account = findAccountByNumber();
        if(account == null) {
            System.out.println("✗ Account not found");
            return;
        }
        
        double amount = getDoubleInput("Enter deposit amount: R");
        account.deposit(amount);
    }
    
    // Method to withdraw money
    private static void withdrawMoney() {
        System.out.println("\n=== WITHDRAW MONEY ===");
        
        Account account = findAccountByNumber();
        if(account == null) {
            System.out.println("✗ Account not found");
            return;
        }
        
        double amount = getDoubleInput("Enter withdrawal amount: R");
        account.withdraw(amount);
    }
    
    // Method to check account balance
    private static void checkAccountBalance() {
        System.out.println("\n=== CHECK ACCOUNT BALANCE ===");
        
        Account account = findAccountByNumber();
        if(account == null) {
            System.out.println("✗ Account not found");
            return;
        }
        
        account.checkBalance();
    }
    
    // Helper method to find account by number
    private static Account findAccountByNumber() {
        long accountNumber = getLongInput("Enter account number: ");
        
        for(Account acc : accounts) {
            if(acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        
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
    
    // Helper method to get long input
    private static long getLongInput(String prompt) {
        System.out.print(prompt);
        while(!scanner.hasNextLong()) {
            System.out.println("✗ Please enter a valid account number");
            System.out.print(prompt);
            scanner.next();
        }
        long value = scanner.nextLong();
        return value;
    }
}