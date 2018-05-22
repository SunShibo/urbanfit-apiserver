<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>课程添加</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
</head>
<body>
<div class="index clear">
    <jsp:include page="../main.jsp"></jsp:include>
    <div class="indexRight1">
        <div class="title">课程管理 > 添加课程</div>
        <div class="tablebox2">
            <form id="courseForm" method="post">
                <table cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td class="td1">课程名称：</td>
                        <td class="td2">
                            <input type="text" name="courseName" placeholder="请输入课程名称"><em>*必填</em>
                        </td>
                    </tr>
                    <tr>
                        <td>课程类型：</td>
                        <td>
                            <div class="select">
                                <div>
                                    <select name="courseType">
                                        <option value="1">成人课程</option>
                                        <option value="2">青少年课程</option>
                                        <option value="3">私教课程</option>
                                        <option value="4">特色课程</option>
                                    </select>
                                </div>
                            </div>
                            <em>*必填</em>
                        </td>
                    </tr>
                    <tr>
                        <td>关联俱乐部：</td>
                        <td>
                            <input type="text"><em>*必填</em>
                        </td>
                    </tr>
                    <tr>
                        <td>规格属性：</td>
                        <td id="courseSizeDiv">
                            <%--<div id="courseSizeDiv_1">
                                <input type="text" placeholder="请输入规格属性" class="long" id="sizeType_1"><input type="button" id="B_add_sizeType" value="添加属性" class="course-btn"><br/>
                                <input type="text" placeholder="请输入规格信息" class="short" id="sizeName_1_1"><input type="button" id="B_add_sizeName_1" data-aid="1" value="添加信息" class="course-btn"><br/>
                                <input type="hidden" name="sizeNameIndex_1" value="1">
                            </div>
                            <input type="hidden" name="sizeTypeIndex" value="1">--%>

                            <input type="button" class="cloneSku" value="添加自定义sku属性"/>
                            <!--sku模板,用于克隆,生成自定义sku-->
                            <div id="skuCloneModel" style="display: none;">
                                <div class="clear"></div>
                                <ul class="SKU_TYPE">
                                    <li is_required='0' propid='' sku-type-name="">
                                        <a href="javascript:void(0);" class="delCusSkuType">移除</a>
                                        <input type="text" class="cusSkuTypeInput" />：
                                    </li>
                                </ul>
                                <ul>
                                    <li>
                                        <input type="checkbox" class="model_sku_val" propvalid='' value="" />
                                        <input type="text" class="cusSkuValInput" />
                                    </li>
                                    <input type="button" class="cloneSkuVal" value="添加自定义属性值"/>
                                </ul>
                                <div class="clear"></div>
                            </div>
                            <!--单个sku值克隆模板-->
                            <li style="display: none;" id="onlySkuValCloneModel">
                                <input type="checkbox" class="model_sku_val" propvalid='' value="" />
                                <input type="text" class="cusSkuValInput" />
                                <a href="javascript:void(0);" class="delCusSkuVal">删除</a>
                            </li>
                            <div class="clear"></div>
                            <div id="skuTable"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>价格：</td>
                        <td>
                            <div id="coursePriceDiv"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>课程内容：</td>
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
                            <input type="hidden" name="courseDistrict" value="${course.courseDistrict}">
                            <a href="javascript:void(0);" id="B_submit">发布</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</div>
<script type="text/javascript" src="/static/js/sku/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/sku/createSkuTable.js"></script>
<script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
<script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
<script type="text/javascript" src="/static/js/common/distpicker.js"></script>
<script type="text/javascript" src="/static/js/manage/course_add.js"></script>
<script type="text/javascript" src="/static/js/sku/customSku.js"></script>
<script type="text/javascript" src="/static/js/sku/getSetSkuVals.js"></script>
</body>