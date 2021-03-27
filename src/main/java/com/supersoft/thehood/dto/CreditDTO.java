package com.supersoft.thehood.dto;

import java.util.Calendar;
import java.util.Date;

import com.supersoft.thehood.hibernate.entity.Credit;

public class CreditDTO {
    private int hoodId;
    private int houseId;
    private int creditId;
    private int debitId;
    private String concept;
    private Date creditDate;
    private double amount;

    public CreditDTO(){
        hoodId = 0;
        houseId = 0;
        debitId = 0;
        creditId = 0;
        concept = "";
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        creditDate = today.getTime();
        amount = 0;
    }

    public CreditDTO(Credit credit){
        this.hoodId = credit.getHoodId();
        this.houseId = credit.getHouseId();
        this.debitId = credit.getDebitId();
        this.creditId = credit.getCreditId();
        this.concept = credit.getConcept();
        this.creditDate = credit.getCreditDate();
        this.amount = credit.getAmount();
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Date getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(Date creditDate) {
        this.creditDate = creditDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
    }

    public int getDebitId() {
        return debitId;
    }

    public void setDebitId(int debitId) {
        this.debitId = debitId;
    }
}
