package com.supersoft.thehood.hibernate.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.supersoft.thehood.dto.ExpenseDTO;

@Entity
@Table(name = "Expense")
public class Expense {
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
        concept = "";
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        expenseDate = today.getTime();
        amount = 0;
    }

    public Expense(String concept, Date expenseDate, double amount){
        this.concept = concept;
        this.expenseDate = expenseDate;
        this.amount = amount;
    }

    public Expense(ExpenseDTO expense){
        this.concept = expense.getConcept();
        this.expenseDate = expense.getExpenseDate();
        this.amount = expense.getAmount();
        this.id = expense.getId();
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
        return "Expense [expenseId =" + id + ", expenseDate=" + expenseDate.toString() + ", concept=" + concept + ", amount=" + amount + "]";
    }
}
