package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User getUserInfo(int id) {
        System.out.println(this);
        User user = userMapper.getUserInfo(id);
        User user1 = (User) redisTemplate.opsForValue().get("user-"+user.getId());
        if (user1 == null) {
            redisTemplate.opsForValue().set("user-"+user.getId(), user);
        }
        return user;
    }

    @Override
    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int Update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User save(User user) {
        int save = userMapper.save(user);
        return user;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
