package com.supersoft.thehood.dto;

import java.util.Calendar;
import java.util.Date;

public class ExpenseDTO {
    private HoodDTO hood;
    private int id;
    private String concept;
    private Date expenseDate;
    private double amount;

    public ExpenseDTO(){
        hood = new HoodDTO();
        concept = "";
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        expenseDate = today.getTime();
        amount = 0;
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
