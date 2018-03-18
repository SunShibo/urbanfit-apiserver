<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>课程修改</title>
    <link href="css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/manage/course_update.js"></script>
    <script type="text/javascript">
        var base_image = '${base_image}';
    </script>
</head>
<body>
    <form id="courseForm" method="post">
        课程名称：
            <c:if test="${course.courseType == 1}">成人课程</c:if>
            <c:if test="${course.courseType == 2}">青少年课程</c:if>
            <c:if test="${course.courseType == 3}">私教课程</c:if>
            <c:if test="${course.courseType == 4}">特色课程</c:if><br/>
        课程内容：
        <textarea name="content">${course.introduce}</textarea>
        <input name="introduce" type="hidden"/><br/>
        <input type="hidden" name="courseId" value="${course.courseId}">
        <input type="hidden" name="courseType" value="${course.courseType}">
        <input type="button" value="发布" id="B_submit">
    </form>
</body>
</html>