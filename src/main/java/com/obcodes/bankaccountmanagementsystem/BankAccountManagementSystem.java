package com.obcodes.bankaccountmanagementsystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main banking system application
 * Demonstrates complete OOP system with menu-driven interface
 * @author Obakeng Phale
 */
public class BankAccountManagementSystem {
    
    private static final ArrayList<Account> accounts = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    
    // Color codes for better UI 
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";
    
    public static void main(String[] args) {
        displayWelcomeBanner();
        initializeTestAccounts();
        
        boolean running = true;
        while (running) {
            try {
                Menu.displayMainMenu();
                int choice = getIntInput("Enter your choice (1-10): ", 1, 10);
                
                switch (choice) {
                    case 1:
                        createNewAccount();
                        break;
                    case 2:
                        viewAllAccounts();
                        break;
                    case 3:
                        searchAccountByName();
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
                        viewTransactionHistory();
                        break;
                    case 9:
                        updateAccountHolderName();
                        break;
                    case 10:
                        exitSystem();
                        running = false;
                        break;
                }
                
                // Pause after each operation
                if (running) {
                    System.out.print("\nPress Enter to continue...");
                    scanner.nextLine();
                    scanner.nextLine(); // Double call to clear buffer
                }
                
            } catch (Exception e) {
                System.out.println(RED + "\n⚠ An unexpected error occurred: " + e.getMessage() + RESET);
                System.out.println("Please try again.");
                scanner.nextLine(); // Clear buffer
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays welcome banner
     */
    private static void displayWelcomeBanner() {
        System.out.println(CYAN + BOLD + "\n" + "✨".repeat(65));
        System.out.println("              BANK ACCOUNT MANAGEMENT SYSTEM v1.0");
        System.out.println("✨".repeat(65));
        System.out.println("\n             A Complete Java OOP Project");
        System.out.println("                by Obakeng Phale" + RESET);
        System.out.println("\n" + "-".repeat(65));
        System.out.println(YELLOW + "Features:" + RESET);
        System.out.println("✓ Complete account management with validation");
        System.out.println("✓ Transaction history tracking");
        System.out.println("✓ Secure money transfers between accounts");
        System.out.println("✓ Account search and filtering");
        System.out.println("✓ Professional error handling");
        System.out.println("-".repeat(65));
    }
    
    /**
     * Initializes test accounts with sample data
     */
    private static void initializeTestAccounts() {
        try {
            accounts.add(new Account("John Doe", 5000.00));
            accounts.add(new Account("Jane Smith", 10000.00));
            accounts.add(new Account("Robert Johnson", 7500.00));
            accounts.add(new Account("Alice Brown", 12000.00));
            accounts.add(new Account("Charlie Wilson", 3000.00));
            
            // Add sample transactions
            accounts.get(0).deposit(1000);
            accounts.get(0).withdraw(500);
            accounts.get(1).deposit(2000);
            accounts.get(2).withdraw(1000);
            accounts.get(0).transfer(accounts.get(1), 500);
            
            System.out.println(GREEN + "✓ 5 test accounts initialized with sample transactions" + RESET);
            
        } catch (Exception e) {
            System.out.println(RED + "✗ Error initializing test accounts: " + e.getMessage() + RESET);
        }
    }
    
    /**
     * Creates a new account with validation
     */
    private static void createNewAccount() {
        System.out.println(CYAN + BOLD + "\n" + "=".repeat(50));
        System.out.println("           CREATE NEW ACCOUNT");
        System.out.println("=".repeat(50) + RESET);
        
        try {
            System.out.print("Enter account holder name: ");
            scanner.nextLine(); // Clear buffer
            String name = scanner.nextLine().trim();
            
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            
            double initialDeposit = getDoubleInput("Enter initial deposit amount: R", 0, 1000000);
            
            Account newAccount = new Account(name, initialDeposit);
            accounts.add(newAccount);
            
            System.out.println(GREEN + "\n" + "✓".repeat(50));
            System.out.println("      ACCOUNT CREATED SUCCESSFULLY!");
            System.out.println("✓".repeat(50) + RESET);
            System.out.println("Account Number: " + newAccount.getAccountNumber());
            System.out.println("Holder Name: " + newAccount.getHolderName());
            System.out.printf("Initial Balance: R%,.2f%n", newAccount.getBalance());
            
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "✗ Account creation failed: " + e.getMessage() + RESET);
        } catch (Exception e) {
            System.out.println(RED + "✗ Unexpected error: " + e.getMessage() + RESET);
        }
    }
    
    /**
     * Displays all accounts in formatted table
     */
    private static void viewAllAccounts() {
        System.out.println(CYAN + BOLD + "\n" + "=".repeat(80));
        System.out.println("                          ALL ACCOUNTS");
        System.out.println("=".repeat(80) + RESET);
        
        if (accounts.isEmpty()) {
            System.out.println(YELLOW + "No accounts found in the system." + RESET);
            return;
        }
        
        System.out.println("Total Accounts: " + accounts.size());
        System.out.println("-".repeat(80));
        System.out.printf("%-15s %-25s %-20s %-15s%n", 
            "Account No.", "Holder Name", "Balance", "Transactions");
        System.out.println("-".repeat(80));
        
        for (Account acc : accounts) {
            acc.displayAccountSummary();
        }
        
        System.out.println("-".repeat(80));
        
        // Calculate totals
        double totalBalance = 0;
        int totalTransactions = 0;
        for (Account acc : accounts) {
            totalBalance += acc.getBalance();
            totalTransactions += acc.getTransactionHistory().size();
        }
        
        System.out.printf(GREEN + "Total Bank Balance: R%,.2f%n" + RESET, totalBalance);
        System.out.printf("Total Transactions: %,d%n", totalTransactions);
    }
    
    /**
     * Searches accounts by name (partial match)
     */
    private static void searchAccountByName() {
        System.out.println(CYAN + BOLD + "\n" + "=".repeat(50));
        System.out.println("         SEARCH ACCOUNTS BY NAME");
        System.out.println("=".repeat(50) + RESET);
        
        System.out.print("Enter name to search: ");
        scanner.nextLine(); // Clear buffer
        String searchName = scanner.nextLine().trim().toLowerCase();
        
        if (searchName.isEmpty() || searchName.length() < 2) {
            System.out.println(RED + "✗ Search term must be at least 2 characters" + RESET);
            return;
        }
        
        ArrayList<Account> searchResults = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getHolderName().toLowerCase().contains(searchName)) {
                searchResults.add(acc);
            }
        }
        
        if (searchResults.isEmpty()) {
            System.out.println(YELLOW + "\nNo accounts found matching: '" + searchName + "'" + RESET);
            return;
        }
        
        System.out.println(GREEN + "\n✓ Found " + searchResults.size() + " account(s) matching: '" + searchName + "'" + RESET);
        System.out.println("-".repeat(80));
        System.out.printf("%-15s %-25s %-20s %-15s%n", 
            "Account No.", "Holder Name", "Balance", "Transactions");
        System.out.println("-".repeat(80));
        
        for (Account acc : searchResults) {
            acc.displayAccountSummary();
        }
        
        System.out.println("-".repeat(80));
        
        // Option to view details
        if (searchResults.size() == 1) {
            System.out.print("\nView details of this account? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("yes") || response.equals("y")) {
                searchResults.get(0).displayAccountInfo();
            }
        }
    }
    
