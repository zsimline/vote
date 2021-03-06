<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>

<!-- 通用模态框 -->
<div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="commonModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="commonModalLabel">消息提示</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="commonModalBody">
        ...
      </div>
      <div class="modal-footer">
        <button id="commonModalButton" type="button" class="btn btn-info" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>