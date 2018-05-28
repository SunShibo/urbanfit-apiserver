var editor;
$(function (){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_course']").addClass("on");

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
    // 初始化课程类型
    initCourseType();
    // 初始化课程选择俱乐部
    initCourseChooseStore();
    // 初始化课程规格、价格信息
    initCourseSizeInfo();
    // 添加俱乐部
    $("#B_add_store").click(openChooseStoreLayer);
})

function initCourseSizeInfo(){
    $.ajax({
        type : "post",
        url : "/course/courseSize",
        data : {"courseId" : course.courseId},
        dataType : "json",
        async : false,
        success : function (data) {
            if(data.code == 1){
                var courseSizeArr = [];
                var lstSizeType = data.data.lstSizeType;
                var lstSizeName = data.data.lstSizeName;
                var sizeNameIndex = 0;
                $.each(lstSizeType, function (i, n){
                    var sizeTypeArr = [];
                    var sizeTypeIndex = parseInt(parseInt(i) + 1);
                    sizeTypeArr.push('<div id="courseSizeDiv_'+ sizeTypeIndex + '">');
                    sizeTypeArr.push('<input type="text" value="' + n.sizeName + '" class="long" data-tid="' + sizeTypeIndex + '" id="sizeType_' + sizeTypeIndex + '">');
                    if(i == 0){
                        sizeTypeArr.push('<input type="button"  id="B_add_sizeType" value="添加属性" class="course-btn">');
                    }else{
                        sizeTypeArr.push('<input type="button" id="B_delete_sizeType_'+ sizeTypeIndex + '" data-aid="'+ sizeTypeIndex + '" value="删除属性" class="course-btn">');
                    }
                    sizeTypeArr.push('<br/>');
                    var sizeIndex = 0;
                    $.each(lstSizeName, function (j, m){
                        if(n.sizeId == m.parentId){
                            sizeNameIndex = sizeNameIndex + 1;
                            sizeTypeArr.push('<div id="sizeNameDiv_' + sizeNameIndex + '">');
                            sizeTypeArr.push('<input type="text" data-sid="' + m.sizeId + '" value="' + m.sizeName + '" class="short" data-nid="' + sizeNameIndex + '" id="sizeName_' + sizeTypeIndex + '_' + sizeNameIndex + '">');
                            if(sizeIndex == 0){
                                sizeTypeArr.push('<input type="button" id="B_add_sizeName_'+ sizeTypeIndex+ '" data-aid="'+ sizeTypeIndex + '" value="添加信息" class="course-btn">');
                            }else{
                                sizeTypeArr.push('<input type="button" id="B_delete_sizeName_' + sizeTypeIndex + '_'+ sizeNameIndex + '" data-nid="' + sizeNameIndex + '" data-tid=' + sizeTypeIndex +'  value="删除" class="course-btn">');
                            }
                            sizeTypeArr.push('<br/>');
                            sizeTypeArr.push('</div>');
                            sizeIndex = sizeIndex + 1;
                        }
                    });
                    sizeTypeArr.push('</div>');
                    $("#courseSizeDiv").append(sizeTypeArr.join(""));
                    $("input[id='B_add_sizeName_"+ sizeTypeIndex +"']").click(addSizeName);
                    $("#B_delete_sizeType_" + sizeTypeIndex + "").click(deleteSizeType);
                    $("#sizeName_" + sizeTypeIndex + "_" + sizeNameIndex +"").blur(showCoursePriceDetail);
                    $("input[name='sizeTypeIndex']").val(sizeTypeIndex);
                    $("input[name='sizeNameIndex']").val(sizeNameIndex);

                    initCoursePriceDetail(data.data.lstSizeDetail);
                })
                $("input[id='B_add_sizeType']").click(addSizeType);
                $("input[id^='B_delete_sizeName_']").each(function (){
                    var tid = $(this).data("tid");
                    var nid = $(this).data("nid");
                    $("#B_delete_sizeName_" + tid + "_" + nid + "").click(deleteSizeName);
                });
            }else{
                alert(data.msg);
                return ;
            }
        }
    });
}

