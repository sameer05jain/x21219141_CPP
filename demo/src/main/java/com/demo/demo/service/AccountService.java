package com.demo.demo.service;

import java.util.List;

import com.demo.demo.dto.AccountDto;
import com.demo.demo.dto.UserDto;
import com.demo.demo.exception.AccountNotFoundException;
import com.demo.demo.exception.UserNotFoundException;

public interface AccountService {
    public UserDto getUserByAccountId(Long accountId) throws AccountNotFoundException, Exception;
    public boolean createAccount(AccountDto accountDto) throws UserNotFoundException, Exception;
    public boolean deleteAccount(Long accountId) throws AccountNotFoundException, Exception;
    public boolean updateAccount(AccountDto accountDto, Long accountId) throws UserNotFoundException, AccountNotFoundException, Exception;
    public List<AccountDto> getAllAccounts() throws Exception;
}
