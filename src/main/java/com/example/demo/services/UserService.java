package com.example.demo.services;

import com.example.demo.models.serviceModels.UserServiceModel;

import java.util.Optional;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUser(String username);

    void login(UserServiceModel userServiceModel);

    void logout();
}
