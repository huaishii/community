package com.ljl.community.service;

import com.ljl.community.dto.QuestionDTO;
import com.ljl.community.expcetion.CustomizeErrorCode;
import com.ljl.community.expcetion.CustomizeException;
import com.ljl.community.mapper.ArticleMapper;
import com.ljl.community.model.Article;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Azz-ll on 2020/1/4
 */
@Service
public class QuestionService {

    @Autowired
    private ArticleMapper articleMapper;

    public Article getByUserId(Integer id) {
        Article article = articleMapper.selectByPrimaryKeyWithUser(id);
        if (article == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        return article;
    }

    public void incView(Integer id) {
        Article article = new Article();
        article.setId(id);
        article.setViewCount(1);
        articleMapper.incView(article);
    }

    public List<QuestionDTO> selectRelated(Article art) {
        if(StringUtils.isBlank(art.getTag())){
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(art.getTag(), ",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Article article = new Article();
        article.setId(art.getId());
        article.setTag(regexpTag);

        List<Article> articles = articleMapper.selectRelated(article);
        List<QuestionDTO> questionDTOS = articles.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
