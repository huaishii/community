package com.ljl.community.dto;

import com.ljl.community.model.User;
import lombok.Data;

/**
 * Created by Azz-ll on 2020/2/29
 */
@Data
public class CommentDTO {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}
