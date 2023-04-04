package com.demo.demo.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {
    
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "user_sequence"),
        @Parameter(name = "initial_value", value = "10000"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
    private long userId;
    @Column(length = 40)
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @Column(length = 100)
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid format")
    private String email;
    @Column(length = 10)
    @NotEmpty(message = "Mobile number cannot be empty")
    private String mobileNumber;
    @Column(length = 10)
    private String secondaryMobile;
    @DateTimeFormat(pattern = "dd-MMM-yyyy")
    @NotNull(message = "Date of birth cannot be empty")
    private Date dob;
    @Column(length = 1)
    @NotEmpty(message = "Gender cannot be empty")
    private String gender;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts;
    //Remove
    public User(String name) {
        this.name = name;   
    }

    public User(){}

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

    public String getSecondaryMobile() {
        return secondaryMobile;
    }

    public void setSecondaryMobile(String secondaryMobile) {
        this.secondaryMobile = secondaryMobile;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
    
}
