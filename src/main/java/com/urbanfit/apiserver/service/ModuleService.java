package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.dao.ModuleDao;
import com.urbanfit.apiserver.entity.Module;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/2.
 */
@Service("moduleService")
@Transactional
public class ModuleService {
    @Resource
    private ModuleDao moduleDao;

    public List<Module> queryModule(){
        return moduleDao.queryModule();
    }

    public Module queryModuleById(Integer moduleId){
        return moduleDao.queryModuleById(moduleId);
    }

    public String updateModule(Module module){
        if(module == null || (module != null && module == null)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Module moduleDetail = moduleDao.queryModuleById(module.getModuleId());
        if(moduleDetail ==null) {
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "修改信息不存在", "").toString();
        }
        moduleDetail.setContent(module.getContent());
        moduleDao.updateModule(module);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "修改成功", "").toString();
    }

    public void updateModuleStatus(Integer moduleId, int status){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("moduleId", moduleId);
        map.put("status", status);
        moduleDao.updateModuleStatus(map);
    }
}
