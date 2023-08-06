package com.example.userservice.util;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static UserDto userDto(User user){
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    public static User toEntity(UserDto userDto){
        User user=new User();
        BeanUtils.copyProperties(userDto,user);
        return user;
    }
}
