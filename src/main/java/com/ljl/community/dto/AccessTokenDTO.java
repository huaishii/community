package com.ljl.community.dto;

import lombok.Data;

/**
 * Created by Azz-ll on 2019/8/2
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
