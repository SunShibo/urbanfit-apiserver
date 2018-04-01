package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.dao.BannerDao;
import com.urbanfit.apiserver.entity.Banner;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.query.PageObject;
import com.urbanfit.apiserver.query.PageObjectUtil;
import com.urbanfit.apiserver.query.QueryInfo;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.util.StringUtils;
import com.urbanfit.apiserver.util.UploadImageUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/1.
 */
@Service("bannerService")
@Transactional
public class BannerService {
    @Resource
    private BannerDao bannerDao;

    public PageObject<Banner> queryBannerList(Integer type, QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        if(type != null){
            map.put("type", type);
        }
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        PageObjectUtil<Banner> page = new PageObjectUtil<Banner>();
        return page.savePageObject(bannerDao.queryBannerCount(map), bannerDao.queryBannerList(map), queryInfo);
    }

    public String deleteBanner(Integer bannerId){
        if(bannerId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Banner banner = bannerDao.queryBannerById(bannerId);
        if(banner == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "banner不存在", "").toString();
        }
        bannerDao.deleteBanner(bannerId);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "删除成功", "").toString();
    }

    public Banner queryBannerById(Integer bannerId){
        return bannerDao.queryBannerById(bannerId);
    }

    public String uploadImageUrl(MultipartFile file){
        if(file == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "paramError")) ;
        }
        if( file.getSize() > 2 * 1024 * 1024){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "big")) ;
        }
        String imageUrl = UploadImageUtil.uploadImageUrl(file, "banner_image_url");
        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("imageUrl", imageUrl);

        JSONObject resultJo = new JSONObject();
        resultJo.put("code", "1");
        resultJo.put("message", "success");
        resultJo.put("data", jo.toString());
        return resultJo.toString();
    }

    public String addBanner(Banner banner){
        if(banner == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        if(StringUtils.isEmpty(banner.getTitle()) || StringUtils.isEmpty(banner.getImageUrl())
                || banner.getType() == null || StringUtils.isEmpty(banner.getLinkUrl())){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        bannerDao.addBanner(banner);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "添加banner成功", "").toString();
    }

    public String updateBanner(Banner banner){
        if(banner == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        if(StringUtils.isEmpty(banner.getTitle()) || StringUtils.isEmpty(banner.getImageUrl())
                || banner.getType() == null ||StringUtils.isEmpty(banner.getLinkUrl())
                || banner.getBannerId() ==  null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Banner bannerDetail = bannerDao.queryBannerById(banner.getBannerId());
        if(bannerDetail == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "banner不存在", "").toString();
        }
        bannerDetail.setTitle(banner.getTitle());
        bannerDetail.setType(banner.getType());
        bannerDetail.setImageUrl(banner.getImageUrl());
        bannerDetail.setLinkUrl(banner.getLinkUrl());
        bannerDetail.setSort(banner.getSort());
        bannerDao.updateBanner(bannerDetail);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "修改banner成功", "").toString();
    }
}
