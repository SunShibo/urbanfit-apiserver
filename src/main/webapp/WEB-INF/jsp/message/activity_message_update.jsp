<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>活动资讯修改</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="/static/js/manage/activity_message_update.js"></script>
</head>
<body>
    <form id="activityMessageForm" method="post">
        标题：<input type="text" name="title" value="${activityMessage.title}"><br/>
        缩略图：
            <c:if test="${activityMessage.thumbnails == null}">
                <img width="65px;" height="65px;" id="uploadImage"/>
            </c:if>
            <c:if test="${activityMessage.thumbnails != null}">
                <img width="65px;" height="65px;" id="uploadImage" src="${baseUrl}${activityMessage.thumbnails}"/>
            </c:if>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="删除" id="B_delete_thumbnails">
            <input type="hidden" name="thumbnails" value="${activityMessage.thumbnails}"><br/>
        内容：
            <textarea name="messageContent">${activityMessage.content}</textarea>
            <input type="hidden" name="content"/><br/>
        <input type="hidden" name="messageId" value="${activityMessage.messageId}">
        <input type="button" value="发布" id="B_submit">
    </form>
</body>
</html>