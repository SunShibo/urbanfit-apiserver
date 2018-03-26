$(function(){
    $("#B_submit").click(addCoupon);
})

function addCoupon(){
    var couponName = $("input[name='couponName']").val();
    if(couponName == ""){
        alert("优惠码名称不能为空");
        return;
    }
    var sourceName = $("input[name='sourceName']").val();
    if(sourceName == ""){
        alert("渠道名称不能为空");
        return;
    }
    var percent = $("input[name='percent']").val();
    if(percent == ""){
        alert("优惠码折扣不能为空");
        return;
    }
    // 判断优惠码为整数或者两位小数
    var percentPattern = /^\d+(\.\d{1,2})?$/;
    if(!percentPattern.test(percent)){
        alert("优惠码折扣输入格式有误");
        return ;
    }
    var beginTime = $("input[name='beginTime']").val();
    if(beginTime == ""){
        alert("请选择开始时间");
        return ;
    }
    var endTime = $("input[name='endTime']").val();
    if(endTime == ""){
        alert("请选择结束时间");
        return ;
    }
    $.ajax({
        type : "post",
        url : "add",
        data : $("#couponForm").serialize(),
        dataType : "json",
        success: function(result, status) {
            if(result.code != "1"){
                alert(result.msg);
                return ;
            }else {
                // 添加成功，跳转到列表页面
                alert("添加优惠码成功");
                window.location.href = "list";
            }
        }
    });
}