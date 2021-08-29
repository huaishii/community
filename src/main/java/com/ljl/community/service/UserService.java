package com.ljl.community.service;

import com.ljl.community.mapper.UserMapper;
import com.ljl.community.model.User;
import com.ljl.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Azz-ll on 2020/1/6
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectByName(String name) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(name);
        List<User> dbUsers = userMapper.selectByExample(userExample);
        User dbUser = null;
        if (dbUsers.size() != 0) {
            dbUser = dbUsers.get(0);
        }
        return dbUser;
    }

    public int create(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(user.getName());
        List<User> dbUsers = userMapper.selectByExample(userExample);
        if (dbUsers.size() == 0) {
            //插入
            user.setGtmCreate(System.currentTimeMillis());
            user.setGtmModified(System.currentTimeMillis());
            userMapper.insert(user);
            return 100;
        } else {
            return 400;
        }
    }

    public void update(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(user.getName());
        List<User> dbUsers = userMapper.selectByExample(userExample);
        User dbUser = null;
        if (dbUsers.size() != 0) {
            dbUser = dbUsers.get(0);
        }
        //更新
        dbUser.setGtmModified(System.currentTimeMillis());
        dbUser.setAvatarUrl(user.getAvatarUrl());
        dbUser.setName(user.getName());
        dbUser.setToken(user.getToken());
        dbUser.setDio(user.getDio());
        UserExample example = new UserExample();
        example.createCriteria()
                .andIdEqualTo(dbUser.getId());
        userMapper.updateByExampleSelective(dbUser, example);
    }

}
