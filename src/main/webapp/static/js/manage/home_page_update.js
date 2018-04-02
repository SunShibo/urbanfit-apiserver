$(function(){
    initHomePageInfo();
    $("#B_submit").click(updateHomePageInfo);
})

function initHomePageInfo(){
    if(content != ""){
        $.each(JSON.parse(content), function (i, n){
            var index = parseInt(i + 1);
            $("input[name='title_" + index + "']").val(n.title);
            $("input[name='linkUrl_" + index + "']").val(n.linkUrl);
        })
    }
}

function updateHomePageInfo(){

}