$(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_banner']").addClass("on");

    initBannerType();
    $("#B_add").click(addBanner);
    $("select[name='type']").change(queryBanner);
    $("a[name^='A_delete_']").click(deleteBanner);
    $("a[name^='A_update_']").click(updateBanner);
})

function queryBanner(){
    document.forms[0].submit();
}

function addBanner(){
    window.location.href = "toAdd";
}

function initBannerType(){
    $("select[name='type']").val(type);
}

function updateBanner(){
    var bannerId = $(this).data("bannerid");
    window.location.href = "toUpdate?bannerId=" + bannerId;
}

function deleteBanner(){
    if(window.confirm("确认删除吗？")){
        var bannerId = $(this).data("bannerid");
        $.ajax({
            type : "post",
            url : "delete",
            data : {"bannerId" : bannerId},
            dataType : "json",
            success : function (resut, status){
                if(resut.code != 1){
                    alert(result.msg);
                    return ;
                }else{
                    alert("删除banner信息成功");
                    window.location.href = "list";
                }
            }
        });
    }
}