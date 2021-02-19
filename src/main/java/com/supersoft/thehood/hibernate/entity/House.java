package com.supersoft.thehood.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "House")
public class House {

    @ManyToOne
    @JoinColumn(name = "hoodId")
    private Hood hood;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "houseId")
    private int id;

    @Column(name = "houseCode")
    private String houseCode;

    @Column(name = "balance")
    private double balance;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private Set<Buddy> buddies;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private Set<Debit> debits;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private Set<Credit> credits;

    public House() {
        houseCode = "";
        balance = 0;
        hood = new Hood();
        buddies = new HashSet<Buddy>();
        debits = new HashSet<Debit>();
        credits = new HashSet<Credit>();
    }

    public House(String houseCode, double balance){
        this.houseCode = houseCode;
        this.balance = balance;
        this.buddies = new HashSet<Buddy>();
        this.debits = new HashSet<Debit>();
        this.credits = new HashSet<Credit>();
    }

    public void addBuddy(Buddy buddy){
        buddy.setHouse(this);
        this.buddies.add(buddy);
    }

    public void addDebit(Debit debit){
        debit.setHouse(this);
        this.debits.add(debit);
    }

    public void addCredit(Credit credit){
        credit.setHouse(this);
        this.credits.add(credit);
    }

    public Hood getHood() {
        return hood;
    }

    public int getId(){
        return id;
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

    public void setHood(Hood hood){
        this.hood = hood;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("House [hoodId=" + hood.getId() + ", id=" + this.id + ", houseCode=" + this.houseCode + ", balance=" + this.balance + "]" + System.lineSeparator());

        for(Buddy buddy : buddies)
            res.append(buddy.toString() + System.lineSeparator());

        for(Debit debit : debits)
            res.append(debit.toString() + System.lineSeparator());

        for(Credit credit : credits)
            res.append(credit.toString() + System.lineSeparator());

        return res.toString();
    }
    
}
