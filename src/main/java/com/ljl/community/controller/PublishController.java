package com.ljl.community.controller;

import com.ljl.community.mapper.ArticleMapper;
import com.ljl.community.mapper.UserMapper;
import com.ljl.community.model.Article;
import com.ljl.community.model.User;
import com.ljl.community.model.UserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Azz-ll on 2019/8/5
 */
@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model) {
        Article article = new Article();
        List<User> users  = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null&&cookies.length!=0) {
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
        if(users.get(0) == null){
            model.addAttribute("error", "用户未登录，请先登录！");
            return "publish";
        }
        article.setTitle(title);
        article.setDescription(description);
        article.setTag(tag);
        article.setGtmCreate(System.currentTimeMillis());
        article.setGtmModified(System.currentTimeMillis());
        article.setCreator(users.get(0).getId());
        articleMapper.insert(article);
        return "publish";
    }
}
