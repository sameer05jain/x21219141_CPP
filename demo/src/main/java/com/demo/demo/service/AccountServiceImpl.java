package com.demo.demo.service;

import java.util.ArrayList;
import java.util.List;



import com.demo.demo.dto.AccountDto;
import com.demo.demo.dto.UserDto;
import com.demo.demo.exception.AccountNotFoundException;
import com.demo.demo.exception.UserNotFoundException;
import com.demo.demo.mapper.AccountMapper;
import com.demo.demo.mapper.UserMapper;
import com.demo.demo.models.Account;
import com.demo.demo.models.User;
import com.demo.demo.repository.AccountRepository;
import com.demo.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean createAccount(AccountDto accountDto) throws UserNotFoundException, Exception {
        // TODO Auto-generated method stub
        if(accountDto == null){
            throw new Exception();
        }
        Account account = new Account();
        account.setBranchName(accountDto.getBranchName());
        account.setAccountType(accountDto.getAccountType());
        account.setAccountBalance(accountDto.getAccountBalance());
        User user = this.userRepository.findById(accountDto.getUserId()).orElseThrow(() -> new UserNotFoundException("Error"));
        account.setUser(user);
        user.getAccounts().add(account);
        this.userRepository.save(user);
        // this.accountRepository.save(account);
        return true;
    }

    @Override
    public UserDto getUserByAccountId(Long accountId) throws AccountNotFoundException, Exception {
        // TODO Auto-generated method stub
        if(accountId == null){
            throw new Exception();
        }
        Account account = this.accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Error"));
        UserDto userDto = this.userMapper.toUserDto(account.getUser());
        return userDto;
    }

    @Override
    public boolean deleteAccount(Long accountId) throws AccountNotFoundException, Exception {
        // TODO Auto-generated method stub
        if(accountId == null){
            throw new Exception();
        }
        Account account = this.accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Error"));
        User user = this.userRepository.findById(account.getUser().getUserId()).orElseThrow(() -> new UserNotFoundException("Error"));
        user.getAccounts().remove(account);
        this.userRepository.save(user);
        return true;
    }

    @Override
    public boolean updateAccount(AccountDto accountDto, Long accountId) throws UserNotFoundException, AccountNotFoundException, Exception {
        // TODO Auto-generated method stub
        if(accountDto == null || accountId == null){
            throw new Exception();
        }
        Account account = this.accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Error"));
        if(accountDto.getAccountBalance() != null){
            account.setAccountBalance(accountDto.getAccountBalance());
        }
        if(accountDto.getAccountType() != null){
            account.setAccountType(accountDto.getAccountType());
        }
        if(accountDto.getBranchName() != null){
            account.setBranchName(accountDto.getBranchName());
        }
        if(accountDto.getUserId() > 0){
            account.setUser(this.userRepository.findById(accountDto.getUserId()).orElseThrow(() -> new UserNotFoundException("Error")));
        }
        this.accountRepository.save(account);
        return true;
    }

    @Override
    public List<AccountDto> getAllAccounts() throws Exception {
        // TODO Auto-generated method stub
        List<AccountDto> returnList = new ArrayList<AccountDto>();
        List<Account> accounts = this.accountRepository.findAll();
        for(Account account : accounts){
            returnList.add(this.accountMapper.toAccountDto(account));
        }
        return returnList;
    }
    
}
