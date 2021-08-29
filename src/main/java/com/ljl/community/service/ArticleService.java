package com.ljl.community.service;

import com.ljl.community.expcetion.CustomizeErrorCode;
import com.ljl.community.expcetion.CustomizeException;
import com.ljl.community.mapper.ArticleMapper;
import com.ljl.community.mapper.CommentMapper;
import com.ljl.community.mapper.UserMapper;
import com.ljl.community.model.Article;
import com.ljl.community.model.ArticleExample;
import com.ljl.community.model.CommentExample;
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
    @Autowired
    private CommentMapper commentMapper;

    public List<Article> findList(String search) {
        if (search == null || search == "") {
            ArticleExample articleExample = new ArticleExample();
            articleExample.setOrderByClause("e.gtm_modified desc");
            List<Article> articleList = articleMapper.selectByExampleWithUser(articleExample);
            return articleList;
        } else {
            List<Article> articleList = articleMapper.searchWithUser(search);
            return articleList;
        }
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
            // 更新

            Article dbArticle = articleMapper.selectByPrimaryKey(article.getId());
            if (dbArticle == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

            if (dbArticle.getCreator().longValue() != article.getCreator().longValue()) {
                throw new CustomizeException(CustomizeErrorCode.INVALID_OPERATION);
            }

            Article updateArticle = new Article();
            updateArticle.setGtmCreate(System.currentTimeMillis());
            updateArticle.setTitle(article.getTitle());
            updateArticle.setDescription(article.getDescription());
            updateArticle.setTag(article.getTag());
            ArticleExample example = new ArticleExample();
            example.createCriteria()
                    .andIdEqualTo(article.getId());
            int updated = articleMapper.updateByExampleSelective(updateArticle, example);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public List<Article> findTag(String tagName) {
        List<Article> articleList = articleMapper.tagWithUser(tagName);
        return articleList;
    }

    public void deleteById(Integer id) {
        articleMapper.deleteByPrimaryKey(id);
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id);
        commentMapper.deleteByExample(commentExample);
    }
}
