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
  <script src="/js/qrcode.min.js"></script>
  <title>链接与二维码</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>

  <div class="container page-qrcode">
    <ul class="nav nav-tabs">
      <li role="presentation"><a href="#">编辑</a></li>
      <li role="presentation"><a href="#">审核报名</a></li>
      <li role="presentation"><a href="#">批量添加</a></li>
      <li role="presentation"><a href="#">结果与日志</a></li>
      <li role="presentation" class="active"><a href="#">链接与二维码</a></li>
    </ul>
    
    <br>
    
    <h4>投票页链接地址</h4>
    <input type="text" id="link-input" class="form-control" readonly value="http://vote.zizaixian.top/vote/action.jsp?aid=<%= request.getAttribute("aid") %>">
    <button class="btn btn-success" onclick="copyLink()">复制链接</button>
    <hr>
    <h4>使用微信扫描二维码</h4>
    <div id="qrcode"></div>

    <span id="aid" class="hidden"><%= request.getAttribute("aid") %></span>

    <script type="text/javascript">
      const link = 'https://lumingvote.com/vote/action.jsp?aid=' + $('#aid').text();
      new QRCode(document.getElementById("qrcode"), link);
      
      function getQueryString(name) {
        const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        const r = window.location.search.substr(1).match(reg);
        return r ? unescape(r[2]) : null;
      }

      function copyLink() {
        $('#link-input')[0].select();
        document.execCommand('copy');
      }
    </script>
  </div>

  <%@ include file="../components/footer.jsp" %>
</body>