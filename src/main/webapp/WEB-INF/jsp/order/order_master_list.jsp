<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>订单列表</title>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/manage/order_master_list.js"></script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">订单管理 > 订单列表</div>
                <form id="orderForm" action="list" method="post">
                    <div class="screen clear">
                        <div class="form">
                            <input type="text" placeholder="订单号/课程名/报名人" name="orderInfo" value="${orderInfo}">
                            <a href="javascript:void(0);" id="B_query">搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <select name="status">
                                <option value="">--全部--</option>
                                <option value="0">未付款</option>
                                <option value="1">已付款</option>
                                <option value="2">已退款</option>
                            </select>
                        </div>
                    </div>
                    <div class="tablebox1">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr class="tr">
                                <td>课程名称</td>
                                <td>价格</td>
                                <td>区域</td>
                                <td>报名人</td>
                                <td>时间</td>
                                <td>状态</td>
                                <td>操作</td>
                            </tr>
                            <c:forEach items="${lstOrder}" var="order">
                                <tr>
                                    <td>
                                        <c:if test="${order.courseType == 1}">成人课程</c:if>
                                        <c:if test="${order.courseType == 2}">青少年课程</c:if>
                                        <c:if test="${order.courseType == 3}">私教课程</c:if>
                                        <c:if test="${order.courseType == 4}">特色课程</c:if>
                                    </td>
                                    <td>${order.payPrice}</td>
                                    <td>${order.courseDistrict}</td>
                                    <td>${order.clientName}</td>
                                    <td>
                                        <fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <label id="status_${order.orderNum}">
                                            <c:if test="${order.courseType == 0}">未支付</c:if>
                                            <c:if test="${order.courseType == 1}">已支付</c:if>
                                            <c:if test="${order.courseType == 2}">已退款</c:if>
                                        </label>
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" name="A_query_${order.orderNum}"
                                           data-ordernum="${order.orderNum}">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:if test="${order.status == 1}">
                                            <label id="update_${order.orderNum}">
                                                <a href="javascript:void(0);" name="A_update_${order.orderNum}"
                                                   data-ordernum="${order.orderNum}">标记为退款</a>
                                            </label>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="page clear">
                        <div class="pages">
                            <jsp:include page="../common/pager.jsp">
                              <jsp:param value="${pager.totalRecord}" name="totalRecord"/>
                              <jsp:param value="list" name="url"/>
                            </jsp:include>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>