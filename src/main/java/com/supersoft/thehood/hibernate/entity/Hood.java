package com.supersoft.thehood.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Hood")
public class Hood{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hoodId")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private double balance;

    @OneToMany(mappedBy = "hood", cascade = CascadeType.ALL)
    private Set<House> houses;

    @OneToMany(mappedBy = "hood", cascade = CascadeType.ALL)
    private Set<Bank> bankEntries;

    @OneToMany(mappedBy = "hood", cascade = CascadeType.ALL)
    private Set<Expense> expenses;

    public Hood(){
        this.houses = new HashSet<House>();
        this.bankEntries = new HashSet<Bank>();
        this.expenses = new HashSet<Expense>();
        this.name = "";
        this.balance = 0;
    }

    public Hood(String name){
        this.name = name;
        this.houses = new HashSet<House>();
        this.bankEntries = new HashSet<Bank>();
        this.expenses = new HashSet<Expense>();
        this.balance = 0;
    }

    public void addHouse(House house){
        house.setHood(this);
        this.houses.add(house);
    }

    public void addBankEntry(Bank bankEntry){
        bankEntry.setHood(this);
        bankEntries.add(bankEntry);
    }

    public void addExpense(Expense expense){
        expense.setHood(this);
        expenses.add(expense);
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Hood [id=" + this.id + ", name=" + this.name + "]" + System.lineSeparator());
        for(House house : houses)
            res.append(house.toString() + System.lineSeparator());
        for(Bank bankEntry : bankEntries)
            res.append(bankEntry.toString() + System.lineSeparator());
        for(Expense expense : expenses)
            res.append(expense.toString() + System.lineSeparator());
        return res.toString();
    }
}
