package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    public User getUserInfo(int id);


    public int deleteById(int id);

    public int Update(User user);

    public User save(User user);

    public List<User> selectAll();
}
