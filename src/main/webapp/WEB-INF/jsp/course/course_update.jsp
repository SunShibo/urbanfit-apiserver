<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>课程修改</title>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/manage/course_update.js"></script>
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
</body>