package com.ljl.community.mapper;

import com.ljl.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Azz-ll on 2019/8/3
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,token,account_id,gtm_create,gtm_modified) values (#{name},#{token},#{accountId},#{gtmCreate},#{gtmModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
