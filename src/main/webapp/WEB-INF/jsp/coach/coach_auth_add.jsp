<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>教练认证</title>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>

  <script type="text/javascript">
      $(function (){
          // 添加菜单样式
          $("div[id^='menu_']").removeClass("on");
          $("div[id='menu_auth']").addClass("on");

          $("#B_submit").click(addCoachAuth);
      })

      // 添加教练认证信息
      function addCoachAuth(){
          var coachName = $("input[name='coachName']").val();
          var coachCardNum = $("input[name='coachCardNum']").val();
          if(coachName == "") {
              alert("教练姓名不能为空");
            return ;
          }
          if(coachCardNum == ""){
              alert("教练证号码不能为空");
              return ;
          }
          $.ajax({
              type : "post",
              url : "add",
              data : {"coachName" : coachName, "coachCardNum" : coachCardNum},
              dataType : "json",
              success: function(result, status) {
                  if(result.errCode == "0"){
                      alert(result.errMsg);
                      return ;
                  }else {
                      // 添加成功，跳转到列表页面
                      alert("添加认证信息成功");
                      window.location.href = "list";
                  }
              }
          })
      }
  </script>

</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">门店管理 > 门店添加</div>
                <div class="tablebox2">
                    <table cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td class="td1">教练姓名：</td>
                            <td class="td2">
                                <input type="text" name="coachName" placeholder="请输入教练姓名"><em>*必填</em>
                            </td>
                        </tr>
                        <tr>
                            <td>教练证号码：</td>
                            <td>
                                <input type="text" name="coachCardNum" placeholder="请输入教练证号码"><em>*必填</em>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <a href="javascript:void(0);" id="B_submit">保存</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
