<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>教练添加</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/manage/coach_add.js"></script>
</head>
<body>
    <form id="coachForm" method="post">
        教练名称：<input type="text" name="coachName"><br/>
        教练职称：<input type="text" name="coachTitle"><br/>
        头像：<br/>
        内容：<br/>
        <input type="button" value="发布" id="B_submit">
    </form>
</body>
</html>