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
## Day 4 Progress ✅
### Major New Features:

### 1. **Transaction History Tracking**
- **NEW Transaction Class**: Stores transaction details with timestamp
- **Complete History**: All deposits, withdrawals, and transfers are recorded
- **Transaction Types**: DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT
- **Detailed View**: View full transaction history for any account

### 2. **Account Search by Name**
- **Partial Match Search**: Case-insensitive name searching
- **Multiple Results**: Displays all matching accounts
- **Quick Details**: Option to view details of found accounts

### 3. **Enhanced Error Handling**
- **Transaction Limits**: Safety limits for deposits (R1M), withdrawals (R50K), transfers (R100K)
- **Input Validation**: Improved validation for all user inputs
- **Error Messages**: Clear, user-friendly error messages

### 4. **Improved User Interface**
- **9-Menu System**: Expanded from 7 to 9 options
- **Formatted Outputs**: Better table formatting with transaction counts
- **System Summary**: Display totals on exit

### Current Features Summary:
- ✅ Account creation with auto-generated 12-digit account numbers
- ✅ Deposit/Withdraw/Transfer with transaction limits
- ✅ Check account balance
- ✅ View all accounts with transaction counts
- ✅ **NEW** Search accounts by name
- ✅ **NEW** View transaction history
- ✅ Input validation and enhanced error handling
- ✅ Menu-driven interface with 9 options

### Project Structure (Updated):
src/
├── com/obcodes/bankaccountmanagementsystem/
│ ├── Account.java # Complete with transaction history
│ ├── BankAccountManagementSystem.java # Full banking operations
│ ├── Menu.java # Enhanced menu system
│ └── Transaction.java # NEW: Transaction record class


### Sample Transaction History Output:
====================================================================================================
TRANSACTION HISTORY - JOHN DOE
Account: 123456789012
====================================================================================================
Transaction ID Date & Time Type Amount Balance Account Description

TXN123... 2024-01-15 10:30:15 DEPOSIT R5000.00 R5000.00 123456789012 Initial deposit
TXN456... 2024-01-15 11:15:22 DEPOSIT R1000.00 R6000.00 123456789012 Cash deposit
TXN789... 2024-01-15 12:45:10 WITHDRAWAL R500.00 R5500.00 123456789012 Cash withdrawal
TXN012... 2024-01-15 14:20:05 TRANSFER_OUT R1000.00 R4500.00 123456789012 Transfer to Acc: 987654321098

Total Transactions: 4
Current Balance: R4500.00


## Coming Next (Day 5 - Final Day):
- File persistence (save/load accounts to file)
- Additional account types (Savings, Current)
- Interest calculation for savings accounts
- Password protection for accounts
- Export transaction history to file
