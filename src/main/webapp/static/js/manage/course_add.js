var editor;
$(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_course']").addClass("on");

    /*$("input[id='B_add_sizeName_1']").click(addSizeName);
    $("input[id='B_add_sizeType']").click(addSizeType);
    $("#sizeName_1_1").blur(showCoursePriceDetail);*/

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
})


function addSizeType(){
    var sizeTypeArr = [];
    var sizeTypeIndex = $("input[name='sizeTypeIndex']").val();
    sizeTypeIndex = parseInt(parseInt(sizeTypeIndex) + 1);
    sizeTypeArr.push('<div id="courseSizeDiv_'+ sizeTypeIndex + '">');
        sizeTypeArr.push('<input type="text" placeholder="请输入规格属性" class="long" id="sizeType_' + sizeTypeIndex + '">');
        sizeTypeArr.push('<br/>');
        sizeTypeArr.push('<input type="text" placeholder="请输入规格信息" class="short" id="sizeName_' + sizeTypeIndex + '_1">');
        sizeTypeArr.push('<input type="button" id="B_add_sizeName_'+ sizeTypeIndex+ '" data-aid="'+ sizeTypeIndex + '" value="添加信息" class="course-btn">');
        sizeTypeArr.push('<br/>');
        sizeTypeArr.push('<input type="hidden" name="sizeNameIndex_' + sizeTypeIndex + '" value="1">');
    sizeTypeArr.push('</div>');

    $("input[name='sizeTypeIndex']").val(sizeTypeIndex);
    $("#courseSizeDiv").append(sizeTypeArr.join(""));
    $("input[id='B_add_sizeName_"+ sizeTypeIndex +"']").click(addSizeName);
}


function addSizeName(){
    var aid = $(this).data("aid");
    var sizeNameArr = [];
    var sizeNameIndex = $("input[name='sizeNameIndex_" + aid + "']").val();
    sizeNameIndex = parseInt(parseInt(sizeNameIndex) + 1);
    sizeNameArr.push('<input type="text" placeholder="请输入规格信息" class="short" id="sizeName_' + aid
        + '_' + sizeNameIndex + '">');
    sizeNameArr.push('<input type="button" id="B_delete_sizeName_' + aid + '_'+ sizeNameIndex+ '" data-nid="'
        + sizeNameIndex + '" data-tid=' + aid +'  value="删除" class="course-btn">');
    sizeNameArr.push('<br/>');
    $("input[name='sizeNameIndex_" + aid + "']").val(sizeNameIndex);
    $("#courseSizeDiv_" + aid + "").append(sizeNameArr.join(""));
    $("input[id='B_delete_sizeName_" + aid + "_" + sizeNameIndex + "']").click(deleteSizeName);
}

function deleteSizeName(){
    var sizeNameId = $(this).data("nid");
    var sizeTypeId = $(this).data("tid");
    $("#sizeName_" + sizeTypeId +"_" + sizeNameId + "").css("margin-top","0px");
    $("#sizeName_" + sizeTypeId +"_" + sizeNameId + "").remove();
    $("#B_delete_sizeName_" + sizeTypeId +"_" + sizeNameId + "").remove();
}

function showCoursePriceDetail(){
    var sizeName = $(this).val();
    if(sizeName != ""){
        /*var sizeTypeIndex = $("input[name='sizeTypeIndex']").val();
        for(var i = 1; i <= sizeTypeIndex; i++){
            $("input[id^='sizeName_" + i +"']").each(function(i, n){
                var sizeName = $(this).val();
            });
        }*/
        var i = 1;
        // 循环第一层
        $("input[id^='sizeName_1_']").each(function(i, n){
            var sizeName = $(this).val();
            if($("input[id^='sizeName_" + i + "_']").length > 0){
                $("input[id^='sizeName_" + i + "_']").each(function(i, n){
                    alert(sizeName + "_" + $(this).val());
                })
            }
        });

    }
}