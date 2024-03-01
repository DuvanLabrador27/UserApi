package com.duvanlabrador.userApi.model.mapper;

import com.duvanlabrador.userApi.model.dto.UserDto;
import com.duvanlabrador.userApi.model.entity.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mappings(
            {
                    @Mapping(source = "userId", target = "userId"),
                    @Mapping(source = "name", target = "name"),
                    @Mapping(source = "lastname", target = "lastname"),
                    @Mapping(source = "email", target = "email"),
                    @Mapping(source = "password", target = "password")
            }
    )
    UserDto userToUserDto(UserEntity userEntity);
    @InheritInverseConfiguration
    UserEntity userToUserEntity(UserDto userDto);

}
