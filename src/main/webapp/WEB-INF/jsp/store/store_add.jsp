<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>门店信息添加</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
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
                                    <input type="text" name="storeName" placeholder="请输入门店名称"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>所属区域：</td>
                                <td>
                                    <div class="select">
                                        <div id="city_info" data-toggle="distpicker">
                                            <select class="prov" id="proviceId" name="provice"></select>
                                            <select class="city" id="cityId" name="city"></select>
                                            <select class="dist" id="districtId" name="district"></select>
                                            <input type="hidden" name="storeDistrict">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>详细地址：</td>
                                <td>
                                    <input type="text" placeholder="请输入详细地址" name="storeAddress"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>联系电话：</td>
                                <td>
                                    <input type="text" placeholder="请输入联系电话" name="mobile"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>联系人：</td>
                                <td>
                                    <input type="text" placeholder="请输入联系人" name="contactName"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>关联课程：</td>
                                <td>
                                    <div id="storeCourseDiv"></div><em>*必填</em>
                                    <div><input type="button" id="B_add_course" value="添加课程" class="store-btn"></div>
                                    <input type="hidden" name="courseIds">
                                </td>
                            </tr>
                            <tr>
                                <td>门店介绍：</td>
                                <td>
                                    <div class="edit">
                                        <textarea name="content"></textarea>
                                        <input name="introduce" type="hidden"/>
                                    </div>
                                    <em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
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
    <script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/common/distpicker.js"></script>
    <script type="text/javascript" src="/static/js/manage/store_add.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/layer/layer.js"></script>
</body>