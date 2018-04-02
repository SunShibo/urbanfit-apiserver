<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>首页模块修改</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/manage/home_page_update.js"></script>
    <script type="text/javascript">
        var content = '${module.content}';
    </script>
</head>
<body>
<div class="index clear">
    <jsp:include page="../main.jsp"></jsp:include>
    <div class="indexRight1">
        <div class="title">模块管理 > 首页修改</div>
        <form id="homePageForm" method="post">
            <div class="tablebox2">
                <table cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td class="td1">标题一：</td>
                        <td class="td2">
                            <input type="text" name="title_1" placeholder="请输入标题" data-mid="1" data-mtext="一">
                        </td>
                    </tr>
                    <tr>
                        <td>缩略图：</td>
                        <td>
                            <div class="suolue">
                                <div class="uploadimg">
                                    <img width="160px;" height="160px;" id="uploadImage" src="../static/img/u37.png"/>
                                    <input type="hidden" name="imageUrl"><br/>
                                </div>
                                <div class="zi">
                                    <span style="color:#FF0000;">&nbsp;</span>
                                    <p class="del" id="B_delete_imageUrl">删除</p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>摘要：</td>
                        <td>
                            <input type="text" name="remark" placeholder="请输入摘要">
                        </td>
                    </tr>
                    <tr>
                        <td class="td1">链接地址一：</td>
                        <td class="td2">
                            <input type="text" name="linkUrl_1" placeholder="请输入链接地址" data-mid="1" data-mtext="一">
                        </td>
                    </tr>
                    <tr>
                        <td>标题二：</td>
                        <td>
                            <input type="text" name="title_2" placeholder="请输入标题" data-mid="2" data-mtext="二">
                        </td>
                    </tr>
                    <tr>
                        <td>链接地址二：</td>
                        <td>
                            <input type="text" name="linkUrl_2" placeholder="请输入链接地址" data-mid="2" data-mtext="二">
                        </td>
                    </tr>
                    <tr>
                        <td>标题三：</td>
                        <td>
                            <input type="text" name="title_3" placeholder="请输入标题" data-mid="3" data-mtext="三">
                        </td>
                    </tr>
                    <tr>
                        <td>链接地址三：</td>
                        <td>
                            <input type="text" name="linkUrl_3" placeholder="请输入链接地址" data-mid="3" data-mtext="三">
                        </td>
                    </tr>
                    <tr>
                        <td>标题四：</td>
                        <td>
                            <input type="text" name="title_4" placeholder="请输入标题" data-mid="4" data-mtext="四">
                        </td>
                    </tr>
                    <tr>
                        <td>链接地址四：</td>
                        <td>
                            <input type="text" name="linkUrl_4" placeholder="请输入链接地址" data-mid="4" data-mtext="四">
                        </td>
                    </tr>
                    <tr>
                        <td>标题五：</td>
                        <td>
                            <input type="text" name="title_5" placeholder="请输入标题" data-mid="5" data-mtext="五">
                        </td>
                    </tr>
                    <tr>
                        <td>链接地址五：</td>
                        <td>
                            <input type="text" name="linkUrl_5" placeholder="请输入链接地址" data-mid="5" data-mtext="五">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="hidden" name="content" value="${module.content}">
                            <input type="hidden" name="type" value="${module.type}">
                            <input type="hidden" name="moduleId" value="${module.moduleId}">
                            <a href="javascript:void(0);" id="B_submit">发布</a>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
</div>
</body>