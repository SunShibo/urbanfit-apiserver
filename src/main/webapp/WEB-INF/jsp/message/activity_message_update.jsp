<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>活动资讯修改</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/cityselect.js"></script>
    <script type="text/javascript" src="/static/js/manage/activity_message_update.js"></script>
</head>
<body>
    <form id="activityMessageForm" method="post">
        标题：<input type="text" name="title" value="${activityMessage.title}"><br/>
        缩略图：<br/>
        内容：<br/>
        <input type="button" value="发布" id="B_submit">
    </form>
</body>
</html>
