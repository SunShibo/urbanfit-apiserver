<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>活动资讯添加</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="/static/js/manage/activity_message_add.js"></script>
</head>
<body>
    <form id="activityMessageForm" method="post">
        标题：<input type="text" name="title"><br/>
        缩略图：
            <img width="65px;" height="65px;" id="uploadImage"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="删除" id="B_delete_thumbnails">
            <input type="hidden" name="thumbnails"><br/>
        内容：
            <textarea name="messageContent"></textarea>
            <input name="content" type="hidden"/><br/>
        <input type="button" value="发布" id="B_submit">
    </form>
</body>
</html>