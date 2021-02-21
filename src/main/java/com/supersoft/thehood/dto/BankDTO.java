package com.supersoft.thehood.dto;

import java.util.Calendar;
import java.util.Date;

import com.supersoft.thehood.hibernate.entity.Credit;
import com.supersoft.thehood.hibernate.entity.Hood;

public class BankDTO {

    private HoodDTO hood;
    private CreditDTO credit;
    private int id;
    private Date incomeDate;
    private String concept;
    private double amount;
    

    public BankDTO(){
        hood = new HoodDTO();
        credit = new CreditDTO();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        incomeDate = today.getTime();
        concept = "";
        amount = 0;
    }

    public HoodDTO getHood() {
        return hood;
    }

    public void setHood(HoodDTO hood) {
        this.hood = hood;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public CreditDTO getCredit() {
        return credit;
    }

    public void setCredit(CreditDTO credit) {
        this.credit = credit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }
}
