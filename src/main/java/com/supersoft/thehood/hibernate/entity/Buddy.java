package com.supersoft.thehood.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.supersoft.thehood.dto.BuddyDTO;

@Entity
@Table(name = "Buddy")
public class Buddy{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buddyId")
    private int buddyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseId")
    private House house;

    @Column(name = "parentHoodId_")
    private int hoodId;

    @Column(name = "parentHouseId_")
    private int houseId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    public Buddy(){
        name = "";
        email = "";
        phone = "";
    }

    public Buddy(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Buddy(BuddyDTO buddy){
        this.buddyId = buddy.getBuddyId();
        this.name = buddy.getName();
        this.email = buddy.getEmail();
        this.phone = buddy.getPhone();
    }

    public int getBuddyId() {
        return buddyId;
    }

    public void setBuddyId(int buddyId) {
        this.buddyId = buddyId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return this.phone;
    }

    @Override
    public String toString(){
        return "Buddy [buddyId=" + buddyId + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
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