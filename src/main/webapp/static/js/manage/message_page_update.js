$(function(){
    initMessagePageInfo();
    $("#B_submit").click(updateMessagePageInfo);
})

function initMessagePageInfo(){
    if(content != ""){
        $.each(JSON.parse(content), function (i, n){
            var index = parseInt(i + 1);
            $("input[name='title_" + index + "']").val(n.title);
            $("input[name='linkUrl_" + index + "']").val(n.linkUrl);
        })
    }
}

function updateMessagePageInfo(){
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
        if(title != "" && linkUrl != ""){
            contentArr.push('{"title":"' + title + '", "linkUrl" : "' + linkUrl + '"}');
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
            updateMessagePageInfoDetail();
        }
    }else{
        $("input[name='content']").val("[" + contentArr.join(",") + "]");
        updateMessagePageInfoDetail();
    }
}

function updateMessagePageInfoDetail(){
    $.ajax({
        type : "post",
        url : "update",
        data : $("#messagePageForm").serialize(),
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