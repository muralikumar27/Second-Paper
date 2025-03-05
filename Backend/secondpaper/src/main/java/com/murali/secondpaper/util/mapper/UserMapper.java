package com.murali.secondpaper.util.mapper;

import com.murali.secondpaper.entity.User;
import com.murali.secondpaper.model.SignupDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User SignupDto2User(SignupDto dto) {
        User user = new User();

        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        return user;
    }
}
