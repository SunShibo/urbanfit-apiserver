package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.Module;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/2.
 */
public interface ModuleDao {

    public List<Module> queryModule();

    public Module queryModuleById(Integer moduleId);

    public void updateModule(Module module);

    public void updateModuleStatus(Map<String, Object> map);

    public Module queryModuleByType(Integer type);
}
