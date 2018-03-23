$(function (){
    $("#B_login").click(login);
})

function login(){
    var account = $("input[name='account']").val();
    var password = $("input[name='password']").val();
    if(account == ""){
        alert("账号不能为空");
        return ;
    }
    if(password == ""){
        alert("密码不能为空");
        return ;
    }
    $.ajax({
        type : "post",
        url : "/user/login",
        data : {"account" : account, "password" : password},
        dataType : "json",
        success: function(result, status) {
            if(result.code != "1"){
                alert(result.msg);
                return ;
            }else {
                alert("ett");
                window.location.href = "/message/list";
            }
        }
    })
}