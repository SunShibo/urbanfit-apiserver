<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>订单列表</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript">
        var orderMaster = {
            "status" : '${status}',
            "provice" : '${provice}',
            "city" : '${city}',
            "district" : '${district}'
        };
    </script>
    <meta charset="utf-8"/>
    <title>我的订单</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/menu.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/layer/layer.js"></script>
    <script type="text/javascript" src="/static/js/web/order_master_list.js"></script>
    <script type="text/javascript">
        var status = '${status}';
    </script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        *{
            padding: 0;
            margin: 0;
        }
        #reason-span{
            display: inline-block;
            vertical-align: top;
            font-size: 20px;
            margin-top: 50px;
            margin-left: auto;

        }
        #ApplyReason{
            display: inline-block;
            vertical-align: top;
            font-size: 20px;
            margin-top: 50px;
            margin-left: 15%;

        }
        #applyBackMoneyDiv{
            margin: 0 auto;
            text-align: center;
            height: 440px;
        }
        #B_apply_back_money{
            display: block;width: 120px;height: 45px;
            background: #f6d332;color: #001111;
            font-size: 18px;border-radius: 4px;
            text-align: center;line-height: 45px;
            margin-top: -45px;margin-left: 10%;
        }
        #reason{
            margin-top: 122px ;
        }
        #No_apply_back_money{
            display: block;width: 120px;height: 45px;
            background: #f6d332;color: #001111;
            font-size: 18px;border-radius: 4px;
            text-align: center;line-height: 45px;
            margin-top: 80px;margin-left: 65%;
        }
        #No-Apply{
            display: inline-block;
            vertical-align: top;
            margin-top: 130px;
            font-size: 20px;
            margin-left: -280px;
        }
    </style>
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
                            <a href="javascript:void(0);" id="export_excel">批量导出excel</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="select">
                                <div>
                                    <select name="status">
                                        <option value="">--全部--</option>
                                        <option value="0">未付款</option>
                                        <option value="1">已付款</option>
                                        <option value="2">申请退款</option>
                                        <option value="3">系统自动取消</option>
                                        <option value="4">已退款</option>
                                        <option value="5">申请退款失败</option>
                                    </select>
                                </div>
                            </div>&nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="select">
                                <div id="distpicker">
                                    <select class="prov" id="proviceId" name="provice"></select>
                                    <select class="city" id="cityId" name="city"></select>
                                    <select class="dist" id="districtId" name="district"></select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tablebox1">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr class="tr">
                                <td>课程名称</td>
                                <td>价格</td>
                                <td>区域</td>
                                <td>报名人</td>
                                <td>创建时间</td>
                                <td>支付时间</td>
                                <td>状态</td>
                                <td>操作</td>
                            </tr>
                            <c:forEach items="${lstOrder}" var="order">
                                <tr>
                                    <td>${order.courseName}</td>
                                    <td>${order.payPrice}</td>
                                    <td>${order.courseDistrict}</td>
                                    <td>${order.clientName}</td>
                                    <td>
                                        <fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <label id="status_${order.orderNum}">
                                            <c:if test="${order.status == 0}">未支付</c:if>
                                            <c:if test="${order.status == 1}">已支付</c:if>
                                            <c:if test="${order.status == 2}">申请退款</c:if>
                                            <c:if test="${order.status == 3}">系统自动取消</c:if>
                                            <c:if test="${order.status == 4}">已退款</c:if>
                                            <c:if test="${order.status == 5}">申请退款失败</c:if>
                                        </label>
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" name="A_query_${order.orderNum}"
                                           data-ordernum="${order.orderNum}">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:if test="${order.status == 2}">
                                            <label id="update_${order.orderNum}">
                                                <a href="javascript:void(0);" name="A_reason_${order.orderNum}"
                                                   data-ordernum="${order.orderNum}">查看退款原因</a>
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
    <div id="applyBackMoneyDiv">
        <div>
            <tr>
                <td class="td1"><span id="ApplyReason">原&nbsp&nbsp&nbsp&nbsp&nbsp因:</span><span id="reason-span"></span></td>
                <td class="td2">${orderMaster.orderNum}</td>
                <span id="No-Apply">不同意退款原因:</span><textarea name="againstReason" id="reason" weight="200px"></textarea><br/>
            </tr>
            <input type="hidden" id="applyOrderNum-hide" value="">
            <input type="button" id="No_apply_back_money" value="不同意退款">
            <input type="button" id="B_apply_back_money" value="同意退款">
        </div>
        </div>
    </div>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/distpicker.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/layer/layer.js"></script>

    <script type="text/javascript" src="/static/js/manage/order_master_list.js"></script>
</body>