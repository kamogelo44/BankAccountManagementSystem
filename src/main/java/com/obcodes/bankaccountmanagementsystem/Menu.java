package com.obcodes.bankaccountmanagementsystem;

/**
 * Handles menu display and user interaction
 * @author Obakeng Phale
 */
public class Menu {
    
    public static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                 MAIN MENU");
        System.out.println("=".repeat(60));
        System.out.println("1.  Create New Account");
        System.out.println("2.  View All Accounts");
        System.out.println("3.  Search Account by Name         [NEW]");
        System.out.println("4.  Deposit Money");
        System.out.println("5.  Withdraw Money");
        System.out.println("6.  Check Account Balance");
        System.out.println("7.  Transfer Money Between Accounts");
        System.out.println("8.  View Transaction History       [NEW]");
        System.out.println("9.  Exit System");
        System.out.println("=".repeat(60));
    }
    
    public static void displayWelcomeMessage() {
        System.out.println("\n" + "★".repeat(65));
        System.out.println("        WELCOME TO BANK ACCOUNT MANAGEMENT SYSTEM");
        System.out.println("★".repeat(65));
        System.out.println("\nA Java project demonstrating OOP concepts:");
        System.out.println("✓ Classes and Objects");
        System.out.println("✓ Encapsulation");
        System.out.println("✓ ArrayList for data management");
        System.out.println("✓ Menu-driven interface");
        System.out.println("✓ Transfer functionality");
        System.out.println("✓ Transaction History (Day 4)");
        System.out.println("✓ Account Search (Day 4)");
    }
    
    public static void displaySearchHeader() {
        System.out.println("\n" + "🔍".repeat(30));
        System.out.println("        ACCOUNT SEARCH");
        System.out.println("🔍".repeat(30));
    }
    
    public static void displayHistoryHeader() {
        System.out.println("\n" + "📜".repeat(30));
        System.out.println("    TRANSACTION HISTORY");
        System.out.println("📜".repeat(30));
    }
}