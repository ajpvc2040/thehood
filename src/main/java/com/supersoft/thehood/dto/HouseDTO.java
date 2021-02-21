package com.supersoft.thehood.dto;

import java.util.HashSet;
import java.util.Set;

public class HouseDTO {
    private HoodDTO hood;
    private int id;
    private String houseCode;
    private double balance;
    private Set<BuddyDTO> buddies;
    private Set<DebitDTO> debits;
    private Set<CreditDTO> credits;

    public HouseDTO(){
        houseCode = "";
        balance = 0;
        hood = new HoodDTO();
        buddies = new HashSet<BuddyDTO>();
        debits = new HashSet<DebitDTO>();
        credits = new HashSet<CreditDTO>();
    }

    public HoodDTO getHood() {
        return hood;
    }

    public void setHood(HoodDTO hood) {
        this.hood = hood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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