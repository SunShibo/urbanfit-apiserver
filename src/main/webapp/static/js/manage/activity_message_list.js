$(function(){
    $("a[name^='A_update']").click(redirectUpdatePage);
    $("a[name^='A_delete']").click(deleteActivityMessage);
    $("#B_add").click(addActivityMessage);
    $("#B_query").click(queryActivityMessage);
})

function queryActivityMessage(){
    document.forms[0].submit();
}

function addActivityMessage(){
    window.location.href = "toAdd";
}

function redirectUpdatePage(){
    var messageId = $(this).data("messageid");
    window.location.href = "toUpdate?messageId=" + messageId;
}

function deleteActivityMessage(){
    if(window.confirm("确认删除吗？")){
        var messageId = $(this).data("messageid");
        $.ajax({
            type : "post",
            url : "delete",
            data : {"messageId" : messageId},
            dataType : "json",
            success: function(result, status) {
                if(result.errCode == "0"){
                    alert(result.errMsg);
                    return ;
                }else {
                    // 添加成功，跳转到列表页面
                    alert("删除活动资讯信息成功");
                    window.location.href = "list";
                }
            }
        })
    }
}