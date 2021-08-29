package com.epoint.xmcg.projectbudgetinfo.api;

import java.util.List;
import java.util.Map;
import com.epoint.database.peisistence.crud.impl.model.PageData;

import com.epoint.xmcg.projectbudgetinfo.api.entity.Projectbudgetinfo;

/**
 * 项目预算信息表对应的后台service接口
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:42]
 */
public interface IProjectbudgetinfoService
{ 
    /**
     * 插入数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    public int insert(Projectbudgetinfo record);

    /**
     * 删除数据
     * 
     * @param guid
     *            主键guid
     * @return int
     */
    public int deleteByGuid(String guid);

    /**
     * 更新数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    public int update(Projectbudgetinfo record);
    
    /**
     * 查询数量
     * 
     * @param conditionMap
     *            查询条件集合
     * @return Integer
     */
    public Integer countProjectbudgetinfo(Map<String, Object> conditionMap);    

    /**
     * 根据ID查找单个实体
     * 
     * @param primaryKey
     *            主键
     * @return T extends BaseEntity
     */
    public Projectbudgetinfo find(Object primaryKey);


    /**
     * 查找单条记录
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T {String、Integer、Long、Record、FrameOu、Object[]等}
     */
    public Projectbudgetinfo find(Map<String, Object> conditionMap);

    /**
     * 查找一个list
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T extends BaseEntity
     */
    public List<Projectbudgetinfo> findList(Map<String, Object> conditionMap);

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
    public PageData<Projectbudgetinfo> paginatorList(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public int getMonenyBefore(String projectguid);

    public List<Projectbudgetinfo> getBudgetInfoByProjectGuid(String rowguid);

    public List<Projectbudgetinfo> getBUdgetByProjectName(String projectName);

}
