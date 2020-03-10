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
          <i class="fa fa-activity" aria-hidden="true"></i>
          <span>用户管理</span>
          <i class="fa fa-angle-right pull-right" aria-hidden="true"></i>
        </a>
      </li>
      <li class="active">
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
          <th>活动标题</th>
          <th>发布者ID</th>
          <th>条目总数</th>
          <th>投票总数</th>
          <th>访问总数</th>
          <th>状态</th>
          <th>操作</th>
        </thead>
        <tbody>
          <c:forEach items="${activitys}" var="activity">
            <tr>
              <td>${activity.title}</td>
              <td>${activity.publisher}</td>
              <td>${activity.sumEntry}</td>
              <td>${activity.sumVoted}</td>
              <td>${activity.sumVisited}</td>
              <td>
                <c:choose>
                  <c:when test="${activity.destroyed}">
                    <i class="fa fa-times-circle" style="color:red"></i>
                  </c:when>
                  <c:otherwise>
                    <i class="fa fa-check-circle" style="color:green"></i>
                  </c:otherwise>
                </c:choose>
              </td>
              <td>
                <c:choose>
                  <c:when test="${activity.destroyed}">
                      <a class="table-operation" style="color: #898989" title="删除活动"><i class="fa fa-times"></i></a>
                    </c:when>
                  <c:otherwise>
                      <a class="table-operation" onclick="deleteActivity(this, '${activity.id}')" title="删除活动"><i class="fa fa-times"></i></a>
                    </c:otherwise>
                </c:choose>
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
          <a href="/admin/activity_manage?page=1"><i class="fa fa-angle-double-left"></i></a>
        </li>
        <li title="上一页">
          <a href="/admin/activity_manage?page=<%= curPage-1 > 0 ? curPage - 1 : 1 %>" class="fui-arrow-left"></a>
        </li>
        <% 
          while (lo <= hi) {
        %>
        <%
            if (lo == curPage) {
          %>
        <li>
          <a href="/admin/activity_manage?page=<%= lo %>" class="active"><%= lo %></a>
        </li>
        <%
            } else {
          %>
        <li>
          <a href="/admin/activity_manage?apage=<%= lo %>"><%= lo %></a>
        </li>
        <%
          }
        %>
        <%
            lo++;
          }
        %>
        <li title="下一页">
          <a href="/admin/activity_manage?page=<%= curPage+1 > sumPages ? sumPages : curPage+1 %>"
            class="fui-arrow-right"></a>
        </li>
        <li title="尾页">
          <a href="/admin/activity_manage?page=<%= sumPages %>"><i class="fa fa-angle-double-right"></i></a>
        </li>
      </ul>
    </div>
  </div>

</body>

</html>