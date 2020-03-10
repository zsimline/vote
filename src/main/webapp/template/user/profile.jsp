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
  <title>用户主页</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>
  <div class="container">
    <div class="user-info">
      <label for="">电子邮件：</label>
      <span>${user.email}</span>
    </div>
    <div class="user-info">
        <label for="">所属组织：</label>
        <input type="organization"  id="organization" class="form-control input-sm" value="${user.organization}">
      </div>
    <div class="user-info">
      <label for="">用户密码：</label>
      <input type="password"  id="password" class="form-control input-sm">
    </div>
    <div class="user-info">
        <button class="btn btn-success" onclick="updateUserInfo()">修改信息</button>
    </div>
  </div>
  <script src="/js/user.js"></script>
  <span id="uid" class="hidden">${user.id}</span>
  <%@ include file="../components/footer.jsp" %>
  <%@ include file="../components/message.jsp" %>
</body>

</html>