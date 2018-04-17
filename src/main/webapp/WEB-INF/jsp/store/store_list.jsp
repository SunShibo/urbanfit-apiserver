<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>门店列表</title>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/manage/store_list.js"></script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
        <div class="indexRight1">
            <div class="title">门店管理 > 门店管理列表</div>
            <form id="storeForm" action="list" method="post">
                <div class="screen clear">
                    <div class="form">
                        <input type="text" placeholder="请输入门店名称" name="storeName" value="${storeName}">
                        <a href="javascript:void(0);" id="B_query">搜索</a>
                    </div>
                    <a href="javascript:void(0);" id="B_add">新建门店</a>
                </div>
                <div class="tablebox1">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr class="tr">
                            <td>门店名称</td>
                            <td>联系电话</td>
                            <td>联系人</td>
                            <td>创建时间</td>
                            <td>操作</td>
                        </tr>
                        <c:forEach items="${lstStore}" var="store">
                            <tr>
                                <td>${store.storeName}</td>
                                <td>${store.mobile}</td>
                                <td>${store.contactName}</td>
                                <td>
                                    <fmt:formatDate value="${store.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>
                                    <a href="javascript:void(0);" name="A_update_${store.storeId}" data-storeid="${store.storeId}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="javascript:void(0);" name="A_delete__${store.storeId}" data-storeid="${store.storeId}">删除</a>
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
</body>
