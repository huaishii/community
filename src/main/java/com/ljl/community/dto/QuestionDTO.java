package com.ljl.community.dto;

import com.ljl.community.model.User;
import lombok.Data;

/**
 * Created by Azz-ll on 2020/1/4
 */
@Data
public class QuestionDTO {
    private User user;

    private Integer id;

    private String title;

    private Long gtmCreate;

    private Long gtmModified;

    private Integer creator;

    private Integer commentCount;

    private Integer viewCount;

    private Integer likeCount;

    private String tag;

    private String description;

}
