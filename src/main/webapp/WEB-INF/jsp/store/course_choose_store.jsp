<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>门店列表</title>
  <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/manage/course_choose_store.js"></script>
  <script type="text/javascript" src="/static/js/mainJs/layer/layer.js"></script>
</head>
<body>
    <div class="index clear">
        <div class="indexRight1" style="width: 95%">
            <form id="storeForm" action="courseStoreList" method="post">
                <div class="screen clear">
                    <div class="form">
                        <input type="text" placeholder="请输入门店名称" name="storeName" value="${storeName}">
                        <a href="javascript:void(0);" id="B_query">搜索</a>
                    </div>
                </div>
                <div class="tablebox1">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr class="tr">
                            <td>门店名称</td>
                            <td>联系电话</td>
                            <td>联系人</td>
                            <td>地址</td>
                            <td>创建时间</td>
                        </tr>
                        <c:forEach items="${lstStore}" var="store">
                            <tr>
                                <td>
                                    <a href="javascript:void(0);" id="A_choose_store_${store.storeId}"
                                       data-storeid="${store.storeId}" data-storename="${store.storeName}">
                                       ${store.storeName}</a>
                                </td>
                                <td>${store.mobile}</td>
                                <td>${store.contactName}</td>
                                <td>${store.storeDistrict}${store.storeAddress}</td>
                                <td>
                                  <fmt:formatDate value="${store.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="page clear">
                    <div class="pages">
                        <jsp:include page="../common/pager.jsp">
                          <jsp:param value="${pager.totalRecord}" name="totalRecord"/>
                          <jsp:param value="courseStoreList" name="url"/>
                        </jsp:include>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
