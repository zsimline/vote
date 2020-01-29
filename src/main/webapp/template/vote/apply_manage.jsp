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
  <link rel="stylesheet" href="/css/jquery.basictable.css">
  <script src="/js/jquery.basictable.min.js"></script>
  <script src="/js/reponsetable.min.js"></script>
  <script src="/js/layer-3.1.1.js"></script>
  <title>报名管理</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>
  <div class="container">
    <ul class="nav nav-tabs">
      <li role="presentation">
        <a href="/vote/edit?aid=${aid}">编辑</a>
      </li>
      <li role="presentation" class="active">
        <a href="/vote/apply_manage?aid=${aid}">报名管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/entry_manage?aid=${aid}">条目管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/gather?aid=${aid}">结果与日志</a>
      </li>
      <li role="presentation">
        <a href="/vote/qrcode?aid=${aid}">链接与二维码</a>
      </li>
    </ul>

    <br>

    <div>
      <div class="btn-container">
        <button class="layui-btn" onclick="exportExcel();"><i class="fa fa-floppy-o"></i>导出Excel</button>
        <button class="layui-btn" onclick="reloadTable();"><i class="fa fa-undo"></i>刷新表格</button>
      </div>
      <table id="table" class="reponsetable"></table>
      <script type="text/html" id="editer">
        <a class="table-operation" onclick="edittr(this)"><i class="fa fa-pencil"></i></a><a class="table-operation" onclick="deletetr(this)"><i class="fa fa-trash"></i></a>
      </script>
    </div>

    <div id="edit-content" class="alertceng">
      <p><span class="title">姓名:</span><input type="text" id="Name" /></p>
      <p><span class="title">年龄:</span><input type="text" id="Age" /></p>
      <p><span class="title">性别:</span><input type="text" id="Gender" /></p>
      <p><span class="title">身高:</span><input type="text" id="Height" /></p>
      <p><span class="title">省份:</span><input type="text" id="Province" /></p>
      <p><span class="title">市级:</span><input type="text" id="Sport" /></p>
    </div>

    <!-- 其它可选列 -->
    <%
      String[] options = (String[])request.getAttribute("options");
      for (int i = 0; i < options.length; i++) {
        if (options[i].equals("1")) {
          out.write("<span class=\"hidden table-column\" data-field=\"Name\" data-title=\"真实姓名\"></span>");
        } else if (options[i].equals("2")) {
          out.write("<span class=\"hidden table-column\" data-field=\"Sex\" data-title=\"真实性别\"></span>");
        } else if (options[i].equals("3")) {
          out.write("<span class=\"hidden table-column\" data-field=\"Age\" data-title=\"真实年龄\"></span>");
        } else if (options[i].equals("4")) {
          out.write("<span class=\"hidden table-column\" data-field=\"Telephone\" data-title=\"手机号码\"></span>");
        } else if (options[i].equals("5")) {
          out.write("<span class=\"hidden table-column\" data-field=\"Email\" data-title=\"电子邮件\"></span>");
        } else if (options[i].equals("6")) {
          out.write("<span class=\"hidden table-column\" data-field=\"School\" data-title=\"学校名称\"></span>");
        } else if (options[i].equals("7")) {
          out.write("<span class=\"hidden table-column\" data-field=\"Company\" data-title=\"公司名称\"></span>");
        } else if (options[i].equals("8")) {
          out.write("<span class=\"hidden table-column\" data-field=\"Address\" data-title=\"收货地址\"></span>");
        }
      }
    %>

    <script src="/js/apply_manage.js"></script>
  
  </div>
  <%@ include file="../components/footer.jsp" %>
</body>