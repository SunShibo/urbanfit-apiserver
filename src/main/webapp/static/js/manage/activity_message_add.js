var editor;
$(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_message']").addClass("on");

    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="messageContent"]', {
            allowFileManager : true,
            items : [
                'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                'insertunorderedlist', '|', 'emoticons', 'image', 'media', 'link','code','source',
                'fullscreen'],
            uploadJson : '/static/js/kindeditor/kd_upload_image.jsp',
            allowFileManager : true,
            fileManagerJson : '/static/js/kindeditor/kd_upload_file.jsp'
        });
    });

    // 上传缩略图
    uploadThumbnails();
    $("#B_delete_thumbnails").click(deleteThumbnails);
    $("#B_submit").click(addActivityMessage);
})

function deleteThumbnails(){
    $("#uploadImage").attr("src", "");
    $("input[name='thumbnails']").val("");
}

function deleteDetailImage(){
    $("#uploadDetailImage").attr("src", "");
    $("input[name='detailImage']").val("");
}

function addActivityMessage(){
    var title = $("input[name='title']").val();
    if(title == ""){
        alert("标题不能为空");
        return ;
    }
    if(editor.html() == ""){
        alert("活动资讯内容不能为空");
        return ;
    }
    $("input[name='content']").val(editor.html());
    $.ajax({
        type : "post",
        url : "add",
        data : $("#activityMessageForm").serialize(),
        dataType : "json",
        success: function(result, status) {
            if(result.errCode == "0"){
                alert(result.errMsg);
                return ;
            }else {
                alert("添加活动资讯信息成功");
                window.location.href = "list";
            }
        }
    })
}

function uploadDetailImage(){
    var button = $("#uploadDetailImage"), interval;
    new AjaxUpload(button, {
        action: "uploadThumbnails",
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
                $("#uploadDetailImage").attr("src", resultData.baseUrl + resultData.thumbnailsUrl);
                $("input[name='detailImage']").val(resultData.thumbnailsUrl);
            }
        }
    });
}

function uploadThumbnails(){
    var button = $("#uploadImage"), interval;
    new AjaxUpload(button, {
        action: "uploadThumbnails",
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
                $("#uploadImage").attr("src", resultData.baseUrl + resultData.thumbnailsUrl);
                $("input[name='thumbnails']").val(resultData.thumbnailsUrl);
            }
        }
    });
}