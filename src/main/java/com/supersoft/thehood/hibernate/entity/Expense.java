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
    private int expenseId;

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
        this.expenseId = expense.getExpenseId();
        this.concept = expense.getConcept();
        this.expenseDate = expense.getExpenseDate();
        this.amount = expense.getAmount();
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
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
        return "Expense [expenseId =" + expenseId + ", expenseDate=" + expenseDate.toString() + ", concept=" + concept + ", amount=" + amount + "]";
    }
}
