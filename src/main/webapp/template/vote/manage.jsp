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
  <script src="/js/manage.js"></script>
  <title>管理投票</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>

  <div class="container">
    <c:forEach items="${activitys}" var="activity">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title">测试投票</h3>
        </div>
        <div class="panel-body">
          <a class="btn btn-primary" href="/vote/edit?aid=${activity.id}">编辑</a>
          <a class="btn btn-success" href="/vote/apply_manage?aid=${activity.id}&status=w&page=1">报名管理</a>
          <a class="btn btn-warning" href="/vote/entry_manage?aid=${activity.id}&page=1">条目管理</a>
          <a class="btn btn-info" href="/vote/gather?aid=${activity.id}">结果与日志</a>
          <a class="btn btn-inverse" href="/vote/qrcode?aid=${activity.id}">链接与二维码</a>
          <a class="btn btn-danger" onclick="deleteActivity('${activity.id}')">删除</a>
        </div>
        <div class="panel-footer">
          <span class="label label-default">报名开始时间：${activity.applyTimeStart}</span>
          <span class="label label-default">报名截止时间：${activity.applyTimeEnd}</span>
          <span class="label label-default">投票开始时间：${activity.voteTimeStart}</span>
          <span class="label label-default">投票截止时间：${activity.voteTimeEnd}</span>
          <br>
          <span class="label label-default">投票总数：${activity.sumVoted}</span>
          <span class="label label-default">访问总数：${activity.sumVisited}</span>

        </div>
      </div>
    </c:forEach>
  </div>
  <%@ include file="../components/modal.jsp" %>
  <%@ include file="../components/footer.jsp" %>
</body>