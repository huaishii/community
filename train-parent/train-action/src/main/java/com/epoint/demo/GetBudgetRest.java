package com.epoint.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epoint.basic.api.common.ApiBaseController;
import com.epoint.core.utils.container.ContainerFactory;
import com.epoint.core.utils.json.JsonUtil;
import com.epoint.frame.service.metadata.code.api.ICodeItemsService;
import com.epoint.xmcg.projectbudgetinfo.api.IProjectbudgetinfoService;
import com.epoint.xmcg.projectbudgetinfo.api.entity.Projectbudgetinfo;

@RestController
public class GetBudgetRest extends ApiBaseController
{

    @PostMapping("/demo/getBUdgetByProjectName")
    public String getBUdgetByProjectName(@RequestParam("params")String params) {
        Map<String, Object> toMap = JsonUtil.jsonToMap(params);
        String projectName = (String) toMap.get("projectName");
        IProjectbudgetinfoService iProjectbudgetinfoService = ContainerFactory.getContainInfo().getComponent(IProjectbudgetinfoService.class);
        List<Projectbudgetinfo> list = iProjectbudgetinfoService.getBUdgetByProjectName(projectName);
        ICodeItemsService iCodeItemsService = ContainerFactory.getContainInfo().getComponent(ICodeItemsService.class);
        
        for (Projectbudgetinfo projectbudgetinfo : list) {
            projectbudgetinfo.setBudgettype(iCodeItemsService.getItemTextByCodeName("预算类型", projectbudgetinfo.getBudgettype()));
        }
        
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("data", list);
        return buildSuccessResponse(JsonUtil.objectToJson(resMap));
    }
    
}
