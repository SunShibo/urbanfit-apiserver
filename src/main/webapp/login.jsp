<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>众力飞特后台管理系统</title>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/manage/login.js"></script>
</head>
<body style="overflow:hidden; width:100%; height:100%;">
    <div id="web_bg" style="position:absolute; width:100%; height:100%; z-index:-1">
        <img src="/static/img/bg.jpg" height="100%" width="100%" />
    </div>
    <div class="index clear">
        <div class="login">
            <img src="/static/img/bbg.png">
            <ul>
                <li><input type="text" placeholder="用户名" class="input" name="account"></li>
                <li><input type="password" placeholder="密&emsp;码" class="input" name="password"></li>
                <li><input type="button" value="登录" class="submit" id="B_login"></li>
                <li>
                    <span style="color:#737579; font-size:14px;">
                        <input type="checkbox" value="">记住登录
                    </span>
                    <a href="#">忘记密码</a>
                </li>
            </ul>
        </div>
    </div>
</body>
