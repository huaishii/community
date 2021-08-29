package com.epoint.xmcg.projectbudgetinfo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.epoint.core.dao.CommonDao;
import com.epoint.core.dao.ICommonDao;
import com.epoint.core.grammar.Record;
import com.epoint.core.utils.sql.SqlConditionUtil;
import com.epoint.core.utils.sql.SqlHelper;
import com.epoint.database.peisistence.crud.impl.model.PageData;
import com.epoint.xmcg.projectbudgetinfo.api.entity.Projectbudgetinfo;

/**
 * 项目预算信息表对应的后台service
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:42]
 */
public class ProjectbudgetinfoService
{
    /**
     * 数据增删改查组件
     */
    protected ICommonDao baseDao;

    public ProjectbudgetinfoService() {
        baseDao = CommonDao.getInstance();
    }

    /**
     * 插入数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    public int insert(Projectbudgetinfo record) {
        return baseDao.insert(record);
    }

    /**
     * 删除数据
     * 
     * @param guid
     *            主键guid
     * @return int
     */
    public <T extends Record> int deleteByGuid(String guid) {
        T t = baseDao.find(Projectbudgetinfo.class, guid);
        return baseDao.delete(t);
    }

    /**
     * 更新数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    public int update(Projectbudgetinfo record) {
        return baseDao.update(record);
    }

    /**
     * 查询数量
     * 
     * @param conditionMap
     *            查询条件集合
     * @return Integer
     */
    public Integer countProjectbudgetinfo(Map<String, Object> conditionMap) {
        SqlConditionUtil conditionUtil = new SqlConditionUtil(conditionMap);
        conditionUtil.setSelectFields("count(*)");
        List<Object> params = new ArrayList<>();
        return baseDao.queryInt(new SqlHelper().getSqlComplete(Projectbudgetinfo.class, conditionUtil.getMap(), params),
                params.toArray());
    }

    /**
     * 根据ID查找单个实体
     * 
     * @param primaryKey
     *            主键
     * @return T extends BaseEntity
     */
    public Projectbudgetinfo find(Object primaryKey) {
        return baseDao.find(Projectbudgetinfo.class, primaryKey);
    }

    /**
     * 查找单条记录
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T {String、Integer、Long、Record、FrameOu、Object[]等}
     */
    public Projectbudgetinfo find(Map<String, Object> conditionMap) {
        List<Object> params = new ArrayList<>();
        return baseDao.find(new SqlHelper().getSqlComplete(Projectbudgetinfo.class, conditionMap, params),
                Projectbudgetinfo.class, params.toArray());
    }

    /**
     * 查找一个list
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T extends BaseEntity
     */
    public List<Projectbudgetinfo> findList(Map<String, Object> conditionMap) {
        List<Object> params = new ArrayList<>();
        return baseDao.findList(new SqlHelper().getSqlComplete(Projectbudgetinfo.class, conditionMap, params),
                Projectbudgetinfo.class, params.toArray());
    }

    /**
     * 分页查找一个list
     * 
     * @param conditionMap
     *            查询条件集合
     * @param pageNumber
     *            记录行的偏移量
     * @param pageSize
     *            记录行的最大数目
     * @return T extends BaseEntity
     */
    public PageData<Projectbudgetinfo> paginatorList(Map<String, Object> conditionMap, int pageNumber, int pageSize) {
        List<Object> params = new ArrayList<>();
        List<Projectbudgetinfo> list = baseDao.findList(
                new SqlHelper().getSqlComplete(Projectbudgetinfo.class, conditionMap, params), pageNumber, pageSize,
                Projectbudgetinfo.class, params.toArray());
        int count = countProjectbudgetinfo(conditionMap);
        PageData<Projectbudgetinfo> pageData = new PageData<Projectbudgetinfo>(list, count);
        return pageData;
    }

    public int getMonenyBefore(String projectguid) {
        String sql = "select IFNULL(sum(budgetmoney),0) from projectbudgetinfo where projectguid=?";
        return baseDao.queryInt(sql, projectguid);
    }

    /**
     * 通过项目guid查询项目预算列表
     * 
     * @param rowguid
     * @return
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Projectbudgetinfo> getBudgetInfoByProjectGuid(String rowguid) {
        String sql = "select * from projectbudgetinfo where projectguid=?";
        return baseDao.findList(sql, Projectbudgetinfo.class, rowguid);
    }

    /**
     * 通过projectName获取预算信息
     *  @param projectName
     *  @return    
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Projectbudgetinfo> getBUdgetByProjectName(String projectName) {
        String sql = "select budgetname,budgettype,budgetmoney,usebudgetmoney from projectbudgetinfo where projectguid in("
                + "select rowguid from projectinfo where projectname=?"
                + ")";
        return baseDao.findList(sql, Projectbudgetinfo.class, projectName);
        
        
    }

}
