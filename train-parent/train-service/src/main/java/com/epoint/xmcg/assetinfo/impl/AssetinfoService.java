package com.epoint.xmcg.assetinfo.impl;

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
import com.epoint.frame.service.metadata.systemparameters.api.IConfigService;
import com.epoint.xmcg.assetinfo.api.entity.Assetinfo;
import com.epoint.xmcg.projectbudgetinfo.api.entity.Projectbudgetinfo;
import com.epoint.xmcg.projectinfo.api.entity.Projectinfo;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

/**
 * 采购基本信息表对应的后台service
 * 
 * @author Epoint
 * @version [版本号, 2021-08-23 11:20:52]
 */
public class AssetinfoService
{
    /**
     * 数据增删改查组件
     */
    protected ICommonDao baseDao;

    public AssetinfoService() {
        baseDao = CommonDao.getInstance();
    }

    /**
     * 插入数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    public int insert(Assetinfo record) {
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
        T t = baseDao.find(Assetinfo.class, guid);
        return baseDao.delete(t);
    }

    /**
     * 更新数据
     * 
     * @param record
     *            BaseEntity或Record对象 <必须继承Record>
     * @return int
     */
    public int update(Assetinfo record) {
        return baseDao.update(record);
    }

    /**
     * 查询数量
     * 
     * @param conditionMap
     *            查询条件集合
     * @return Integer
     */
    public Integer countAssetinfo(Map<String, Object> conditionMap) {
        SqlConditionUtil conditionUtil = new SqlConditionUtil(conditionMap);
        conditionUtil.setSelectFields("count(*)");
        List<Object> params = new ArrayList<>();
        return baseDao.queryInt(new SqlHelper().getSqlComplete(Assetinfo.class, conditionUtil.getMap(), params),
                params.toArray());
    }

    /**
     * 根据ID查找单个实体
     * 
     * @param primaryKey
     *            主键
     * @return T extends BaseEntity
     */
    public Assetinfo find(Object primaryKey) {
        return baseDao.find(Assetinfo.class, primaryKey);
    }

    /**
     * 查找单条记录
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T {String、Integer、Long、Record、FrameOu、Object[]等}
     */
    public Assetinfo find(Map<String, Object> conditionMap) {
        List<Object> params = new ArrayList<>();
        return baseDao.find(new SqlHelper().getSqlComplete(Assetinfo.class, conditionMap, params), Assetinfo.class,
                params.toArray());
    }

    /**
     * 查找一个list
     * 
     * @param conditionMap
     *            查询条件集合
     * @return T extends BaseEntity
     */
    public List<Assetinfo> findList(Map<String, Object> conditionMap) {
        List<Object> params = new ArrayList<>();
        return baseDao.findList(new SqlHelper().getSqlComplete(Assetinfo.class, conditionMap, params), Assetinfo.class,
                params.toArray());
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
    public PageData<Assetinfo> paginatorList(Map<String, Object> conditionMap, int pageNumber, int pageSize) {
        List<Object> params = new ArrayList<>();
        List<Assetinfo> list = baseDao.findList(new SqlHelper().getSqlComplete(Assetinfo.class, conditionMap, params),
                pageNumber, pageSize, Assetinfo.class, params.toArray());
        int count = countAssetinfo(conditionMap);
        PageData<Assetinfo> pageData = new PageData<Assetinfo>(list, count);
        return pageData;
    }

    /**
     * 根据采购rowguid更新预算已使用额
     * 
     * @param assetrowguid
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void updateUseMoneny(String assetrowguid) {
        Assetinfo assetinfo = find(assetrowguid);
        String budgetGuid = assetinfo.getBudgetguid();
        if (StringUtil.isNotBlank(budgetGuid)) {
            Projectbudgetinfo projectbudgetinfo = baseDao.find(Projectbudgetinfo.class, budgetGuid);
            projectbudgetinfo.setUsebudgetmoney(projectbudgetinfo.getUsebudgetmoney() + assetinfo.getTotalmoney());
            baseDao.update(projectbudgetinfo);
        }
    }

    /**
     * 恢复采购rowguid更新预算已使用额
     * 
     * @param assetrowguid
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void removeUseMoneny(String assetrowguid) {
        Assetinfo assetinfo = find(assetrowguid);
        String budgetGuid = assetinfo.getBudgetguid();
        if (StringUtil.isNotBlank(budgetGuid)) {
            Projectbudgetinfo projectbudgetinfo = baseDao.find(Projectbudgetinfo.class, budgetGuid);
            projectbudgetinfo.setUsebudgetmoney(projectbudgetinfo.getUsebudgetmoney() - assetinfo.getTotalmoney());
            baseDao.update(projectbudgetinfo);
        }
    }

    /**
     * 获取项目经理
     * 
     * @param assetrowguid
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String findProjectmanager(String assetrowguid) {
        Assetinfo assetinfo = find(assetrowguid);
        String budgetGuid = assetinfo.getBudgetguid();
        if (StringUtil.isNotBlank(budgetGuid)) {
            Projectbudgetinfo projectbudgetinfo = baseDao.find(Projectbudgetinfo.class, budgetGuid);
            Projectinfo projectinfo = baseDao.find(Projectinfo.class, projectbudgetinfo.getProjectguid());
            return projectinfo.getProjectmanager();
        }
        return "";
    }

    /**
     * 超过物品采购总价
     * 
     * @param assetrowguid
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean overMoneny(String assetrowguid) {
        Assetinfo assetinfo = find(assetrowguid);
        Integer totalmoney = assetinfo.getTotalmoney();
        IConfigService iConfigService = ContainerFactory.getContainInfo().getComponent(IConfigService.class);
        String limitMoneny = iConfigService.getFrameConfigValue("limitmoneny");
        return totalmoney > Integer.parseInt(limitMoneny);
    }

    /**
     * 没有超过物品采购总价
     * 
     * @param assetrowguid
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean LowMoneny(String assetrowguid) {
        return !overMoneny(assetrowguid);
    }

    /**
     * 更新状态
     * 
     * @param assetrowguid
     * @param status
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void updateStatus(String assetrowguid, String status) {
        Assetinfo assetinfo = find(assetrowguid);
        assetinfo.setStatus(Integer.parseInt(status));
        baseDao.update(assetinfo);
    }

}
