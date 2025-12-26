# Bank Account Management System

A Java project demonstrating Object-Oriented Programming concepts.

## Features
- Account management with encapsulation
- ArrayList for storing multiple accounts
- Menu-driven interface
- Deposit, withdraw, and transfer functionality

## Technologies
- Java
- Apache NetBeans IDE
- # Bank Account Management System

## Day 3 Progress 
### New Feature: Money Transfer Between Accounts
**Complete Transfer Functionality Implemented:**

1. **Account.transfer() Method**
   - Validates sender and recipient accounts
   - Prevents same-account transfers
   - Ensures sufficient funds
   - Provides transaction rollback if deposit fails
   - Displays detailed transfer confirmation

2. **User Interface Enhancements**
   - New menu option (6) for money transfers
   - Formatted account display with tables
   - Transfer confirmation step
   - Improved visual feedback with separators

3. **Safety Features**
   - User confirmation before completing transfer
   - Balance verification display
   - Transaction rollback capability
   - Clear error messages for failed transfers

### Current Features Summary:
- Account creation with auto-generated account numbers
- Deposit money with validation
- Withdraw money with balance checking
- Check account balance
- View all accounts in formatted table
- **NEW** Transfer money between accounts
- Input validation and error handling
- Menu-driven interface with 7 options

### Project Structure
src/
├── com/obcodes/bankaccountmanagementsystem/
│ ├── Account.java # Complete with deposit, withdraw, transfer
│ ├── BankAccountManagementSystem.java # Full banking operations
│ └── Menu.java # Enhanced menu system

### Sample Transfer Output:
=== TRANSFER INITIATED ===
From: John Doe (Acc: 123456789012)
To: Jane Smith (Acc: 987654321098)
Amount: R500.00
==============================

✓ Withdrawal successful: R500.00
Remaining balance: R4500.00
✓ Deposit successful: R500.00

✓ TRANSFER COMPLETED SUCCESSFULLY
===================================
Sender (John Doe):
New Balance: R4500.00

Recipient (Jane Smith):
New Balance: R10500.00
===================================

## Coming Next (Day 4):
- Transaction history tracking
- Account search by name
- Enhanced error handling
- File persistence (optional)

