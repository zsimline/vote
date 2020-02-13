<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>

<div id="tip" class="hidden">
</div>
  
<style>
#tip {
  font-size: 14px;
  font-style: italic;
  padding: 10px 5px;
  text-align: center;
  color: #919191;
}
</style>
  
<script>
function showTip(content) {
  $('#tip').html(content).removeClass('hidden').addClass('show');
}
function hideTip(content) {
  $('#tip').removeClass('show').addClass('hidden');
}
</script>