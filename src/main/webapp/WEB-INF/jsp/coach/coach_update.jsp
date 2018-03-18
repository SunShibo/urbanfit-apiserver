<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>教练修改</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="/static/js/manage/coach_update.js"></script>
</head>
<body>
    <form id="coachForm" method="post">
        教练名称：<input type="text" name="coachName" value="${coach.coachName}"><br/>
        教练职称：<input type="text" name="coachTitle" value="${coach.coachTitle}"><br/>
        头像：
        <c:if test="${empty coach.headPortrait}">
            <img width="65px;" height="65px;" id="uploadImage"/>
        </c:if>
        <c:if test="${not empty coach.headPortrait}">
            <img width="65px;" height="65px;" id="uploadImage" src="${baseUrl}${coach.headPortrait}"/>
        </c:if>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" value="删除" id="B_delete_headPortrait">
        <input type="hidden" name="headPortrait" value="${coach.headPortrait}"><br/>
        内容：
        <textarea name="content">${coach.introduce}</textarea>
        <input name="introduce" type="hidden"/><br/>
        <input type="hidden" name="coachId" value="${coach.coachId}">
        <input type="button" value="发布" id="B_submit">
    </form>
</body>
</html>