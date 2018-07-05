<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>课程列表</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/manage/store_choose_course.js"></script>
    <script type="text/javascript" src="/static/js/mainJs/layer/layer.js"></script>
    <script type="text/javascript">
        var courseType = '${courseType}';
    </script>
</head>
<body>
    <div class="index clear">
        <div class="indexRight1" style="width: 95%">
            <form id="storeForm" action="storeCourseList" method="post">
                <div class="screen clear">
                    <div class="form">
                        <input type="text" placeholder="请输入课程名称" name="courseName" value="${courseName}">
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <select name="courseType">
                            <option value="">--全部--</option>
                            <option value="1">成人课程</option>
                            <option value="2">青少年课程</option>
                            <option value="3">私教课程</option>
                            <option value="4">特色课程</option>
                        </select>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" id="B_query">搜索</a>
                    </div>
                </div>
                <div class="tablebox1">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr class="tr">
                            <td>课程名称</td>
                            <td>课程类型</td>
                            <td>课程价格</td>
                            <td>创建时间</td>
                        </tr>
                        <c:forEach items="${lstCourse}" var="course">
                            <tr>
                                <td>
                                  <a href="javascript:void(0);" id="A_choose_course_${course.courseId}"
                                     data-courseid="${course.courseId}" data-coursename="${course.courseName}">
                                      ${course.courseName}</a>
                                </td>
                                <td>
                                    <c:if test="${course.courseType == 1}">成人课程</c:if>
                                    <c:if test="${course.courseType == 2}">青少年课程</c:if>
                                    <c:if test="${course.courseType == 3}">私教课程</c:if>
                                    <c:if test="${course.courseType == 4}">特色课程</c:if>
                                </td>
                                <td>${course.coursePrice}</td>
                                <td>
                                  <fmt:formatDate value="${course.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="page clear">
                    <div class="pages">
                        <jsp:include page="../common/pager.jsp">
                          <jsp:param value="${pager.totalRecord}" name="totalRecord"/>
                          <jsp:param value="storeCourseList" name="url"/>
                        </jsp:include>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
