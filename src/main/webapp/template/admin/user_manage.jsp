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
      <li class="active">
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
      <table class="reponsetable">
        <thead>
          <th>UID</th>
          <th>邮件地址</th>
          <th>所属组织</th>
          <th>身份</th>
          <th>状态</th>
          <th>操作</th>
        </thead>
        <tbody>
          <c:forEach items="${users}" var="user">
            <tr>
              <td>${user.id}</td>
              <td>${user.email}</td>
              <td>${user.organization}</td>
              <td>
                <c:choose>
                  <c:when test="${user.isStaff}">管理员</c:when>
                  <c:otherwise>普通用户</c:otherwise>
                </c:choose>
              </td>
              <td>
                <c:choose>
                  <c:when test="${user.isActive}">
                    <i class="fa fa-check-circle" style="color:green"></i>
                  </c:when>
                  <c:otherwise>
                    <i class="fa fa-times-circle" style="color:red"></i>
                  </c:otherwise>
                </c:choose>
              </td>
              <td>
                <a class="table-operation" onclick="switchUserPrivilege(this, '${user.id}')" title="切换身份"><i class="fa fa-plug"></i></a>
                <a class="table-operation" onclick="switchUserStatus(this, '${user.id}')" title="切换状态""><i class="fa fa-magnet"></i></a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <ul class="pagination">
        <%
          int curPage = (Integer)request.getAttribute("page");
          int sumPages = (Integer)request.getAttribute("sumPages");
          int lo = curPage - 4 > 0 ? curPage - 4 : 1;
          int hi = lo + 7 > sumPages ? sumPages : lo + 7;
        %>
        <li title="首页">
          <a href="/admin/user_manage?page=1"><i class="fa fa-angle-double-left"></i></a>
        </li>
        <li title="上一页">
          <a href="/admin/user_manage?page=<%= curPage-1 > 0 ? curPage - 1 : 1 %>" class="fui-arrow-left"></a>
        </li>
        <% 
          while (lo <= hi) {
        %>
        <%
            if (lo == curPage) {
          %>
        <li>
          <a href="/admin/user_manage?page=<%= lo %>" class="active"><%= lo %></a>
        </li>
        <%
            } else {
          %>
        <li>
          <a href="/admin/user_manage?apage=<%= lo %>"><%= lo %></a>
        </li>
        <%
          }
        %>
        <%
            lo++;
          }
        %>
        <li title="下一页">
          <a href="/admin/user_manage?page=<%= curPage+1 > sumPages ? sumPages : curPage+1 %>"
            class="fui-arrow-right"></a>
        </li>
        <li title="尾页">
          <a href="/admin/user_manage?page=<%= sumPages %>"><i class="fa fa-angle-double-right"></i></a>
        </li>
      </ul>
    </div>
  </div>

</body>

</html>