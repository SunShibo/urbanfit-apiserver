$(function(){
    initHomePageInfo();  // 初始化页面信息
    $("#B_submit").click(updateHomePageInfo);
    uploadImageUrl();    // 上传图片
    $("#B_delete_imageUrl").click(deleteImageUrl);
})

function initHomePageInfo(){
    $.ajax({
        type : "post",
        url : "queryById",
        data : {"moduleId" : moduleId},
        dataType : "json",
        success : function(result, status){
            if(result.code != 1){
                alert(result.msg);
                return ;
            }else{
                var content = result.data.module.content;
                var baseUrl = result.data.baseUrl;
                if(content != ""){
                    $.each(content, function (i, n){
                        var index = parseInt(i + 1);
                        $("input[name='title_" + index + "']").val(n.title);
                        $("input[name='linkUrl_" + index + "']").val(n.linkUrl);
                        // 缩略图地址
                        if(index == 1){
                            $("#uploadImage").attr("src", baseUrl + n.imageUrl);
                            $("input[name='imageUrl_" + index + "']").val(n.imageUrl);
                            $("input[name='remark_" + index + "']").val(n.remark);
                        }
                    })
                }
            }
        }
    })
}

function updateHomePageInfo(){
    var isUpdate = true;
    var contentArr = [];
    $("input[name^='title_']").each(function (){
        var mid = $(this).data("mid");
        var mtext = $(this).data("mtext");
        var title = $(this).val();
        var linkUrl = $("input[name='linkUrl_" + mid +"']").val();
        if(title != "" && linkUrl == ""){
            alert("链接地址" + mtext +"不能为空");
            isUpdate = false;
            return false;
        }
        if(title == "" && linkUrl != ""){
            alert("标题" + mtext +"不能为空");
            isUpdate = false;
            return false;
        }
        var imageUrl = "";
        var remark = "";
        if(mid == 1){
            imageUrl = $("input[name='imageUrl_" + mid + "']").val();
            remark = $("input[name='remark_" + mid + "']").val();
        }
        if(title != "" && linkUrl != ""){
            contentArr.push('{"title":"' + title + '","linkUrl":"' + linkUrl + '","imageUrl":"'
                + imageUrl + '","remark":"' + remark + '"}');
        }
    });
    // 如果不满足添加修改条件，直接返回
    if(isUpdate == false){
        return ;
    }
    // 满足修改条件，修改信息
    if(contentArr == ""){
        $("input[name='content']").val("");
        if(window.confirm("当前没有添加信息，确认保存吗？")){
            updateHomePageInfoDetail();
        }
    }else{
        $("input[name='content']").val("[" + contentArr.join(",") + "]");
        updateHomePageInfoDetail();
    }
}

function updateHomePageInfoDetail(){
    $.ajax({
        type : "post",
        url : "update",
        data : $("#homePageForm").serialize(),
        dataType : "json",
        success : function(result, status){
            if(result.code != 1){
                alert(result.msg);
                return ;
            }else{
                alert("修改成功");
                window.location.href = "list";
            }
        }
    });
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
                $("input[name='imageUrl_1']").val(resultData.imageUrl);
            }
        }
    });
}

function deleteImageUrl(){
    $("#uploadImage").attr("src", "/static/img/u37.png");
    $("input[name='imageUrl']").val("");
}