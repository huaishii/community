package com.ljl.community.dto;

import lombok.Data;

/**
 * Created by Azz-ll on 2020/2/26
 */
@Data
public class CommentCreateDTO {
    private Integer parentId;
    private String content;
    private Integer type;
}
