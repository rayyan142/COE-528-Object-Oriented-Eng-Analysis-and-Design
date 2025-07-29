package coe528.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * OVERVIEW: Customers are mutable users in the banking application with a balance and a membership level.
 * The level (Silver, Gold, or Platinum) is determined by the customer's balance.
 * A customer can deposit and withdraw an amount from their account as well as make online purchases,
 * with a different purchase fee depending on their level. The class is mutable because the balance and level
 * can change over the lifetime of a customer object.
 *
 * Abstraction Function:
 * AF(c) = a customer c such that
 * c.username is the username,
 * c.password is the password,
 * c.balance is the account balance,
 * c.level is "Silver" if c.balance < 10,000, "Gold" if c.balance < 20,000, and "Platinum" otherwise.
 *
 * Representation Invariant:
 * true if c.balance >= 0 &&
 * (c.level.equals("Silver") || c.level.equals("Gold") || c.level.equals("Platinum")),
 * false otherwise.
 */

public class Customer extends User {
    
    private double balance;
    private String level;
    
    /**
     * Initializes a Customer with a username, password, and balance.
     * Updates the customer level based on the initial balance and saves the customer data to a file.
     *
     * @param username Customer's username
     * @param password Customer's password
     * @param balance Initial balance of the Customer
     * @requires balance >= 0
     * @effects Initializes a customer with username, password, and balance
     */
    public Customer(String username, String password, double balance) {
        super(username, password);
        this.balance = balance;
        updateLevel();
        saveToFile();
    }
    
    /**
     * Updates the level of the customer based on the current balance.
     *
     * @modifies this.level
     * @effects Sets the level of the customer based on the current balance.
     */
    private void updateLevel() {
        if (this.balance < 10000) {
            this.level = "Silver";
        } else if (this.balance < 20000) {
            this.level = "Gold";
        } else {
            this.level = "Platinum";
        }
    }
    
    /**
     * Deposits the specified amount into the customer's account if it's a positive amount.
     * Also updates the level and saves the data to a file.
     *
     * @param amount The amount to deposit.
     * @requires amount > 0
     * @modifies this.balance
     * @effects increments this.balance by amount and updates this.level
     * @throws IllegalArgumentException if amount is not positive
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.balance += amount;
        updateLevel();
        saveToFile();
    }
    
    /**
     * Withdraws the specified amount from the customer's account if sufficient funds are available.
     * Also updates the level and saves the data to a file.
     *
     * @param amount The amount to withdraw.
     * @requires amount > 0 and amount <= this.balance
     * @modifies this.balance
     * @effects decrements this.balance by amount and updates this.level
     * @returns true if the withdrawal was successful, false otherwise.
     * @throws IllegalArgumentException if amount is not positive or if insufficient funds.
     */
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (this.balance >= amount) {
            this.balance -= amount;
            updateLevel();
            saveToFile();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Attempts an online purchase with the specified amount if sufficient funds are available.
     * Applies a transaction fee based on the customer's level. Updates the level and saves the data to a file.
     *
     * @param amount The amount of the online purchase.
     * @requires amount >= 50
     * @modifies this.balance
     * @effects decrements this.balance by (amount + fee) and updates this.level
     * @returns true if the purchase was successful, false otherwise.
     */
    public boolean onlinePurchase(double amount) {
        if (amount < 50) {
            System.out.println("Minimum amount for online purchase is $50.");
            return false;
        }
        double fee = calculateFee();
        if (this.balance >= (amount + fee)) {
            this.balance -= (amount + fee);
            updateLevel();
            saveToFile();
            return true;
        } else {
            System.out.println("Insufficient funds for this purchase.");
            return false;
        }
    }
    
    /**
     * Calculates the fee for an online purchase based on the customer's level.
     *
     * @effects Returns a fee based on the customer's level.
     * @returns The fee for the transaction.
     * @throws IllegalStateException if the level is not recognized.
     */
    private double calculateFee() {
        switch (this.level) {
            case "Silver":
                return 20;
            case "Gold":
                return 10;
            case "Platinum":
                return 0;
            default:
                throw new IllegalStateException("Unexpected value: " + this.level);
        }
    }
    
    /**
     * Saves the current information of the customer to a file.
     *
     * @modifies This modifies the file corresponding to the customer.
     * @effects Stores the current customer's username, password, and balance to
     * a file.
     */
    private void saveToFile() {
        File file = new File(username + ".txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(username + System.lineSeparator());
            writer.write(password + System.lineSeparator());
            writer.write(String.valueOf(balance) + System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves the current balance of the customer.
     *
     * @return the current balance of this Customer.
     * @effects returns the current balance which is a non-negative number.
     */
    public double getBalance() {
        return this.balance;
    }
    
    /**
     * Returns the current level of the customer based on their balance.
     *
     * @return the current level of this Customer as a String
     * @effects returns the current level "Silver", "Gold", or "Platinum"
     */
    public String getLevel() {
        return this.level;
    }
    
    /**
     * Returns a string representation of the customer that includes the
     * username, balance, and level.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", balance=" + balance +
                ", level='" + level + '\'' +
                '}';
    }
        
    /**
     * Checks if the representation invariant holds for this customer.
     * 
     * @effects Returns true if the representation invariant holds, false otherwise.
     */
    public boolean repOk() {
        return balance >= 0 && 
               (level.equals("Silver") || level.equals("Gold") || level.equals("Platinum")) &&
               username != null && !username.isEmpty() &&
               password != null && !password.isEmpty();
    }
}
