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
            int choice = getIntInput("Enter your choice (1-9): ");
            
            switch(choice) {
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    viewAllAccounts();
                    break;
                case 3:
                    searchAccountByName(); // NEW: Day 4 Feature
                    break;
                case 4:
                    depositMoney();
                    break;
                case 5:
                    withdrawMoney();
                    break;
                case 6:
                    checkAccountBalance();
                    break;
                case 7:
                    transferMoney();
                    break;
                case 8:
                    viewTransactionHistory(); // NEW: Day 4 Feature
                    break;
                case 9:
                    exitSystem();
                    running = false;
                    break;
                default:
                    System.out.println("\n✗ Invalid choice. Please select 1-9.");
            }
        }
        
        scanner.close();
    }
    
    // Display welcome banner
    private static void displayWelcomeBanner() {
        System.out.println("\n" + "🌟".repeat(55));
        System.out.println("          BANK ACCOUNT MANAGEMENT SYSTEM");
        System.out.println("🌟".repeat(55));
        System.out.println("\nA Java OOP Project by Obakeng Phale");
        System.out.println("Day 4: Transaction History & Account Search Implemented");
        System.out.println("-".repeat(55));
    }
    
    // Initialize with some test accounts
    private static void initializeTestAccounts() {
        accounts.add(new Account("John Doe", 5000.00));
        accounts.add(new Account("Jane Smith", 10000.00));
        accounts.add(new Account("Bob Johnson", 7500.00));
        accounts.add(new Account("Alice Brown", 12000.00));
        accounts.add(new Account("Charlie Wilson", 3000.00));
        
        // Add some test transactions
        accounts.get(0).deposit(1000);
        accounts.get(0).withdraw(500);
        accounts.get(1).deposit(2000);
        accounts.get(2).withdraw(1000);
        
        System.out.println("\n✓ 5 test accounts initialized with sample transactions");
    }
    
    // Method to create a new account
    private static void createNewAccount() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("      CREATE NEW ACCOUNT");
        System.out.println("=".repeat(40));
        
        System.out.print("Enter account holder name: ");
        scanner.nextLine(); // Clear buffer
        String name = scanner.nextLine().trim();
        
        if(name.isEmpty()) {
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
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                      ALL ACCOUNTS");
        System.out.println("=".repeat(70));
        
        if(accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        
        System.out.println("Total Accounts: " + accounts.size());
        System.out.println("-".repeat(70));
        System.out.println(String.format("%-15s %-20s %-12s %-10s", 
            "Account No.", "Holder Name", "Balance", "Transactions"));
        System.out.println("-".repeat(70));
        
        for(Account acc : accounts) {
            acc.displayAccountSummary();
        }
        
        System.out.println("-".repeat(70));
        
        // Calculate and display total bank balance
        double totalBalance = 0;
        int totalTransactions = 0;
        for(Account acc : accounts) {
            totalBalance += acc.getBalance();
            totalTransactions += acc.getTransactionHistory().size();
        }
        
        System.out.println("Total Bank Balance: R" + String.format("%.2f", totalBalance));
        System.out.println("Total Transactions: " + totalTransactions);
    }
    
    // NEW: Method to search account by name (Day 4 Feature)
    private static void searchAccountByName() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("      SEARCH ACCOUNT BY NAME");
        System.out.println("=".repeat(40));
        
        System.out.print("Enter name to search: ");
        scanner.nextLine(); // Clear buffer
        String searchName = scanner.nextLine().trim().toLowerCase();
        
        if(searchName.isEmpty()) {
            System.out.println("✗ Search name cannot be empty");
            return;
        }
        
        ArrayList<Account> searchResults = new ArrayList<>();
        
        for(Account acc : accounts) {
            if(acc.getHolderName().toLowerCase().contains(searchName)) {
                searchResults.add(acc);
            }
        }
        
        if(searchResults.isEmpty()) {
            System.out.println("\n✗ No accounts found matching: '" + searchName + "'");
            return;
        }
        
        System.out.println("\n✓ Found " + searchResults.size() + " account(s) matching: '" + searchName + "'");
        System.out.println("-".repeat(70));
        System.out.println(String.format("%-15s %-20s %-12s %-10s", 
            "Account No.", "Holder Name", "Balance", "Transactions"));
        System.out.println("-".repeat(70));
        
        for(Account acc : searchResults) {
            acc.displayAccountSummary();
        }
        
        System.out.println("-".repeat(70));
        
        // Option to view details of a specific account
        if(searchResults.size() == 1) {
            System.out.print("\nView details of this account? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if(response.equals("yes") || response.equals("y")) {
                searchResults.get(0).displayAccountInfo();
            }
        }
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
        
        // Validate amount
        if(amount <= 0) {
            System.out.println("✗ Deposit amount must be positive");
            return;
        }
        
        if(amount > 1000000) { // Limit for safety
            System.out.println("✗ Deposit amount exceeds maximum limit of R1,000,000");
            return;
        }
        
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
        
        // Enhanced validation
        if(amount <= 0) {
            System.out.println("✗ Withdrawal amount must be positive");
            return;
        }
        
        if(amount > 50000) { // Daily withdrawal limit
            System.out.println("✗ Withdrawal amount exceeds daily limit of R50,000");
            return;
        }
        
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
    
    // Method to transfer money between accounts
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
        
        // Enhanced validation
        if(amount <= 0) {
            System.out.println("✗ Transfer amount must be positive");
            return;
        }
        
        if(amount > 100000) { // Transfer limit
            System.out.println("✗ Transfer amount exceeds limit of R100,000");
            return;
        }
        
        // Confirm transfer
        System.out.print("\nConfirm transfer of R" + String.format("%.2f", amount) + "? (yes/no): ");
        scanner.nextLine(); // Clear buffer
        String confirmation = scanner.nextLine().toLowerCase();
        
        if(confirmation.equals("yes") || confirmation.equals("y")) {
            sender.transfer(recipient, amount);
        } else {
            System.out.println("✗ Transfer cancelled by user");
        }
    }
    
    // NEW: Method to view transaction history (Day 4 Feature)
    private static void viewTransactionHistory() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("     VIEW TRANSACTION HISTORY");
        System.out.println("=".repeat(40));
        
        Account account = findAccountByNumber();
        if(account == null) {
            System.out.println("✗ Account not found");
            return;
        }
        
        account.displayTransactionHistory();
        
        // Additional options
        System.out.println("\n" + "-".repeat(40));
        System.out.println("Additional Options:");
        System.out.println("1. View account information");
        System.out.println("2. Return to main menu");
        System.out.print("Select option: ");
        
        int option = getIntInput("");
        
        if(option == 1) {
            account.displayAccountInfo();
        }
    }
    
    // NEW: Exit system method
    private static void exitSystem() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("              SYSTEM SUMMARY");
        System.out.println("=".repeat(60));
        
        System.out.println("Total Accounts: " + accounts.size());
        
        double totalBalance = 0;
        int totalTransactions = 0;
        for(Account acc : accounts) {
            totalBalance += acc.getBalance();
            totalTransactions += acc.getTransactionHistory().size();
        }
        
        System.out.println("Total Bank Balance: R" + String.format("%.2f", totalBalance));
        System.out.println("Total Transactions: " + totalTransactions);
        System.out.println("-".repeat(60));
        System.out.println("\nThank you for using our banking system!");
        System.out.println("Goodbye! 👋");
        System.out.println("=".repeat(60));
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
    
    // Helper method to get integer input with validation
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
    
    // Helper method to get double input with validation
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