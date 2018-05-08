<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>教练认证</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript">
      $(function(){
          // 添加菜单样式
          $("div[id^='menu_']").removeClass("on");
          $("div[id='menu_auth']").addClass("on");

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
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
        <div class="indexRight1">
            <div class="title">认证管理 > 认证管理列表</div>
            <form id="coachAuthForm" action="list" method="post">
                <div class="screen clear">
                    <div class="form">
                        <input type="text" placeholder="请输入教练名称或是教练证号码" name="authInfo" value="${authInfo}">
                        <a href="javascript:void(0);" id="B_query">搜索</a>
                    </div>
                    <a href="javascript:void(0);" id="B_add">新建认证</a>
                </div>
                <div class="tablebox1">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr class="tr">
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
    </body>
</body>
