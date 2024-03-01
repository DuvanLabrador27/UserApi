package com.duvanlabrador.userApi.service.impl;

import com.duvanlabrador.userApi.exception.ResourceNotFoundException;
import com.duvanlabrador.userApi.model.dto.UserDto;
import com.duvanlabrador.userApi.model.entity.UserEntity;
import com.duvanlabrador.userApi.model.mapper.UserMapper;
import com.duvanlabrador.userApi.model.repository.UserRepository;
import com.duvanlabrador.userApi.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }



    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> user = this.userRepository.findAll();
        return user.stream().map(users -> this.userMapper.userToUserDto(users)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long userId) {
        UserEntity userOptional = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return this.userMapper.userToUserDto(userOptional);
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = this.userMapper.userToUserEntity(userDto);
        UserEntity user = this.userRepository.save(userEntity);
        return this.userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        UserEntity userEntity = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId ));
        userEntity.setName(userDto.getName());
        userEntity.setLastname(userDto.getLastname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());

        UserEntity newUser = this.userRepository.save(userEntity);
        return this.userMapper.userToUserDto(newUser);
    }

    @Override
    public Boolean deleteUser(Long userId) {
        UserEntity user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId ));
        if(user != null){
            this.userRepository.delete(user);
            return true;
        }else{
            return false;
        }
    }
}
