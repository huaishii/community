package com.ljl.community.dto;

import lombok.Data;

/**
 * Created by Azz-ll on 2020/3/4
 */
@Data
public class NotificationDTO {
    private Integer id;
    private Integer gmtCreate;
    private Integer status;
    private Integer notifier;
    private String notifierName;
    private String outerTitle;
    private Integer outerid;
    private String typeName;
    private Integer type;
}
