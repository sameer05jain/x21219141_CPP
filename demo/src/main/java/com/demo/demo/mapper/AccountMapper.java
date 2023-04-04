package com.demo.demo.mapper;

import com.demo.demo.dto.AccountDto;
import com.demo.demo.models.Account;

import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    
    public AccountDto toAccountDto(Account account){
        if(account == null){
            return null;
        }
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(account.getAccountId());
        accountDto.setBranchName(account.getBranchName());
        accountDto.setAccountBalance(account.getAccountBalance());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setUserId(account.getUser().getUserId());
        return accountDto;
    }

}