function initCoursePriceDetail(lstSizeDetail){
    var courseSizeInfoArr = [];
    var totalRow = 1;
    var skuTypeArr = [];
    $("input[id^='sizeType_']").each(function(){
        var skuTypeObj = {};        //sku类型对象
        //SKU属性类型标题
        skuTypeObj.skuTypeTitle = $(this).val();
        skuTypeObj.skuTypeKey = $(this).data("tid");
        var skuValueArr = [];       //存放SKU值得数组
        var tid = $(this).data("tid");
        var tname = $(this).val();
        var sizeNameLength = 0;
        var sizeNameArr = [];       // 存放规格信息
        $("input[id^='sizeName_" + tid + "_']").each(function(){
            if($(this).val() != ""){
                sizeNameLength += 1;
                var skuValObj = {};                             //SKU值对象
                skuValObj.skuValueTitle = $(this).val();      //SKU值名称
                skuValObj.skuValueId = $(this).data("nid");   //SKU值主键
                skuValObj.skuValueSizeId = $(this).data("sid");
                skuValueArr.push(skuValObj);
                var sizeNameInfo = {"sizeNameId" : $(this).data("nid"), "sizeName" : $(this).val()};
                sizeNameArr.push(sizeNameInfo);
            }
        });
        totalRow = totalRow * sizeNameLength;
        skuTypeObj.skuValues = skuValueArr;               //sku值数组
        skuTypeObj.skuValueLen = skuValueArr.length;     //sku值长度
        skuTypeArr.push(skuTypeObj);                       //保存进数组中

        // 处理课程规格数据信息
        var courseSizeInfo = {"sizeTypeId" : tid, "sizeTypeName" : tname, "sizeNameInfo" : sizeNameArr};
        courseSizeInfoArr.push(courseSizeInfo);
    })
    $("input[name='courseSizeInfo']").val(JSON.stringify(courseSizeInfoArr));

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
        var sizeNameIdArr = []
        var sizeNameTrueIdArr = [];
        var currRowDoms = "";
        var rowCount = 1;                                       //记录行数
        for(var j = 0; j < skuTypeArr.length; j ++) {          //sku列
            var skuValues = skuTypeArr[j].skuValues;           //SKU值数组
            var skuValueLen = skuValues.length;                //sku值长度
            rowCount = (rowCount * skuValueLen);                //目前的生成的总行数
            var anInterBankNum = (totalRow / rowCount);         //跨行数
            var point = ((i / anInterBankNum) % skuValueLen);
            if (0 == (i % anInterBankNum)) {                    //需要创建td
                currRowDoms += '<td rowspan=' + anInterBankNum + '>' + skuValues[point].skuValueTitle + '</td>';
                sizeNameIdArr.push(skuValues[point].skuValueId);
                sizeNameTrueIdArr.push(skuValues[point].skuValueSizeId);
            }else{
                sizeNameIdArr.push(skuValues[parseInt(point)].skuValueId);
                sizeNameTrueIdArr.push(skuValues[parseInt(point)].skuValueSizeId);
            }
        }

        var courseSizePrice;
        var courseSizeIsSale;
        $.each(lstSizeDetail, function (i, n){
            if(n.sizeDetail == sizeNameTrueIdArr.join(",")) {
                courseSizePrice = n.sizePrice;
                courseSizeIsSale = n.isSale;
            }
        })
        skuDetailArr.push('<tr>' + currRowDoms + '<td><input type="text" value="' + courseSizePrice + '" data-pid="' + i + '" name="sizePrice_' + i +'"/></td>');
        if(courseSizeIsSale == 0){
            skuDetailArr.push(  '<td><input type="checkbox" value="1" id="sizeIsSale_' + i + '">不可售<input type="hidden" name=""></td>');
        }else{
            skuDetailArr.push(  '<td><input type="checkbox" checked="checked" value="1" id="sizeIsSale_' + i + '">不可售<input type="hidden" name=""></td>');
        }
        skuDetailArr.push(  '<input type="hidden" value=' + sizeNameIdArr.join(",") + ' name="sizePriceInfo_' + i +'">');
        skuDetailArr.push(  '<input type="hidden" value=' + sizeNameTrueIdArr.join(",") + ' name="sizePriceInfoTrue_' + i +'">');
        skuDetailArr.push('</tr>');
    }
    skuDetailArr.push('</table>');
    $("#coursePriceDiv").html(skuDetailArr.join(""));
}

