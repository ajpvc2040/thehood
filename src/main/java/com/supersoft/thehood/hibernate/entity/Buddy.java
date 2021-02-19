package com.supersoft.thehood.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Buddy")
public class Buddy{

    @ManyToOne
    @JoinColumn(name = "HouseId")
    private House house;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buddyId")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    public Buddy(){
        house = new House();
        name = "";
        email = "";
        phone = "";
    }

    public Buddy(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.house = new House();
    }

    public void setHouse(House house){
        this.house = house;
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
        return "Buddy [houseId=" + house.getId() + ", buddyId=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
    }

}