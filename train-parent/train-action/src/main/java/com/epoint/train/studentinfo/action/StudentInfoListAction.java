package com.epoint.train.studentinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.controller.BaseController;
import com.epoint.core.dto.model.DataGridModel;
import com.epoint.train.studentinfo.api.IStudentInfoService;
import com.epoint.train.studentinfo.api.entity.StudentInfo;

@RestController("studentinfolistaction")
@Scope("request")
public class StudentInfoListAction extends BaseController
{

    private StudentInfo dataBean;
    
    private DataGridModel<StudentInfo> model;
    
    @Autowired
    private IStudentInfoService studentInfoSercive;

    @Override
    public void pageLoad() {
        // TODO Auto-generated method stub

    }
    
    public DataGridModel<StudentInfo> getDataGridData(){
        if(model == null) {
            model = new DataGridModel<StudentInfo>() {

                @Override
                public List<StudentInfo> fetchData(int arg0, int arg1, String arg2, String arg3) {
                    // TODO Auto-generated method stub
                    return studentInfoSercive.findList();
                }
                
            };
        }
        return model;
        
    }
    
    public void deleteSelect() {
        List<String> selectKeys = getDataGridData().getSelectKeys();
        for (String str : selectKeys) {
            studentInfoSercive.deleteGuid(str);
        }
        addCallbackParam("msg", "删除成功！");
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
