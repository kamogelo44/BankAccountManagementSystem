# Bank Account Management System

A complete Java Object-Oriented Programming (OOP) project demonstrating core programming concepts with a practical banking application.

##  Project Overview

This project implements a fully-functional bank account management system that showcases essential OOP principles through a menu-driven console application. Developed over 5 days with incremental GitHub commits, it serves as an excellent portfolio piece for Java developers.

##  Learning Objectives

This project demonstrates:
- **Classes and Objects**: Modeling real-world banking entities
- **Encapsulation**: Data hiding with private fields and public methods
- **ArrayList**: Dynamic collection management for multiple accounts
- **Exception Handling**: Comprehensive error management
- **Input Validation**: Robust user input verification
- **Menu-driven Interface**: User-friendly console interaction

## Features

### Account Management
- Create new accounts with auto-generated 12-digit account numbers
- View all accounts with detailed information
- Search accounts by holder name (partial match)
- Update account holder names
- Automatic transaction history for all account changes

### Banking Operations
- **Deposit Money**: With validation and transaction limits
- **Withdraw Money**: With balance verification and daily limits
- **Check Balance**: Real-time account balance display
- **Transfer Funds**: Secure transfers between accounts with rollback capability

### Transaction History
- Complete record of all transactions
- Timestamped entries with unique IDs
- Transaction types: DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT
- Detailed transaction descriptions

### Security & Validation
- Input validation for all user entries
- Transaction limits (deposit: R1M, withdrawal: R50K, transfer: R100K)
- Account number verification
- Name validation with character restrictions
- Amount validation with decimal precision

### User Experience
- Color-coded console output
- Formatted tables and professional displays
- Confirmation prompts for critical operations
- Clear error messages with recovery suggestions
- System pause between operations

##  Project Structure
src/
├── com/obcodes/bankaccountmanagementsystem/
│ ├── Account.java # Main account class with all operations
│ ├── Transaction.java # Transaction record class
│ ├── BankAccountManagementSystem.java # Main application with menu system
│ └── Menu.java # Menu display class

## 🚀 How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line terminal or IDE

### Compilation
```bash
# Navigate to src directory
cd src

# Compile all Java files
javac com/obcodes/bankaccountmanagementsystem/*.java

# Run the application
java com.obcodes.bankaccountmanagementsystem.BankAccountManagementSystem

Using an IDE (NetBeans/Eclipse/IntelliJ)
Import the project as a Java application
✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨
              BANK ACCOUNT MANAGEMENT SYSTEM v1.0
✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨

======================================================================
                  BANKING SYSTEM MAIN MENU
======================================================================
  Account Management:
  1.  Create New Account
  2.  View All Accounts
  3.  Search Account by Name
  9.  Update Account Holder Name

  Transactions:
  4.  Deposit Money
  5.  Withdraw Money
  6.  Check Account Balance
  7.  Transfer Money Between Accounts

  Reports & History:
  8.  View Transaction History

  System:
  10. Exit System
======================================================================
Enter your choice (1-10): 1
Run BankAccountManagementSystem.java as the main class

🔧 Technical Implementation
Core Classes
1. Account Class
Encapsulation: Private fields with public getters/setters

Methods: deposit(), withdraw(), transfer(), checkBalance()

Validation: Built-in validation for all operations

History: Maintains complete transaction records

2. Transaction Class
Immutable: Once created, transactions cannot be modified

Tracking: Records type, amount, timestamp, and balance

Formatting: Professional display formatting

3. BankAccountManagementSystem Class
Controller: Main application logic

Menu Handling: 10-option menu system

Validation: Comprehensive input validation

Error Handling: Graceful error recovery

4. Menu Class
Separation of Concerns: Dedicated menu display logic

User Interface: Professional formatting and colors

Key OOP Concepts Demonstrated
Encapsulation

Private fields with controlled access

Validation within setter methods

Immutable transaction records

Abstraction

Complex banking operations simplified through methods

Hidden implementation details

Data Structures

ArrayList for dynamic account storage

Transaction history tracking

Error Handling

Try-catch blocks throughout

Custom exception messages

User-friendly error recovery

📈 Development Timeline
Day 1: Foundation
Basic Account class with encapsulation

Account number generation

Initial menu structure

Day 2: Core Operations
Complete deposit and withdrawal functionality

ArrayList management for multiple accounts

Enhanced menu system

Day 3: Advanced Features
Money transfer between accounts

Transaction confirmation

Improved user interface

Day 4: History & Search
Transaction history tracking

Account search by name

Enhanced error handling

Day 5: Final Polish
Complete input validation

Professional error handling

Color-coded interface

Final documentation

🧪 Testing Features
The system includes:

5 pre-loaded test accounts

Sample transactions for demonstration

Boundary testing for all validations

Error scenario handling

📝 Code Quality Features
JavaDoc Comments: Comprehensive documentation

Meaningful Naming: Descriptive variable and method names

Modular Design: Separation of concerns

Constants Usage: No magic numbers

Formatting: Consistent code style

🎓 Educational Value
This project is ideal for:

Java beginners learning OOP concepts

Students preparing for programming interviews

Developers building portfolio projects

Understanding real-world application development

🔮 Future Enhancements
Potential improvements for extended learning:

File persistence for data storage

Database integration

GUI interface

Web application version

Additional account types (Savings, Business)

Interest calculation

Email notifications

👨‍💻 Author
Obakeng Phale
Java Developer
GitHub: @obcodes

📄 License
This project is open source and available for educational purposes.
