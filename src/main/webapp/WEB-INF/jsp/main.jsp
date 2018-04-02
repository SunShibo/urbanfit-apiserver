<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>众力飞特后台管理系统</title>
</head>
<body>
    <div class="indexLeft">
        <div class="titlebox">
            <h1>众力飞特</h1>
            <span>后台管理系统</span>
        </div>
        <div class="nav">
            <div class="navbox">
                <div class="navboxa" id="menu_message">
                    <a href="${pageContext.request.contextPath}/message/list">活动资讯</a>
                </div>
                <div class="navboxa on" id="menu_course">
                    <a href="${pageContext.request.contextPath}/course/list">课程介绍</a>
                </div>
                <div class="navboxa" id="menu_coach">
                    <a href="${pageContext.request.contextPath}/coach/list">教练团队</a>
                </div>
                <div class="navboxa" id="menu_store">
                    <a href="${pageContext.request.contextPath}/store/list">门店管理</a>
                </div>
                <div class="navboxa" id="menu_auth">
                    <a href="${pageContext.request.contextPath}/auth/list">认证管理</a>
                </div>
                <div class="navboxa" id="menu_coupon">
                    <a href="${pageContext.request.contextPath}/coupon/list">优惠码管理</a>
                </div>
                <div class="navboxa" id="menu_order">
                    <a href="${pageContext.request.contextPath}/order/list">订单管理</a>
                </div>
                <div class="navboxa" id="menu_banner">
                    <a href="${pageContext.request.contextPath}/banner/list">banner管理</a>
                </div>
                <div class="navboxa" id="menu_module">
                    <a href="${pageContext.request.contextPath}/module/list">模块管理</a>
                </div>
            </div>
        </div>
    </div>
    <div class="indexRight">
        <div class="top">
            <ul>
                <li><a href="javascript:void(0);"><img src="../static/img/exit.png">退出</a></li>
            </ul>
        </div>
</body>
