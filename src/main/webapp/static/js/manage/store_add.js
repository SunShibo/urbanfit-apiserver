var editor;
$(function(){
    // 添加菜单样式
    $("div[id^='menu_']").removeClass("on");
    $("div[id='menu_store']").addClass("on");

    $("input[name='storeDistrict']").val("");
    $("#B_submit").click(addStore);
    $("#B_add_course").click(openChooseCourseLayer);

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

function openChooseCourseLayer(){
    var courseIds = $("input[name='courseIds']").val();
    layer.open({
        title : '选择课程',
        type: 2,
        content : "/course/storeCourseList?courseIds=" + courseIds,
        area: ['80%', '85%'],
        full: true,
        end : function (){
            var chooseStatus = $("body").data("STORE_CHOOSE_COURSE");
            if(chooseStatus == "success"){
                var courseId = $("body").data("COURSE_ID");
                var courseName = $("body").data("COURSE_NAME");
                var courseCourseArr = [];
                courseCourseArr.push('<div id="storeCourseDiv_' + courseId + '">')
                courseCourseArr.push('<input type="text" class="long" value="' + courseName + '" ' +
                    'data-course="' + courseId + '">');
                courseCourseArr.push('<input type="button" value="删除俱乐部" data-courseid="' + courseId
                    + '" id="B_delete_course_' + courseId +'" class="course-btn">');
                courseCourseArr.push('</div>');
                $("#storeCourseDiv").append(courseCourseArr.join(""));
                // 更新俱乐部id
                updateChooseCourseId(courseId);
                $("#B_delete_course_" + courseId +"").click(deleteChooseCourse);
            }
        }
    });
}

function updateChooseCourseId(courseId){
    var courseIds = $("input[name='courseIds']").val();
    if(courseIds == ""){
        $("input[name='courseIds']").val(courseId);
    }else{
        $("input[name='courseIds']").val(courseIds + "," + courseId);
    }
}

function deleteChooseCourse(){
    var courseId = $(this).data("courseid");
    var courseIds = $("input[name='courseIds']").val();
    if(courseIds != ""){
        var courseIdArr = [];
        $.each(courseIds.split(","), function(i, n){
            if(courseId != n){
                courseIdArr.push(n);
            }
        })
        if(courseIdArr == ""){
            $("input[name='courseIds']").val("");
        }else{
            $("input[name='courseIds']").val(courseIdArr.join(","));
        }
        $("#storeCourseDiv_" + courseId + "").remove();
    }
}

function addStore(){
    var storeName = $("input[name='storeName']").val();
    if(storeName == ""){
        alert("门店名称不能为空");
        return ;
    }
    // 判断地址是否选择省市区信息
    var proviceInfo = $("#proviceId").val();
    if(proviceInfo == ""){
        alert("请选择省市区信息");
        return ;
    }
    var cityInfo = $("#cityId").val();
    if(cityInfo == ""){
        alert("请选择省市区信息");
        return ;
    }
    var districtInfo = $("#districtId").val();
    if(districtInfo == ""){
        alert("请选择省市区信息");
        return ;
    }
    var storeDistrict = proviceInfo + "," + cityInfo;
    if(districtInfo != null){
        storeDistrict += "," + districtInfo;
    }
    $("input[name='storeDistrict']").val(storeDistrict);
    var storeAddress = $("input[name='storeAddress']").val();
    if(storeAddress == ""){
        alert("详细地址不能为空");
        return ;
    }
    var courseIds = $("input[name='courseIds']").val();
    if(courseIds == ""){
        alert("请选择门店课程");
        return;
    }
    var introduce = editor.html();
    if(introduce == ""){
        alert("门店介绍不能为空");
        return ;
    }
    /*var mobile = $("input[name='mobile']").val();
     if(mobile == ""){
     alert("联系电话不能为空");
     return ;
     }
     // 判断电话号码是否有误
     var mobilePattern = /^[1][3,4,5,7,8][0-9]{9}$/;
     if (!mobilePattern.test(mobile)){
     alert("联系电话输入格式有误");
     return ;
     }
     var contactName = $("input[name='contactName']").val();
     if(contactName == ""){
     alert("联系人不能为空");
     return ;
     }*/
    $.ajax({
        type : "post",
        url : "add",
        data : $("#storeForm").serialize(),
        dataType : "json",
        success: function(result, status) {
            if(result.errCode == "0"){
                alert(result.errMsg);
                return ;
            }else {
                // 添加成功，跳转到列表页面
                alert("添加门店信息成功");
                window.location.href = "list";
            }
        }
    })
}