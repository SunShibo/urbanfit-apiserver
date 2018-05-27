<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>课程修改</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <style>
        .ke-container{width: 96% !important;}
    </style>
    <script type="text/javascript">
        var base_image = '${base_image}';
    </script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">课程管理 > 课程编辑</div>
                <div class="tablebox2">
                    <form id="courseForm" method="post">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td class="td1">课程名称：</td>
                                <td class="td2">
                                    <input type="text" name="courseName" value="${course.courseName}"
                                           placeholder="请输入课程名称"><em>*必填</em>
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
                                    <div id="courseStoreDiv"></div><em>*必填</em>
                                    <div><input type="button" id="B_add_store" value="添加俱乐部" class="store-btn"></div>
                                    <input type="hidden" name="storeIds" value="${course.storeId}">
                                </td>
                            </tr>
                            <tr>
                                <td>规&nbsp;&nbsp;&nbsp;&nbsp;格：</td>
                                <td id="courseSizeDiv">
                                    <input type="hidden" name="sizeNameIndex" value="1">
                                    <input type="hidden" name="sizeTypeIndex" value="1">
                                    <input type="hidden" name="courseSizeInfo">
                                </td>
                            </tr>
                            <tr>
                                <td>价&nbsp;&nbsp;&nbsp;&nbsp;格：</td>
                                <td>
                                    <div id="coursePriceDiv"></div>
                                    <input type="hidden" name="sizePriceInfo">
                                </td>
                            </tr>
                            <tr>
                                <td>课程内容：</td>
                                <td>
                                    <div class="edit">
                                        <textarea name="content">${course.introduce}</textarea>
                                        <input name="introduce" type="hidden"/>
                                        <input type="hidden" name="courseId" value="${course.courseId}">
                                        <input type="hidden" name="courseType" value="${course.courseType}">
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
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="/static/js/common/distpicker.js"></script>
    <script type="text/javascript" src="/static/js/manage/course_update.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/layer/layer.js"></script>
    <script type="text/javascript">
        var course = {
            courseId : '${course.courseId}',
            courseType : '${course.courseType}',
            storeId : '${course.storeId}'
        };
    </script>
</body>