    /**
     * Deposits money into an account
     */
    private static void depositMoney() {
        System.out.println(CYAN + BOLD + "\n" + "=".repeat(40));
        System.out.println("            DEPOSIT MONEY");
        System.out.println("=".repeat(40) + RESET);
        
        Account account = findAccountByNumber();
        if (account == null) return;
        
        account.checkBalance();
        double amount = getDoubleInput("\nEnter deposit amount: R", 0.01, 1000000);
        
        if (!account.deposit(amount)) {
            System.out.println(RED + "✗ Deposit operation failed" + RESET);
        }
    }
    
    /**
     * Withdraws money from an account
     */
    private static void withdrawMoney() {
        System.out.println(CYAN + BOLD + "\n" + "=".repeat(40));
        System.out.println("           WITHDRAW MONEY");
        System.out.println("=".repeat(40) + RESET);
        
        Account account = findAccountByNumber();
        if (account == null) return;
        
        account.checkBalance();
        double amount = getDoubleInput("\nEnter withdrawal amount: R", 0.01, 50000);
        
        if (!account.withdraw(amount)) {
            System.out.println(RED + "✗ Withdrawal operation failed" + RESET);
        }
    }
    
    /**
     * Checks account balance
     */
    private static void checkAccountBalance() {
        System.out.println(CYAN + BOLD + "\n" + "=".repeat(40));
        System.out.println("        CHECK ACCOUNT BALANCE");
        System.out.println("=".repeat(40) + RESET);
        
        Account account = findAccountByNumber();
        if (account == null) return;
        
        account.checkBalance();
    }
    
    /**
     * Transfers money between accounts
     */
    private static void transferMoney() {
        System.out.println(CYAN + BOLD + "\n" + "=".repeat(50));
        System.out.println("           TRANSFER MONEY");
        System.out.println("=".repeat(50) + RESET);
        
        System.out.println("\n--- SENDER ACCOUNT ---");
        Account sender = findAccountByNumber();
        if (sender == null) return;
        
        System.out.println("\n--- RECIPIENT ACCOUNT ---");
        Account recipient = findAccountByNumber();
        if (recipient == null) return;
        
        // Validate accounts are different
        if (sender.getAccountNumber() == recipient.getAccountNumber()) {
            System.out.println(RED + "✗ Cannot transfer to the same account" + RESET);
            return;
        }
        
        sender.checkBalance();
        recipient.checkBalance();
        
        double amount = getDoubleInput("\nEnter transfer amount: R", 0.01, 100000);
        
        // Get confirmation
        System.out.print("\nConfirm transfer? (yes/no): ");
        scanner.nextLine(); // Clear buffer
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (confirmation.equals("yes") || confirmation.equals("y")) {
            if (!sender.transfer(recipient, amount)) {
                System.out.println(RED + "✗ Transfer operation failed" + RESET);
            }
        } else {
            System.out.println(YELLOW + "✗ Transfer cancelled by user" + RESET);
        }
    }
    
