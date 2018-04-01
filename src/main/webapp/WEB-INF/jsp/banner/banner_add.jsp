<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <title>banner添加</title>
  <link type="text/css" href="/static/css/main.css" rel="stylesheet"/>
  <script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
  <script type="text/javascript" src="/static/js/common/ajaxupload.js"></script>
  <script type="text/javascript" src="/static/js/manage/banner_add.js"></script>
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
                                  <input type="text" name="title" placeholder="请输入banner标题"><em>*必填</em>
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
                                            <img width="160px;" height="160px;" id="uploadImage" src="/static/img/u37.png"/>
                                            <input type="hidden" name="imageUrl"><br/>
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
                                    <input type="text" name="linkUrl" placeholder="请输入链接地址"><em>*必填</em>
                                </td>
                            </tr>
                            <tr>
                                <td>排序：</td>
                                <td>
                                  <input type="text" name="sort" placeholder="请输入排序值"><em>*必填</em>
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