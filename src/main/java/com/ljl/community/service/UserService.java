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


    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> dbUsers = userMapper.selectByExample(userExample);
        User dbUser = null;
        if (dbUsers.size() != 0) {
            dbUser = dbUsers.get(0);
        }
        if (dbUser == null) {
            //插入
            user.setGtmCreate(System.currentTimeMillis());
            user.setGtmModified(user.getGtmCreate());
            userMapper.insert(user);
        } else {
            //更新
            dbUser.setGtmModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.updateByPrimaryKey(dbUser);
        }
    }
}
