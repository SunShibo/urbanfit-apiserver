<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>首页模块修改</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon"/>
    <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="/static/js/manage/home_page_update.js"></script>
    <script type="text/javascript">
        var moduleId = '${moduleId}';
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
                        <td class="td1"><strong>位置一：</strong></td>
                        <td class="td2"></td>
                    </tr>
                    <tr>
                        <td class="td1">标题：</td>
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
                                    <input type="hidden" name="imageUrl_1"><br/>
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
                            <input type="text" name="remark_1" placeholder="请输入摘要">
                        </td>
                    </tr>
                    <tr>
                        <td class="td1">链接地址：</td>
                        <td class="td2">
                            <input type="text" name="linkUrl_1" placeholder="请输入链接地址" data-mid="1" data-mtext="一">
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><strong>位置二：</strong></td>
                        <td class="td2"></td>
                    </tr>
                    <tr>
                        <td>标题：</td>
                        <td>
                            <input type="text" name="title_2" placeholder="请输入标题" data-mid="2" data-mtext="二">
                        </td>
                    </tr>
                    <tr>
                        <td>链接地址：</td>
                        <td>
                            <input type="text" name="linkUrl_2" placeholder="请输入链接地址" data-mid="2" data-mtext="二">
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><strong>位置三：</strong></td>
                        <td class="td2"></td>
                    </tr>
                    <tr>
                        <td>标题：</td>
                        <td>
                            <input type="text" name="title_3" placeholder="请输入标题" data-mid="3" data-mtext="三">
                        </td>
                    </tr>
                    <tr>
                        <td>链接地址：</td>
                        <td>
                            <input type="text" name="linkUrl_3" placeholder="请输入链接地址" data-mid="3" data-mtext="三">
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><strong>位置四：</strong></td>
                        <td class="td2"></td>
                    </tr>
                    <tr>
                        <td>标题：</td>
                        <td>
                            <input type="text" name="title_4" placeholder="请输入标题" data-mid="4" data-mtext="四">
                        </td>
                    </tr>
                    <tr>
                        <td>链接地址：</td>
                        <td>
                            <input type="text" name="linkUrl_4" placeholder="请输入链接地址" data-mid="4" data-mtext="四">
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><strong>位置五：</strong></td>
                        <td class="td2"></td>
                    </tr>
                    <tr>
                        <td>标题：</td>
                        <td>
                            <input type="text" name="title_5" placeholder="请输入标题" data-mid="5" data-mtext="五">
                        </td>
                    </tr>
                    <tr>
                        <td>链接地址：</td>
                        <td>
                            <input type="text" name="linkUrl_5" placeholder="请输入链接地址" data-mid="5" data-mtext="五">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="hidden" name="content" >
                            <input type="hidden" name="moduleId" value="${moduleId}">
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