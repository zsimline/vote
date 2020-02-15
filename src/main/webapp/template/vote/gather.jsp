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
  <link rel="stylesheet" href="/css/action.css">
  <script src="/js/echarts-4.6.0.min.js"></script>
  <title>结果汇总</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>
  <div class="container">
    <ul class="nav nav-tabs">
      <li role="presentation">
        <a href="/vote/edit?aid=${aid}">编辑</a>
      </li>
      <li role="presentation">
        <a href="/vote/apply_manage?aid=${aid}&status=w&page=1">报名管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/entry_manage?aid=${aid}&page=1">条目管理</a>
      </li>
      <li role="presentation" class="active">
        <a href="/vote/gather?aid=${aid}">结果与日志</a>
      </li>
      <li role="presentation">
        <a href="/vote/qrcode?aid=${aid}">链接与二维码</a>
      </li>
    </ul>

    <br>

    <div id="container-ranking"></div>
    <%@ include file="../components/tip.jsp" %>
    <div class="text-center">
      <button class="btn btn-success btn-load" onclick="loadMoreEntry()">加载更多</button>
    </div>
    
    <hr>
    
    <div id="charts">
      <div id="gather-sex"></div>
      <div id="gather-province"></div>
    </div>
    <div id="gather-date" style="width: 100%; height: 400px;"></div>

    <span id="aid" class="hidden">${aid}</span>

    <script src="/js/ranking.js"></script>
    <script src="/js/gather.js"></script>
  </div>
  <%@ include file="../components/modal.jsp" %>
  <%@ include file="../components/footer.jsp" %>
</body>