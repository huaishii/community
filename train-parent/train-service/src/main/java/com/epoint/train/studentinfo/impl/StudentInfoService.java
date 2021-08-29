package com.epoint.train.studentinfo.impl;

import java.util.List;

import com.epoint.core.dao.CommonDao;
import com.epoint.core.dao.ICommonDao;
import com.epoint.train.studentinfo.api.entity.StudentInfo;

public class StudentInfoService
{

    protected ICommonDao baseDao;
    public StudentInfoService(){
        baseDao = CommonDao.getInstance();
    }
    
    public int insert(StudentInfo studentInfo) {
        // TODO Auto-generated method stub
        return baseDao.insert(studentInfo);
    }

    public int update(StudentInfo studentInfo) {
        // TODO Auto-generated method stub
        return baseDao.update(studentInfo);
    }

    public StudentInfo find(Integer studentId) {
        // TODO Auto-generated method stub
        return baseDao.find(StudentInfo.class, studentId);
    }

    public List<StudentInfo> findList() {
        // TODO Auto-generated method stub
        String sql = "select * from studentinfo";
        return baseDao.findList(sql, StudentInfo.class);
    }

    public int deleteGuid(String studentId) {
        // TODO Auto-generated method stub
        StudentInfo studentInfo = baseDao.find(StudentInfo.class, studentId);
        return baseDao.delete(studentInfo);
    }

}
