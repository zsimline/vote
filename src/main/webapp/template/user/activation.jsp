<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>

<!DOCTYPE HTML>
<html lang="zh">

<head>
  <%@ include file="../components/meta.jsp" %>
  <%@ include file="../components/link.jsp" %>
  <link rel="stylesheet" href="/css/user.css">
  <title>激活账户</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>

  <div class="container">
    <div class="hidden activation">
      <i class="fa fa-check"></i>
      <span>激活成功</span>
    </div>
    <div class="hidden activation">
      <i class="fa fa-check"></i>
      <span>激活失败</span>
    </div>
  </div>

  <span id="email" class="hidden"><%= request.getAttribute("email") %></span>
  <span id="code" class="hidden"><%= request.getAttribute("code") %></span>

  <script src="/js/user.js"></script>

  <%@ include file="../components/message.jsp" %>
  <script>window.onload = handleActivation();</script>
  <%@ include file="../components/footer.jsp" %>
</body>

</html>