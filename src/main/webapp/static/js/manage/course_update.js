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
    $("#B_delete_image").click(deleteImageUrl);
    uploadCourseImageUrl();
    initCityInfo();
})

// 修改课程信息
function updateCourse(){
    var courseTitle = $("input[name='courseTitle']").val();
    if(courseTitle == ""){
        alert("标题不能为空");
        return ;
    }
    var coursePrice = $("input[name='coursePrice']").val();
    if(coursePrice == ""){
        alert("价格不能为空");
        return ;
    }
    var coursePricePattern = /^\d+(\.\d{1,2})?$/;
    if(!coursePricePattern.test(coursePrice)){
        alert("价格输入格式有误");
        return ;
    }
    var courseImageUrl = $("input[name='courseImageUrl']").val();
    if(courseImageUrl == ""){
        alert("请选择课程图片");
        return ;
    }
    var introduce = editor.html();
    if(introduce == ""){
        alert("课程内容不能为空");
        return ;
    }
    var isCity = true;
    var courseDistrictArr = [];
    $("div[id^='city_info']").each(function (){
        var index = $(this).data("index");
        var proviceInfo = $("#proviceId_" + index + "").val();
        if(proviceInfo == ""){
            alert("第" + index + "个所属区域请选择省市区信息");
            isCity = false;
            return false;
        }
        var cityInfo = $("#cityId_" + index + "").val();
        if(cityInfo == ""){
            alert("第" + index + "个所属区域请选择省市区信息");
            isCity = false;
            return false;
        }
        var districtInfo = $("#districtId_" + index + "").val();
        if(districtInfo == ""){
            alert("第" + index + "个所属区域请选择省市区信息");
            isCity = false;
            return false;
        }
        var courseDistrict = proviceInfo + "," + cityInfo + "," + districtInfo;
        courseDistrictArr.push(courseDistrict);
    });

    if(isCity == false){
        return ;
    }
    $("input[name='courseDistrict']").val(courseDistrictArr.join("#"));
    $("input[name='introduce']").val(editor.html());
    var courseId = $("input[name='courseId']").val();
    $.ajax({
        type : "post",
        url : "update",
        data : $("#courseForm").serialize(),
        dataType : "json",
        success: function(result, status) {
            if(result.code != "1"){
                alert(result.msg);
                return ;
            }else {
                // 修改成功，跳转到列表页面
                alert("修改课程信息成功");
                window.location.href = "list";
            }
        }
    })
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