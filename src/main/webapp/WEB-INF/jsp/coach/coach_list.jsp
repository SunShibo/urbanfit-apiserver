<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>教练列表</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/manage/coach_list.js"></script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">教练团队 > 教练团队列表</div>
                <form id="coachForm" action="list" method="post">
                    <div class="screen clear">
                        <div class="form">
                            <input type="text" placeholder="请输入教练名称" name="coachName" value="${coachName}">
                            <a href="javascript:void(0);" id="B_query">搜索</a>
                        </div>
                        <a href="javascript:void(0);" id="B_add">新建教练</a>
                    </div>
                    <div class="tablebox1">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr class="tr">
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