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
  <style>
    body {
      background-image: url(/images/bg-index-2.jpg);
      background-repeat: no-repeat;
      background-size: 100% 100%;
    }

    .navbar-inverse {
      background: transparent;
    }

    .container {
      text-align: center;
    }

    h1 {
      margin-top: 150px;
      letter-spacing: 5px;
      color: white
    }

    .btn {
      font-size: 24px;
      margin: auto;
      color: white;
      margin-top: 50px;
      padding: 10px 35px;
    }
  </style>
  <title>鹿鸣投票系统</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>
  <div class="container">
    <h1>欢迎使用鹿鸣远程投票托管服务</h1>
    <a class="btn btn-primary" href="/vote/publish">即刻创建投票</a>
  </div>
  <%@ include file="../components/footer.jsp" %>
</body>