// 添加规格信息
function addSizeName(){
    var aid = $(this).data("aid");
    var sizeNameArr = [];
    var sizeNameIndex = $("input[name='sizeNameIndex']").val();
    sizeNameIndex = parseInt(parseInt(sizeNameIndex) + 1);
    sizeNameArr.push('<div id="sizeNameDiv_' + sizeNameIndex + '">');
    sizeNameArr.push('<input type="text" placeholder="请输入规格信息" class="short" data-nid="'
        + sizeNameIndex + '" id="sizeName_' + aid + '_' + sizeNameIndex + '">');
    sizeNameArr.push('<input type="button" id="B_delete_sizeName_' + aid + '_'+ sizeNameIndex + '" data-nid="'
        + sizeNameIndex + '" data-tid=' + aid +'  value="删除" class="course-btn">');
    sizeNameArr.push('  <br/>');
    sizeNameArr.push('</div>')
    $("input[name='sizeNameIndex']").val(sizeNameIndex);
    $("#courseSizeDiv_" + aid + "").append(sizeNameArr.join(""));
    $("input[id='B_delete_sizeName_" + aid + "_" + sizeNameIndex + "']").click(deleteSizeName);
    $("#sizeName_" + aid + "_" + sizeNameIndex + "").blur(showCoursePriceDetail);
}

// 删除规格信息
function deleteSizeName(){
    var sizeNameId = $(this).data("nid");
    $("#sizeNameDiv_" + sizeNameId + "").remove();
    showCoursePriceDetail();
}

// 删除规格属性
function deleteSizeType(){
    var aid = $(this).data("aid");
    $("#courseSizeDiv_" + aid + "").remove();
    showCoursePriceDetail();
}


function showCoursePriceDetail(){
    var courseSizeInfoArr = [];
    var totalRow = 1;
    var skuTypeArr = [];
    $("input[id^='sizeType_']").each(function(){
        var skuTypeObj = {};        //sku类型对象
        //SKU属性类型标题
        skuTypeObj.skuTypeTitle = $(this).val();
        skuTypeObj.skuTypeKey = $(this).data("tid");
        var skuValueArr = [];       //存放SKU值得数组
        var tid = $(this).data("tid");
        var tname = $(this).val();
        var sizeNameLength = 0;
        var sizeNameArr = [];       // 存放规格信息
        $("input[id^='sizeName_" + tid + "_']").each(function(){
            if($(this).val() != ""){
                sizeNameLength += 1;
                var skuValObj = {};                             //SKU值对象
                skuValObj.skuValueTitle = $(this).val();      //SKU值名称
                skuValObj.skuValueId = $(this).data("nid");   //SKU值主键
                skuValueArr.push(skuValObj);
                var sizeNameInfo = {"sizeNameId" : $(this).data("nid"), "sizeName" : $(this).val()};
                sizeNameArr.push(sizeNameInfo);
            }
        });
        totalRow = totalRow * sizeNameLength;
        skuTypeObj.skuValues = skuValueArr;               //sku值数组
        skuTypeObj.skuValueLen = skuValueArr.length;     //sku值长度
        skuTypeArr.push(skuTypeObj);                       //保存进数组中

        // 处理课程规格数据信息
        var courseSizeInfo = {"sizeTypeId" : tid, "sizeTypeName" : tname, "sizeNameInfo" : sizeNameArr};
        courseSizeInfoArr.push(courseSizeInfo);
    })
    $("input[name='courseSizeInfo']").val(JSON.stringify(courseSizeInfoArr));

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
        var sizeNameIdArr = []
        var currRowDoms = "";
        var rowCount = 1;                                       //记录行数
        for(var j = 0; j < skuTypeArr.length; j ++) {          //sku列
            var skuValues = skuTypeArr[j].skuValues;           //SKU值数组
            var skuValueLen = skuValues.length;                //sku值长度
            rowCount = (rowCount * skuValueLen);                //目前的生成的总行数
            var anInterBankNum = (totalRow / rowCount);         //跨行数
            var point = ((i / anInterBankNum) % skuValueLen);
            if (0 == (i % anInterBankNum)) {                    //需要创建td
                currRowDoms += '<td rowspan=' + anInterBankNum + '>' + skuValues[point].skuValueTitle + '</td>';
                sizeNameIdArr.push(skuValues[point].skuValueId);
            }else{
                sizeNameIdArr.push(skuValues[parseInt(point)].skuValueId);
            }
        }
        skuDetailArr.push('<tr>' + currRowDoms + '<td><input type="text" data-pid="' + i + '" name="sizePrice_' + i +'"/></td>');
        skuDetailArr.push(  '<td><input type="checkbox" value="1" id="sizeIsSale_' + i + '">不可售<input type="hidden" name=""></td>');
        skuDetailArr.push(  '<input type="hidden" value=' + sizeNameIdArr.join(",") + ' name="sizePriceInfo_' + i +'">');
        skuDetailArr.push('</tr>');
    }
    skuDetailArr.push('</table>');
    $("#coursePriceDiv").html(skuDetailArr.join(""));
}

