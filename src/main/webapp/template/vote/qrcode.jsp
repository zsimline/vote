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
  <link rel="stylesheet" href="/css/manage.css">
  <script src="/js/qrcode.min.js"></script>
  <title>链接与二维码</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>

  <div class="container page-qrcode">
    <ul class="nav nav-tabs">
      <li role="presentation">
        <a href="/vote/edit?aid=${aid}">编辑</a>
      </li>
      <li role="presentation">
        <a href="/vote/apply_manage?aid=${aid}&status=w&page=1">报名管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/entry_manage?aid=${aid}&page=1">条目管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/gather?aid=${aid}">结果与日志</a>
      </li>
      <li role="presentation" class="active">
        <a href="/vote/qrcode?aid=${aid}">链接与二维码</a>
      </li>
    </ul>

    <br>

    <h4>投票页链接地址</h4>
    <input type="text" id="link-input" class="form-control" readonly
      value="http://vote.zizaixian.top/vote/action?aid=${aid}">
    <button class="btn btn-success" onclick="copyLink()">复制链接</button>
    <hr>
    <h4>使用微信扫描二维码</h4>
    <div id="qrcode"></div>

    <span id="aid" class="hidden"><%= request.getAttribute("aid") %></span>

    <script type="text/javascript">
      const link = 'https://lumingvote.com/vote/action?aid=' + $('#aid').text();
      new QRCode(document.getElementById("qrcode"), link);

      function copyLink() {
        $('#link-input')[0].select();
        document.execCommand('copy');
      }
    </script>
  </div>

  <%@ include file="../components/footer.jsp" %>
</body>