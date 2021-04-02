package com.supersoft.thehood.hibernate.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.supersoft.thehood.dto.CreditDTO;

@Entity
@Table(name = "Credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creditId")
    private int creditId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debitId")
    private Debit debit;

    @Column(name = "parentHoodId_")
    private int hoodId;

    @Column(name = "parentHouseId_")
    private int houseId;

    @Column(name = "parentDebitId_")
    private int debitId;

    @Column(name = "concept")
    private String concept;

    @Column(name = "creditDate")
    private Date creditDate;

    @Column(name = "amount")
    private double amount;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "creditId")
    private Bank bank;

    public Credit(){
        concept = "";
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        creditDate = today.getTime();
        amount = 0;
    }

    public Credit(String concept, Date creditDate, double amount){
        this.concept = concept;
        this.creditDate = creditDate;
        this.amount = amount;
    }

    public Credit(CreditDTO credit){
        this.amount = credit.getAmount();
        this.concept = credit.getConcept();
        this.creditDate = credit.getCreditDate();
        this.creditId = credit.getCreditId();
        this.bank = new Bank(this);
    }

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public void setConcept(String concept){
        this.concept = concept;
    }

    public String getConcept(){
        return this.concept;
    }

    public void setCreditDate(Date creditDate){
        this.creditDate = creditDate;
    }

    public Date getCreditDate(){
        return this.creditDate;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return this.amount;
    }

    @Override
    public String toString(){
        return "Credit [creditId=" + creditId + ", concept=" + concept + ", creditDate=" + creditDate.toString() + ", amount=" + amount + "]";
    }

    public Debit getDebit() {
        return debit;
    }

    public void setDebit(Debit debit) {
        this.debit = debit;
    }

    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
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

    public void addBank(Bank bank){
        bank.setHoodId(this.hoodId);
        bank.setHouseId(this.houseId);
        bank.setDebitId(this.debitId);
        bank.setCreditId(this.creditId);
        this.bank = bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void loadLazyBank(){
        this.bank.getBankId();
    }
    
}
