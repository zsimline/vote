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
  <link rel="stylesheet" href="/css/action.css?v=0.0.6">
  <title>鹿鸣投票-${activity.title}</title>
</head>

<body>
  <div>
    <img src="${activity.imgMain}" id="ac-img">
  </div>
  <div class="container-fluid">
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

    <div id="ac-summary-title">
      投票规则：每个微信每天可投一次，每次最多选择 ${activity.maximum} ${activity.quantifier}${activity.suffix}
    </div>

    <!-- 活动简介 -->
    <div id="ac-summary-title">
      活动简介：
      <i class="fa fa-angle-double-down" onclick="switchSummaryShow(this)"></i>
    </div>
    <div id="ac-summary">
      ${activity.summary}
    </div>

    <div id="search-box">
      <input type="text" id="search-content" class="form-control" placeholder="输入标题或编号">
      <button class="btn btn-success" onclick="searchEntry()">搜索</button>
    </div>

    <div id="entry-container"></div>

    <%@ include file="../components/tip.jsp" %>

    <footer>
      由<a href="http://vote.zizaixian.top">鹿鸣投票</a>提供技术支持
    </footer>
  </div>

  <div id="tool-box">
    <span>已选择 <em>0</em> / ${activity.maximum}</span>
    <button class="btn btn-success" onclick="handleSubmit()">提交投票</button>
    <a href="/vote/ranking?aid=${activity.id}">查看排名</a>
  </div>

  <span class="hidden" id="aid">${activity.id}</span>
  <span class="hidden" id="maximum">${activity.maximum}</span>
  <span class="hidden" id="explain-reason">${activity.explainReason}</span>
  <span class="hidden" id="have-prize">${activity.havePrize}</span>

  <script src="/js/action.js?v=0.3.7"></script>

  <%@ include file="../components/message.jsp" %>
  <%@ include file="../components/modal.jsp" %>
</body>

</html>