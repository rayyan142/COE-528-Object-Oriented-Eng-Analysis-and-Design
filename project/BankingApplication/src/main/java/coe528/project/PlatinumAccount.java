/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author rayya
 */

public class PlatinumAccount extends Account {
    
     public boolean onlinePurchase(int amount){
    if (this.getBalance() >= amount){
        this.decreaseBalance(amount);
        return true;
    } else {
        return false;
    }
}
    
    public String getLevel(){
        return "You are a Platinum Member!";
    }
    
}