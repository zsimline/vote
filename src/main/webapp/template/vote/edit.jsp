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
  <link rel="stylesheet" href="/css/manage.css">
  <title>编辑报名</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>
  <div class="container">
    <ul class="nav nav-tabs">
      <li role="presentation" class="active">
        <a href="/vote/edit?aid=${aid}">编辑</a>
      </li>
      <li role="presentation">
        <a href="/vote/apply_manage?aid=${aid}">报名管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/item_manage?aid=${aid}">条目管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/gather?aid=${aid}">结果与日志</a>
      </li>
      <li role="presentation">
        <a href="/vote/qrcode?aid=${aid}">链接与二维码</a>
      </li>
    </ul>

    <br>
    
  </div>
  <%@ include file="../components/footer.jsp" %>
</body>