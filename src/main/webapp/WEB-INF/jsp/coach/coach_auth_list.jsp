<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>教练认证</title>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript">
      $(function(){
          $("a[name^='A_delete']").click(deleteCothAuth);
          $("a[name^='A_update']").click(updateCoachAuth);
          $("#B_add").click(addCoachAuth);
          $("#B_query").click(queryCoachAuth);
      })

      function queryCoachAuth(){
          document.forms[0].submit();
      }

      function addCoachAuth(){
          window.location.href = "toAdd";
      }

      function updateCoachAuth(){
          var authId = $(this).data("authid");
          window.location.href = "toUpdate?authId=" + authId;
      }

      function deleteCothAuth(){
          if(window.confirm("确认删除吗？")) {
              var authId = $(this).data("authid");
              $.ajax({
                  type: "post",
                  url: "delete",
                  data: {"authId": authId},
                  dataType: "json",
                  success: function (result, status) {
                      if (result.errCode == "0") {
                          alert(result.errMsg);
                          return;
                      } else {
                          // 删除成功，跳转到列表页面
                          alert("删除教练认证成功");
                          window.location.href = "list";
                      }
                  }
              })
          }
      }
  </script>
</head>
<body>
    <input type="button" id="B_add" value="添加"><br/>
    <form id="coachAuthForm" action="list" method="post">
        <input type="text" name="authInfo" value="${authInfo}">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="B_query" value="查询">
        <table>
            <tr>
                <td>教练姓名</td>
                <td>教练证号码</td>
                <td>发布时间</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${lstCoachAuth}" var="auth">
                <tr>
                    <td>${auth.coachName}</td>
                    <td>${auth.coachCardNum}</td>
                    <td>
                        <fmt:formatDate value="${auth.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <a href="javascript:void(0);" name="A_update_${auth.authId}" data-authid="${auth.authId}">编辑</a>&nbsp;&nbsp;
                        <a href="javascript:void(0);" name="A_delete_${auth.authId}" data-authid="${auth.authId}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>
