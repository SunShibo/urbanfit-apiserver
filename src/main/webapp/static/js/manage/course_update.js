var editor;
$(function (){
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
})

// 修改课程信息
function updateCourse(){
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
        data : {"courseId" : courseId, "introduce" : editor.html()},
        dataType : "json",
        success: function(result, status) {
            if(result.errCode == "0"){
                alert(result.errMsg);
                return ;
            }else {
                // 修改成功，跳转到列表页面
                alert("修改课程信息成功");
                window.location.href = "list";
            }
        }
    })
}