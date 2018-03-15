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
    <input type="button" id="B_add" value="添加"><br/>
    <form id="activityMessageForm" action="list" method="post">
        <input type="text" name="title" value="${title}">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="B_query" value="查询">
        <table>
            <tr>
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
    </form>
</body>