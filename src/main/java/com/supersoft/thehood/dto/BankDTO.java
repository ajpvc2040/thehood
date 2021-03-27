package com.supersoft.thehood.dto;

import java.util.Calendar;
import java.util.Date;

import com.supersoft.thehood.hibernate.entity.Bank;

public class BankDTO {

    private int hoodId;
    private int houseId;
    private int debitId;
    private int creditId;
    private int bankId;
    private Date incomeDate;
    private String concept;
    private double amount;
    

    public BankDTO(){
        hoodId = 0;
        houseId = 0;
        debitId = 0;
        creditId = 0;
        bankId = 0;
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        incomeDate = today.getTime();
        concept = "";
        amount = 0;
    }

    public BankDTO(Bank bank){
        this.hoodId = bank.getHoodId();
        this.houseId = bank.getHouseId();
        this.debitId = bank.getDebitId();
        this.creditId = bank.getCreditId();
        this.bankId = bank.getBankId();
        this.incomeDate = bank.getIncomeDate();
        this.concept = bank.getConcept();
        this.amount = bank.getAmount();
    }

    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
    }

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public double getAmount() {
        return amount;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getBankId() {
        return bankId;
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

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public int getDebitId() {
        return debitId;
    }

    public void setDebitId(int debitId) {
        this.debitId = debitId;
    }
}
