<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>订单详情</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript">
        $(function (){
            // 添加菜单样式
            $("div[id^='menu_']").removeClass("on");
            $("div[id='menu_order']").addClass("on");
        })
    </script>
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
                                <td>${orderMaster.childrenName}</td>
                            </tr>
                            <tr>
                                <td>手机号：</td>
                                <td>${orderMaster.clientMobile}</td>
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
                                    <c:if test="${orderMaster.status == 2}">申请退款</c:if>
                                    <c:if test="${orderMaster.status == 3}">系统自动取消</c:if>
                                    <c:if test="${orderMaster.status == 4}">已退款</c:if>
                                    <c:if test="${orderMaster.status == 5}">申请退款失败</c:if>
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
                                <td>${orderMaster.courseName}</td>
                            </tr>
                            <tr>
                                <td>课程类型：</td>
                                <td>${orderMaster.courseTypeName}</td>
                            </tr>
                            <tr>
                                <td>课程规格：</td>
                                <td>${orderMaster.courseSize}</td>
                            </tr>
                            <tr>
                                <td>俱乐部名称：</td>
                                <td>${orderMaster.storeName}</td>
                            </tr>
                            <tr>
                                <td>俱乐部地址：</td>
                                <td>${orderMaster.storeAddress}</td>
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
                            <tr>
                                <td>备注信息：</td>
                                <td>${orderMaster.remarks}</td>
                            </tr>
                            <tr>
                                <td>申请退款时间：</td>
                                <td>
                                <fmt:formatDate value="${applyDetail.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>
                                <td>处理退款时间：</td>
                                <td>
                                <fmt:formatDate value="${applyDetail.handleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>