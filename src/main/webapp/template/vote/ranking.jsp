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
  <link rel="stylesheet" href="/css/ranking.css">
  <title>${activity.title}-排名</title>
</head>

<body>
  <div id="container-ranking"></div>
  <div class="text-center">
    <button class="btn btn-success" onclick="loadMoreEntry()">加载更多</button>
  </div>

  <span id="aid" class="hidden">${activity.id}</span>

  <%@ include file="../components/modal.jsp" %>
  <script src="/js/ranking.js"></script>
</body>

</html>