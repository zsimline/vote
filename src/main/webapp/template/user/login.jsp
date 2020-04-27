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
      <input type="password" id="password" autocomplete="current-password" placeholder="密码" regex="^\w+">
    </div>
    <a class="btn-submit" onclick="handleLogin()">
      &nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i>
    </a>
    <div class="lg-ref">
      <a href="register">注册账户</a>
      &nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;
      <a href="javascript:$('#pwdforgetMoal').modal();">忘记密码</a>
    </div>
  </div>
  <script src="/js/user.js"></script>

  <div class="modal fade" id="pwdforgetMoal" tabindex="-1" role="dialog" aria-labelledby="pwdforgetMoalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="pwdforgetMoalLabel">忘记密码</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body" id="pwdforgetMoalBody">
          <label for="">输入电子邮件地址：</label>
          <input type="email" class="form-control input-sm">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-success" data-dismiss="modal" onclick="resetPassword()" >重置密码</button>
        </div>
      </div>
    </div>
  </div>

  <%@ include file="../components/message.jsp" %>
</body>

</html>