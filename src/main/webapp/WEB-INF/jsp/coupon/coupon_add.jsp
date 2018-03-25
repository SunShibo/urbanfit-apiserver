<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>优惠码信息添加</title>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <link type="text/css" href="/static/plugins/daterangepicker/daterangepicker-bs3.css"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/daterangepicker/daterangepicker.js"></script>
    <script type="text/javascript" src="/static/js/manage/coupon_add.js"></script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">优惠码管理 > 优惠码添加</div>
                <form id="couponForm" method="post">
                    <div class="tablebox2">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td class="td1">优惠码名称：</td>
                                <td class="td2">
                                   <input type="text" name="couponName" placeholder="请输入优惠码名称"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>渠道名称：</td>
                                <td>
                                  <input type="text" name="sourceName" placeholder="请输入渠道名称"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>折扣率：</td>
                                <td>
                                  <input type="text" name="percent" placeholder="请输入折扣率"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>开始时间：</td>
                                <td>
                                    <input name="beginTime" type="text"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>结束时间</td>
                                <td>
                                    <input name="endTime" type="text"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <a href="javascript:void(0);" id="B_submit">发布</a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>