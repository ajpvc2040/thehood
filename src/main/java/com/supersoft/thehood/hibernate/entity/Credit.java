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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Credit")
public class Credit {

    @ManyToOne
    @JoinColumn(name = "houseId")
    private House house;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creditId")
    private int id;

    @Column(name = "concept")
    private String concept;

    @Column(name = "creditDate")
    private Date creditDate;

    @Column(name = "amount")
    private double amount;

    public Credit(){
        house = new House();
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

    public int getId() {
        return id;
    }

    public void setHouse(House house){
        this.house = house;
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

    public double getAmoung(){
        return this.amount;
    }

    @Override
    public String toString(){
        return "Credit [houseId=" + house.getId() + ", creditId=" + id + ", concept=" + concept + ", creditDate=" + creditDate.toString() + ", amount=" + amount + "]";
    }
    
}
