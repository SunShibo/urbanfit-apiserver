$(function (){
    $("#pageSelect").change(function (){
        var purl = $(this).data("purl");
        changePageSize(purl, this);
    });

    $("a[name='A_toPage']").click(function (){
        var tourl = $(this).data("tourl");
        var tosize = $(this).data("tosize");
        _toPage(tourl, tosize);
    });

    $("#B_redirectPage").click(function (){
        var rurl = $(this).data("rurl");
        var rsize = $(this).data("rsize");
        var rtotal = $(this).data("rtotal");

        redirectPage(rurl, rsize, rtotal);
    });
})

function redirectPage(a,b,c){
    var d = document.getElementById("rPage"), e = trim(d.value);
    if ("" == e || isNaN(e))return alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u9875\u6570"), d.select(), !1;
    e = validatePage(e, c, b);
    var f = (e - 1) * b;
    document.forms[0].action = a + "?pager.offset=" + f + "&pageSize=" + b + "&pageNo=" + d.value, document.forms[0].submit()
}

function changePageSize(a,b){
    document.forms[0].action=a+"?pager.offset=0&pageSize="+b.value,document.forms[0].submit()}function validatePage(a,b,c){var d=Math.ceil(b/c);return a=0>a?1:a,a=a>d?d:a}

function _toPage(a, b) {
    document.forms[0].action = a + "&pageSize=" + b, document.forms[0].submit()
}

function trim(a) {
    return a.replace(/(^\s*)|(\s*$)/g, "")
}