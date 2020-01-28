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
  <title>审核报名</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>
  <div class="container">
    <ul class="nav nav-tabs">
      <li role="presentation">
        <a href="/vote/edit?aid=${aid}">编辑</a>
      </li>
      <li role="presentation" class="active">
        <a href="/vote/review?aid=${aid}">审核报名</a>
      </li>
      <li role="presentation">
        <a href="/vote/additem?aid=${aid}">批量添加</a>
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
        <button class="layui-btn" onclick="reloadtable();"><i class="fa fa-undo"></i>刷新表格</button>
      </div>
      <table id="table" class="reponsetable"></table>
      <div class="btn-group">
        <script type="text/html" id="editer">
          <a class="table-operation" onclick="edittr(this)"><i class="fa fa-pencil"></i></a><a class="table-operation" onclick="deletetr(this)"><i class="fa fa-trash"></i></a>
        </script>
    </div>

    </div>

    <div id="edit-content" class="alertceng">
      <p><span class="title">姓名:</span><input type="text" id="Name" /></p>
      <p><span class="title">年龄:</span><input type="text" id="Age" /></p>
      <p><span class="title">性别:</span><input type="text" id="Gender" /></p>
      <p><span class="title">身高:</span><input type="text" id="Height" /></p>
      <p><span class="title">省份:</span><input type="text" id="Province" /></p>
      <p><span class="title">市级:</span><input type="text" id="Sport" /></p>
    </div>

    <script type="text/javascript">
      $(function () {
        $('#table').basictable({
          breakpoint: 768
        });
        $("#table").reponsetable({
          "id": "table",
          "operation": "editer",
          "type": "numbers",
          "colum": [
            { "field": "Name", "title": "姓名" },
            { "field": "Age", "title": "年龄" },
            { "field": "Gender", "title": "性别" },
            { "field": "Height", "title": "身高" },
            { "field": "Province", "title": "省份" },
            { "field": "Sport", "title": "市级" }
          ],
          "data": [
            { "Name": "张三", "Age": 15, "Gender": "男", "Height": 189, "Province": "河北", "Sport": "唐山市" },
            { "Name": "李四", "Age": 15, "Gender": "男", "Height": 189, "Province": "河北", "Sport": "邯郸市" },
            { "Name": "王五", "Age": 15, "Gender": "男", "Height": 189, "Province": "河北", "Sport": "沧州市" },
            { "Name": "赵六", "Age": 15, "Gender": "男", "Height": 189, "Province": "河北", "Sport": "邢台市" },
            { "Name": "陈七", "Age": 15, "Gender": "女", "Height": 189, "Province": "河北", "Sport": "保定市" },
          ]
        });
      });
      
      function exportExcel() {
        var tableobj = $("#table").data("tableObj");
        reponse.JSONToCSVConvertor(tableobj, true, "人员表格");
      }

      function reloadtable() {
        var data = [
          { "Name": "1111", "Age": 15, "Gender": "1", "Height": 189, "Province": "1", "Sport": "1" },
          { "Name": "2222", "Age": 15, "Gender": "2", "Height": 2, "Province": "2", "Sport": "2" },
          { "Name": "3333", "Age": 15, "Gender": "4", "Height": 189, "Province": "3", "Sport": "3" }
        ]
        reponse.reloadtable(data, "table");
      }

      function edittr(a, e) {
        var tr = $(a).parent().parent();
        reponse.resiverowdata(tr, "table");
        var rowdata = $("#table").data("rowdata");

        layer.open({
          type: 1,
          title: '编辑人员信息',
          closeBtn: 1,
          area: '516px',
          skin: '#fff',
          shadeClose: true,
          content: $('#edit-content'),
          btn: ["保存", "关闭"],
          btn1: function (index, layero) {
            var Name = $("#Name").val();
            var Age = $("#Age").val();
            var Gender = $("#Gender").val();
            var Height = $("#Height").val();
            var Province = $("#Province").val();
            var Sport = $("#Sport").val();
            var obj = {
              "Name": Name,
              "Age": Age,
              "Gender": Gender,
              "Height": Height,
              "Province": Province,
              "Sport": Sport
            };
            reponse.editsavetr(obj, "table");
            layer.close(index);
          }, btn2: function (index, layero) {
            layer.close(index);
          }
        });
      }
      
      function deletetr(a, e) {
        var tr = $(a).parent().parent();
        reponse.deletetr(tr, e);
      }
    </script>

  </div>
  <%@ include file="../components/footer.jsp" %>
</body>