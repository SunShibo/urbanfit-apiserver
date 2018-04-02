<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>活动资讯添加</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/static/js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/static/js/kindeditor/zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/manage/activity_message_add.js"></script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">活动资讯 > 活动资讯编辑</div>
                <div class="tablebox2">
                    <form id="activityMessageForm" method="post">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td class="td1">标&nbsp;&nbsp;题：</td>
                                <td class="td2">
                                    <input type="text" name="title" placeholder="请输入活动资讯标题"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>缩略图：</td>
                                <td>
                                    <div class="suolue">
                                        <div class="uploadimg">
                                            <img width="160px;" height="160px;" id="uploadImage" src="../static/img/u37.png"/>
                                            <input type="hidden" name="thumbnails" value="${activityMessage.thumbnails}"><br/>
                                        </div>
                                        <div class="zi">
                                            <span style="color:#FF0000;">*必填</span>
                                            <p class="del" id="B_delete_thumbnails">删除</p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>内&nbsp;&nbsp;容：</td>
                                <td>
                                    <div class="edit">
                                        <textarea name="messageContent"></textarea>
                                        <input type="hidden" name="content"/>
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