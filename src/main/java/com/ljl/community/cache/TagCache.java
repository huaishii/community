package com.ljl.community.cache;

import com.ljl.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Azz-ll on 2020/3/2
 */
public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO tagGame = new TagDTO();
        tagGame.setCategoryName("快速分类");
        tagGame.setTags(Arrays.asList("游戏攻略", "开黑交友", "举手提问"));
        tagDTOS.add(tagGame);

        TagDTO onlineGame = new TagDTO();
        onlineGame.setCategoryName("网络游戏");
        onlineGame.setTags(Arrays.asList("英雄联盟", "地下城与勇士", "魔兽世界", "最终幻想14", "剑网三", "剑灵"));
        tagDTOS.add(onlineGame);

        TagDTO singleGame  = new TagDTO();
        singleGame .setCategoryName("单机游戏");
        singleGame .setTags(Arrays.asList("骑马与砍杀2", "生化危机3：重置版", "怪物猎人世界：冰原"));
        tagDTOS.add(singleGame );


        TagDTO mobileGame = new TagDTO();
        mobileGame.setCategoryName("手机游戏");
        mobileGame.setTags(Arrays.asList("王者荣耀", "崩坏3", "第五人格", "命运冠位指定", "皇室战争"));
        tagDTOS.add(mobileGame);

        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }

}
