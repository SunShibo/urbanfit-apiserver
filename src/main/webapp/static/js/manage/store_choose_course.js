$(function (){
    $("#B_query").click(queryStoreCourse);
    $("a[id^='A_choose_course_']").click(chooseStoreCourse);
    $("select[name='courseType']").change(queryStoreCourse);
    initCourseType();
    parent.$("body").data("STORE_CHOOSE_COURSE", "");
    parent.$("body").data("COURSE_ID", "");
    parent.$("body").data("COURSE_NAME", "");
})

function initCourseType(){
    $("select[name='courseType']").val(courseType);
}

function queryStoreCourse(){
    document.forms[0].submit();
}

function chooseStoreCourse(){
    if(window.confirm("确认选择该课程？")){
        var courseId = $(this).data("courseid");
        var courseName = $(this).data("coursename");
        parent.$("body").data("STORE_CHOOSE_COURSE", "success");
        parent.$("body").data("COURSE_ID", courseId);
        parent.$("body").data("COURSE_NAME", courseName);
        closeLayer();
    }
}

function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
}