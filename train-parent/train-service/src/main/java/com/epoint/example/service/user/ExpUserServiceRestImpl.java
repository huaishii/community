package com.epoint.example.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例对外REST服务接口
 */
@RestController
@RequestMapping("/example/expuserservice")
public class ExpUserServiceRestImpl
{

    /**
     * 注入复用内部服务接口
     */
    @Autowired
    ExpUserService expUserService;

    @RequestMapping(value = "/getuserbyid", method = RequestMethod.POST)
    public String getUserById(@RequestParam(value = "userguid", required = true) String userGuid) {
        return expUserService.getUserById(userGuid);
    }

}
