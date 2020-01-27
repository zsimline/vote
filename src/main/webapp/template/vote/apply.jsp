<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>

<!DOCTYPE HTML>
<html lang="zh">

<head>
  <%@ include file="../components/meta.jsp" %>
  <%@ include file="../components/link.jsp" %>
  <script src="/tinymce/tinymce-5.1.5.min.js"></script>
  <script src="/js/flatui-radio-0.0.3.js"></script>
  <script src="/js/flatui-checkbox-0.0.3.js"></script>
  <script src="/js/apply.js"></script>
  <title>活动报名</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>

  <div class="container">
    <div class="inputcon">
      <label for="title">标题</label>
      <input type="text" id="title" class="form-control input-sm" placeholder="请输入标题" maxlength="15">
      <span class="extra-tip">如姓名、作品名等，最多15个字符</span>
    </div>
    <div class="inputcon">
      <label for="img-name">介绍图片</label>
      <input type="file" id="img-name" class="form-control input-sm">
      <span class="extra-tip">您个人的介绍图片，文件小于1M</span>
    </div>

    <label>详细描述</label>
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

    <%
      String[] options = (String[])request.getAttribute("options");
      for (int i = 0; i < options.length; i++) {
    %>
      <%
        if (options[i].equals("1")) {
      %>
        <%@ include file="../components/options/name.jsp" %>
      <%
        } else if (options[i].equals("2")) {
      %>
      <%@ include file="../components/options/sex.jsp" %>
      <%
        } else if (options[i].equals("3")) {
      %>
      <%@ include file="../components/options/age.jsp" %>
      <%
        } else if (options[i].equals("4")) {
      %>
      <%@ include file="../components/options/telephone.jsp" %>
      <%
        } else if (options[i].equals("5")) {
      %>
      <%@ include file="../components/options/email.jsp" %>
      <%
        } else if (options[i].equals("6")) {
      %>
      <%@ include file="../components/options/school.jsp" %>
      <%
        } else if (options[i].equals("7")) {
      %>
      <%@ include file="../components/options/company.jsp" %>
      <%
        } else if (options[i].equals("8")) {
      %> 
      <%@ include file="../components/options/address.jsp" %>
      <%
        }
      %>
    <% 
      } 
    %>

    <label class="checkbox" for="lisence">
      <input type="checkbox" id="lisence" data-toggle="checkbox">
      我同意投票<a href="license">服务条款</a>
    </label>

    <button class="btn btn-primary" onclick="handleSubmit()">　　报名　　</button>

  </div>

  <span id="aid" class="hidden"><%= request.getAttribute("aid") %></span>

  <%@ include file="../components/modal.jsp" %>
  <%@ include file="../components/footer.jsp" %>
</body>

</html>