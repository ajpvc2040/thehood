package com.supersoft.thehood.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.supersoft.thehood.dto.UserDTO;
import com.supersoft.thehood.hibernate.util.StringAttributeConverter;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "HoodUser")
public class User {
    @Id
    @Column(name = "userCode")
    private String userCode;

    @Column(name = "parentHoodId_")
    private int hoodId;
    
    @Column(name = "password")
    @Convert(converter = StringAttributeConverter.class)
    private String password;
    
    @Column(name = "admin")
    @Type(type= "org.hibernate.type.NumericBooleanType")
    private boolean admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoodId")
    private Hood hood;

    public User(){
        userCode = "";
        password = "";
        admin = false;
        hood = new Hood();
    }

    public User(UserDTO user){

        userCode = user.getUserCode().toUpperCase();
        password = user.getPassword();
        admin = user.isAdmin();
        hood = null;
    }

    public String validateUser(){
        if(userCode.trim().length()<5)
            return "User has to be more than 5 characters.";
        if(password.trim().length()<8)
            return "Password has to be more than 8 characters.";
        return "OK";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Hood getHood() {
        return hood;
    }

    public void setHood(Hood hood) {
        this.hood = hood;
    }

    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
    }
    
}
