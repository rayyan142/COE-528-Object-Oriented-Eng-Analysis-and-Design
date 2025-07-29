/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author rayya
 */
public abstract class Account {
    
    private String accountLevel;
    protected int balance;
    
    public Account (){
        balance = 100;
    }
    
    public int getBalance(){
        return this.balance;
    }
    
    public void setBalance(int balance){
        if (balance >= 0){
            this.balance = balance;
        }
    }
    
    public void increaseBalance(int amount){
        balance += amount;
    }
    
    public void decreaseBalance(int amount){
        balance -= amount;
    }
    
    public abstract boolean onlinePurchase(int amount);
    
    public String getLevel(){
        return accountLevel;
    }
}
