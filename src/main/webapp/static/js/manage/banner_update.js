$(function (){
    initBannerType();
    $("#B_submit").click(updateBanner);
    uploadImageUrl();
    $("#B_delete_imageUrl").click(deleteImageUrl);
})

function initBannerType(){
    $("select[name='type']").val(type);
}

function updateBanner(){
    var title = $("input[name='title']").val();
    if (title == "") {
        alert("标题不能为空");
        return;
    }
    var imageUrl = $("input[name='imageUrl']").val();
    if (imageUrl == "") {
        alert("请选择轮播图片");
        return;
    }
    var linkUrl = $("input[name='linkUrl']").val();
    if (linkUrl == "") {
        alert("链接地址不能为空");
        return;
    }
    var sort = $("input[name='sort']").val();
    if (sort == "") {
        alert("请填写排序值");
        return;
    }

    $.ajax({
        type: "post",
        url: "update",
        data: $("#bannerForm").serialize(),
        dataType: "json",
        success: function (result, status) {
            if (result.errCode == "0") {
                alert(result.errMsg);
                return;
            } else {
                // 修改成功，跳转到列表页面
                alert("修改banner信息成功");
                window.location.href = "list";
            }
        }
    })
}

function uploadImageUrl(){
    var button = $("#uploadImage"), interval;
    new AjaxUpload(button, {
        action: "uploadImage",
        type:"post",
        name: 'myFile',
        responseType : 'json',
        onSubmit: function(file, ext) {
            if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF)$/.test(ext))) {
                alert("您上传的图片格式不对，请重新选择！");
                return false;
            }
        },
        onComplete: function(file, response) {
            if(response.message == "big"){
                alert("图片太大，请重新选择！");
                return ;
            }else if(response.message == "paramError"){
                alert("参数有误！");
                return ;
            }else if(response.message == "fail"){
                alert("修改失败，请重新修改！");
                return ;
            }else if(response.message == "success"){
                var resultData = response.data;
                $("#uploadImage").attr("src", resultData.baseUrl + resultData.imageUrl);
                $("input[name='imageUrl']").val(resultData.imageUrl);
            }
        }
    });
}

function deleteImageUrl(){
    $("#uploadImage").attr("src", "/static/img/u37.png");
    $("input[name='imageUrl']").val("");
}