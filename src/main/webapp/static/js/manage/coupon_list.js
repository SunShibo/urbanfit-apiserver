$(function (){
    $("select[name='status']").change(queryCoupon);
    $("#B_query").click(queryCoupon);
    initCouponStatus();
    $("#B_add").click(redirectAddPage);
    $("a[name^='A_detail_']").click(queryCouponDetail);
    $("a[name^='A_update_']").click(updateCouponStatus);
})

function queryCoupon(){
    document.forms[0].submit();
}

function initCouponStatus(){
    $("select[name='status']").val(stauts);
}

function redirectAddPage(){
    window.location.href = "toAdd";
}

function queryCouponDetail(){
    var couponId = $(this).data("couponid");
    window.location.href = "detail?couponId="+couponId;
}

function updateCouponStatus(){
    if(window.confirm("确定标记为已过期状态吗？")) {
        var couponId = $(this).data("couponid");
        $.ajax({
            type: "post",
            url: "update",
            data: {"couponId": couponId},
            async: false,
            dataType: "json",
            success: function (result, status) {
                if (result.code != "1") {
                    alert(result.msg);
                    return;
                } else {
                    // 设为过期成功，执行操作
                    $("#status_" + couponId + "").text("已过期");
                    $("#update_" + couponId + "").text("");
                }
            }
        });
    }
}