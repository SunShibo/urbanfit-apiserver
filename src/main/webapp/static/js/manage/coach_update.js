var editor;
$(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_coach']").addClass("on");

    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="content"]', {
            allowFileManager : true,
            items : [
                'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                'insertunorderedlist', '|', 'emoticons', 'image', 'link','code','source','fullscreen'],
            uploadJson : '/static/js/kindeditor/kd_upload_image.jsp',
            allowFileManager : true,
            fileManagerJson : '/static/js/kindeditor/kd_upload_file.jsp'
        });
    });

    // 上传缩略图
    uploadHeadPortrait();
    $("#B_submit").click(updateCoach);
    $("#B_delete_headPortrait").click(deleteHeadPortrait);
})

function deleteHeadPortrait(){
    $("#uploadImage").attr("src", "");
    $("input[name='headPortrait']").val("");
}

function updateCoach(){
    var coachName = $("input[name='coachName']").val();
    if(coachName == ""){
        alert("教练名称不能为空");
        return ;
    }
    var coachTitle = $("input[name='coachTitle']").val();
    if(coachTitle == ""){
        alert("教练职称不能为空");
        return ;
    }
    var headPortrait = $("input[name='headPortrait']").val();
    if(headPortrait == ""){
        alert("头像不能为空");
        return ;
    }
    if(editor.html() == ""){
        alert("内容不能为空");
        return ;
    }
    $("input[name='introduce']").val(editor.html());
    $.ajax({
        type : "post",
        url : "update",
        data : $("#coachForm").serialize(),
        dataType : "json",
        success: function(result, status) {
            if(result.errCode == "0"){
                alert(result.errMsg);
                return ;
            }else {
                alert("修改教练信息成功");
                window.location.href = "list";
            }
        }
    })
}

function uploadHeadPortrait(){
    var button = $("#uploadImage"), interval;
    new AjaxUpload(button, {
        action: "uploadHeadPortrait",
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
                $("#uploadImage").attr("src", resultData.baseUrl + resultData.headPortrait);
                $("input[name='headPortrait']").val(resultData.headPortrait);
            }
        }
    });
}