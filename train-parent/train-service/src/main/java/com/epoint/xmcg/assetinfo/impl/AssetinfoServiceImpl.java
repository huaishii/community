package com.epoint.xmcg.assetinfo.impl;

import java.util.List;
import java.util.Map;
import com.epoint.database.peisistence.crud.impl.model.PageData;
import org.springframework.stereotype.Component;

import com.epoint.xmcg.assetinfo.api.IAssetinfoService;
import com.epoint.xmcg.assetinfo.api.entity.Assetinfo;

/**
 * 采购基本信息表对应的后台service实现类
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:52]
 */
@Component
public class AssetinfoServiceImpl implements IAssetinfoService
{
    /**
     * 插入数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    @Override
    public int insert(Assetinfo record) {
        return new AssetinfoService().insert(record);
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
        return new AssetinfoService().deleteByGuid(guid);
    }

    /**
     * 更新数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    @Override
    public int update(Assetinfo record) {
        return new AssetinfoService().update(record);
    }

    /**
     * 查询数量
     * 
     * @param conditionMap
     *            查询条件集合
     * @return Integer
     */
    @Override
    public Integer countAssetinfo(Map<String, Object> conditionMap) {
        return new AssetinfoService().countAssetinfo(conditionMap);
    }

    /**
     * 根据ID查找单个实体
     * 
     * @param primaryKey
     *            主键
     * @return T extends BaseEntity
     */
    @Override
    public Assetinfo find(Object primaryKey) {
       return new AssetinfoService().find(primaryKey);
    }

    /**
     * 查找单条记录
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T {String、Integer、Long、Record、FrameOu、Object[]等}
     */
    @Override
    public Assetinfo find(Map<String, Object> conditionMap) {
        return new AssetinfoService().find(conditionMap);
    }

    /**
     * 查找一个list
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T extends BaseEntity
     */
    @Override
    public List<Assetinfo> findList(Map<String, Object> conditionMap) {
       return new AssetinfoService().findList(conditionMap);
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
    public PageData<Assetinfo> paginatorList(Map<String, Object> conditionMap, int pageNumber, int pageSize) {
       return new AssetinfoService().paginatorList(conditionMap, pageNumber, pageSize);
    }

}
