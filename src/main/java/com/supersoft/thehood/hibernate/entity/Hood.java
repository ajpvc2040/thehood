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

import com.supersoft.thehood.dto.HoodDTO;

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

    public Hood(HoodDTO hood){
        this.name = hood.getName();
        this.balance = hood.getBalance();
        this.bankEntries = hood.getBankEntries();
        this.expenses = hood.getExpenses();
        this.houses = hood.getHouses();
        this.id = hood.getId();
    }

    public Set<House> getHouses() {
        return houses;
    }

    public Set<Bank> getBankEntries() {
        return bankEntries;
    }

    public Set<Expense> getExpenses() {
        return expenses;
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
