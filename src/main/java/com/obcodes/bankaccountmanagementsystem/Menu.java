package com.obcodes.bankaccountmanagementsystem;

/**
 * Handles menu display and user interaction
 * Simplified version without colors for better compatibility
 * @author Obakeng Phale
 */
public class Menu {
    
    /**
     * Displays the main menu
     */
    public static void displayMainMenu() {
        System.out.println("\n==========================================");
        System.out.println("        BANKING SYSTEM MAIN MENU");
        System.out.println("==========================================");
        System.out.println("  Account Management:");
        System.out.println("  1. Create New Account");
        System.out.println("  2. View All Accounts");
        System.out.println("  3. Search Account by Name");
        
        System.out.println("\n  Transactions:");
        System.out.println("  4. Deposit Money");
        System.out.println("  5. Withdraw Money");
        System.out.println("  6. Check Account Balance");
        System.out.println("  7. Transfer Money Between Accounts");
        
        System.out.println("\n  Reports & History:");
        System.out.println("  8. View Transaction History");
        
        System.out.println("\n  System:");
        System.out.println("  9. Exit System");
        System.out.println("==========================================");
    }
    
    /**
     * Displays welcome message
     */
    public static void displayWelcomeMessage() {
        System.out.println("\n==========================================");
        System.out.println("  BANK ACCOUNT MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("\nA complete Java OOP project demonstrating:");
        System.out.println("- Classes and Objects");
        System.out.println("- Encapsulation and Data Hiding");
        System.out.println("- ArrayList for Data Management");
        System.out.println("- Menu-driven User Interface");
        System.out.println("- Complete Error Handling");
        System.out.println("- Input Validation");
        System.out.println("- Transaction History Tracking");
        System.out.println("==========================================");
    }
}