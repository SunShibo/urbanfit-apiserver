<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>优惠券列表</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/manage/coupon_list.js"></script>
    <script type="text/javascript">
        var stauts = '${status}';
    </script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">优惠码管理 > 优惠码列表</div>
                <form id="couponForm" action="list" method="post">
                    <div class="screen clear">
                        <div class="form">
                            <input type="text" placeholder="请输入优惠码ID/优惠码名称/渠道名称"
                                   name="couponInfo" value="${couponInfo}">
                            <a href="javascript:void(0);" id="B_query">搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <select name="status">
                                <option value="">--全部--</option>
                                <option value="0">已生成</option>
                                <option value="1">已过期</option>
                            </select>
                        </div>
                        <a href="javascript:void(0);" id="B_add">新建优惠码</a>
                    </div>
                    <div class="tablebox1">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr class="tr">
                                <td>优惠码</td>
                                <td>优惠码名称</td>
                                <td>渠道名称</td>
                                <td>创建时间</td>
                                <td>状态</td>
                                <td>状态</td>
                            </tr>
                            <c:forEach items="${lstCoupon}" var="coupon">
                                <tr>
                                    <td>${coupon.couponNum}</td>
                                    <td>${coupon.couponName}</td>
                                    <td>${coupon.sourceName}</td>
                                    <td>
                                        <fmt:formatDate value="${coupon.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <label id="status_${coupon.couponId}">
                                            <c:if test="${coupon.status == 0}">已生成</c:if>
                                            <c:if test="${coupon.status == 1}">已过期</c:if>
                                        </label>
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" name="A_detail_${coupon.couponId}"
                                           data-couponid="${coupon.couponId}">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:if test="${coupon.status == 0}">
                                            <label id="update_${coupon.couponId}">
                                                <a href="javascript:void(0);" name="A_update_${coupon.couponId}"
                                                   data-couponid="${coupon.couponId}">设为过期</a>
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
