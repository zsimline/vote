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
  <button class="btn btn-success">
    加载更多
  </button>
  <span id="aid" class="hidden">${activity.id}</span>

  <%@ include file="../components/modal.jsp" %>
  <script src="/js/ranking.js"></script>
</body>

</html>