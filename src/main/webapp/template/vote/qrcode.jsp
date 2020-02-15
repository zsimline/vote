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
        <a href="/vote/edit?aid=${activity.id}">编辑</a>
      </li>
      <li role="presentation">
        <a href="/vote/apply_manage?aid=${activity.id}&status=w&page=1">报名管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/entry_manage?aid=${activity.id}&page=1">条目管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/gather?aid=${activity.id}">结果与日志</a>
      </li>
      <li role="presentation" class="active">
        <a href="/vote/qrcode?aid=${activity.id}">链接与二维码</a>
      </li>
    </ul>

    <br>

    <h4>投票页链接地址</h4>
    <input type="text" id="link-action" class="form-control" readonly
      value="http://vote.zizaixian.top/vote/action?aid=${activity.id}">
    <button class="btn btn-success" onclick="copyLink('link-action')">复制链接</button>
    <hr>
    <h4>使用微信扫描二维码</h4>
    <div id="qrcode-action"></div>

    <br><br>

    <c:if test="${activity.externalApply}">
      <h4>报名页链接地址</h4>
      <input type="text" id="link-apply" class="form-control" readonly
        value="http://vote.zizaixian.top/vote/apply?aid=${activity.id}">
      <button class="btn btn-success" onclick="copyLink('link-apply')">复制链接</button>
      <hr>
      <h4>使用微信扫描二维码</h4>
      <div id="qrcode-apply"></div>
    </c:if>

    <script type="text/javascript">
      const linkAction = 'http://vote.zizaixian.top/vote/action?aid=${activity.id}'
      new QRCode(document.getElementById("qrcode-action"), linkAction);
    
      if (document.getElementById("qrcode-apply")) {
        const linkApply = 'http://vote.zizaixian.top/vote/apply?aid=${activity.id}'
        new QRCode(document.getElementById("qrcode-apply"), linkApply);
      }

      function copyLink(selector) {
        document.getElementById(selector).select();
        document.execCommand('copy');
        showMsg('success', '拷贝成功！');
      }
    </script>
  </div>
  
  <%@ include file="../components/message.jsp" %>
  <%@ include file="../components/footer.jsp" %>
</body>

</html>