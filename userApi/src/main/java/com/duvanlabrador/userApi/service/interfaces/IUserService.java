package com.duvanlabrador.userApi.service.interfaces;

import com.duvanlabrador.userApi.exception.ResourceNotFoundException;
import com.duvanlabrador.userApi.model.dto.UserDto;

import java.util.List;

public interface IUserService {
    public List<UserDto> getAllUsers();
    public UserDto getUser(Long userId) throws ResourceNotFoundException;
    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(Long userId, UserDto userDto);
    public Boolean deleteUser(Long userId);
}
