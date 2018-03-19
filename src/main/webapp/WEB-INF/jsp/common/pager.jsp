<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>

<script type="text/javascript" src="/static/js/mainJs/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/common/pager.js"></script>
<%
  String pageSize = request.getParameter("pageSize");
  if (null == pageSize || "".equals(pageSize)) {
    pageSize = "10";
  }
  request.setAttribute("pageSize", pageSize);
%>
    <pg:pager export="curPage=pageNumber" maxIndexPages="5"
          items="${param.totalRecord }"
          maxPageItems="${pageSize}"
          url="${param.url }">

    <pg:param name="keywords"/>

    <pg:last>
        <span>共&nbsp;<em>${pageNumber}</em>&nbsp;页</span>
			<span class="ml0">每页&nbsp;<select id="pageSelect" data-purl="${param.url}">
              <option value="10" <c:if test="${pageSize == 10}"> selected="selected"</c:if>>10</option>
              <option value="30" <c:if test="${pageSize == 30}"> selected="selected"</c:if>>30</option>
              <option value="50" <c:if test="${pageSize == 50}"> selected="selected"</c:if>>50</option>
            </select>&nbsp;条&nbsp;
			</span>
    </pg:last>
  <c:forEach items="${param.params }" var="p">
    <pg:param name="${p }"/>
  </c:forEach>

  <pg:first>
    <a class="nxt" href="javascript:void(0);" name="A_toPage" data-tourl="${pageUrl}&pageNo=${pageNumber}"
       data-tosize="${pageSize}">首页</a>
  </pg:first>
  <pg:prev>
    <a class="nxt" href="javascript:void(0);" name="A_toPage" data-tourl="${pageUrl}&pageNo=${pageNumber}"
       data-tosize="${pageSize}">上一页</a>
  </pg:prev>
  <pg:pages>
    <c:if test="${curPage eq pageNumber }">
      <a class="numsel" href="javascript:void(0)">${pageNumber }</a>
    </c:if>
    <c:if test="${curPage != pageNumber }">
      <a class="num" href="javascript:void(0);" name="A_toPage" data-tourl="${pageUrl}&pageNo=${pageNumber}"
         data-tosize="${pageSize}">${pageNumber }</a>
    </c:if>
  </pg:pages>
  <pg:next>
    <a class="nxt" href="javascript:void(0); " name="A_toPage" data-tourl="${pageUrl}&pageNo=${pageNumber}"
       data-tosize="${pageSize}">下一页</a>
  </pg:next>
  <pg:last>
    <a class="nxt" href="javascript:void(0); " name="A_toPage" data-tourl="${pageUrl}&pageNo=${pageNumber}"
       data-tosize="${pageSize}">尾页</a>
  </pg:last>

  <span>跳转到&nbsp;</span><span class="ml0"><input type="text" id="rPage" value="${curPage }"/></span>
  <a class="tiao" href="javascript:void(0);" id="B_redirectPage" data-rurl="${param.url}" data-rsize="${pageSize}"
     data-rtotal="${param.totalRecord}">跳转</a>
  <input type="hidden" name="sort" value="${param.sort }"/>
  <input type="hidden" name="order" value="${param.order }"/>
</pg:pager>