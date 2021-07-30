package com.fallon.banking.models;

public class SavingsAccount extends Account{

    private final String type = "savings";

    public SavingsAccount(double balance, String nickName) {
        super(balance, nickName);

    }




    @Override
    public String getType(){
        return type;
    }

}
