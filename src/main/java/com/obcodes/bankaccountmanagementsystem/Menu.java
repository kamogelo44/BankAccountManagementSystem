package com.obcodes.bankaccountmanagementsystem;

/**
 * Handles menu display and user interaction
 * @author Obakeng Phale
 */
public class Menu {
    
    public static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("        MAIN MENU");
        System.out.println("=".repeat(40));
        System.out.println("1. Create New Account");
        System.out.println("2. View All Accounts");
        System.out.println("3. Deposit Money");
        System.out.println("4. Withdraw Money");
        System.out.println("5. Check Account Balance");
        System.out.println("6. Exit System");
        System.out.println("=".repeat(40));
    }
    
    public static void displayWelcomeMessage() {
        System.out.println("\n" + "*".repeat(50));
        System.out.println("  WELCOME TO BANK ACCOUNT MANAGEMENT SYSTEM");
        System.out.println("*".repeat(50));
        System.out.println("\nA Java project demonstrating OOP concepts:");
        System.out.println("- Classes and Objects");
        System.out.println("- Encapsulation");
        System.out.println("- ArrayList for data management");
        System.out.println("- Menu-driven interface");
    }
    
    public static void displayTransactionMenu() {
        System.out.println("\n" + "-".repeat(30));
        System.out.println("    TRANSACTION MENU");
        System.out.println("-".repeat(30));
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        System.out.println("5. Back to Main Menu");
        System.out.println("-".repeat(30));
    }
}