package com.fallon.banking.models;

public class CheckingAccount extends Account{
    private final String type = "checking";

    public CheckingAccount(double balance, String nickName) {
        super(balance, nickName);

    }

    public CheckingAccount(int id, double balance, String nickName) {
        super(id, balance, nickName);
    }

    public CheckingAccount(){
        super();
    }

    @Override
    public String getType(){
        return type;
    }




}
