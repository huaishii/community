package com.epoint.train.studentinfo.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.controller.BaseController;
import com.epoint.core.dto.model.SelectItem;
import com.epoint.train.studentinfo.api.IStudentInfoService;
import com.epoint.train.studentinfo.api.entity.StudentInfo;

@RestController("studentinfolistaddaction")
@Scope("request")
public class StudentInfoListAddAction extends BaseController
{

    private StudentInfo dataBean = null;
    
    @Autowired
    private IStudentInfoService studentInfoService;
    
    private List<SelectItem> professTItems;
    @Override
    public void pageLoad() {
        // TODO Auto-generated method stub
        dataBean = new StudentInfo();
    }
    
    public void add() {
        studentInfoService.insert(dataBean);
        addCallbackParam("msg", "保存成功！");
        dataBean = null;
    }
    
    public void addNew() {
        add();
        dataBean = new StudentInfo();
    }
    
    public List<SelectItem> getProfession(){
        if(professTItems == null) {
            professTItems = new ArrayList<>();
            professTItems.add(new SelectItem("1","计算机控制"));
            professTItems.add(new SelectItem("2","网络安全"));
            professTItems.add(new SelectItem("3","数据库"));
        }
         return professTItems;
    }
    
    public StudentInfo getDataBean() {
        if(dataBean == null) {
            dataBean = new StudentInfo();
        }
        return dataBean;
    }
    public void setDataBean(StudentInfo dataBean) {
        this.dataBean = dataBean;
    }

}
