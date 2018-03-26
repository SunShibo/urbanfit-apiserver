var editor;
$(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_course']").addClass("on");

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

    $("#B_submit").click(updateCourse);
    $("#B_delete_image").click(deleteImageUrl);
    uploadCourseImageUrl();
})

// 修改课程信息
function updateCourse(){
    var courseTitle = $("input[name='courseTitle']").val();
    if(courseTitle == ""){
        alert("标题不能为空");
        return ;
    }
    var coursePrice = $("input[name='coursePrice']").val();
    if(coursePrice == ""){
        alert("价格不能为空");
        return ;
    }
    var coursePricePattern = /^\d+(\.\d{1,2})?$/;
    if(!coursePricePattern.test(coursePrice)){
        alert("价格输入格式有误");
        return ;
    }
    var courseImageUrl = $("input[name='courseImageUrl']").val();
    if(courseImageUrl == ""){
        alert("请选择课程图片");
        return ;
    }
    var introduce = editor.html();
    if(introduce == ""){
        alert("课程内容不能为空");
        return ;
    }
    $("input[name='introduce']").val(editor.html());
    var courseId = $("input[name='courseId']").val();
    $.ajax({
        type : "post",
        url : "update",
        data : $("#courseForm").serialize(),
        dataType : "json",
        success: function(result, status) {
            if(result.code != "1"){
                alert(result.msg);
                return ;
            }else {
                // 修改成功，跳转到列表页面
                alert("修改课程信息成功");
                window.location.href = "list";
            }
        }
    })
}

function uploadCourseImageUrl(){
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
                $("#uploadImage").attr("src", resultData.baseUrl + resultData.courseImageUrl);
                $("input[name='courseImageUrl']").val(resultData.courseImageUrl);
            }
        }
    });
}

function deleteImageUrl(){
    $("#uploadImage").attr("src", "");
    $("input[name='courseImageUrl']").val("");
}