package com.supersoft.thehood.hibernate.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supersoft.thehood.dto.DebitDTO;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Debit")
public class Debit {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debitId")
    private int debitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseId")
    private House house;

    @Column(name = "parentHoodId_")
    private int hoodId;

    @Column(name = "parentHouseId_")
    private int houseId;

    @Column(name = "concept")
    private String concept;

    @Column(name = "debitDate")
    private Date debitDate;

    @Column(name = "amount")
    private double amount;

    @Column(name = "paid")
    @Type(type= "org.hibernate.type.NumericBooleanType")
    private Boolean paid;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "debitId")
    private Set<Credit> credits;

    public Debit(){
        concept = "";
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        debitDate = today.getTime();
        amount = 0;
        paid = false;
        credits = new HashSet<Credit>();
    }

    public Debit(String concept, Date debitDate, double amount){
        this.concept = concept ;
        this.debitDate = debitDate;
        this.amount = amount;
        this.paid = false;
        credits = new HashSet<Credit>();
    }

    public Debit(Debit debit){
        this.amount = debit.amount;
        this.concept = debit.concept;
        this.debitDate = debit.debitDate;
        this.paid = debit.paid;
        credits = new HashSet<Credit>();
    }

    public Debit(DebitDTO debit){
        this.concept = debit.getConcept();
        this.debitDate = debit.getDebitDate();
        this.amount = debit.getAmount();
        this.debitId = debit.getDebitId();
        this.paid = debit.isPaid();
        credits = new HashSet<Credit>();
    }

    public void loadLazyCredits(){
        this.credits.size();
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

    public double getUnpaidAmount() {
        double tempAmount = this.amount;
        for(Credit credit : credits)
            tempAmount -= credit.getAmount();
        return tempAmount;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public double getAmount() {
        return amount;
    }

    public void addCredit(Credit credit){
        credit.setHoodId(this.hoodId);
        credit.setHouseId(this.houseId);
        credit.setDebitId(this.debitId);
        this.credits.add(credit);
    }

    public void setCredits(Set<Credit> credits) {
        this.credits = credits;
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

}
