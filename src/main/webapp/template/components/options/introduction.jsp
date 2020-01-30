<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>

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