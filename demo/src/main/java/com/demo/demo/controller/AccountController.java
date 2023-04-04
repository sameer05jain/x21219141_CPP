package com.demo.demo.controller;

import java.util.List;

import com.demo.demo.dto.AccountDto;
import com.demo.demo.dto.UserDto;
import com.demo.demo.dto.ResponseDto;
import com.demo.demo.exception.AccountNotFoundException;
import com.demo.demo.exception.UserNotFoundException;
import com.demo.demo.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class AccountController {
    
    @Autowired
    AccountService accountService;

    @GetMapping(path = "/accounts/{accountId}")
    public ResponseEntity<UserDto> getUserByAccountId(@PathVariable Long accountId) throws AccountNotFoundException, Exception{
        return new ResponseEntity<UserDto>(this.accountService.getUserByAccountId(accountId), HttpStatus.OK);
    }

    @PostMapping(path = "/accounts")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody AccountDto accountDto) throws UserNotFoundException, Exception{
        if(this.accountService.createAccount(accountDto)){
            return new ResponseEntity<>(new ResponseDto("ACCOUNT_CREATED", "Account created!"), HttpStatus.OK);    
        }
        return new ResponseEntity<>(new ResponseDto("ERROR", "Error creating account!"), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/accounts/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) throws AccountNotFoundException, Exception{
        if(this.accountService.deleteAccount(accountId)){
            return new ResponseEntity<>("Account deleted!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error deleting account!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/accounts/{accountId}")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody AccountDto accountDto, @PathVariable Long accountId) throws UserNotFoundException, AccountNotFoundException, Exception{
        if(this.accountService.updateAccount(accountDto, accountId)){
            return new ResponseEntity<>(new ResponseDto("ACCOUNT_UPDATED", "Account updated!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto("ERROR", "Error updating account!"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts() throws Exception{
        return new ResponseEntity<List<AccountDto>>(this.accountService.getAllAccounts(), HttpStatus.OK);
    }

}
