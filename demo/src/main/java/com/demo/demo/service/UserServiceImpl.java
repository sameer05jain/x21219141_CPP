package com.demo.demo.service;

import com.demo.demo.repository.AccountRepository;
import com.demo.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.demo.demo.dto.AccountDto;
import com.demo.demo.dto.UserDto;
import com.demo.demo.exception.UserAlreayExistsException;
import com.demo.demo.exception.UserNotFoundException;
import com.demo.demo.mapper.AccountMapper;
import com.demo.demo.mapper.UserMapper;
import com.demo.demo.models.Account;
import com.demo.demo.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountRepository accountRepository;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDto getUserById(Long userId) throws UserNotFoundException, Exception{

        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Error"));
        UserDto userDto = this.userMapper.toUserDto(user);
        return userDto;
    }

    @Override
    public boolean createUser(UserDto userDto) throws Exception, UserAlreayExistsException{
        // TODO Auto-generated method stub
        if(userDto == null){
            throw new Exception();
        }else if(this.userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new UserAlreayExistsException("Error");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setSecondaryMobile(userDto.getSecondaryNumber());
        user.setDob(userDto.getDob());
        user.setGender(userDto.getGender());
        user.setAccounts(new HashSet<Account>());
        this.userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(Long userId) throws UserNotFoundException, Exception {
        // TODO Auto-generated method stub
        if(userId == null){
            throw new Exception();
        }
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Error"));
        this.userRepository.delete(user);
        return true;
    }

    @Override
    public boolean updateUser(UserDto userDto, Long userId) throws UserNotFoundException, Exception {
        // TODO Auto-generated method stub
        if(userId == null || userDto == null){
            throw new Exception();
        }
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Error"));
        if(userDto.getEmail() != null){
            user.setEmail(userDto.getEmail());
        }
        if(userDto.getName() != null){
            user.setName(userDto.getName());
        }
        if(userDto.getMobileNumber() != null){
            user.setMobileNumber(userDto.getMobileNumber());
        }
        if(userDto.getSecondaryNumber() != null){
            user.setSecondaryMobile(userDto.getSecondaryNumber());
        }
        if(userDto.getDob() != null){
            user.setDob(userDto.getDob());
        }
        if(userDto.getGender() != null){
            user.setGender(userDto.getGender());
        }
        this.userRepository.save(user);
        return true;
    }

    @Override
    public List<UserDto> getAllUsers() throws Exception{
        // TODO Auto-generated method stub
        List<UserDto> returnList = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        for(User user : users){
            returnList.add(this.userMapper.toUserDto(user));
        }
        return returnList;
    }

    @Override
    public List<AccountDto> getUserAccountsByUserId(Long userId) throws UserNotFoundException, Exception{
        // TODO Auto-generated method stub
        if(userId == null){
            throw new Exception();
        }
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Error"));
        List<AccountDto> returnList = new ArrayList<AccountDto>();
        for(Account account : user.getAccounts()){
            returnList.add(this.accountMapper.toAccountDto(account));
        }
        return returnList;
    }

    @Override
    public boolean deleteUserAccountsByUserId(Long userId) throws UserNotFoundException, Exception{
        // TODO Auto-generated method stub
        if(userId == null){
            throw new Exception();
        }
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Error"));
        user.getAccounts().removeAll(user.getAccounts());
        this.userRepository.save(user);
        return true;
    }
    
}
