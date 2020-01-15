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
    <h5>基本设置</h5><hr>
    <div class="inputcon">
      <label for="">投票标题</label>
      <input type="text" class="form-control input-sm" placeholder="请输入投票标题" maxlength="40">
      <span class="extra-tip">投票的标题，最多40个字符</span>
    </div>
    <div class="inputcon">
      <label for="">宣传图片</label>
      <input type="file" class="form-control input-sm">
      <span class="extra-tip">投票页顶部图片，文件小于1M</span>
    </div>
    <div class="inputcon">
      <label for="">条目称谓</label>
      <input type="text" class="form-control input-sm" placeholder="请输入条目称谓" value="选手" maxlength="3">
      <span class="extra-tip">投票条目称谓，如选手、作品等，最多三个汉字</span>
    </div>
    <div class="inputcon">
      <label for="">条目量词</label>
      <input type="text" class="form-control input-sm" placeholder="请输入条目量词" value="个" maxlength="1">
      <span class="extra-tip">用于描述选项个数，如个、位等，最多一个汉字</span>
    </div>
    <div class="inputcon">
      <label for="">一次最多选择</label>
      <input type="number" class="form-control input-sm" value="1" min="1" max="100">
      <span class="extra-tip">每个用户最多可同时向几个条目投票，最小值为1，最大值为100</span>
    </div>
    <div class="inputcon">
      <label for="">投票开始时间</label>
      <input type="text" class="form-control input-sm" id="datetimepicker1">
      <span class="extra-tip">开始时间后才能投票</span>
    </div>
    <div class="inputcon">
      <label for="">投票截止时间</label>
      <input type="text" class="form-control input-sm" id="datetimepicker2">
      <span class="extra-tip">截止时间后不能继续投票</span>
    </div>
    <div class="inputcon">
      <label for="">报名开始时间</label>
      <input type="text" class="form-control input-sm" id="datetimepicker3">
      <span class="extra-tip">开始时间后才能报名</span>
    </div>
    <div class="inputcon">
      <label for="">报名截止时间</label>
      <input type="text" class="form-control input-sm" id="datetimepicker4">
      <span class="extra-tip">截止时间后不能继续报名</span>
    </div>
    <h5>活动简介</h5><hr>
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
