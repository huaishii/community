package com.ljl.community.controller;

import com.ljl.community.dto.FileDTO;
import com.ljl.community.model.User;
import com.ljl.community.provider.AliyunProvider;
import com.ljl.community.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by huaishi on 2020/4/15
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AliyunProvider aliyunProvider;

    @GetMapping("/douser")
    public String log() {
        return "/login";
    }

    @GetMapping("/register")
    public String reg() {
        return "/register";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "name") String name, @RequestParam(name = "password") String password,
                        HttpServletResponse response, Model model) {
        if (StringUtils.isBlank(name)) {
            model.addAttribute("error", "用户名不能为空");
            return "login";
        }
        model.addAttribute("name", name);
        if (StringUtils.isBlank(password)) {
            model.addAttribute("error", "密码不能为空");
            return "login";
        }
        User user = userService.selectByName(name);
        if (user == null) {
            model.addAttribute("error", "用户名或密码错误，请重新输入");
            return "login";
        }
        if (user.getPassword().equals(password) || user.getPassword() == password) {
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            userService.update(user);
        } else {
            model.addAttribute("error", "用户名或密码错误，请重新输入");
            return "login";
        }
        return "redirect:/";

    }

    @PostMapping("/sign")
    public String sign(@RequestParam(name = "name") String name, @RequestParam(name = "password") String password,
                       HttpServletResponse response, Model model) {
        if(StringUtils.isBlank(name)){
            model.addAttribute("error", "用户名不能为空，请重新输入");
            return "register";
        }
        User user = new User();
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setName(name);
        user.setPassword(password);
        user.setAvatarUrl("/img/头像.png");
        int code = userService.create(user);
        if (code == 400) {
            model.addAttribute("error", "用户名重复，请重新输入");
            return "register";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        request.getSession().removeAttribute("unreadCount");
        return "redirect:/";
    }

    @GetMapping("/userInfo")
    public String userInfo(){
        return "userInfo";
    }

    @PostMapping("/imgUpload")
    public String imgUpload(@RequestParam(name = "img")MultipartFile img,String name,
                            String dio, Model model){
        String url = "";
        try {
            url = aliyunProvider.upload(img.getInputStream(),img.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setName(name);
        user.setAvatarUrl(url);
        user.setDio(dio);
        userService.update(user);
        return "redirect:/userInfo";
    }
}
