package com.supersoft.thehood.hibernate.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.supersoft.thehood.dto.DebitDTO;

@Entity
@Table(name = "Debit")
public class Debit {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debitId")
    private int debitId;

    @Column(name = "concept")
    private String concept;

    @Column(name = "dabitDate")
    private Date debitDate;

    @Column(name = "amount")
    private double amount;

    @Column(name = "paid")
    private boolean paid;

    public Debit(){
        concept = "";
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        debitDate = today.getTime();
        amount = 0;
        paid = false;
    }

    public Debit(String concept, Date debitDate, double amount){
        this.concept = concept ;
        this.debitDate = debitDate;
        this.amount = amount;
        this.paid = false;
    }

    public Debit(Debit debit){
        this.amount = debit.amount;
        this.concept = debit.concept;
        this.debitDate = debit.debitDate;
        this.paid = debit.paid;
    }

    public Debit(DebitDTO debit){
        this.concept = debit.getConcept();
        this.debitDate = debit.getDebitDate();
        this.amount = debit.getAmount();
        this.debitId = debit.getDebitId();
        this.paid = debit.isPaid();
    }

    public int getDebitId() {
        return debitId;
    }

    public void setDebitId(int debitId) {
        this.debitId = debitId;
    }

    public void setConcept(String concept){
        this.concept = concept;
    }

    public String getConcept(){
        return this.concept;
    }

    public void setDebitDate(Date debitDate){
        this.debitDate = debitDate;
    }

    public Date getDebitDate(){
        return debitDate;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString(){
        return "Debit [id=" + debitId + ", debitDate=" + debitDate.toString() + ", concept=" + concept + ", amount=" + amount + "]";
    }

    public double getAmount() {
        return amount;
    }

}
