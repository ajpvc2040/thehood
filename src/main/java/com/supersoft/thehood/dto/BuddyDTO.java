package com.supersoft.thehood.dto;

public class BuddyDTO {
    private HouseDTO house;
    private int id;
    private String name;
    private String email;
    private String phone;

    public BuddyDTO(){
        house = new HouseDTO();
        name = "";
        email = "";
        phone = "";
    }

    public HouseDTO getHouse() {
        return house;
    }

    public void setHouse(HouseDTO house) {
        this.house = house;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
