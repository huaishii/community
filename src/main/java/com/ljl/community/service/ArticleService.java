package com.ljl.community.service;

import com.ljl.community.mapper.ArticleMapper;
import com.ljl.community.mapper.UserMapper;
import com.ljl.community.model.Article;
import com.ljl.community.service.imp.ArticleServiceImp;
import com.sun.org.glassfish.external.amx.AMX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azz-ll on 2019/8/8
 */
@Service
public class ArticleService implements ArticleServiceImp {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public List<Article> findList() {
        List<Article> articleList = articleMapper.selectByExampleWithUser(null);
        return articleList;
    }
}
