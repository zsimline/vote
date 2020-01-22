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
  <script src="js/qrcode.min.js"></script>
  <title>链接与二维码</title>
</head>

<body>
  <%@ include file="components/header.jsp" %>

  <div class="container">
    <div id="qrcode"></div>
    <script type="text/javascript">
      const link = getQueryString('aid');
      new QRCode(document.getElementById("qrcode"), link);
      
      function getQueryString(name) {
        const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        const r = window.location.search.substr(1).match(reg);
        return r ? unescape(r[2]) : null;
      }
    </script>
  </div>

  <%@ include file="components/modal.jsp" %>
  <%@ include file="components/footer.jsp" %>
</body>