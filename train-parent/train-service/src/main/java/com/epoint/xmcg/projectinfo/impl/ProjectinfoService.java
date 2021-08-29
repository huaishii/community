package com.epoint.xmcg.projectinfo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.epoint.core.dao.CommonDao;
import com.epoint.core.dao.ICommonDao;
import com.epoint.core.grammar.Record;
import com.epoint.core.utils.container.ContainerFactory;
import com.epoint.core.utils.sql.SqlConditionUtil;
import com.epoint.core.utils.sql.SqlHelper;
import com.epoint.core.utils.string.StringUtil;
import com.epoint.database.peisistence.crud.impl.model.PageData;
import com.epoint.frame.service.organ.user.api.IUserService;
import com.epoint.xmcg.projectinfo.api.entity.Projectinfo;

/**
 * 项目信息表对应的后台service
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:26]
 */
public class ProjectinfoService
{
    /**
     * 数据增删改查组件
     */
    protected ICommonDao baseDao;

    public ProjectinfoService() {
        baseDao = CommonDao.getInstance();
    }

    /**
     * 插入数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    public int insert(Projectinfo record) {
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
        T t = baseDao.find(Projectinfo.class, guid);
        return baseDao.delete(t);
    }

    /**
     * 更新数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    public int update(Projectinfo record) {
        return baseDao.update(record);
    }

    /**
     * 查询数量
     * 
     * @param conditionMap
     *            查询条件集合
     * @return Integer
     */
    public Integer countProjectinfo(Map<String, Object> conditionMap) {
        SqlConditionUtil conditionUtil = new SqlConditionUtil(conditionMap);
        conditionUtil.setSelectFields("count(*)");
        List<Object> params = new ArrayList<>();
        return baseDao.queryInt(new SqlHelper().getSqlComplete(Projectinfo.class, conditionUtil.getMap(), params),
                params.toArray());
    }

    /**
     * 根据ID查找单个实体
     * 
     * @param primaryKey
     *            主键
     * @return T extends BaseEntity
     */
    public Projectinfo find(Object primaryKey) {
        return baseDao.find(Projectinfo.class, primaryKey);
    }

    /**
     * 查找单条记录
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T {String、Integer、Long、Record、FrameOu、Object[]等}
     */
    public Projectinfo find(Map<String, Object> conditionMap) {
        List<Object> params = new ArrayList<>();
        return baseDao.find(new SqlHelper().getSqlComplete(Projectinfo.class, conditionMap, params), Projectinfo.class,
                params.toArray());
    }

    /**
     * 查找一个list
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T extends BaseEntity
     */
    public List<Projectinfo> findList(Map<String, Object> conditionMap) {
        List<Object> params = new ArrayList<>();
        return baseDao.findList(new SqlHelper().getSqlComplete(Projectinfo.class, conditionMap, params),
                Projectinfo.class, params.toArray());
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
    public PageData<Projectinfo> paginatorList(Map<String, Object> conditionMap, int pageNumber, int pageSize) {
        List<Object> params = new ArrayList<>();
        List<Projectinfo> list = baseDao.findList(
                new SqlHelper().getSqlComplete(Projectinfo.class, conditionMap, params), pageNumber, pageSize,
                Projectinfo.class, params.toArray());
        int count = countProjectinfo(conditionMap);
        PageData<Projectinfo> pageData = new PageData<Projectinfo>(list, count);
        return pageData;
    }

    /**
     * 检查项目名称是否重复
     * 
     * @param projectname
     * @return
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int checkName(String projectname) {
        String sql = "select count(1) from projectinfo where projectname = ?";
        return baseDao.queryInt(sql, projectname);
    }

    /**
     * 修改页重名验证
     * 
     * @param projectNameOld
     * @param projectNameNew
     * @return
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public int checkExist(String projectNameOld, String projectNameNew) {
        String sql = "select count(1) from projectinfo where projectname != ? and projectname = ?";
        return baseDao.queryInt(sql, projectNameOld, projectNameNew);
    }

    /**
     * 将所有人员转换为文本
     *  @param projectUser
     *  @return    
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String getAllUser(String projectUser) {
        String[] allUser = projectUser.split(";");
        List<String> nameList = new ArrayList<>();
        IUserService iUserService = ContainerFactory.getContainInfo().getComponent(IUserService.class);
        for (String str : allUser) {
            String name = iUserService.getUserNameByUserGuid(str);
            nameList.add(name);
        }
        return StringUtil.join(nameList, "；");
    }

}
