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
          $("a[name='A_delete']").click(deleteAuth);
      })

      function deleteAuth(){
          var authId = $(this).data("authid");
          $.ajax({
              type : "post",
              url : "delete",
              data : {"authId" : authId},
              dataType : "json",
              success: function(result, status) {
                  if(result.errCode == "0"){
                    alert(result.errMsg);
                    return ;
                  }else {
                    // 删除成功，跳转到列表页面
                    alert("跳转到列表页面");
                  }
              }
          })
      }
  </script>
</head>
<body>
    <form>
        <table>
            <tr>
                <td>id</td>
                <td>认证教练</td>
                <td>发布时间</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${lstCoachAuth}" var="auth">
                <tr>
                    <td>${auth.authId}</td>
                    <td>【${auth.coachName}】【${auth.coachCardNum}】</td>
                    <td>
                        <fmt:formatDate value="${auth.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <a href="javascript:void(0);" name="A_delete" data-authid="${auth.authId}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>
