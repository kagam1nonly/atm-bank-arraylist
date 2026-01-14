package com.kagami.atm;

import java.io.Serializable;

public class User implements Serializable {
    private String fullName;
    private String userName;
    private int pin;
    private double balance;

    public User(String fullName, String userName, int pin, double balance) {
        this.fullName = fullName;
        this.userName = userName;
        this.pin = pin;
        this.balance = balance;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
