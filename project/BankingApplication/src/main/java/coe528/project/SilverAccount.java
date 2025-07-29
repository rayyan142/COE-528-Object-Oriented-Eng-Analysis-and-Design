/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author rayya
 */

public class SilverAccount extends Account {
    
    
    private String accountLevel;
    protected int balance;
    
    public SilverAccount (){
        balance = 100;
    }
    
    public int getBalance(){
        return balance;
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
    
    public boolean onlinePurchase(int amount){
    if (this.getBalance() >= amount + 20){
        this.decreaseBalance(amount + 20);
        return true;
    } else {
        return false;
    }
}
    
    public String getLevel(){
        return "You are a Silver Member!";
    }
    
}
