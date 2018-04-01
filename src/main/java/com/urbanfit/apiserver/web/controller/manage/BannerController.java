package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.entity.Banner;
import com.urbanfit.apiserver.service.BannerService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/1.
 */
@Controller
@RequestMapping("/banner")
public class BannerController extends BaseCotroller{

    @Resource
    private BannerService bannerService;

    @RequestMapping("/list")
    public ModelAndView queryBannerList(Integer pageNo, Integer pageSize, Integer type){
        ModelAndView view = new ModelAndView();
        pager = bannerService.queryBannerList(type, getQueryInfo(pageNo, pageSize));
        view.setViewName("/banner/banner_list");
        view.addObject("lstBanner", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("type", type);
        return view;
    }

    @RequestMapping("/toAdd")
    public ModelAndView redirectAddPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/banner/banner_add");
        return view;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer bannerId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/banner/banner_update");
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        view.addObject("banner", bannerService.queryBannerById(bannerId));
        return view;
    }

    @RequestMapping("/delete")
    public void deleteBanner(HttpServletResponse response, Integer bannerId){
        String result = bannerService.deleteBanner(bannerId);
        safeTextPrint(response, result);
    }

    @RequestMapping("/uploadImage")
    public void uploadImageUrl(HttpServletResponse response, @RequestParam("myFile") MultipartFile file){
        String result = bannerService.uploadImageUrl(file);
        safeTextPrint(response, result);
    }

    @RequestMapping("/add")
    public void addBanner(HttpServletResponse response, Banner banner){
        String result = bannerService.addBanner(banner);
        safeTextPrint(response, result);
    }

    @RequestMapping("/update")
    public void updateBanner(HttpServletResponse response, Banner banner){
        String result = bannerService.updateBanner(banner);
        safeTextPrint(response, result);
    }
}
