package com.supersoft.thehood.dto;

import java.util.Calendar;
import java.util.Date;

import com.supersoft.thehood.hibernate.entity.Debit;

public class DebitDTO {
    private int hoodId;
    private int houseId;
    private int debitId;
    private String concept;
    private Date debitDate;
    private double amount;
    private boolean paid;

    public DebitDTO(){
        hoodId = 0;
        houseId = 0;
        debitId = 0;
        concept = "";
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        debitDate = today.getTime();
        amount = 0;
    }

    public DebitDTO(Debit debit){
        this.hoodId = debit.getHoodId();
        this.houseId = debit.getHouseId();
        this.debitId = debit.getDebitId();
        this.concept = debit.getConcept();
        this.debitDate = debit.getDebitDate();
        this.amount = debit.getUnpaidAmount();
        this.paid = debit.isPaid();
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

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Date getDebitDate() {
        return debitDate;
    }

    public void setDebitDate(Date debitDate) {
        this.debitDate = debitDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
    }
    
}
