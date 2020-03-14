package com.ljl.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljl.community.dto.NotificationDTO;
import com.ljl.community.mapper.ArticleMapper;
import com.ljl.community.mapper.UserMapper;
import com.ljl.community.model.Article;
import com.ljl.community.model.ArticleExample;
import com.ljl.community.model.User;
import com.ljl.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(value = "action") String action, Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            PageHelper.startPage(pn, 10);
            ArticleExample articleExample = new ArticleExample();
            articleExample.createCriteria().andCreatorEqualTo(user.getId());
            articleExample.setOrderByClause("gtm_create desc");
            List<Article> articleList = articleMapper.selectByExample(articleExample);
            PageInfo pageInfo = new PageInfo(articleList, 5);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        } else if ("replies".equals(action)) {
            List<NotificationDTO> notificationDTOList = notificationService.list(user.getId());
            PageInfo pageInfo = new PageInfo(notificationDTOList, 5);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        return "profile";
    }
}