// 添加规格属性
function addSizeType(){
    var sizeTypeArr = [];
    var sizeNameIndex = $("input[name='sizeNameIndex']").val();
    sizeNameIndex = parseInt(parseInt(sizeNameIndex) + 1);
    var sizeTypeIndex = $("input[name='sizeTypeIndex']").val();
    sizeTypeIndex = parseInt(parseInt(sizeTypeIndex) + 1);
    sizeTypeArr.push('<div id="courseSizeDiv_'+ sizeTypeIndex + '">');
    sizeTypeArr.push('<input type="text" placeholder="请输入规格属性" class="long" data-tid="' + sizeTypeIndex + '" id="sizeType_' + sizeTypeIndex + '">');
    sizeTypeArr.push('<input type="button" id="B_delete_sizeType_'+ sizeTypeIndex + '" data-aid="'+ sizeTypeIndex + '" value="删除属性" class="course-btn">');
    sizeTypeArr.push('<br/>');
    sizeTypeArr.push('<input type="text" placeholder="请输入规格信息" class="short" data-nid="' + sizeNameIndex + '" id="sizeName_' + sizeTypeIndex + '_' + sizeNameIndex + '">');
    sizeTypeArr.push('<input type="button" id="B_add_sizeName_'+ sizeTypeIndex+ '" data-aid="'+ sizeTypeIndex + '" value="添加信息" class="course-btn">');
    sizeTypeArr.push('<br/>');
    sizeTypeArr.push('</div>');

    $("input[name='sizeTypeIndex']").val(sizeTypeIndex);
    $("input[name='sizeNameIndex']").val(sizeNameIndex);
    $("#courseSizeDiv").append(sizeTypeArr.join(""));
    $("input[id='B_add_sizeName_"+ sizeTypeIndex +"']").click(addSizeName);
    $("#B_delete_sizeType_" + sizeTypeIndex + "").click(deleteSizeType);
    $("#sizeName_" + sizeTypeIndex + "_" + sizeNameIndex + "").blur(showCoursePriceDetail);
}

