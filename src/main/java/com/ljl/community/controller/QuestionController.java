package com.ljl.community.controller;

import com.ljl.community.dto.CommentDTO;
import com.ljl.community.dto.QuestionDTO;
import com.ljl.community.enums.CommentTypeEnum;
import com.ljl.community.model.Article;
import com.ljl.community.service.CommentService;
import com.ljl.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Azz-ll on 2020/1/4
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model){
        Article article = questionService.getByUserId(id);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(article);
        List<CommentDTO> comments = commentService.listByQuestionId(id, CommentTypeEnum.QUESTION);
        questionService.incView(id);
        model.addAttribute("question",article);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
