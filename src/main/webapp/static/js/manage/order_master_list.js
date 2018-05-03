$(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_order']").addClass("on");

    $("#distpicker").distpicker({
        province: orderMaster.provice,
        city: orderMaster.city,
        district: orderMaster.district,
    });

    initOrderMasterStatus();
    $("#B_query").click(queryOrderMaster);
    $("select[name='status']").change(queryOrderMaster);
    $("select[name='provice']").change(queryOrderMaster);
    $("select[name='city']").change(queryOrderMaster);
    $("select[name='district']").change(queryOrderMaster);

    $("a[name^='A_query_']").click(queryOrderMasterDetail);
    $("a[name^='A_update_']").click(updateOrderMaster);
})

function initOrderMasterStatus(){
    $("select[name='status']").val(orderMaster.status);
}

function queryOrderMaster(){
    document.forms[0].submit();
}

function queryOrderMasterDetail(){
    var orderNum = $(this).data("ordernum");
    window.location.href = "detail?orderNum=" + orderNum;
}

function updateOrderMaster(){
    if(window.confirm("确认标记为已退款状态吗？")){
        var orderNum = $(this).data("ordernum");
        $.ajax({
            type : "post",
            url : "update",
            data : {"orderNum" : orderNum},
            dataType : "json",
            success : function(result, status){
                if(result.code != "1"){
                    alert(result.msg);
                    return ;
                }else{
                    $("#status_" + orderNum + "").text("已退款");
                    $("#update_" + orderNum + "").text("");
                }
            }
        })
    }
}