function initCourseChooseStore(){
    $.ajax({
        type : "post",
        url : "/store/courseChooseStore",
        data : {"storeIds" : course.storeId},
        dataType : "json",
        async : false,
        success : function (data) {
            if(data.code == 1){
                $.each(data.data, function (i, n){
                    var courseStoreArr = [];
                    courseStoreArr.push('<div id="courseStoreDiv_' + n.storeId + '">')
                    courseStoreArr.push('<input type="text" class="long" value="' + n.storeName + '" ' +
                        'data-store="' + n.storeId + '">');
                    courseStoreArr.push('<input type="button" value="删除俱乐部" data-storeid="' + n.storeId
                        + '" id="B_delete_store_' + n.storeId +'" class="course-btn">');
                    courseStoreArr.push('</div>');
                    $("#courseStoreDiv").append(courseStoreArr.join(""));
                    $("#B_delete_store_" + n.storeId +"").click(deleteChooseStore);
                })
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

function initCourseType(){
    $("select[name='courseType']").val(course.courseType);
}

// 修改课程信息
function updateCourse(){
    var courseName = $("input[name='courseName']").val();
    if(courseName == ""){
        alert("课程名称不能为空");
        return;
    }
    var storeIds = $("input[name='storeIds']").val();
    if(storeIds == ""){
        alert("请关联俱乐部");
        return;
    }
    var courseSizeInfo = $("input[name='courseSizeInfo']").val();
    if(courseSizeInfo == ""){
        alert("请填写规格属性信息");
        return;
    }
    // 判断价格是否填写
    if(sizePriceIsWrite() == false){
        alert("请填写规格价格信息");
        return;
    }
    var introduce = editor.html();
    if(introduce == ""){
        alert("课程内容不能为空");
        return ;
    }
    // 处理课程规格价格信息
    dealCourseSizePrice();
    // 修改课程
    var courseType = $("select[name='courseType']").val();
    var sizePriceInfo = $("input[name='sizePriceInfo']").val();
    $.ajax({
        type : "post",
        url : "/course/update",
        data : {"courseName" : courseName, "storeIds" : storeIds, "courseSizeInfo" : courseSizeInfo,
            "sizePriceInfo" : sizePriceInfo, "introduce" : introduce, "courseType" : courseType,
            "courseId" : course.courseId},
        dataType : "json",
        success : function (data){
            if(data.code == 1){
                alert("修改课程成功");
                window.location.href = "/course/list";
            }else{
                alert(data.msg);
                return ;
            }
        }
    });
}

function dealCourseSizePrice(){
    var sizePriceInfoArr = [];
    $("input[name^='sizePrice_']").each(function(i, n){
        var pid = $(this).data("pid");
        var price = $(this).val();
        var sizePriceInfo = $("input[name='sizePriceInfo_" + pid + "']").val();
        var isSale = $("#sizeIsSale_" + pid +"").is(":checked") ? 1 : 0;
        var sizePriceDetail = {"courseSize" : sizePriceInfo, "sizePrice" : price, "isSale" : isSale};
        sizePriceInfoArr.push(sizePriceDetail);
    });
    $("input[name='sizePriceInfo']").val(JSON.stringify(sizePriceInfoArr));
}

function sizePriceIsWrite(){
    var isWrite = true;
    // 判断价格是否填写
    $("input[name^='sizePrice_']").each(function(i, n){
        if($(this).val() == ""){
            isWrite = false;
            return ;
        }else{
            var coursePricePattern = /^\d+(\.\d{1,2})?$/;
            if(!coursePricePattern.test($(this).val())){
                isWrite = false;
                return ;
            }
        }
    });
    return isWrite;
}

function uploadCourseImageUrl(){
    var button = $("#uploadImage"), interval;
    new AjaxUpload(button, {
        action: "uploadImage",
        type:"post",
        name: 'myFile',
        responseType : 'json',
        onSubmit: function(file, ext) {
            if (!(ext && /^(jpg|JPG|png|PNG|gif|GIF)$/.test(ext))) {
                alert("您上传的图片格式不对，请重新选择！");
                return false;
            }
        },
        onComplete: function(file, response) {
            if(response.message == "big"){
                alert("图片太大，请重新选择！");
                return ;
            }else if(response.message == "paramError"){
                alert("参数有误！");
                return ;
            }else if(response.message == "fail"){
                alert("修改失败，请重新修改！");
                return ;
            }else if(response.message == "success"){
                var resultData = response.data;
                $("#uploadImage").attr("src", resultData.baseUrl + resultData.courseImageUrl);
                $("input[name='courseImageUrl']").val(resultData.courseImageUrl);
            }
        }
    });
}

function deleteImageUrl(){
    $("#uploadImage").attr("src", "");
    $("input[name='courseImageUrl']").val("");
}

function initCityInfo(){
    var courseDistrict = $("input[name='courseDistrict']").val();
    if(courseDistrict != ""){
        $.each(courseDistrict.split("#"), function (i, n){
            var districtIndex =  parseInt(i + 1);
            var courseDistrictHtmlArr = [];
            courseDistrictHtmlArr.push('<div id="city_info_' + districtIndex +'" data-index="' +districtIndex + '">');
            courseDistrictHtmlArr.push("<br/>");
            courseDistrictHtmlArr.push('<select class="prov" id="proviceId_' + districtIndex + '" name="provice"></select>&nbsp;');
            courseDistrictHtmlArr.push('<select class="city" id="cityId_' + districtIndex +'" name="city"></select>&nbsp;');
            courseDistrictHtmlArr.push('<select class="dist" id="districtId_' + districtIndex +'" name="district"></select>&nbsp;');

            if(districtIndex == 1){
                courseDistrictHtmlArr.push('<input type="button" id="btn_add_courseDistrict" value="添加" class="course-btn">');
            }else {
                courseDistrictHtmlArr.push('<input type="button" id="delete_courseDistrict_' + districtIndex
                    +'" data-index="' +districtIndex + '" value="删除" class="course-btn">');
            }
            courseDistrictHtmlArr.push('</div>');
            $("#courseDistrictDiv").append(courseDistrictHtmlArr.join(""));
            $("input[name='districtIndex']").val(districtIndex);

            var courseDirectArr = n.split(",");
            var proviceInfo = courseDirectArr[0];
            var cityInfo = courseDirectArr[1];
            var districtInfo = courseDirectArr[2];

            $("input[id^='delete_courseDistrict_']").click(function (){
                var index = $(this).data("index");
                $("#city_info_" + index +"").remove();
            });

            $("#city_info_" + districtIndex + "").distpicker({
                province: proviceInfo,
                city: cityInfo,
                district: districtInfo
            });
        });
        $("#btn_add_courseDistrict").click(addCourseDistrict);
    }else{
        var courseDistrictHtmlArr = [];
        courseDistrictHtmlArr.push('<div id="city_info_1" data-index="1">');
            courseDistrictHtmlArr.push("<br/>");
            courseDistrictHtmlArr.push('<select class="prov" id="proviceId_1" name="provice"></select>&nbsp;');
            courseDistrictHtmlArr.push('<select class="city" id="cityId_1" name="city"></select>&nbsp;');
            courseDistrictHtmlArr.push('<select class="dist" id="districtId_1" name="district"></select>&nbsp;');
            courseDistrictHtmlArr.push('<input type="button" id="btn_add_courseDistrict" value="添加" class="course-btn">');
        courseDistrictHtmlArr.push('</div>');
        $("#courseDistrictDiv").append(courseDistrictHtmlArr.join(""));
        $("input[name='districtIndex']").val(1);
        $("#btn_add_courseDistrict").click(addCourseDistrict);

        $("#city_info_1").distpicker({
            province: "",
            city: "",
            district: "",
        });
    }
}

function addCourseDistrict(){
    var districtIndex =  parseInt(parseInt($("input[name='districtIndex']").val()) + 1);
    var courseDistrictArr = [];
    courseDistrictArr.push('<div id="city_info_' + districtIndex +'" data-index="' + districtIndex + '">');
        courseDistrictArr.push("<br/>");
        courseDistrictArr.push('<select class="prov" id="proviceId_' + districtIndex + '" name="provice"></select>&nbsp;');
        courseDistrictArr.push('<select class="city" id="cityId_' + districtIndex +'" name="city"></select>&nbsp;');
        courseDistrictArr.push('<select class="dist" id="districtId_' + districtIndex +'" name="district"></select>&nbsp;');
        courseDistrictArr.push('<input type="button" id="delete_courseDistrict_' + districtIndex
            +'" data-index="' +districtIndex + '" value="删除" class="course-btn">');
    courseDistrictArr.push('</div>');
    $("#courseDistrictDiv").append(courseDistrictArr.join(""));
    $("input[name='districtIndex']").val(districtIndex);

    $("#city_info_" + districtIndex + "").distpicker({
        province: null,
        city: null,
        district: null
    });

    $("input[id^='delete_courseDistrict_']").click(function (){
        var index = $(this).data("index");
        $("#city_info_" + index +"").remove();
    });
}