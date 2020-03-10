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
  <title>忘记密码</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>
  <div class="container">
    <div id="pwdforget">
      <input type="email" class="form-control input-sm">
    </div>
  </div>
  <%@ include file="../components/footer.jsp" %>
</body>

</html>