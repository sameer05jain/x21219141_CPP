package com.demo.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.demo.demo.dto.AccountDto;
import com.demo.demo.dto.UserDto;
import com.demo.demo.dto.ResponseDto;
import com.demo.demo.exception.UserNotFoundException;
import com.demo.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/hello")
    public Map<String,String> hello() {
        Map<String,String> retMap = new HashMap<String,String>();
        retMap.put("Hello", "World");
        return retMap;
    }

    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) throws UserNotFoundException,Exception {
        return new ResponseEntity<UserDto>(this.userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<ResponseDto> createUser(@RequestBody @Valid UserDto userDto) throws Exception {
        if(this.userService.createUser(userDto)){
            return new ResponseEntity<>(new ResponseDto("USER_CREATED", "User created!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto("ERROR", "Error creating user!"), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) throws UserNotFoundException, Exception {
        if(this.userService.deleteUser(userId)){
            return new ResponseEntity<>("User deleted!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error deleting user!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/users/{userId}")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long userId) throws UserNotFoundException, Exception {
        if(this.userService.updateUser(userDto, userId)){
            return new ResponseEntity<>(new ResponseDto("USER_UPDATED", "User updated!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto("ERROR", "Error updating user!"), HttpStatus.OK);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserDto>> getAllUsers() throws Exception {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/useraccounts/{userId}")
    public ResponseEntity<List<AccountDto>> getUserAccountsByUserId(@PathVariable Long userId) throws UserNotFoundException, Exception {
        return new ResponseEntity<>(this.userService.getUserAccountsByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteuseraccounts/{userId}")
    public ResponseEntity<String> deleteUserAccountsByUserId(@PathVariable Long userId) throws UserNotFoundException, Exception {
        if(this.userService.deleteUserAccountsByUserId(userId)){
            return new ResponseEntity<>("User accounts deleted!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error deleting user accounts!", HttpStatus.BAD_REQUEST);
    }
}
