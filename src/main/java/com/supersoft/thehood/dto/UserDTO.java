package com.supersoft.thehood.dto;

import com.supersoft.thehood.hibernate.entity.User;

public class UserDTO {

    private int hoodId;
    private String userCode;
    private String password;
    private boolean admin;

    public UserDTO(){
        userCode = "";
        password = "";
        admin = false;
    }

    public UserDTO(User user){
        this.hoodId = user.getHoodId();
        this.userCode = user.getUserCode();
        this.password = user.getPassword();
        this.admin = user.isAdmin();
    }

    public int getHoodId() {
        return hoodId;
    }

    public void setHoodId(int hoodId) {
        this.hoodId = hoodId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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
}
