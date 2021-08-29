package com.epoint.train.studentinfo.api.entity;

import java.util.Date;

import com.epoint.core.BaseEntity;
import com.epoint.core.annotation.Entity;

@Entity(table = "studentinfo", id = "studentId")
public class StudentInfo extends BaseEntity
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     *  学生编号 
     *  @return    
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Integer getStudentId() {
        return super.get("studentId");
    }

    public void setStudentId(Integer studentId) {
        super.set("studentId", studentId);
    }
    
    /**
     *  学生姓名
     *  @return    
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String getStudentName() {
        return super.get("studentName");
    }

    public void setStudentName(String studentName) {
        super.set("studentName", studentName);
    }

    /**
     *  专业
     *  @return    
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String getProfession() {
        return super.get("profession");
    }

    public void setProfession(String profession) {
        super.set("profession", profession);
    }
    
    /**
     *  入学日期
     *  @return    
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public Date getAddDate() {
        return super.get("addDate");
    }

    public void setAddDate(Date addDate) {
        super.set("addDate", addDate);
    }
}
