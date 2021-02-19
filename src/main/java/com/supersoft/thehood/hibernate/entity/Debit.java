package com.supersoft.thehood.hibernate.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Debit")
public class Debit {
    @ManyToOne
    @JoinColumn(name = "houseId")
    private House house;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debitId")
    private int id;

    @Column(name = "concept")
    private String concept;

    @Column(name = "dabitDate")
    private Date debitDate;

    @Column(name = "amount")
    private double amount;

    public Debit(){
        house = new House();
        concept = "";
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        debitDate = today.getTime();
        amount = 0;
    }

    public Debit(String concept, Date debitDate, double amount){
        this.concept = concept ;
        this.debitDate = debitDate;
        this.amount = amount;
    }

    public void setHouse(House house){
        this.house = house;
    }

    public House getHouse(){
        return this.house;
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

    @Override
    public String toString(){
        return "Debit [houseId=" + house.getId() + ", id=" + id + ", debitDate=" + debitDate.toString() + ", concept=" + concept + ", amount=" + amount + "]";
    }

}
