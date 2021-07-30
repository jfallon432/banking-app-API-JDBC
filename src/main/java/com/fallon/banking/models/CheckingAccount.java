package com.fallon.banking.models;

public class CheckingAccount extends Account{
    private final String type = "checking";

    public CheckingAccount(double balance, String nickName) {
        super(balance, nickName);

    }




    @Override
    public String getType(){
        return type;
    }


}
