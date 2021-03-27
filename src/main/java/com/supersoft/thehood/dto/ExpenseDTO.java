package com.supersoft.thehood.dto;

import java.util.Calendar;
import java.util.Date;

import com.supersoft.thehood.hibernate.entity.Expense;

public class ExpenseDTO {
    private int hoodId;
    private int expenseId;
    private String concept;
    private Date expenseDate;
    private double amount;

    public ExpenseDTO(){
        hoodId = 0;
        expenseId = 0;
        concept = "";
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        expenseDate = today.getTime();
        amount = 0;
    }

    public ExpenseDTO(Expense expense){
        this.hoodId = expense.getHoodId();
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

    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
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
    
}
