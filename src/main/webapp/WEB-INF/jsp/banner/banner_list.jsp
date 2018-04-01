<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>banner列表</title>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/manage/banner_list.js"></script>
  <script type="text/javascript">
      var type = '${type}';
  </script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">banner管理 > banner列表</div>
                <form id="activityMessageForm" action="list" method="post">
                    <div class="screen clear">
                        <div class="form">
                            <select name="type">
                                <option value="">--全部--</option>
                                <option value="1">首页</option>
                                <option value="2">活动资讯</option>
                            </select>
                        </div>
                        <a href="javascript:void(0);" id="B_add">新建banner</a>
                    </div>
                    <div class="tablebox1">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr class="tr">
                                <td>标题</td>
                                <td>类型</td>
                                <td>排序</td>
                                <td>创建时间</td>
                                <td>操作</td>
                            </tr>
                            <c:forEach items="${lstBanner}" var="banner">
                                <tr>
                                    <td>${banner.title}</td>
                                    <td>
                                        <c:if test="${banner.type == 1}">首页</c:if>
                                        <c:if test="${banner.type == 2}">活动资讯</c:if>
                                    </td>
                                    <td>${banner.sort}</td>
                                    <td>
                                      <fmt:formatDate value="${banner.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                      <a href="javascript:void(0);" name="A_update_${banner.bannerId}"
                                         data-bannerid="${banner.bannerId}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                      <a href="javascript:void(0);" name="A_delete_${banner.bannerId}"
                                         data-bannerid="${banner.bannerId}">删除</a>
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