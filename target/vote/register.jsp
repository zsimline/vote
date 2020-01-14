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
  <link href="css/login.css" rel="stylesheet" type="text/css">
  <title>用户登录</title>
</head>

<body class="lg-container">
  <div class="lg-box">
    <h1 class="lg-title">鹿鸣投票</h1>
    <div class="input-group">
      <i class="fa fa-user" aria-hidden="true"></i>
      <input type="text" placeholder="设置个昵称"" maxlength="15">
    </div>
    <div class="input-group">
      <i class="fa fa-lock" aria-hidden="true"></i>
      <input type="password" placeholder="设置一个密码吧" regex="^\w+" maxlength="32">
    </div>
    <div class="input-group">
      <i class="fa fa-envelope" aria-hidden="true"></i>
      <input type="email" placeholder="请输入您常用的电子邮件" maxlength="255">
    </div>
    <div class="input-group">
      <i class="fa fa-users" aria-hidden="true"></i>
      <input type="text" placeholder="所属组织 (公司、学校、个人等)" regex="^\w+" maxlength="45">
    </div>
    <a href="" class="btn-submit">
      &nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i>
    </a>
    <div class="lg-ref">
      <a href="">注册账户</a>
      &nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;
      <a href="">忘记密码</a>
    </div>
  </div>
</body>

</html>