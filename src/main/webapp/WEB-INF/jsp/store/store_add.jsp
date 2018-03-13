<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>教练认证</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/cityselect.js"></script>
    <script type="text/javascript" src="/static/js/manage/store_add.js"></script>
</head>
<body>
    门店名称：<input type="text" name="storeName"><br/>
    所属区域：<div id="city_1">
        <select class="prov" id="proviceId" name="provice"></select>
        <select class="city" id="cityId" disabled="disabled" name="city"></select>
        <select class="dist" id="districtId" disabled="disabled" name="district"></select>
        <span class="reg-con gary">请选择省市</span>
    </div>
    详细地址：<input type="text" name="storeAddress"><br/>
    联系地址：<input type="text" name="mobile"><br/>
    联系人：<input type="text" name="contactName"><br/>

</body>
</html>
