<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>教练添加</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/static/js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/static/js/kindeditor/zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/manage/coach_add.js"></script>
    <style>
        .ke-container{width: 96% !important;}
    </style>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">教练团队 > 教练编辑</div>
                <div class="tablebox2">
                    <form id="coachForm" method="post">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td class="td1">教练名称：</td>
                                <td class="td2">
                                    <input type="text" name="coachName" placeholder="请输入教练名称"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>教练职称：</td>
                                <td>
                                    <input type="text" name="coachTitle" placeholder="请输入教练职称"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>头&nbsp;&nbsp;&nbsp;&nbsp;像：</td>
                                <td>
                                    <div class="suolue">
                                        <div class="uploadimg">
                                            <img width="160px;" height="160px;" id="uploadImage"/>
                                            <input type="hidden" name="headPortrait" value="${coach.headPortrait}">
                                        </div>
                                        <div class="zi">
                                            <span style="color:#FF0000;">*必填</span>
                                            <p class="del" id="B_delete_headPortrait">删除</p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
                                <td>
                                    <div class="edit">
                                        <textarea name="content"></textarea>
                                        <input name="introduce" type="hidden"/>
                                    </div>
                                    <em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <a href="javascript:void(0);" id="B_submit">发布</a>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>