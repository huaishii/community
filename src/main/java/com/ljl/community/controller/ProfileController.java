package com.ljl.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljl.community.mapper.ArticleMapper;
import com.ljl.community.mapper.UserMapper;
import com.ljl.community.model.Article;
import com.ljl.community.model.ArticleExample;
import com.ljl.community.model.User;
import com.ljl.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Azz-ll on 2019/8/10
 */
@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(value = "action") String action, Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "pn",defaultValue = "1")Integer pn) {
        Cookie[] cookies = request.getCookies();
        List<User> users = null;
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    UserExample example = new UserExample();
                    example.createCriteria()
                            .andTokenEqualTo(cookie.getValue());
                    users = userMapper.selectByExample(example);
                    if (users.size() != 0) {
                        request.getSession().setAttribute("user", users.get(0));
                    }
                    break;
                }
            }
        }
        if (users.get(0) == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("seletion", "questions");
            model.addAttribute("seletionName", "我的问题");
        } else if ("replies".equals(action)) {
            model.addAttribute("seletion", "replies");
            model.addAttribute("seletionName", "最新回复");
        }
        PageHelper.startPage(pn,10);
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andCreatorEqualTo(users.get(0).getId());
        List<Article> articleList = articleMapper.selectByExample(articleExample);
        PageInfo pageInfo = new PageInfo(articleList,5);
        model.addAttribute("articlePageInfo",pageInfo);
        return "profile";
    }
}
