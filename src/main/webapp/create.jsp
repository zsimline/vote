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
  <script src="tinymce/tinymce-5.1.5.min.js"></script>
  <script src="js/flatui-checkbox.js"></script>
  <title>创建投票</title>
</head>

<body>
  <%@ include file="components/header.jsp" %>
  
  <!-- 投票配置项 -->
  <div class="container">
    <h5>基本设置</h5>
    <div class="inputcon">
      <label for="">投票标题</label>
      <input type="text" class="form-control input-sm" placeholder="请输入投票标题" maxlength="40">
    </div>
    <div class="inputcon">
      <label for="">宣传图片</label>
      <input type="file" class="form-control input-sm">
    </div>
    <div class="inputcon">
      <label for="">条目称谓</label>
      <input type="text" class="form-control input-sm" placeholder="请输入条目称谓" value="选手" maxlength="3">
    </div>
    <div class="inputcon">
      <label for="">条目量词</label>
      <input type="text" class="form-control input-sm" placeholder="请输入条目量词" value="个" maxlength="1">
    </div>
    <div class="inputcon">
      <label for="">一次最多选择</label>
      <input type="number" class="form-control input-sm" value="1" min="1" max="100">
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
    <h5>活动简介</h5>
    <div id="tinymce"></div>
    
    <label class="checkbox" for="checkbox1">
        <input type="checkbox" value="" id="checkbox1" data-toggle="checkbox">
        我同意投票<a href="./license.jsp">服务条款</a>
    </label>

    <button class="btn btn-primary">
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布投票&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </button>
  </div>
  
  <!-- 时间拾取器配置 -->
  <script type="text/javascript">
    $(function () {
      $('#datetimepicker1').datetimepicker({ locale: 'zh-cn' });
      $('#datetimepicker2').datetimepicker({ locale: 'zh-cn' });
      $('#datetimepicker3').datetimepicker({ locale: 'zh-cn' });
      $('#datetimepicker4').datetimepicker({ locale: 'zh-cn' });
    });
  </script>

  <!-- 富文本编辑器配置 -->
  <script>
    tinymce.init({
      selector: '#tinymce',
      language: 'zh_CN',
      min_height: 500,
      width: 1200,
      plugins: 'print preview searchreplace autolink directionality visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor insertdatetime advlist lists imagetools textpattern help emoticons autosave autoresize',
      toolbar: 'undo redo | forecolor backcolor bold italic underline strikethrough link anchor | alignleft aligncenter alignright alignjustify outdent indent | formatselect fontselect fontsizeselect | bullist numlist | blockquote subscript superscript removeformat',
      fontsize_formats: '12px 14px 16px 18px 24px 36px 48px 56px 72px',
    });
  </script>

  <%@ include file="components/footer.jsp" %>
</body>

</html>
