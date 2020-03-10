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
  <link rel="stylesheet" href="/css/admin.css">
  <link rel="stylesheet" href="/css/jquery.basictable.css">
  <script src="/js/admin.js"></script>
  <title>用户管理</title>
</head>

<body>
  <div class="container-fluid">
    <ul class="nav-left-container col-lg-2">
      <li>
        <a href="/admin/user_manage?page=1">
          <i class="fa fa-user" aria-hidden="true"></i>
          <span>用户管理</span>
          <i class="fa fa-angle-right pull-right" aria-hidden="true"></i>
        </a>
      </li>
      <li>
        <a href="/admin/activity_manage?page=1">
          <i class="fa fa-tasks" aria-hidden="true"></i>
          <span>活动管理</span>
          <i class="fa fa-angle-right pull-right" aria-hidden="true"></i>
        </a>
      </li>
    </ul>
    <div class="col-lg-10">
      <a href="/admin/user_manage?page=1" class="card">用户管理</a>
      <a href="/admin/activity_manage?page=1" class="card">活动管理</a>
    </div>
  </div>
  <%@ include file="../components/footer.jsp" %>
</body>

</html>