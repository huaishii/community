package com.epoint.xmcg.projectbudgetinfo.impl;

import java.util.List;
import java.util.Map;
import com.epoint.database.peisistence.crud.impl.model.PageData;
import org.springframework.stereotype.Component;

import com.epoint.xmcg.projectbudgetinfo.api.IProjectbudgetinfoService;
import com.epoint.xmcg.projectbudgetinfo.api.entity.Projectbudgetinfo;

/**
 * 项目预算信息表对应的后台service实现类
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:42]
 */
@Component
public class ProjectbudgetinfoServiceImpl implements IProjectbudgetinfoService
{
    /**
     * 插入数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    @Override
    public int insert(Projectbudgetinfo record) {
        return new ProjectbudgetinfoService().insert(record);
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
        return new ProjectbudgetinfoService().deleteByGuid(guid);
    }

    /**
     * 更新数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    @Override
    public int update(Projectbudgetinfo record) {
        return new ProjectbudgetinfoService().update(record);
    }

    /**
     * 查询数量
     * 
     * @param conditionMap
     *            查询条件集合
     * @return Integer
     */
    @Override
    public Integer countProjectbudgetinfo(Map<String, Object> conditionMap) {
        return new ProjectbudgetinfoService().countProjectbudgetinfo(conditionMap);
    }

    /**
     * 根据ID查找单个实体
     * 
     * @param primaryKey
     *            主键
     * @return T extends BaseEntity
     */
    @Override
    public Projectbudgetinfo find(Object primaryKey) {
       return new ProjectbudgetinfoService().find(primaryKey);
    }

    /**
     * 查找单条记录
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T {String、Integer、Long、Record、FrameOu、Object[]等}
     */
    @Override
    public Projectbudgetinfo find(Map<String, Object> conditionMap) {
        return new ProjectbudgetinfoService().find(conditionMap);
    }

    /**
     * 查找一个list
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T extends BaseEntity
     */
    @Override
    public List<Projectbudgetinfo> findList(Map<String, Object> conditionMap) {
       return new ProjectbudgetinfoService().findList(conditionMap);
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
    public PageData<Projectbudgetinfo> paginatorList(Map<String, Object> conditionMap, int pageNumber, int pageSize) {
       return new ProjectbudgetinfoService().paginatorList(conditionMap, pageNumber, pageSize);
    }

    @Override
    public int getMonenyBefore(String projectguid) {
        return new ProjectbudgetinfoService().getMonenyBefore(projectguid);
    }

    @Override
    public List<Projectbudgetinfo> getBudgetInfoByProjectGuid(String rowguid) {
        return new ProjectbudgetinfoService().getBudgetInfoByProjectGuid(rowguid);
    }

    @Override
    public List<Projectbudgetinfo> getBUdgetByProjectName(String projectName) {
        return new ProjectbudgetinfoService().getBUdgetByProjectName(projectName);
    }

}
