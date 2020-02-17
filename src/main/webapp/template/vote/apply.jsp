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
  <script src="/js/apply.js"></script>
  <title>活动报名</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>

  <div class="container">
    <c:choose>
      <c:when test="${applyTimeStatus == -1}">
        <h3 style="color: red;">报名还未开始！请勿提交报名数据。</h3>
      </c:when>
      <c:when test="${applyTimeStatus == 1}">
        <h3 style="color: red;">报名已经截止！请勿提交报名数据。</h3>
      </c:when>
    </c:choose>

    <h5>${activity.title}</h5>
    <%@ include file="../components/options/title.jsp" %>

    <%
      String[] options = (String[])request.getAttribute("options");
      for (int i = 0; i < options.length; i++) {
    %>
      <%
        if (options[i].equals("0")) {
      %>
        <%@ include file="../components/options/img_entry.jsp" %>
      <%
        } else if (options[i].equals("1")) {
      %>
        <%@ include file="../components/options/introduction.jsp" %>
      <%
        } else if (options[i].equals("2")) {
      %>
        <%@ include file="../components/options/name.jsp" %>
      <%
        } else if (options[i].equals("3")) {
      %>
      <%@ include file="../components/options/sex.jsp" %>
      <%
        } else if (options[i].equals("4")) {
      %>
      <%@ include file="../components/options/age.jsp" %>
      <%
        } else if (options[i].equals("5")) {
      %>
      <%@ include file="../components/options/telephone.jsp" %>
      <%
        } else if (options[i].equals("6")) {
      %>
      <%@ include file="../components/options/email.jsp" %>
      <%
        } else if (options[i].equals("7")) {
      %>
      <%@ include file="../components/options/school.jsp" %>
      <%
        } else if (options[i].equals("8")) {
      %>
      <%@ include file="../components/options/company.jsp" %>
      <%
        } else if (options[i].equals("9")) {
      %> 
      <%@ include file="../components/options/address.jsp" %>
      <%
        }
      %>
    <% 
      } 
    %>

    <%@ include file="../components/lisence.jsp" %>

    <button class="btn btn-primary" onclick="handleSubmit()">　　报名　　</button>
  </div>

  <span id="aid" class="hidden">${activity.id}</span>

  <%@ include file="../components/message.jsp" %>
  <%@ include file="../components/footer.jsp" %>
</body>

</html>