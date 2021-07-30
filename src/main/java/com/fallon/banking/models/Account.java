package com.fallon.banking.models;

public abstract class Account {
    private int id;
    private double balance;
    private String nickName;

    public Account(double balance, String nickName) {
        this.balance = balance;
        this.nickName = nickName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNickName() {
        return nickName;
    }

    public abstract String getType();

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", nickName='" + nickName +
                ", type='" + this.getType() +'\'' +
                '}';
    }
}
