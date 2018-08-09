package com.jiang.springboot_shiro.mapper;

import com.jiang.springboot_shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public User findUser(User user);
    public User findUserByName(String name);
    public List<User> queryAll();
    public boolean deleteUserByName(String name);
    public int add(User user);
    public boolean update(User user);
}
