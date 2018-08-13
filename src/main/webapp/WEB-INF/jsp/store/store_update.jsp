<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>门店信息修改</title>
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
                                    <input type="text" name="storeName" value="${store.storeName}"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>所属区域：</td>
                                <td>
                                    <div class="select">
                                        <div id="distpicker">
                                            <select class="prov" id="proviceId" name="provice"></select>
                                            <select class="city" id="cityId" name="city"></select>
                                            <select class="dist" id="districtId" name="district"></select>
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
                                <td>关联课程：</td>
                                <td>
                                    <div id="storeCourseDiv"></div><em>*必填</em>
                                    <div><input type="button" id="B_add_course" value="添加课程" class="store-btn"></div>
                                    <input type="hidden" name="courseIds" value="${store.courseIds}">
                                </td>
                            </tr>
                            <tr>
                                <td>门店图片：</td>
                                <td>
                                    <div class="suolue">
                                        <div class="uploadimg">
                                            <c:if test="${store.storeImageUrl == null}">
                                                <img width="160px;" height="160px;" id="uploadImage" src="../static/img/u37.png"/>
                                            </c:if>
                                            <c:if test="${store.storeImageUrl != null}">
                                                <img width="160px;" height="160px;" id="uploadImage" src="${baseUrl}${store.storeImageUrl}"/>
                                            </c:if>
                                            <input type="hidden" name="storeImageUrl" value="${store.storeImageUrl}"><br/>
                                        </div>
                                        <div class="zi">
                                            <span style="color:#FF0000;">*必填</span>
                                            <p class="del" id="B_delete_image">删除</p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>门店介绍：</td>
                                <td>
                                    <div class="edit">
                                        <textarea name="content">${store.introduce}</textarea>
                                        <input name="introduce" type="hidden" value="${store.introduce}"/>
                                    </div>
                                    <em>*必填</em>
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
    <script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/common/distpicker.js"></script>
    <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="/static/js/manage/store_update.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/layer/layer.js"></script>
    <script type="text/javascript">
        var storeDistrict = '${store.storeDistrict}';
        var storeId = '${store.storeId}';
    </script>
</body>
