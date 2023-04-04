package com.demo.demo.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDto {

    long userId;
    @NotEmpty(message = "Name cannot be empty")
    String name;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid format")
    String email;
    @NotEmpty(message = "Mobile number cannot be empty")
    String mobileNumber;
    String secondaryNumber;
    // @JsonFormat(pattern = "dd-MMM-yyyy")
    @NotNull(message = "Mobile number cannot be empty")
    Date dob;
    @NotEmpty(message = "Gender cannot be empty")
    String gender;
    Set<AccountDto> accounts;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
    
    public String getMobileNumber() {
        return mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public String getSecondaryNumber() {
        return secondaryNumber;
    }
    
    public void setSecondaryNumber(String secondaryNumber) {
        this.secondaryNumber = secondaryNumber;
    }
    
    public Date getDob() {
        return dob;
    }
    
    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    public Set<AccountDto> getAccounts() {
        return accounts;
    }
    
    public void setAccounts(Set<AccountDto> accounts) {
        this.accounts = accounts;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
