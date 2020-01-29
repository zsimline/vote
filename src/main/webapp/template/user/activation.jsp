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

  <span id="email" class="hidden"><%= request.getAttribute("email") %></span>
  <span id="code" class="hidden"><%= request.getAttribute("code") %></span>

  <script>
    window.onload = function() {
    const activationAddress = "http://127.0.0.1/api/user/activation?email=" + $('#email').text() + "&code=" + $('#code').text();
    get(activationAddress)
      .then(data => {
        if (!(data.code % 100)) {
          openModal('success', data.codeDesc);
        } else {
          openModal('error', `${data.code} ${data.codeDesc}`);
        }
      })
      .catch(err => {
        console.log(err);
        openModal('error', '验证账户失败')
      });
    }
    </script>
  <title>激活账户</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>

  <div class="container"></div>

  <%@ include file="../components/modal.jsp" %>
  <%@ include file="../components/footer.jsp" %>
</body>

</html>
