$(function(){
    $("a[name^='A_update']").click(redirectUpdatePage);
    $("a[name^='A_delete']").click(deleteStore);
    $("#B_add").click(addStore);
    $("#B_query").click(queryStore);
})

function queryStore(){
    document.forms[0].submit();
}

function addStore(){
    window.location.href = "toAdd";
}

function redirectUpdatePage(){
    var storeId = $(this).data("storeid");
    window.location.href = "toUpdate?storeId=" + storeId;
}

function deleteStore(){
    if(window.confirm("确认删除吗？")){
        var storeId = $(this).data("storeid");
        $.ajax({
            type : "post",
            url : "delete",
            data : {"storeId" : storeId},
            dataType : "json",
            success: function(result, status) {
                if(result.errCode == "0"){
                    alert(result.errMsg);
                    return ;
                }else {
                    // 添加成功，跳转到列表页面
                    alert("删除门店信息成功");
                    window.location.href = "list";
                }
            }
        })
    }
}