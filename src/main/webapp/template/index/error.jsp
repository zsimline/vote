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
    .error-box {
      margin-top: 70px;
      padding-left: 100px;
    }
    .error-box h1 {
      color: #0188DE;
      font-size: 44px;
    }
    .error-box h5 {
      color: #0188DE;
      font-size: 16px;
    }
    .error-box p {
      color:rgb(102, 102, 102);
      font-size: 16px;
      margin-top: 40px;
    }
  </style>

  <title>鹿鸣投票系统</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>
  <div class="container">
    <div class="error-box">
      <h1>抱歉，找不到此页面~</h1>
      <h5>Sorry, the site now can not be accessed.</h5>
      <p>你请求访问的页面，暂时找不到，我们建议你返回官网首页进行浏览，谢谢！</p>
    </div>
  </div>
  <%@ include file="../components/footer.jsp" %>
</body>