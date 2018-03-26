$(function(){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_store']").addClass("on");

    $("#city_info").citySelect({
        nodata: "none",
        required: false
    });

    $("input[name='storeDistrict']").val("");
    $("#B_submit").click(addStore);
})

function addStore(){
    var storeName = $("input[name='storeName']").val();
    if(storeName == ""){
        alert("门店名称不能为空");
        return ;
    }
    // 判断地址是否选择省市区信息
    var proviceInfo = $("#proviceId").val();
    if(proviceInfo == ""){
        alert("请选择省市区信息");
        return ;
    }
    var cityInfo = $("#cityId").val();
    if(cityInfo == ""){
        alert("请选择省市区信息");
        return ;
    }
    var districtInfo = $("#districtId").val();
    if(districtInfo == ""){
        alert("请选择省市区信息");
        return ;
    }
    var storeDistrict = proviceInfo + "," + cityInfo;
    if(districtInfo != null){
        storeDistrict += "," + districtInfo;
    }
    $("input[name='storeDistrict']").val(storeDistrict);
    var storeAddress = $("input[name='storeAddress']").val();
    if(storeAddress == ""){
        alert("详细地址不能为空");
        return ;
    }
    var mobile = $("input[name='mobile']").val();
    if(mobile == ""){
        alert("联系电话不能为空");
        return ;
    }
    // 判断电话号码是否有误
    var mobilePattern = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (!mobilePattern.test(mobile)){
        alert("联系电话输入格式有误");
        return ;
    }
    var contactName = $("input[name='contactName']").val();
    if(contactName == ""){
        alert("联系人不能为空");
        return ;
    }
    $.ajax({
        type : "post",
        url : "add",
        data : $("#storeForm").serialize(),
        dataType : "json",
        success: function(result, status) {
            if(result.errCode == "0"){
                alert(result.errMsg);
                return ;
            }else {
                // 添加成功，跳转到列表页面
                alert("添加门店信息成功");
                window.location.href = "list";
            }
        }
    })
}