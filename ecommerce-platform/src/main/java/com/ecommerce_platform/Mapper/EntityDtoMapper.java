package com.ecommerce_platform.Mapper;

import org.springframework.stereotype.Component;

import com.ecommerce_platform.DTO.UserDto;
import com.ecommerce_platform.Entity.User;

@Component
public class EntityDtoMapper {


    //user entity to user DTO

    public UserDto mapUserToDtoBasic(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setName(user.getName());
        return userDto;

    }
}
