package com.supersoft.thehood.dto;

import java.util.HashSet;
import java.util.Set;

public class HouseDTO {
    private int hoodId;
    private int houseId;
    private String houseCode;
    private double balance;
    private Set<BuddyDTO> buddies;
    private Set<DebitDTO> debits;
    private Set<CreditDTO> credits;

    public HouseDTO(){
        houseCode = "";
        balance = 0;
        buddies = new HashSet<BuddyDTO>();
        debits = new HashSet<DebitDTO>();
        credits = new HashSet<CreditDTO>();
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

    public Set<BuddyDTO> getBuddies() {
        return buddies;
    }

    public void setBuddies(Set<BuddyDTO> buddies) {
        this.buddies = buddies;
    }

    public Set<DebitDTO> getDebits() {
        return debits;
    }

    public void setDebits(Set<DebitDTO> debits) {
        this.debits = debits;
    }

    public Set<CreditDTO> getCredits() {
        return credits;
    }

    public void setCredits(Set<CreditDTO> credits) {
        this.credits = credits;
    }
}