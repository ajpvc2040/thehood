package com.supersoft.thehood.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ibm.icu.util.Calendar;
import com.supersoft.thehood.dto.BankDTO;


@Entity
@Table(name = "Bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankId")
    private int bankId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creditId")
    private Credit credit;

    @Column(name = "hoodId_")
    private int hoodId;

    @Column(name = "houseId_")
    private int houseId;

    @Column(name = "debitId_")
    private int debitId;

    @Column(name = "creditId_")
    private int creditId;

    @Column(name = "incomeDate")
    private Date incomeDate;

    @Column(name = "concept")
    private String concept;

    @Column(name = "amount")
    private double amount;

    public Bank(){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        incomeDate = today.getTime();
        concept = "";
        amount = 0;
    }

    public Bank(Credit credit){
        this.concept = credit.getConcept();
        this.incomeDate = credit.getCreditDate();
        this.amount = credit.getAmount();
        this.creditId = credit.getCreditId();
    }

    public Bank(BankDTO bank){
        this.bankId = bank.getBankId();
        this.creditId = bank.getCreditId();
        this.amount = bank.getAmount();
        this.concept = bank.getConcept();
        this.incomeDate = bank.getIncomeDate();
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    @Override
    public String toString() {
        return "Bank [creditId=" + creditId + ", id=" + bankId + ", incomeDate=" + incomeDate + ", concept=" + concept + ", amount=" + amount + "]";
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
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

}
