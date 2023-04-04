package com.demo.demo.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Account {
    
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "account_sequence"),
        @Parameter(name = "initial_value", value = "1000000000"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
    private long accountId;
    @ManyToOne
    @NotNull(message = "User must be tagged to account")
    private User user;
    @Column(length = 40)
    @NotBlank(message = "Branch name cannot be empty")
    private String branchName;
    @Column(length = 1)
    @NotBlank(message = "Account type cannot be empty")
    private String accountType;
    @Column(precision = 10, scale = 2)
    @NotNull(message = "Account balance cannot be empty")
    private BigDecimal accountBalance;
    
    public Account() {
    }

    public long getAccountId() {
      return accountId;
    }

    public void setAccountId(long accountId) {
      this.accountId = accountId;
    }

    public User getUser() {
      return user;
    }

    public void setUser(User user) {
      this.user = user;
    }

    public String getBranchName() {
      return branchName;
    }

    public void setBranchName(String branchName) {
      this.branchName = branchName;
    }

    public String getAccountType() {
      return accountType;
    }

    public void setAccountType(String accountType) {
      this.accountType = accountType;
    }

    public BigDecimal getAccountBalance() {
      return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
      this.accountBalance = accountBalance;
    }

    
    

}
