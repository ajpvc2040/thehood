package com.supersoft.thehood.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class User {

    
    private String userCode;

    
    private String password;

    
    private boolean admin;

    private Set<Screen> allowedScreens;

    public User(){
        userCode = "";
        password = "";
        admin = false;
        allowedScreens = new HashSet<Screen>();
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

    public Set<Screen> getAllowedScreens() {
        return allowedScreens;
    }
    
    public void setAllowedScreens(Set<Screen> allowedScreens) {
        this.allowedScreens = allowedScreens;
    }
    
}
