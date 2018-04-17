<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>模块列表</title>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/manage/module_list.js"></script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">模块管理 > 模块列表</div>
                <div class="screen clear"></div>
                <form id="activityMessageForm" action="list" method="post">
                    <div class="tablebox1">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr class="tr">
                                <td>模块</td>
                                <td>操作</td>
                            </tr>
                            <c:forEach items="${lstModule}" var="module">
                                <tr>
                                    <td>
                                        <c:if test="${module.type == 1}">首页</c:if>
                                        <c:if test="${module.type == 2}">活动资讯</c:if>
                                    </td>
                                    <td>
                                        <a href="javascript:void(0);" name="A_update_${module.moduleId}"
                                           data-moduleid="${module.moduleId}" data-type="${module.type}">编辑</a>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:if test="${module.status == 0}">
                                            <a href="javascript:void(0);" name="A_disable_${module.moduleId}"
                                               data-moduleid="${module.moduleId}" data-type="${module.type}">禁用</a>
                                        </c:if>
                                        <c:if test="${module.status == 1}">
                                            <a href="javascript:void(0);" name="A_enable_${module.moduleId}"
                                               data-moduleid="${module.moduleId}" data-type="${module.type}">启用</a>
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