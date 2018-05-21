$(function (){
    $("input[id='B_add_sizeName_1']").click(addSizeName);
    $("input[id='B_add_sizeType']").click(addSizeType);
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