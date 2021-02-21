package com.supersoft.thehood.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @OneToOne
    @JoinColumn(name = "creditId")
    private Credit credit;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankId")
    private int id;

    @Column(name = "incomeDate")
    private Date incomeDate;

    @Column(name = "concept")
    private String concept;

    @Column(name = "amount")
    private double amount;

    public Bank(){
        credit = new Credit();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        incomeDate = today.getTime();
        concept = "";
        amount = 0;
    }

    public Bank(String concept, Date incomeDate, double amount, Credit credit){
        this.concept = concept;
        this.incomeDate = incomeDate;
        this.amount = amount;
        this.credit = credit;
    }

    public Bank(BankDTO bank){
        this.amount = bank.getAmount();
        this.concept = bank.getConcept();
        this.credit = new Credit(bank.getCredit());
        this.id = bank.getId();
        this.incomeDate = bank.getIncomeDate();
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public int getId() {
        return id;
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
        return "Bank [creditId=" + credit.getId() + ", id=" + id + ", incomeDate=" + incomeDate + ", concept=" + concept + ", amount=" + amount + "]";
    }

}
