package com.epoint.train.studentinfo.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.epoint.train.studentinfo.api.IStudentInfoService;
import com.epoint.train.studentinfo.api.entity.StudentInfo;

@Component
public class StudentInfoServiceImpl implements IStudentInfoService
{

    @Override
    public int insert(StudentInfo studentInfo) {
        // TODO Auto-generated method stub
        return new StudentInfoService().insert(studentInfo);
    }

    @Override
    public int update(StudentInfo studentInfo) {
        // TODO Auto-generated method stub
        return new StudentInfoService().update(studentInfo);
    }

    @Override
    public StudentInfo find(Integer studentId) {
        // TODO Auto-generated method stub
        return new StudentInfoService().find(studentId);
    }

    @Override
    public List<StudentInfo> findList() {
        // TODO Auto-generated method stub
        return new StudentInfoService().findList();
    }

    @Override
    public int deleteGuid(String studentId) {
        // TODO Auto-generated method stub
        return new StudentInfoService().deleteGuid(studentId);
    }

}
