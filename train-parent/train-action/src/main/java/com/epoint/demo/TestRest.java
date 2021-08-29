package com.epoint.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.api.common.ApiBaseController;

@RestController
public class TestRest extends ApiBaseController
{
    @PostMapping("/demo/getDemoInfo")
    public String getDemoInfo(@RequestParam(name = "params") String params) {
        return buildSuccessResponse(params);
    }

}
