<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>

<!DOCTYPE HTML>
<html lang="zh">

<head>
  <%@ include file="components/meta.jsp" %>
  <%@ include file="components/link.jsp" %>
  <script src="js/manage.js"></script>
  <title>管理投票</title>
</head>

<body>
  <%@ include file="components/header.jsp" %>

  <div class="container">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">测试投票</h3>
      </div>
      <div class="panel-body">
        <button class="btn btn-primary">编辑</button>
        <button class="btn btn-success">审核报名</button>
        <button class="btn btn-warning">批量添加</button>
        <button class="btn btn-info">结果与日志</button>
        <button class="btn btn-inverse">链接与二维码</button>
        <button class="btn btn-danger" onclick="deleteActivity('4028aa466fcc6417016fcc6418420001')">删除</button>
      </div>
      <div class="panel-footer">
        <span class="label label-default">投票总数：612568</span>
        <span class="label label-default">访问总数：17845962</span>
        <span class="label label-default">创建时间：2020-01-21 21:33</span>
      </div>
    </div>
  </div>

  <%@ include file="components/modal.jsp" %>
  <%@ include file="components/footer.jsp" %>
</body>