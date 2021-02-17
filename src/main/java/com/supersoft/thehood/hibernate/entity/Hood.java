package com.supersoft.thehood.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Hood")
public class Hood{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public Hood(){

    }

    public Hood(String name){
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return "Hood [id=" + this.id + ", name=" + this.name + "]";
    }
}
