package com.ljl.community.service;

import com.ljl.community.expcetion.CustomizeErrorCode;
import com.ljl.community.expcetion.CustomizeException;
import com.ljl.community.mapper.ArticleMapper;
import com.ljl.community.mapper.UserMapper;
import com.ljl.community.model.Article;
import com.ljl.community.model.ArticleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Azz-ll on 2019/8/8
 */

@Service
public class ArticleService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;

    public List<Article> findList() {
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("gtm_create desc");
        List<Article> articleList = articleMapper.selectByExampleWithUser(articleExample);
        return articleList;
    }

    public Article selectById(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return article;
    }

    public void createOrUpdate(Article article) {
        if (article.getId() == null) {
            article.setGtmCreate(System.currentTimeMillis());
            article.setGtmModified(article.getGtmCreate());
            article.setViewCount(0);
            article.setCommentCount(0);
            article.setLikeCount(0);
            articleMapper.insert(article);
        } else {
            int updated = articleMapper.updateByPrimaryKey(article);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
