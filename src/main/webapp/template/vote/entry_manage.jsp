<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html lang="zh">

<head>
  <%@ include file="../components/meta.jsp" %>
  <%@ include file="../components/link.jsp" %>
  <link rel="stylesheet" href="/css/jquery.basictable.css">
  <link rel="stylesheet" href="/css/manage.css">
  <script src="/js/jquery.basictable.min.js"></script>
  <script src="/js/basictable.min.js"></script>
  <script src="/js/flatui-radio-0.0.3.js"></script>
  <title>条目管理</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>
  <div class="container">
    <ul class="nav nav-tabs">
      <li role="presentation">
        <a href="/vote/edit?aid=${activity.id}">编辑</a>
      </li>
      <li role="presentation">
        <a href="/vote/apply_manage?aid=${activity.id}&status=w&page=1">报名管理</a>
      </li>
      <li role="presentation" class="active">
        <a href="/vote/entry_manage?aid=${activity.id}&page=${page}">条目管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/gather?aid=${activity.id}">结果与日志</a>
      </li>
      <li role="presentation">
        <a href="/vote/qrcode?aid=${activity.id}">链接与二维码</a>
      </li>
    </ul>

    <br>

    <!-- 响应式表格 -->
    <div class="table-container">
      <div class="btn-container">
        <button class="layui-btn" onclick="exportExcel();"><i class="fa fa-floppy-o"></i>导出Excel</button>
      </div>
      
      <table id="table" class="reponsetable"></table>
      
      <script type="text/html" id="editer">
        <a class="table-operation" onclick="freeze(this)" title="冻结"><i class="fa fa-times"></i></a>
      </script>
    </div>

    <div class="pagination">
      <ul>
        <%
          int curPage = Integer.valueOf((String)request.getAttribute("page"));
          int sumPages = (Integer)request.getAttribute("sumPages");
          int lo = curPage - 4 > 0 ? curPage - 4 : 1;
          int hi = lo + 7 > sumPages ? sumPages : lo + 7;
        %>
        <li title="首页">
          <a href="/vote/entry_manage?aid=${aid}&page=1"><i class="fa fa-angle-double-left"></i></a>
        </li>
        <li title="上一页">
          <a href="/vote/entry_manage?aid=${aid}&page=<%= curPage-1 > 0 ? curPage - 1 : 1 %>" class="fui-arrow-left"></a>
        </li>
        <% 
          while (lo <= hi) {
        %>
          <%
            if (lo == curPage) {
          %>
              <li>
                <a href="/vote/entry_manage?aid=${aid}&page=<%= lo %>" class="active"><%= lo %></a>
              </li>
          <%
            } else {
          %>
              <li>
                <a href="/vote/entry_manage?aid=${aid}&page=<%= lo %>"><%= lo %></a>
              </li>
          <%
            }
          %>
        <%
            lo++;
          }
        %>
        <li title="下一页">
          <a href="/vote/entry_manage?aid=${aid}&page=<%= curPage+1 > sumPages ? sumPages : curPage+1 %>" class="fui-arrow-right"></a>
        </li>
        <li title="尾页">
          <a href="/vote/entry_manage?aid=${aid}&page=<%= sumPages %>"><i class="fa fa-angle-double-right"></i></a>
        </li>
      </ul>
      <div class="pagination-jump">
        共 ${sumPages} 页 到第
        <input type="text" id="page-jump" class="form-control input-sm"/>
        页<button class="btn" onclick="jumpByPage()">跳转</button>
      </div>
    </div>

    <span id="aid" class="hidden">${activity.id}</span>
    <span id="page" class="hidden">${page}</span>
    
    <script src="/js/entry_manage.js"></script>
  </div>
  <%@ include file="../components/message.jsp" %>
  <%@ include file="../components/modal.jsp" %>
  <%@ include file="../components/footer.jsp" %>
</body>