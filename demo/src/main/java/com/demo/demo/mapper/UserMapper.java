package com.demo.demo.mapper;

import java.util.HashSet;

import com.demo.demo.dto.AccountDto;
import com.demo.demo.dto.UserDto;
import com.demo.demo.models.Account;
import com.demo.demo.models.User;
import com.demo.demo.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountMapper accountMapper;
    
    public UserDto toUserDto(User user){
        if(user == null){
            return null;
        }
        UserDto rUserDto = new UserDto();
        rUserDto.setUserId(user.getUserId());
        rUserDto.setName(user.getName());
        rUserDto.setEmail(user.getEmail());
        rUserDto.setMobileNumber(user.getMobileNumber());
        rUserDto.setSecondaryNumber(user.getSecondaryMobile());
        rUserDto.setGender(user.getGender());
        rUserDto.setDob(user.getDob());
        rUserDto.setAccounts(new HashSet<AccountDto>());
        for(Account userAccount : user.getAccounts()){
            rUserDto.getAccounts().add(this.accountMapper.toAccountDto(userAccount));
        }
        return rUserDto;
    }

}
