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
  <script src="tinymce/tinymce-5.1.5.min.js"></script>
  <script src="js/flatui-radio-0.0.3.js"></script>
  <title>活动报名</title>
</head>

<body>
  <%@ include file="components/header.jsp" %>

  <div class="container">
    <div class="inputcon">
      <label for="title">标题</label>
      <input type="text" id="title" class="form-control input-sm" placeholder="请输入标题" maxlength="15">
      <span class="extra-tip">如姓名、作品名等，最多15个字符</span>
    </div>
    <div class="inputcon">
      <label for="img-name">宣传图片</label>
      <input type="file" id="img-name" class="form-control input-sm">
      <span class="extra-tip">您个人的宣传图片，文件小于1M</span>
    </div>
    <div id="tinymce"></div>
    <!-- 富文本编辑器配置 -->
    <script>
      tinymce.init({
        selector: '#tinymce',
        language: 'zh_CN',
        min_height: 500,
        width: 1200,
        statusbar: false,
        plugins: 'print preview searchreplace autolink directionality visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor insertdatetime advlist lists imagetools textpattern help emoticons autosave autoresize',
        toolbar: 'undo redo | forecolor backcolor bold italic underline strikethrough link anchor | alignleft aligncenter alignright alignjustify outdent indent | formatselect fontselect fontsizeselect | bullist numlist | blockquote subscript superscript removeformat',
        fontsize_formats: '12px 14px 16px 18px 24px 36px 48px 56px 72px',
      });
    </script>

  <%@ include file="components/options/name.jsp" %>
  <%@ include file="components/options/sex.jsp" %>

  </div>

  <%@ include file="components/modal.jsp" %>
  <%@ include file="components/footer.jsp" %>
</body>

</html>