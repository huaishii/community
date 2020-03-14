package com.ljl.community.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Azz-ll on 2020/3/2
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
