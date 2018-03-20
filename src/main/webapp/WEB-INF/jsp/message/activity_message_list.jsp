<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>活动资讯列表</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/manage/activity_message_list.js"></script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
        <div class="indexRight1">
            <div class="title">门店管理 > 门店管理列表</div>
            <form id="activityMessageForm" action="list" method="post">
                <div class="screen clear">
                    <input type="text" placeholder="请输入文章标题" name="title" value="${title}">
                    <a href="javascript:void(0);" id="B_query">搜索</a>
                    <a href="javascript:void(0);" id="B_add">新建教练</a>
                </div>
                <div class="tablebox1">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr class="tr">
                            <td>标题</td>
                            <td>创建时间</td>
                            <td>操作</td>
                        </tr>
                        <c:forEach items="${lstActivityMessage}" var="activityMessage">
                            <tr>
                                <td>${activityMessage.title}</td>
                                <td>
                                    <fmt:formatDate value="${activityMessage.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>
                                    <a href="javascript:void(0);" name="A_update_${activityMessage.messageId}"
                                       data-messageid="${activityMessage.messageId}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="javascript:void(0);" name="A_delete_${activityMessage.messageId}"
                                       data-messageid="${activityMessage.messageId}">删除</a>
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