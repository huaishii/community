package com.ljl.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljl.community.mapper.ArticleMapper;
import com.ljl.community.mapper.UserMapper;
import com.ljl.community.model.Article;
import com.ljl.community.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Azz-ll on 2019/8/2
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(required = false,value = "pn",defaultValue = "1") Integer pn,
                        @RequestParam(value = "search",required = false)String search) {
        PageHelper.startPage(pn, 10);
        List<Article> articles = articleService.findList(search);
        PageInfo pageInfo=new PageInfo(articles,5);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("search",search);
        return "index";
    }
}
