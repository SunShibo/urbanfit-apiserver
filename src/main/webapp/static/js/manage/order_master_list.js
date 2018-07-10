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
    //$("a[name^='A_update_']").click(updateOrderMaster);
    $("#export_excel").click(exportExcel);
    $("#B_apply_back_money").click(function(){
        var orderNum =$("#applyOrderNum-hide").val();//获取隐藏域的值
        applyBackMoney(orderNum);
    });
    // 查看申请退款原因
    $("a[name^='A_reason_']").click(function (){
        var orderNum = $(this).attr("data-ordernum");//获取值
        $("#applyOrderNum-hide").val(orderNum);//设置隐藏域
        openApplyBackMoneyReason(orderNum);
    });

    $("#No_apply_back_money").click(againstBackMoney);
})
function againstBackMoney(){
    var orderNum = $("#applyOrderNum-hide").val()
    var reason = $("textarea[name='againstReason']").val();
    if(reason == ""){
        alert("请填写不同意退款原因！");
        return ;
    }
    // 不同意申请操作
    $.ajax({
        type : "post",
        url : "against",    // 调用方法
        data : {"orderNum" : orderNum, "reason" : reason},   // 参数信息
        dataType : "json",
        success : function (result){      // 调用方法成功处理函数
            if(result.code != 1){
                alert(result.msg);
                return ;
            }else{     // 成功处理
                $("#status_" + orderNum + "").text("退款失败");
                $("#update_" + orderNum + "").text("");
            }
        }
    });

}
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

function exportExcel(){
    var  orderInfo=$("input[name='orderInfo']").val();
    var  provice= $("select[name='provice']").val();
    var  status = $("select[name='status']").val();
    window.location.href= "/order/download?&orderInfo="+orderInfo+ "&provice=" + provice +
        "&status="+status;
}
function openApplyBackMoneyReason(orderNum){
    $.ajax({
        type : "post",
        url : "reason",
        data : {"orderNum" : orderNum},
        dataType : "json",
        success : function(result, status){
            if(result.success == true){
                $("#reason-span").html(result.data);
                layer.open({
                    title : '申请退款处理',
                    type: 1,
                    content : $("#applyBackMoneyDiv"),//指定弹出DIV内容
                    area: ['500px', '540px'],
                    full: false
                });
            }else {
                alert('not success');
            }
        }
    })

}

function applyBackMoney(orderNum){
    if(window.confirm("确认同意退款吗？")){
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

