package com.supersoft.thehood.hibernate.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Expense")
public class Expense {
    
    @ManyToOne
    @JoinColumn(name = "hoodId")
    private Hood hood;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expenseId")
    private int id;

    @Column(name = "concept")
    private String concept;

    @Column(name = "expenseDate")
    private Date expenseDate;

    @Column(name = "amount")
    private double amount;

    public Expense(){
        hood = new Hood();
        concept = "";
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        expenseDate = today.getTime();
        amount = 0;
    }

    public Expense(String concept, Date expenseDate, double amount){
        this.hood = new Hood();
        this.concept = concept;
        this.expenseDate = expenseDate;
        this.amount = amount;
    }

    public Hood getHood() {
        return hood;
    }

    public void setHood(Hood hood) {
        this.hood = hood;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense [hoodId=" + hood.getId() + ", expenseId =" + id + ", expenseDate=" + expenseDate.toString() + ", concept=" + concept + ", amount=" + amount + "]";
    }
}
