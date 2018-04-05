package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.dao.BannerDao;
import com.urbanfit.apiserver.dao.ModuleDao;
import com.urbanfit.apiserver.entity.Banner;
import com.urbanfit.apiserver.entity.Module;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.util.DateUtils;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.util.UploadImageUtil;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    @Resource
    private BannerDao bannerDao;

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

    public String uploadImageUrl(MultipartFile file){
        if(file == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "paramError")) ;
        }
        if( file.getSize() > 2 * 1024 * 1024){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "big")) ;
        }
        String imageUrl = UploadImageUtil.uploadImageUrl(file, "module_image_url");
        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("imageUrl", imageUrl);

        JSONObject resultJo = new JSONObject();
        resultJo.put("code", "1");
        resultJo.put("message", "success");
        resultJo.put("data", jo.toString());
        return resultJo.toString();
    }

    public String queryModuleByModuleId(Integer moduleId){
        if(moduleId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Module module = moduleDao.queryModuleById(moduleId);
        if(module == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "数据不存在", "").toString();
        }
        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("module", JsonUtils.getJsonString4JavaPOJO(module, DateUtils.LONG_DATE_PATTERN));
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public String queryModuleList(Integer type){
        if(type == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        List<Banner> lstBanner = bannerDao.queryBannerByType(type);
        Module module = moduleDao.queryModuleByType(type);
        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("lstBanner", CollectionUtils.isEmpty(lstBanner) ? "" : JsonUtils.getJsonString4JavaListDate(
                lstBanner, DateUtils.LONG_DATE_PATTERN));
        jo.put("module", module == null ? "" : JsonUtils.getJsonString4JavaPOJO(module, DateUtils.LONG_DATE_PATTERN));
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }
}
