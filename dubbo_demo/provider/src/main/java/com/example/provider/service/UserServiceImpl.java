package com.example.provider.service;

import model.UserModel;
import org.springframework.stereotype.Component;
import service.UserService;

/**
 * @Author wangxi
 * @Time 2020/5/11 11:43
 */
@Component
public class UserServiceImpl implements UserService {
    @Override
    public UserModel getUserModel() {
        return new UserModel();
    }
}