    /**
     * Views transaction history for an account
     */
    private static void viewTransactionHistory() {
        System.out.println(CYAN + BOLD + "\n" + "=".repeat(50));
        System.out.println("       VIEW TRANSACTION HISTORY");
        System.out.println("=".repeat(50) + RESET);
        
        Account account = findAccountByNumber();
        if (account == null) return;
        
        account.displayTransactionHistory();
    }
    
    /**
     * Updates account holder name
     */
    private static void updateAccountHolderName() {
        System.out.println(CYAN + BOLD + "\n" + "=".repeat(50));
        System.out.println("       UPDATE ACCOUNT HOLDER NAME");
        System.out.println("=".repeat(50) + RESET);
        
        Account account = findAccountByNumber();
        if (account == null) return;
        
        System.out.println("Current Account Holder: " + account.getHolderName());
        System.out.print("Enter new account holder name: ");
        scanner.nextLine(); // Clear buffer
        String newName = scanner.nextLine().trim();
        
        if (newName.isEmpty()) {
            System.out.println(RED + "✗ Name cannot be empty" + RESET);
            return;
        }
        
        try {
            String oldName = account.getHolderName();
            account.setHolderName(newName);
            System.out.println(GREEN + "✓ Account holder name updated successfully!" + RESET);
            System.out.println("Changed from: " + oldName);
            System.out.println("Changed to: " + account.getHolderName());
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "✗ Update failed: " + e.getMessage() + RESET);
        }
    }
    
    /**
     * Exits the system with summary
     */
    private static void exitSystem() {
        System.out.println(CYAN + BOLD + "\n" + "=".repeat(65));
        System.out.println("               SYSTEM TERMINATION SUMMARY");
        System.out.println("=".repeat(65) + RESET);
        
        System.out.println("Total Accounts in System: " + accounts.size());
        
        double totalBalance = 0;
        int totalTransactions = 0;
        for (Account acc : accounts) {
            totalBalance += acc.getBalance();
            totalTransactions += acc.getTransactionHistory().size();
        }
        
        System.out.printf("Total Bank Balance: R%,.2f%n", totalBalance);
        System.out.println("Total Transactions Processed: " + totalTransactions);
        System.out.println("-".repeat(65));
        
        System.out.println(YELLOW + BOLD + "\nThank you for using Bank Account Management System!" + RESET);
        System.out.println("Developed by: Obakeng Phale");
        System.out.println("GitHub: https://github.com/yourusername/bank-account-management-system");
        System.out.println(CYAN + BOLD + "\n" + "✨".repeat(65) + RESET);
    }
    
    /**
     * Finds account by account number
     * @return Account if found, null otherwise
     */
    private static Account findAccountByNumber() {
        System.out.print("Enter account number: ");
        
        try {
            long accountNumber = scanner.nextLong();
            
            for (Account acc : accounts) {
                if (acc.getAccountNumber() == accountNumber) {
                    return acc;
                }
            }
            
            System.out.println(RED + "✗ Account with number " + accountNumber + " not found" + RESET);
            return null;
            
        } catch (InputMismatchException e) {
            System.out.println(RED + "✗ Invalid account number format. Please enter a 12-digit number." + RESET);
            scanner.next(); // Clear invalid input
            return null;
        }
    }
    
    /**
     * Gets integer input with validation
     * @param prompt Input prompt
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @return Validated integer
     */
    private static int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                
                if (value < min || value > max) {
                    System.out.printf(RED + "✗ Please enter a number between %d and %d%n" + RESET, min, max);
                    continue;
                }
                
                return value;
                
            } catch (InputMismatchException e) {
                System.out.println(RED + "✗ Invalid input. Please enter a valid number." + RESET);
                scanner.next(); // Clear invalid input
            }
        }
    }
    
    /**
     * Gets double input with validation
     * @param prompt Input prompt
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @return Validated double
     */
    private static double getDoubleInput(String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                
                if (value < min || value > max) {
                    System.out.printf(RED + "✗ Please enter an amount between R%.2f and R%.2f%n" + RESET, min, max);
                    continue;
                }
                
                return Math.round(value * 100.0) / 100.0; // Round to 2 decimal places
                
            } catch (InputMismatchException e) {
                System.out.println(RED + "✗ Invalid input. Please enter a valid amount." + RESET);
                scanner.next(); // Clear invalid input
            }
        }
    }
}