package com.supersoft.thehood.dto;

import com.supersoft.thehood.hibernate.entity.Buddy;

public class BuddyDTO {
    
    private int hoodId;
    private int houseId;
    private int buddyId;
    private String name;
    private String email;
    private String phone;

    public BuddyDTO(){
        hoodId = 0;
        houseId = 0;
        buddyId = 0;
        name = "";
        email = "";
        phone = "";
    }

    public BuddyDTO(Buddy buddy){
        this.hoodId = buddy.getHoodId();
        this.houseId = buddy.getHouseId();
        this.buddyId = buddy.getBuddyId();
        this.name = buddy.getName();
        this.email = buddy.getEmail();
        this.phone = buddy.getPhone();
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public int getBuddyId() {
        return buddyId;
    }

    public void setBuddyId(int buddyId) {
        this.buddyId = buddyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
    }
}
