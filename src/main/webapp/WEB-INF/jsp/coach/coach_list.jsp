<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>教练列表</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/manage/coach_list.js"></script>
</head>
<body>
    <a href="/message/list">活动资讯</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/course/list">课程介绍</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/coach/list">教练团队</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/store/list">门店管理</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/auth/list">认证管理</a><br/><br/>
    <input type="button" id="B_add" value="添加"><br/>
    <form id="coachForm" action="list" method="post">
        <input type="text" name="coachName" value="${coachName}">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="B_query" value="查询">
        <table>
            <tr>
                <td>教练名称</td>
                <td>教练职称</td>
                <td>创建时间</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${lstCoach}" var="coach">
                <tr>
                    <td>${coach.coachName}</td>
                    <td>${coach.coachTitle}</td>
                    <td>
                        <fmt:formatDate value="${coach.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <a href="javascript:void(0);" name="A_update_${coach.coachId}"
                           data-coachid="${coach.coachId}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" name="A_delete_${coach.coachId}"
                           data-coachid="${coach.coachId}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>