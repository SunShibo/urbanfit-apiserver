<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>教练添加</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="/static/js/kindeditor/zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="/static/js/manage/coach_add.js"></script>
</head>
<body>
    <form id="coachForm" method="post">
        教练名称：<input type="text" name="coachName"><br/>
        教练职称：<input type="text" name="coachTitle"><br/>
        头像：
            <img width="65px;" height="65px;" id="uploadImage"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="删除" id="B_delete_headPortrait">
            <input type="hidden" name="headPortrait"><br/>
        内容：
            <textarea name="content"></textarea>
            <input name="introduce" type="hidden"/><br/>
        <input type="button" value="发布" id="B_submit">
    </form>
</body>
</html>