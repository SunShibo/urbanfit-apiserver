<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>教练认证</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/cityselect.js"></script>
    <script type="text/javascript" src="/static/js/manage/store_update.js"></script>
    <script type="text/javascript">
        var storeDistrict = '${store.storeDistrict}';
    </script>
</head>
<body>
    <form id="storeForm" method="post">
        门店名称：<input type="text" name="storeName" value="${store.storeName}"><br/>
        所属区域：<div id="city_info">
            <select class="prov" id="proviceId" name="provice"></select>
            <select class="city" id="cityId" disabled="disabled" name="city"></select>
            <select class="dist" id="districtId" disabled="disabled" name="district"></select>
            <input type="hidden" name="storeDistrict" value="${store.storeDistrict}">
        </div>
        详细地址：<input type="text" name="storeAddress" value="${store.storeAddress}" ><br/>
        联系电话：<input type="text" name="mobile" value="${store.mobile}"><br/>
        联系人：<input type="text" name="contactName" value="${store.contactName}"><br/>
        <input type="hidden" name="storeId" value="${store.storeId}">
        <input type="button" value="提交" id="B_submit">
    </form>
</body>
</html>
