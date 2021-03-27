package com.supersoft.thehood.dto;

import com.supersoft.thehood.hibernate.entity.House;

public class HouseDTO {
    private int hoodId;
    private int houseId;
    private String houseCode;
    private double balance;

    public HouseDTO(){
        houseCode = "";
        balance = 0;
    }

    public HouseDTO(House house){
        this.hoodId = house.getHoodId();
        this.houseId = house.getHouseId();
        this.balance = house.getBalance();
        this.houseCode = house.getHouseCode();
    }

    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}