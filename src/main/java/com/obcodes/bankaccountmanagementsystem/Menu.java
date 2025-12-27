package com.obcodes.bankaccountmanagementsystem;

/**
 * Handles menu display and user interaction
 * Demonstrates separation of concerns
 * @author Obakeng Phale
 */
public class Menu {
    
    // Color codes for better UI
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BOLD = "\u001B[1m";
    
    /**
     * Displays the main menu
     */
    public static void displayMainMenu() {
        System.out.println(CYAN + BOLD + "\n" + "=".repeat(70));
        System.out.println("                  BANKING SYSTEM MAIN MENU");
        System.out.println("=".repeat(70) + RESET);
        System.out.println(GREEN + "  Account Management:" + RESET);
        System.out.println("  1.  Create New Account");
        System.out.println("  2.  View All Accounts");
        System.out.println("  3.  Search Account by Name");
        System.out.println("  9.  Update Account Holder Name");
        
        System.out.println(YELLOW + "\n  Transactions:" + RESET);
        System.out.println("  4.  Deposit Money");
        System.out.println("  5.  Withdraw Money");
        System.out.println("  6.  Check Account Balance");
        System.out.println("  7.  Transfer Money Between Accounts");
        
        System.out.println(CYAN + "\n  Reports & History:" + RESET);
        System.out.println("  8.  View Transaction History");
        
        System.out.println(RESET + BOLD + "\n  System:" + RESET);
        System.out.println("  10. Exit System");
        System.out.println(CYAN + BOLD + "=".repeat(70) + RESET);
    }
    
    /**
     * Displays welcome message
     */
    public static void displayWelcomeMessage() {
        System.out.println("\n" + "★".repeat(75));
        System.out.println("          WELCOME TO BANK ACCOUNT MANAGEMENT SYSTEM");
        System.out.println("★".repeat(75));
        System.out.println("\nA complete Java OOP project demonstrating:");
        System.out.println("✓ Classes and Objects");
        System.out.println("✓ Encapsulation and Data Hiding");
        System.out.println("✓ ArrayList for Data Management");
        System.out.println("✓ Menu-driven User Interface");
        System.out.println("✓ Complete Error Handling");
        System.out.println("✓ Input Validation");
        System.out.println("✓ Transaction History Tracking");
    }
}