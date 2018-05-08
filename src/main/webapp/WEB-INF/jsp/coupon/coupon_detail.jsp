<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>优惠码信息添加</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript">
        $(function (){
            // 添加菜单样式
            $("div[id^='menu_']").removeClass("on");
            $("div[id='menu_coupon']").addClass("on");
        })
    </script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">优惠码管理 > 优惠码详情</div>
                <form id="couponForm" method="post">
                    <div class="tablebox2">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td>优惠码：</td>
                                <td>${coupon.couponNum}</td>
                            </tr>
                            <tr>
                                <td class="td1">优惠码名称：</td>
                                <td class="td2">${coupon.couponName}</td>
                            </tr>
                            <tr>
                                <td>渠道名称：</td>
                                <td>${coupon.sourceName}</td>
                            </tr>
                            <tr>
                                <td>折扣率：</td>
                                <td>${coupon.percent}%</td>
                            </tr>
                            <tr>
                                <td>生产时间：</td>
                                <td>
                                    <fmt:formatDate value="${coupon.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>
                                <td>开始时间：</td>
                                <td>
                                    <fmt:formatDate value="${coupon.beginTime}" pattern="yyyy-MM-dd"/>
                                </td>
                            </tr>
                            <tr>
                                <td>结束时间</td>
                                <td>
                                    <fmt:formatDate value="${coupon.endTime}" pattern="yyyy-MM-dd"/>
                                </td>
                            </tr>
                            <tr>
                                <td>状态</td>
                                <td>
                                    <c:if test="${coupon.status == 0}">已生成</c:if>
                                    <c:if test="${coupon.status == 1}">已过期</c:if>
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>