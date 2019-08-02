package com.ljl.community.controller;

import com.ljl.community.dto.AccessTokenDTO;
import com.ljl.community.dto.GithubUser;
import com.ljl.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        String accessToken = githuberProvider.gitAccessToken(accessTokenDTO);
        GithubUser user = githuberProvider.gitUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
