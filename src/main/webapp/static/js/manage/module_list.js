$(function(){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_module']").addClass("on");

    $("a[name^='A_enable_']").click(function (){
        var moduleId = $(this).data("moduleid");
        updateModuleStatus(moduleId, 0);
    });

    $("a[name^='A_disable_']").click(function (){
        var moduleId = $(this).data("moduleid");
        updateModuleStatus(moduleId, 1);
    });

    $("a[name^='A_update_']").click(redirectUpdatePage);
})

function updateModuleStatus(moduleId, status){
    if(status == 1){
        if(window.confirm("禁用后客户端不显示，确定要禁用吗？")){
            window.location.href = "updateStatus?moduleId=" + moduleId + "&status=" + status;
        }
    }else{
        if(window.confirm("启动后客户端将显示此内容，确定要启用吗？")){
            window.location.href = "updateStatus?moduleId=" + moduleId + "&status=" + status;
        }
    }
}

function redirectUpdatePage(){
    var moduleId = $(this).data("moduleid");
    var type = $(this).data("type");
    window.location.href = "toUpdate?moduleId=" + moduleId + "&type=" + type;
}