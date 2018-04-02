<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>banner修改</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mainJs/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/ajaxupload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/manage/banner_update.js"></script>
    <script type="text/javascript">
       var type = ${banner.type};
    </script>
</head>
<body>
    <div class="index clear">
        <jsp:include page="../main.jsp"></jsp:include>
            <div class="indexRight1">
                <div class="title">banner管理讯 > banner添加</div>
                <div class="tablebox2">
                    <form id="bannerForm" method="post">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td class="td1">标&nbsp;&nbsp;题：</td>
                                <td class="td2">
                                  <input type="text" name="title" value="${banner.title}"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>轮播类型：</td>
                                <td>
                                    <div class="select">
                                        <select name="type">
                                            <option value="1">首页</option>
                                            <option value="2">活动资讯</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>轮播图片：</td>
                                <td>
                                    <div class="suolue">
                                        <div class="uploadimg">
                                            <img width="160px;" height="160px;" id="uploadImage" src="${baseUrl}${banner.imageUrl}"/>
                                            <input type="hidden" name="imageUrl" value="${banner.imageUrl}"><br/>
                                        </div>
                                        <div class="zi">
                                            <span style="color:#FF0000;">*必填</span>
                                            <p class="del" id="B_delete_imageUrl">删除</p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>链接地址：</td>
                                <td>
                                    <input type="text" name="linkUrl" value="${banner.linkUrl}"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>排序：</td>
                                <td>
                                    <input type="text" name="sort" value="${banner.sort}"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <input type="hidden" name="bannerId" value="${banner.bannerId}">
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