<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>课程修改</title>
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
                                    <c:if test="${course.courseType == 1}">成人课程</c:if>
                                    <c:if test="${course.courseType == 2}">青少年课程</c:if>
                                    <c:if test="${course.courseType == 3}">私教课程</c:if>
                                    <c:if test="${course.courseType == 4}">特色课程</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>标题：</td>
                                <td>
                                    <input type="text" name="courseTitle" value="${course.courseTitle}"
                                           placeholder="请输入课程标题"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>价格：</td>
                                <td>
                                    <input type="text" name="coursePrice" value="${course.coursePrice}"
                                           placeholder="请输入课程价格"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>所属区域：</td>
                                <td>
                                    <div id="courseDistrictDiv" class="select"></div>
                                    <input type="hidden" name="districtIndex" value="1">
                                    <input type="hidden" name="courseDistrict" value="${course.courseDistrict}">
                                </td>
                            </tr>
                            <tr>
                                <td>课程图片：</td>
                                <td>
                                    <div class="suolue">
                                        <div class="uploadimg">
                                            <c:if test="${course.courseImageUrl == null}">
                                                <img width="160px;" height="160px;" id="uploadImage" src="../static/img/u37.png"/>
                                            </c:if>
                                            <c:if test="${course.courseImageUrl != null}">
                                                <img width="160px;" height="160px;" id="uploadImage" src="${baseUrl}${course.courseImageUrl}"/>
                                            </c:if>
                                            <input type="hidden" name="courseImageUrl" value="${course.courseImageUrl}"><br/>
                                        </div>
                                        <div class="zi">
                                            <span style="color:#FF0000;">*必填</span>
                                            <p class="del" id="B_delete_image">删除</p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>课程内容：</td>
                                <td>
                                    <div class="edit">
                                        <textarea name="content">${course.introduce}</textarea>
                                        <input name="introduce" type="hidden"/>
                                        <input type="hidden" name="courseId" value="${course.courseId}">
                                        <input type="hidden" name="courseName" value="${course.courseName}">
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
</body>