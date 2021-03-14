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

    @Column(name = "concept")
    private String concept;

    @Column(name = "creditDate")
    private Date creditDate;

    @Column(name = "amount")
    private double amount;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "creditId")
    private Expense expense;

    public Credit(){
        concept = "";
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        creditDate = today.getTime();
        amount = 0;
        expense = new Expense();
    }

    public Credit(String concept, Date creditDate, double amount){
        this.concept = concept;
        this.creditDate = creditDate;
        this.amount = amount;
        this.expense = new Expense (concept, creditDate, amount);
    }

    public Credit(CreditDTO credit){
        this.amount = credit.getAmount();
        this.concept = credit.getConcept();
        this.creditDate = credit.getCreditDate();
        this.creditId = credit.getCreditId();
        this.expense = new Expense();
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
    
}
