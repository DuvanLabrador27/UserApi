package com.duvanlabrador.userApi.controller;

import com.duvanlabrador.userApi.exception.ResourceNotFoundException;
import com.duvanlabrador.userApi.model.dto.UserDto;
import com.duvanlabrador.userApi.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final IUserService userService;
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<UserDto> getAllUser(){
        return this.userService.getAllUsers();
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) throws ResourceNotFoundException{
        UserDto user = userService.getUser(userId);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/userCreate")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        try {
         UserDto user = this.userService.createUser(userDto);
         return new ResponseEntity<>(user,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){

            UserDto user = this.userService.updateUser(userId, userDto);
            if(user!=null){
            return new ResponseEntity<>(user, HttpStatus.OK);}
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
            Boolean user = this.userService.deleteUser(userId);
            if (user) {
                return new ResponseEntity<>("The user has been delete correctly", HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
