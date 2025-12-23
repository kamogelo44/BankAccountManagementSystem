package com.obcodes.bankaccountmanagementsystem;

import java.util.ArrayList;

/**
 * @author Obakeng Phale
 */
public class BankAccountManagementSystem {
    
    private static ArrayList<Account> accounts = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println(
        "=================================="
        +"   BANK ACCOUNT MANAGEMENT SYSTEM"
        +"=================================="
        );
        System.out.println("\nWelcome! Starting the system...");
        
        // Test creating an account
        Account testAccount = new Account("John Doe", 1000.00);
        accounts.add(testAccount);
        
        System.out.println("\nTest Account Created:");
        testAccount.displayAccountInfo();
        
        // Test deposit
        System.out.println("Depositing R500...");
        if(testAccount.deposit(500)) {
            System.out.println("Deposit successful!");
        }
        testAccount.displayAccountInfo();
        
        System.out.println("System ready for Day 2 implementation!");
    }
    
}