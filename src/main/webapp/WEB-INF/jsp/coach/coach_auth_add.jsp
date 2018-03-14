<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>教练认证</title>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>

  <script type="text/javascript">
      $(function (){
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
    教练姓名：<input type="text" name="coachName"><br/>
    教练证号码：<input type="text" name="coachCardNum"><br/>
    <input type="button" value="提交" id="B_submit"/>
</body>
</html>
