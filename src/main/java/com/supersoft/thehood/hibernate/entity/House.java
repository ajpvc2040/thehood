package com.supersoft.thehood.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supersoft.thehood.dto.HouseDTO;

@Entity
@Table(name = "House")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "houseId")
    private int houseId;

    @Column(name = "hoodId_")
    private int hoodId;

    @Column(name = "houseCode")
    private String houseCode;

    @Column(name = "balance")
    private double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoodId")
    private Hood hood;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "houseId")
    private Set<Buddy> buddies;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "houseId")
    private Set<Debit> debits;

    public House() {
        houseCode = "";
        balance = 0;
        buddies = new HashSet<Buddy>();
        debits = new HashSet<Debit>();
        hood = new Hood();
    }

    public House(String houseCode, double balance){
        this.houseCode = houseCode;
        this.balance = balance;
        this.buddies = new HashSet<Buddy>();
        this.debits = new HashSet<Debit>();
        this.hood = new Hood();
    }

    public House(HouseDTO house){
        this.houseId = house.getHouseId();
        this.houseCode = house.getHouseCode();
        this.balance = house.getBalance();
        this.buddies = new HashSet<Buddy>();
        this.debits = new HashSet<Debit>();
        this.hood = null;
    }

    public void addDebit(Debit debit){
        debit.setHoodId(this.hoodId);
        debit.setHouseId(this.houseId);
        this.debits.add(debit);
        this.balance -= debit.getAmount();
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public void addBuddy(Buddy buddy){
        buddy.setHoodId(this.hoodId);
        buddy.setHouseId(this.houseId);
        this.buddies.add(buddy);
    }

    public String getHouseCode(){
        return houseCode;
    }

    public void setHouseCode(String houseCode){
        this.houseCode = houseCode;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("House [id=" + this.houseId + ", houseCode=" + this.houseCode + ", balance=" + this.balance + "]" + System.lineSeparator());

        for(Buddy buddy : buddies)
            res.append(buddy.toString() + System.lineSeparator());

        for(Debit debit : debits)
            res.append(debit.toString() + System.lineSeparator());

        return res.toString();
    }

    public void setBuddies(Set<Buddy> buddies) {
        this.buddies = buddies;
    }

    public void setDebits(Set<Debit> debits) {
        this.debits = debits;
    }

    public Hood getHood() {
        return hood;
    }

    public void setHood(Hood hood) {
        this.hood = hood;
    }

    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
    }
    
}
