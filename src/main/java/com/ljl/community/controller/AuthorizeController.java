package com.ljl.community.controller;

import com.ljl.community.dto.AccessTokenDTO;
import com.ljl.community.dto.GithubUser;
import com.ljl.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Azz-ll on 2019/8/2
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githuberProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setClient_id("297ac07a3904c55a48d8");
        accessTokenDTO.setClient_secret("2562a365a4719fb12d5826dfda9975c6aca52ea0");
        accessTokenDTO.setState(state);
        String accessToken = githuberProvider.gitAccessToken(accessTokenDTO);
        GithubUser user = githuberProvider.gitUser(accessToken);
        return "index";
    }
}
