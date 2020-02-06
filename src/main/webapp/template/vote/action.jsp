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
  <link rel="stylesheet" href="/css/action.css">
  <title>报名管理</title>
</head>

<body>
  <div class="container-fluid">
    <div>
      <img src="${activity.imgMain}" id="ac-img">
    </div>
    <h1 id="ac-title">${activity.title}</h1>
    <div id="ac-info">
      <div>
        <span>参与${activity.suffix}</span><br>
        <span>${activity.sumEntry}</span>
      </div>
      <div>
        <span>累积投票</span><br>
        <span>${activity.sumVoted}</span>
      </div>
      <div>
        <span>访问次数</span><br>
        <span>${activity.sumVisited}</span>
      </div>
    </div>
    <div class="ac-time">
      <i class="fa fa-clock-o"></i>开始时间：${activity.voteTimeStart}
    </div>
    <div class="ac-time">
      <i class="fa fa-clock-o"></i>截止时间：${activity.voteTimeEnd}
    </div>

    <div id="ac-summary">
      <div id="ac-summary-title">
        活动介绍：<i class="fa fa-angle-double-down"></i>
      </div>
      ${activity.summary}
    </div>
  </div>
  
  <script src="/js/action.js"></script>

  <%@ include file="../components/modal.jsp" %>

</body>

</html>