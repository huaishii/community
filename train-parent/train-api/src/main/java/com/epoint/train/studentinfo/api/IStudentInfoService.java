package com.epoint.train.studentinfo.api;

import java.util.List;

import com.epoint.train.studentinfo.api.entity.StudentInfo;

public interface IStudentInfoService
{

    //新增
    public int insert(StudentInfo studentInfo);
    
    //修改
    public int update(StudentInfo studentInfo);
    
    //查询单个实体
    public StudentInfo find(Integer studentId);
    
    //查询列表
    public List<StudentInfo> findList();
    
    //删除
    public int deleteGuid(String guid);
}
