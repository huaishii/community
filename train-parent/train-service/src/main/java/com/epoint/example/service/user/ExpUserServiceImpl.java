package com.epoint.example.service.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.epoint.core.utils.json.JsonUtil;

/**
 * 示例内部服务接口实现
 */
@Component
public class ExpUserServiceImpl implements ExpUserService
{

    // 注意不要在这里定义全局dao成员变量
    // 错误的用法：private ICommonDao dao;

    @Override
    public String getUserById(String userGuid) {
        // 一般在这里写业务逻辑、数据库操作
        // CommonDao.getInstance().findList(...)

        // 这里只是演示用直接返回json字符串
        Map<String, String> result = new HashMap<String, String>(16);
        result.put("username", "admin");
        return JsonUtil.objectToJson(result);
    }

}
