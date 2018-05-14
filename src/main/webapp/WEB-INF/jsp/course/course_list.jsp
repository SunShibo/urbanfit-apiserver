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
    <script type="text/javascript" src="/static/js/manage/course_list.js"></script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">课程介绍 > 课程介绍列表</div>
                    <div class="screen clear">
                    </div>
                    <form id="courseForm" action="list" method="post">
                        <div class="tablebox1">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr class="tr">
                                    <td>课程名称</td>
                                    <td>操作</td>
                                </tr>
                                <c:forEach items="${lstCourse}" var="course">
                                    <tr>
                                        <td>${course.courseName}</td>
                                        <td>
                                            <a href="javascript:void(0);" name="A_update_${course.courseId}"
                                               data-courseid="${course.courseId}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <c:if test="${course.status == 0}">
                                                <a href="javascript:void(0);" name="A_down_${course.courseId}"
                                                   data-courseid="${course.courseId}">下架</a>
                                            </c:if>
                                            <c:if test="${course.status == 1}">
                                                <a href="javascript:void(0);" name="A_up_${course.courseId}"
                                                   data-courseid="${course.courseId}">上架</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>