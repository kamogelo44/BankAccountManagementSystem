package com.obcodes.bankaccountmanagementsystem;

/**
 * Handles menu display and user interaction
 * @author Obakeng Phale
 */
public class Menu {
    
    public static void displayMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Create New Account");
        System.out.println("2. View All Accounts");
        System.out.println("3. Deposit Money");
        System.out.println("4. Withdraw Money");
        System.out.println("5. Check Balance");
        System.out.println("6. Exit");
        System.out.println("=====================");
        System.out.print("Enter your choice (1-6): ");
    }
    
    public static void displayWelcomeMessage() {
        System.out.println("\nWelcome to Bank Account Management System!");
        System.out.println("A simple Java project demonstrating OOP concepts.");
    }
}