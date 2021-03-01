package com.supersoft.thehood.dto;

import java.util.HashSet;
import java.util.Set;

public class HoodDTO {
    private int hoodId;
    private String name;
    private double balance;
    private Set<HouseDTO> houses;
    private Set<BankDTO> bankEntries;
    private Set<ExpenseDTO> expenses;

    public HoodDTO(){
        this.houses = new HashSet<HouseDTO>();
        this.bankEntries = new HashSet<BankDTO>();
        this.expenses = new HashSet<ExpenseDTO>();
        this.name = "";
        this.balance = 0;
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

    public Set<HouseDTO> getHouses() {
        return houses;
    }

    public void setHouses(Set<HouseDTO> houses) {
        this.houses = houses;
    }

    public Set<BankDTO> getBankEntries() {
        return bankEntries;
    }

    public void setBankEntries(Set<BankDTO> bankEntries) {
        this.bankEntries = bankEntries;
    }

    public Set<ExpenseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<ExpenseDTO> expenses) {
        this.expenses = expenses;
    }

    
}
