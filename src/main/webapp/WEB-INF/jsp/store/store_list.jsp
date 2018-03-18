<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>门店列表</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/manage/store_list.js"></script>
</head>
<body>
    <a href="/message/list">活动资讯</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/course/list">课程介绍</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/coach/list">教练团队</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/store/list">门店管理</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/auth/list">认证管理</a><br/><br/>
    <input type="button" id="B_add" value="添加"><br/>
    <form id="storeForm" action="list" method="post">
        <input type="text" name="storeName" value="${storeName}">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="B_query" value="查询">
        <table>
            <tr>
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
    </form>
</body>
