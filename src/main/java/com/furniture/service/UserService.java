package com.furniture.service;

import com.furniture.model.User;
import com.furniture.model.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
