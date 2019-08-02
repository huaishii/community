package com.ljl.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Azz-ll on 2019/8/2
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
