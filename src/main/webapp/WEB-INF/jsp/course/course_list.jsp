<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>课程列表</title>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("a[name^='A_update']").click(redirectUpdatePage);
        })
        // 跳转到修改页面
        function redirectUpdatePage(){
            var courseId = $(this).data("courseid");
            window.location.href = "toUpdate?courseId=" + courseId;
        }
    </script>
</head>
<body>
    <a href="/message/list">活动资讯</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/course/list">课程介绍</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/coach/list">教练团队</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/store/list">门店管理</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/auth/list">认证管理</a><br/><br/>
    <form id="courseForm" action="list" method="post">
        <table>
            <tr>
                <td>课程名称</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${lstCourse}" var="course">
                <tr>
                    <td>
                        <c:if test="${course.courseType == 1}">成人课程</c:if>
                        <c:if test="${course.courseType == 2}">青少年课程</c:if>
                        <c:if test="${course.courseType == 3}">私教课程</c:if>
                        <c:if test="${course.courseType == 4}">特色课程</c:if>
                    </td>
                    <td>
                        <a href="javascript:void(0);" name="A_update_${course.courseId}"
                           data-courseid="${course.courseId}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>