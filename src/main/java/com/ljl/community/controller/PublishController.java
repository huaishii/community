package com.ljl.community.controller;

import com.ljl.community.cache.TagCache;
import com.ljl.community.model.Article;
import com.ljl.community.model.User;
import com.ljl.community.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Azz-ll on 2019/8/5
 */
@Controller
public class PublishController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Integer id,Model model){
        Article question = articleService.selectById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", TagCache.get());
        return "/publish";
    }

    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());

        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        Article article = new Article();
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error", "用户未登录，请先登录！");
            return "publish";
        }
        article.setTitle(title);
        article.setDescription(description);
        article.setTag(tag);
        article.setCreator(user.getId());
        article.setId(id);
        articleService.createOrUpdate(article);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id")Integer id){
        articleService.deleteById(id);
        return "redirect:/profile/questions";
    }
}
