<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>门店信息修改</title>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">门店管理 > 门店添加</div>
                <form id="storeForm" method="post">
                    <div class="tablebox2">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td class="td1">门店名称：</td>
                                <td class="td2">
                                    <input type="text" name="storeName" value="${store.storeName}"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>所属区域：</td>
                                <td>
                                    <div class="select">
                                        <div id="city_info">
                                            <select class="prov" id="proviceId" name="provice"></select>
                                            <select class="city" id="cityId" disabled="disabled" name="city"></select>
                                            <select class="dist" id="districtId" disabled="disabled" name="district"></select>
                                            <input type="hidden" name="storeDistrict" value="${store.storeDistrict}">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>详细地址：</td>
                                <td>
                                    <input type="text" name="storeAddress" value="${store.storeAddress}"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>联系电话：</td>
                                <td>
                                    <input type="text" name="mobile" value="${store.mobile}"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>联系人：</td>
                                <td>
                                    <input type="text" name="contactName" value="${store.contactName}"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <input type="hidden" name="storeId" value="${store.storeId}">
                                    <a href="javascript:void(0);" id="B_submit">发布</a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/cityselect.js"></script>
    <script type="text/javascript" src="/static/js/manage/store_update.js"></script>
    <script type="text/javascript">
        var storeDistrict = '${store.storeDistrict}';
    </script>
</body>
