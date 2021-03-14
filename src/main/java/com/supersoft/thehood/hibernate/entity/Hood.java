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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supersoft.thehood.dto.BankDTO;
import com.supersoft.thehood.dto.ExpenseDTO;
import com.supersoft.thehood.dto.HoodDTO;
import com.supersoft.thehood.dto.HouseDTO;

@Entity
@Table(name = "Hood")
public class Hood{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hoodId")
    private int hoodId;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private double balance;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hoodId")
    private Set<House> houses;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hoodId")
    private Set<Bank> bankEntries;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hoodId")
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
        this.bankEntries = new HashSet<Bank>();
        this.expenses = new HashSet<Expense>();
        this.houses = new HashSet<House>();
        for(BankDTO bank : hood.getBankEntries())
            this.bankEntries.add(new Bank(bank));
        for(ExpenseDTO expense : hood.getExpenses())
            this.expenses.add(new Expense(expense));
        for(HouseDTO house : hood.getHouses())
            this.houses.add(new House(house));
        this.hoodId = hood.getHoodId();
    }

    public void addExpense(Expense expense){
        this.expenses.add(expense);
        this.balance -= expense.getAmount();
    }

    public void addBank(Credit credit){
        this.bankEntries.add(new Bank(credit));
        this.balance += credit.getAmount();
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
    
    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
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
        res.append("Hood [id=" + this.hoodId + ", name=" + this.name + "]" + System.lineSeparator());
        for(House house : houses)
            res.append(house.toString() + System.lineSeparator());
        for(Bank bankEntry : bankEntries)
            res.append(bankEntry.toString() + System.lineSeparator());
        for(Expense expense : expenses)
            res.append(expense.toString() + System.lineSeparator());
        return res.toString();
    }

    public void setHouses(Set<House> houses) {
        this.houses = houses;
    }

    public void setBankEntries(Set<Bank> bankEntries) {
        this.bankEntries = bankEntries;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }
}
