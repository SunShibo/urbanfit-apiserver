$(function(){
    $("#B_query").click(queryCourseStore);
    $("a[id^='A_choose_store_']").click(chooseCourseStore);

    parent.$("body").data("COURSE_CHOOSE_STORE", "");
    parent.$("body").data("STORE_ID", "");
    parent.$("body").data("STORE_NAME", "");
})

function queryCourseStore(){
    document.forms[0].submit();
}

function chooseCourseStore(){
    if(window.confirm("确认选择该俱乐部？")){
        var storeId = $(this).data("storeid");
        var storeName = $(this).data("storename");
        parent.$("body").data("COURSE_CHOOSE_STORE", "success");
        parent.$("body").data("STORE_ID", storeId);
        parent.$("body").data("STORE_NAME", storeName);
        closeLayer();
    }
}

function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index);
}