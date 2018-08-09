package com.jiang.springboot_shiro.service;

import com.jiang.springboot_shiro.entity.User;
import com.jiang.springboot_shiro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUser(User user){
        return userMapper.findUser(user);
    }
    public User findUserByName(String name){
        return userMapper.findUserByName(name);
    }
    public List<User> qureyAll(){
       return  userMapper.queryAll();
    }
    public boolean deleteUserByName(String name) {
        return userMapper.deleteUserByName(name);
    }
    public int add(User user){
        return userMapper.add(user);
    }
    public boolean update(User user){
        return userMapper.update(user);
    }
}
