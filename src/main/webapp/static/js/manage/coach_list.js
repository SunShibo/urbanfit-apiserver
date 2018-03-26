$(function(){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_coach']").addClass("on");

    $("a[name^='A_update']").click(redirectUpdatePage);
    $("a[name^='A_delete']").click(deleteCoach);
    $("#B_add").click(addCoach);
    $("#B_query").click(queryCoach);
})

function queryCoach(){
    document.forms[0].submit();
}

function addCoach(){
    window.location.href = "toAdd";
}

function redirectUpdatePage(){
    var coachId = $(this).data("coachid");
    window.location.href = "toUpdate?coachId=" + coachId;
}

function deleteCoach(){
    if(window.confirm("确认删除吗？")){
        var coachId = $(this).data("coachid");
        $.ajax({
            type : "post",
            url : "delete",
            data : {"coachId" : coachId},
            dataType : "json",
            success: function(result, status) {
                if(result.errCode == "0"){
                    alert(result.errMsg);
                    return ;
                }else {
                    // 添加成功，跳转到列表页面
                    alert("删除教练信息成功");
                    window.location.href = "list";
                }
            }
        })
    }
}