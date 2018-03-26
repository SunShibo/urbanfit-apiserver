<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>订单详情</title>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">订单管理 > 订单详情</div>
                <form id="orderMasterForm" method="post">
                    <div class="tablebox2">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td class="td1">订单号：</td>
                                <td class="td2">${orderMaster.orderNum}</td>
                            </tr>
                            <tr>
                                <td>学生姓名：</td>
                                <td>${orderMaster.clientName}</td>
                            </tr>
                            <tr>
                                <td>手机号：</td>
                                <td>${orderMaster.clientMobile}</td>
                            </tr>
                            <tr>
                                <td>区域：</td>
                                <td>${orderMaster.courseDistrict}</td>
                            </tr>
                            <tr>
                                <td>支付方式：</td>
                                <td>
                                    <c:if test="${orderMaster.payment == 0}">支付宝</c:if>
                                    <c:if test="${orderMaster.payment == 1}">微信</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>支付金额：</td>
                                <td>${orderMaster.payPrice}</td>
                            </tr>
                            <tr>
                                <td>支付状态：</td>
                                <td>
                                    <c:if test="${orderMaster.status == 0}">待支付</c:if>
                                    <c:if test="${orderMaster.status == 1}">已支付</c:if>
                                    <c:if test="${orderMaster.status == 2}">已退款</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>支付时间：</td>
                                <td>
                                    <fmt:formatDate value="${orderMaster.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>
                                <td>课程名称：</td>
                                <td>
                                    <c:if test="${orderMaster.courseType == 1}">成人课程</c:if>
                                    <c:if test="${orderMaster.courseType == 2}">青少年课程</c:if>
                                    <c:if test="${orderMaster.courseType == 3}">私教课程</c:if>
                                    <c:if test="${orderMaster.courseType == 4}">特色课程</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>课程价格：</td>
                                <td>${orderMaster.price}</td>
                            </tr>
                            <tr>
                                <td>优惠券信息：</td>
                                <td>
                                    <c:if test="${orderMaster.isUseCoupon == 1}">
                                        -${orderMaster.couponPrice} &nbsp;&nbsp;${orderMaster.couponName}
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>