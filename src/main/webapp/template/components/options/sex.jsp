<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>

<script src="/js/flatui-radio-0.0.3.js"></script>
<div class="inputcon">
  <label for="sex">真实性别</label>
  <label class="radio" onclick="$('#sex').val('男');">
    <input type="radio" name="sex" data-toggle="radio">男
  </label>
  <label class="radio" onclick="$('#sex').val('女');">
    <input type="radio" name="sex" data-toggle="radio">女
  </label>
  <span class="extra-tip">选择您的性别</span>
  <input type="text" id="sex" class="hidden">
</div>
