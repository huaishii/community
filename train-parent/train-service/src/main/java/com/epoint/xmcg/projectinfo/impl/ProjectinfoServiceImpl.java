package com.epoint.xmcg.projectinfo.impl;

import java.util.List;
import java.util.Map;
import com.epoint.database.peisistence.crud.impl.model.PageData;
import org.springframework.stereotype.Component;

import com.epoint.xmcg.projectinfo.api.IProjectinfoService;
import com.epoint.xmcg.projectinfo.api.entity.Projectinfo;

/**
 * 项目信息表对应的后台service实现类
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:26]
 */
@Component
public class ProjectinfoServiceImpl implements IProjectinfoService
{
    /**
     * 插入数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    @Override
    public int insert(Projectinfo record) {
        return new ProjectinfoService().insert(record);
    }

    /**
     * 删除数据
     * 
     * @param guid
     *            主键guid
     * @return int
     */
    @Override
    public int deleteByGuid(String guid) {
        return new ProjectinfoService().deleteByGuid(guid);
    }

    /**
     * 更新数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    @Override
    public int update(Projectinfo record) {
        return new ProjectinfoService().update(record);
    }

    /**
     * 查询数量
     * 
     * @param conditionMap
     *            查询条件集合
     * @return Integer
     */
    @Override
    public Integer countProjectinfo(Map<String, Object> conditionMap) {
        return new ProjectinfoService().countProjectinfo(conditionMap);
    }

    /**
     * 根据ID查找单个实体
     * 
     * @param primaryKey
     *            主键
     * @return T extends BaseEntity
     */
    @Override
    public Projectinfo find(Object primaryKey) {
        return new ProjectinfoService().find(primaryKey);
    }

    /**
     * 查找单条记录
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T {String、Integer、Long、Record、FrameOu、Object[]等}
     */
    @Override
    public Projectinfo find(Map<String, Object> conditionMap) {
        return new ProjectinfoService().find(conditionMap);
    }

    /**
     * 查找一个list
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T extends BaseEntity
     */
    @Override
    public List<Projectinfo> findList(Map<String, Object> conditionMap) {
        return new ProjectinfoService().findList(conditionMap);
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
    @Override
    public PageData<Projectinfo> paginatorList(Map<String, Object> conditionMap, int pageNumber, int pageSize) {
        return new ProjectinfoService().paginatorList(conditionMap, pageNumber, pageSize);
    }

    @Override
    public int checkName(String projectname) {
        return new ProjectinfoService().checkName(projectname);
    }

    @Override
    public int checkExist(String projectNameOld, String projectNameNew) {
        return new ProjectinfoService().checkExist(projectNameOld, projectNameNew);
    }

    @Override
    public String getAllUser(String projectUser) {
        return new ProjectinfoService().getAllUser(projectUser);
    }

}
