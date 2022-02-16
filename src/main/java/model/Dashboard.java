/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zahro
 */
public class Dashboard {
    
    int sum_income,balance,sum_outcome;
    
    public Dashboard(int sum_income, int balance, int sum_outcome) {
        this.sum_income = sum_income;
        this.balance = balance;
        this.sum_outcome = sum_outcome;
    }

    public int getSum_income() {
        return sum_income;
    }

    public void setSum_income(int sum_income) {
        this.sum_income = sum_income;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getSum_outcome() {
        return sum_outcome;
    }

    public void setSum_outcome(int sum_outcome) {
        this.sum_outcome = sum_outcome;
    }
    
    
    
}
