$(function(){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_course']").addClass("on");

    $("a[name^='A_update']").click(redirectUpdatePage);
    // 下架课程
    $("a[name^='A_down_']").click(function (){
        var courseId = $(this).data("courseid");
        downOrUpCourse(courseId, 1);
    });
    // 上架课程
    $("a[name^='A_up_']").click(function (){
        var courseId = $(this).data("courseid");
        downOrUpCourse(courseId, 0);
    });
})
// 跳转到修改页面
function redirectUpdatePage(){
    var courseId = $(this).data("courseid");
    window.location.href = "toUpdate?courseId=" + courseId;
}

function downOrUpCourse(courseId, status){
    $.ajax({
        type : "post",
        url : "updateStatus",
        data : {"courseId" : courseId, "status" : status},
        dataType : "json",
        success: function(result, status) {
            if(result.code != "1"){
                alert(result.msg);
                return ;
            }else {
                // 修改成功，跳转到列表页面
                alert("修改状态成功");
                window.location.href = "list";
            }
        }
    })
}