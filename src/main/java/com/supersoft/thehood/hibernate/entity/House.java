package com.supersoft.thehood.hibernate.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.supersoft.thehood.dto.BuddyDTO;
import com.supersoft.thehood.dto.CreditDTO;
import com.supersoft.thehood.dto.DebitDTO;
import com.supersoft.thehood.dto.HouseDTO;

@Entity
@Table(name = "House")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "houseId")
    private int houseId;

    @Column(name = "houseCode")
    private String houseCode;

    @Column(name = "balance")
    private double balance;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "houseId")
    private Set<Buddy> buddies;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "houseId")
    private Set<Debit> debits;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "houseId")
    private Set<Credit> credits;

    public House() {
        houseCode = "";
        balance = 0;
        buddies = new HashSet<Buddy>();
        debits = new HashSet<Debit>();
        credits = new HashSet<Credit>();
    }

    public House(String houseCode, double balance){
        this.houseCode = houseCode;
        this.balance = balance;
        this.buddies = new HashSet<Buddy>();
        this.debits = new HashSet<Debit>();
        this.credits = new HashSet<Credit>();
    }

    public House(HouseDTO house){
        this.houseId = house.getHouseId();
        this.houseCode = house.getHouseCode();
        this.balance = house.getBalance();
        this.buddies = new HashSet<Buddy>();
        this.debits = new HashSet<Debit>();
        this.credits = new HashSet<Credit>();
        for(BuddyDTO buddy : house.getBuddies())
            this.buddies.add(new Buddy(buddy));
        for(DebitDTO debit : house.getDebits())
            this.debits.add(new Debit(debit));
        for(CreditDTO credit : house.getCredits())
            this.credits.add(new Credit(credit));
    }

    public void addDebit(Debit debit){
        this.debits.add(debit);
        this.balance -= debit.getAmount();
    }

    public void addCredit(Credit credit){
        this.credits.add(credit);
        this.balance += credit.getAmount();
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public Set<Buddy> getBuddies(){
        return this.buddies;
    }

    public String getHouseCode(){
        return houseCode;
    }

    public void setHouseCode(String houseCode){
        this.houseCode = houseCode;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("House [id=" + this.houseId + ", houseCode=" + this.houseCode + ", balance=" + this.balance + "]" + System.lineSeparator());

        for(Buddy buddy : buddies)
            res.append(buddy.toString() + System.lineSeparator());

        for(Debit debit : debits)
            res.append(debit.toString() + System.lineSeparator());

        for(Credit credit : credits)
            res.append(credit.toString() + System.lineSeparator());

        return res.toString();
    }

    public void setBuddies(Set<Buddy> buddies) {
        this.buddies = buddies;
    }

    public Set<Debit> getDebits() {
        return debits;
    }

    public void setDebits(Set<Debit> debits) {
        this.debits = debits;
    }

    public Set<Credit> getCredits() {
        return credits;
    }

    public void setCredits(Set<Credit> credits) {
        this.credits = credits;
    }
    
}
