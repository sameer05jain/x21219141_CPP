package com.demo.demo.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.demo.demo.dto.AccountDto;
import com.demo.demo.dto.UserDto;
import com.demo.demo.exception.UserNotFoundException;

public interface UserService {
    public UserDto getUserById(Long userId) throws UserNotFoundException, Exception;
    public boolean createUser(UserDto userDto) throws Exception;
    public boolean deleteUser(Long userId) throws UserNotFoundException, Exception;
    public boolean updateUser(UserDto userDto, Long userId) throws UserNotFoundException, Exception;
    public List<UserDto> getAllUsers() throws Exception;
    public List<AccountDto> getUserAccountsByUserId(Long userId) throws UserNotFoundException, Exception;
    public boolean deleteUserAccountsByUserId(Long userId) throws UserNotFoundException, Exception;
}
