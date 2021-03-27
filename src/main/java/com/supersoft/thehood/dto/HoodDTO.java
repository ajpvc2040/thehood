package com.supersoft.thehood.dto;

import com.supersoft.thehood.hibernate.entity.Hood;

public class HoodDTO {
    private int hoodId;
    private String name;
    private double balance;

    public HoodDTO(){
        this.hoodId = 0;
        this.name = "";
        this.balance = 0;
    }

    public HoodDTO(Hood hood){
        this.hoodId = hood.getHoodId();
        this.balance = hood.getBalance();
        this.name = hood.getName();
    }

    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
}
