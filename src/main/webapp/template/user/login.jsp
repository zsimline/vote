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
  <link href="/css/user.css" rel="stylesheet" type="text/css">
  <script src="/js/user.js"></script>
  <title>用户登录</title>
</head>

<body class="lg-container">
  <div class="lg-box">
    <h1 class="lg-title">鹿鸣投票</h1>
    <div class="input-group">
      <i class="fa fa-user" aria-hidden="true"></i>
      <input type="email" id="email" placeholder="电子邮件">
    </div>
    <div class="input-group">
      <i class="fa fa-lock" aria-hidden="true"></i>
      <input type="password" id="password" placeholder="密码" regex="^\w+">
    </div>
    <a class="btn-submit" onclick="handleLogin()">
      &nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i>
    </a>
    <div class="lg-ref">
      <a href="register">注册账户</a>
      &nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;
      <a href="">忘记密码</a>
    </div>
  </div>
  <%@ include file="../components/modal.jsp" %>
</body>

</html>