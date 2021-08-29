package com.epoint.example.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.controller.BaseController;
import com.epoint.example.service.user.ExpUserService;

/**
 * 示例Action
 */
@RestController("expuserlistaction")
@Scope("request")
public class ExpUserListAction extends BaseController
{

    /**
     * 注入内部服务接口
     */
    @Autowired
    ExpUserService expUserService;

    @Override
    public void pageLoad() {
        // 调用服务接口方法
        expUserService.getUserById("100");
    }

}
