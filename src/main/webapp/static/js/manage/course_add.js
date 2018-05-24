var editor;
$(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_course']").addClass("on");

    $("input[id='B_add_sizeName_1']").click(addSizeName);
    $("input[id='B_add_sizeType']").click(addSizeType);
    $("#sizeName_1_1").blur(showCoursePriceDetail);
    $("#B_add_store").click(openChooseStoreLayer);

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

// 添加规格属性
function addSizeType(){
    var sizeTypeArr = [];
    var sizeTypeIndex = $("input[name='sizeTypeIndex']").val();
    sizeTypeIndex = parseInt(parseInt(sizeTypeIndex) + 1);
    sizeTypeArr.push('<div id="courseSizeDiv_'+ sizeTypeIndex + '">');
        sizeTypeArr.push('<input type="text" placeholder="请输入规格属性" class="long" data-tid="' + sizeTypeIndex + '" id="sizeType_' + sizeTypeIndex + '">');
        sizeTypeArr.push('<input type="button" id="B_delete_sizeType_'+ sizeTypeIndex + '" data-aid="'+ sizeTypeIndex + '" value="删除属性" class="course-btn">');
        sizeTypeArr.push('<br/>');
        sizeTypeArr.push('<input type="text" placeholder="请输入规格信息" class="short" id="sizeName_' + sizeTypeIndex + '_1">');
        sizeTypeArr.push('<input type="button" id="B_add_sizeName_'+ sizeTypeIndex+ '" data-aid="'+ sizeTypeIndex + '" value="添加信息" class="course-btn">');
        sizeTypeArr.push('<br/>');
        sizeTypeArr.push('<input type="hidden" name="sizeNameIndex_' + sizeTypeIndex + '" value="1">');
    sizeTypeArr.push('</div>');

    $("input[name='sizeTypeIndex']").val(sizeTypeIndex);
    $("#courseSizeDiv").append(sizeTypeArr.join(""));
    $("input[id='B_add_sizeName_"+ sizeTypeIndex +"']").click(addSizeName);
    $("#B_delete_sizeType_" + sizeTypeIndex + "").click(deleteSizeType);
    $("#sizeName_" + sizeTypeIndex + "_1").blur(showCoursePriceDetail);
}

// 添加规格信息
function addSizeName(){
    var aid = $(this).data("aid");
    var sizeNameArr = [];
    var sizeNameIndex = $("input[name='sizeNameIndex_" + aid + "']").val();
    sizeNameIndex = parseInt(parseInt(sizeNameIndex) + 1);
    sizeNameArr.push('<div id="sizeNameDiv_' + aid + '">');
        sizeNameArr.push('<input type="text" placeholder="请输入规格信息" class="short" data-nid="'
            + sizeNameIndex + '" id="sizeName_' + aid + '_' + sizeNameIndex + '">');
        sizeNameArr.push('<input type="button" id="B_delete_sizeName_' + aid + '_'+ sizeNameIndex+ '" data-nid="'
            + sizeNameIndex + '" data-tid=' + aid +'  value="删除" class="course-btn">');
        sizeNameArr.push('<br/>');
    sizeNameArr.push('</div>')
    $("input[name='sizeNameIndex_" + aid + "']").val(sizeNameIndex);
    $("#courseSizeDiv_" + aid + "").append(sizeNameArr.join(""));
    $("input[id='B_delete_sizeName_" + aid + "_" + sizeNameIndex + "']").click(deleteSizeName);
    $("#sizeName_" + aid + "_" + sizeNameIndex + "").blur(showCoursePriceDetail);
}

// 删除规格信息
function deleteSizeName(){
    var sizeTypeId = $(this).data("tid");
    $("#sizeNameDiv_" + sizeTypeId + "").remove();
    showCoursePriceDetail();
}

// 删除规格属性
function deleteSizeType(){
    var aid = $(this).data("aid");
    $("#courseSizeDiv_" + aid + "").remove();
    showCoursePriceDetail();
}


function showCoursePriceDetail(){
    var totalRow = 1;
    var skuTypeArr = [];
    $("input[id^='sizeType_']").each(function(){
        var skuTypeObj = {};//sku类型对象
        //SKU属性类型标题
        skuTypeObj.skuTypeTitle =$(this).val();
        skuTypeObj.skuTypeKey = $(this).data("tid");
        var skuValueArr = [];//存放SKU值得数组
        var tid = $(this).data("tid");
        var sizeNameLength = 0;

        $("input[id^='sizeName_" + tid + "_']").each(function(){
            if($(this).val() != ""){
                sizeNameLength += 1;

                var skuValObj = {};                             //SKU值对象
                skuValObj.skuValueTitle = $(this).val();      //SKU值名称
                skuValObj.skuValueId = $(this).data("nid");   //SKU值主键
                skuValueArr.push(skuValObj);
            }
        });
        totalRow = totalRow * sizeNameLength;
        skuTypeObj.skuValues = skuValueArr;               //sku值数组
        skuTypeObj.skuValueLen = skuValueArr.length;     //sku值长度
        skuTypeArr.push(skuTypeObj);                       //保存进数组中
    })

    var skuDetailArr = [];
    skuDetailArr.push('<table class="skuTable" cellpadding="0" cellspacing="0"><tr>');
    //创建表头
    for(var t = 0; t < skuTypeArr.length; t ++){
        skuDetailArr.push('<th>' + skuTypeArr[t].skuTypeTitle + '</th>');
    }
    skuDetailArr.push('<th>价格</th>');
    skuDetailArr.push('<th>是否可售</th>');
    skuDetailArr.push('</tr>');

    //循环处理表体
    for(var i = 0 ; i < totalRow ; i ++){
        var currRowDoms = "";
        var rowCount = 1;                                       //记录行数
        for(var j = 0; j < skuTypeArr.length; j ++) {        //sku列
            var skuValues = skuTypeArr[j].skuValues;           //SKU值数组
            var skuValueLen = skuValues.length;                //sku值长度
            rowCount = (rowCount * skuValueLen);                //目前的生成的总行数
            var anInterBankNum = (totalRow / rowCount);         //跨行数
            var point = ((i / anInterBankNum) % skuValueLen);
            if (0 == (i % anInterBankNum)) {                    //需要创建td
                currRowDoms += '<td rowspan=' + anInterBankNum + '>' + skuValues[point].skuValueTitle + '</td>';
            }
        }
        skuDetailArr.push('<tr>' + currRowDoms + '<td><input type="text"/></td>');
        skuDetailArr.push(  '<td><input type="checkbox">不可售</td>');
        skuDetailArr.push('</tr>');
    }
    skuDetailArr.push('</table>');
    $("#coursePriceDiv").html(skuDetailArr.join(""));
}

function openChooseStoreLayer(){
    var storeIds = $("input[name='storeIds']").val();
    layer.open({
        title : '选择俱乐部',
        type: 2,
        content : "/store/courseStoreList?storeIds=" + storeIds,
        area: ['80%', '85%'],
        full: true,
        end : function (){
            var chooseStatus = $("body").data("COURSE_CHOOSE_STORE");
            if(chooseStatus == "success"){
                var storeId = $("body").data("STORE_ID");
                var storeName = $("body").data("STORE_NAME");
                var courseStoreArr = [];
                courseStoreArr.push('<div id="courseStoreDiv_' + storeId + '">')
                courseStoreArr.push('<input type="text" class="long" value="' + storeName + '" ' +
                    'data-store="' + storeId + '">');
                courseStoreArr.push('<input type="button" value="删除俱乐部" data-storeid="' + storeId
                    + '" id="B_delete_store_' + storeId +'" class="course-btn">');
                courseStoreArr.push('</div>');
                $("#courseStoreDiv").append(courseStoreArr.join(""));
                // 更新俱乐部id
                updateChooseStoreId(storeId);
                $("#B_delete_store_" + storeId +"").click(deleteChooseStore);
            }
        }
    });
}

function updateChooseStoreId(storeId){
    var storeIds = $("input[name='storeIds']").val();
    if(storeIds == ""){
        $("input[name='storeIds']").val(storeId);
    }else{
        $("input[name='storeIds']").val(storeIds + "," + storeId);
    }
}

function deleteChooseStore(){
    var storeId = $(this).data("storeid");
    var storeIds = $("input[name='storeIds']").val();
    if(storeIds != ""){
        var storeIdArr = [];
        $.each(storeIds.split(","), function(i, n){
            if(storeId != n){
                storeIdArr.push(n);
            }
        })
        if(storeIdArr == ""){
            $("input[name='storeIds']").val("");
        }else{
            $("input[name='storeIds']").val(storeIdArr.join(","));
        }
        $("#courseStoreDiv_" + storeId + "").remove();
    }
}