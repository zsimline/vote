<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>


<!DOCTYPE HTML>
<html lang="zh">

<head>
  <%@ include file="components/meta.jsp" %>
  <%@ include file="components/link.jsp" %>
  <link rel="stylesheet" href="css/datetimepicker-4.0.0.min.css">
  <script src="js/moment-with-locales-2.9.0.js"></script>
  <script src="js/datetimepicker-4.0.0.js"></script>
  <title>创建投票</title>
</head>

<body>
  <%@ include file="components/header.jsp" %>
  <div class="container">
    <div class="inputcon">
      <label for="">投票标题</label>
      <input type="text" class="form-control input-sm" placeholder="请输入投票标题">
    </div>
    <div class="inputcon">
      <label for="">宣传图片</label>
      <input type="file" class="form-control input-sm">
    </div>
    <div class="inputcon">
      <label for="">条目称谓</label>
      <input type="text" class="form-control input-sm" placeholder="请输入条目称谓" value="选手">
    </div>
    <div class="inputcon">
      <label for="">投票开始时间</label>
      <input type="text" class="form-control input-sm" id="datetimepicker1">
    </div>
    <div class="inputcon">
      <label for="">投票截止时间</label>
      <input type="text" class="form-control input-sm" id="datetimepicker2">
    </div>
    <div class="inputcon">
      <label for="">报名开始时间</label>
      <input type="text" class="form-control input-sm" id="datetimepicker3">
    </div>
    <div class="inputcon">
      <label for="">报名截止时间</label>
      <input type="text" class="form-control input-sm" id="datetimepicker4">
    </div>
  </div>
  <script type="text/javascript">
    $(function () {
      $('#datetimepicker1').datetimepicker({locale: 'zh-cn'});
      $('#datetimepicker2').datetimepicker({locale: 'zh-cn'});
      $('#datetimepicker3').datetimepicker({locale: 'zh-cn'});
      $('#datetimepicker4').datetimepicker({locale: 'zh-cn'});
    });
  </script>
  <%@ include file="components/footer.jsp" %>
</body>
</html>