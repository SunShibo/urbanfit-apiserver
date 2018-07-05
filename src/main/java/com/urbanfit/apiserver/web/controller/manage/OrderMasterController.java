package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.entity.OrderMaster;
import com.urbanfit.apiserver.service.OrderMasterService;
import com.urbanfit.apiserver.util.DateUtils;
import com.urbanfit.apiserver.util.ExcelUtil;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/25.
 */
@Controller
@RequestMapping("/order")
public class OrderMasterController extends BaseCotroller{

    @Resource
    private OrderMasterService orderMasterService;

    @RequestMapping("/list")
    public ModelAndView queryOrderMasterList(String orderInfo, String provice, String city, String district,
                                             Integer status, Integer pageNo, Integer pageSize){
        pager = orderMasterService.queryOrderMasterList(orderInfo, provice, city, district, status,
                getQueryInfo(pageNo, pageSize));

        ModelAndView view = new ModelAndView();
        view.setViewName("/order/order_master_list");
        view.addObject("lstOrder", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("orderInfo", orderInfo);
        view.addObject("provice", provice);
        view.addObject("city", city);
        view.addObject("district", district);
        view.addObject("status", status);
        return view;
    }

    @RequestMapping("/detail")
    public ModelAndView queryOderMaterDetail(String orderNum){
        OrderMaster orderMaster = orderMasterService.queryOderMaterDetail(orderNum);
        ModelAndView view = new ModelAndView();
        view.setViewName("/order/order_master_detail");
        view.addObject("orderMaster", orderMaster);
        return view;
    }

    @RequestMapping("/update")
    public void updateOrderMasterStatus(HttpServletResponse response, String orderNum){
        String result = orderMasterService.updateOrderMasterStatus(orderNum);
        safeJsonPrint(response, result);
    }


    @RequestMapping("/download")
    public void download(HttpServletResponse response, String orderInfo, String provice, String city,String dist,
                            Integer status) throws Exception{
        String fileName="订单报表";
        List<OrderMaster> lstOrderMaster = orderMasterService.queryExportOrderMaster(orderInfo, provice, city,
                dist, status);
        List<Map<String,Object>> list = createExcelRecord(lstOrderMaster);

        String[] columnNames = {"课程名称","订单号","价格",
                "支付价格","门店名称","报名人","手机号码","学生名称","创建时间",
                "支付时间","状态","是否使用优惠券","优惠码", "优惠价格"};     //列名

        String[] keys = {"courseName","orderNum","price","payPrice","StoreName", "clientName","clientMobile",
                "childrenNam", "createTime","payTime", "status", "isUseCoupon","couponNum",
                "couponNumPrice"};     //map中的key

        download(list, keys, columnNames, fileName, response);
    }


    private void download(List<Map<String,Object>> list,String[] keys, String[] columnNames,String fileName,
                         HttpServletResponse response) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(),
                "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    private List<Map<String, Object>> createExcelRecord(List<OrderMaster> projects) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        OrderMaster project = null;
        // 查询所有的用户信息
        for (int j = 0; j < projects.size(); j++) {
            project=projects.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("courseName", project.getCourseName());
            mapValue.put("orderNum", project.getOrderNum());
            mapValue.put("price", project.getPrice());
            mapValue.put("payPrice",project.getPayPrice() );
            mapValue.put("StoreName", project.getStoreName());
            mapValue.put("childrenName", project.getChildrenName());
            mapValue.put("clientMobile", project.getClientMobile());
            String createTime = project.getCreateTime() == null ? "" : DateUtils.formatDate(
                    DateUtils.LONG_DATE_PATTERN, project.getCreateTime());
            mapValue.put("createTime", createTime);
            String payTime = project.getCreateTime() == null ? "" : DateUtils.formatDate(
                    DateUtils.LONG_DATE_PATTERN, project.getPayTime());
            mapValue.put("payTime",payTime);
            if (project.getStatus()==0){
                mapValue.put("status","未支付");
            }else if (project.getStatus()==1){
                mapValue.put("status","已支付");
            }else if (project.getStatus()==2) {
                mapValue.put("status", "已退款");
            }else  if (project.getStatus()==3){
                mapValue.put("status","系统自动取消");
            }
            mapValue.put("clientName",project.getClientName());
            if(project.getIsUseCoupon()==0){
                mapValue.put("isUseCoupon","否");
            }else if (project.getIsUseCoupon()==0){
                mapValue.put("isUseCoupon","是");
            }

            mapValue.put("couponNum",project.getCouponNum());
            mapValue.put("couponNumPrice",project.getCouponPrice());
            listmap.add(mapValue);
        }
        return listmap;
    }
}
