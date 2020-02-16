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
  <link rel="stylesheet" href="/css/datetimepicker-4.0.0.min.css">
  <script src="/js/moment-with-locales-2.9.0.js"></script>
  <script src="/js/datetimepicker-4.0.0.js"></script>
  <script src="/js/bootstrap-switch-1.3.js"></script>
  <title>编辑报名</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>
  <div class="container">
    <ul class="nav nav-tabs">
      <li role="presentation" class="active">
        <a href="/vote/edit?aid=${activity.id}">编辑</a>
      </li>
      <li role="presentation">
        <a href="/vote/apply_manage?aid=${activity.id}&status=w&page=1">报名管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/entry_manage?aid=${activity.id}&page=1">条目管理</a>
      </li>
      <li role="presentation">
        <a href="/vote/gather?aid=${activity.id}">结果与日志</a>
      </li>
      <li role="presentation">
        <a href="/vote/qrcode?aid=${activity.id}">链接与二维码</a>
      </li>
    </ul>

    <h5>基本设置</h5>
    <hr>
    <div class="inputcon">
      <label for="title">投票标题</label>
      <input type="text" id="title" class="form-control input-sm" maxlength="40" value="${activity.title}">
      <span class="extra-tip">投票的标题，最多40个字符</span>
    </div>
    <div class="inputcon">
      <label for="img-main">宣传图片</label>
      <input type="file" id="img-main" class="form-control input-sm">
      <span class="extra-tip">投票页顶部图片，文件小于1M</span>
    </div>
    <div class="inputcon">
      <label for="suffix">条目称谓</label>
      <input id="suffix" type="text" class="form-control input-sm" placeholder="请输入条目称谓" maxlength="3" value="${activity.suffix}">
      <span class="extra-tip">投票条目称谓，如选手、作品等，最多三个汉字</span>
    </div>
    <div class="inputcon">
      <label for="quantifier">条目量词</label>
      <input type="text" id="quantifier" class="form-control input-sm" placeholder="请输入条目量词" maxlength="1" value="${activity.quantifier}">
      <span class="extra-tip">用于描述选项个数，如个、位等，最多一个汉字</span>
    </div>
    <div class="inputcon">
      <label for="maxium">一次最多选择</label>
      <input type="number" id="maxium" class="form-control input-sm" value="${activity.maximum}" min="1" max="100">
      <span class="extra-tip">每个用户最多可同时向几个条目投票，最小值为1，最大值为100</span>
    </div>
    <div class="inputcon">
      <label for="reason-length">理由最少字数</label>
      <input type="number" id="reason-length" class="form-control input-sm" value="${activity.reasonLength}" min="0" max="127">
      <span class="extra-tip">投票时阐述理由的最小字数，0代表不需要阐述理由</span>
    </div>
    <div class="inputcon">
      <label for="vote-time-start">投票开始时间</label>
      <input type="text" class="form-control input-sm" id="vote-time-start" value="${activity.voteTimeStart}">
      <span class="extra-tip">开始时间后才能投票</span>
    </div>
    <div class="inputcon">
      <label for="vote-time-end">投票截止时间</label>
      <input type="text" class="form-control input-sm" id="vote-time-end" value="${activity.voteTimeEnd}">
      <span class="extra-tip">截止时间后不能继续投票</span>
    </div>
    <div class="inputcon">
      <label for="apply-time-start">报名开始时间</label>
      <input type="text" class="form-control input-sm" id="apply-time-start" value="${activity.applyTimeStart}">
      <span class="extra-tip">开始时间后才能报名</span>
    </div>
    <div class="inputcon">
      <label for="apply-time-end">报名截止时间</label>
      <input type="text" class="form-control input-sm" id="apply-time-end" value="${activity.applyTimeEnd}">
      <span class="extra-tip">截止时间后不能继续报名</span>
    </div>

    <h5>活动简介</h5>
    <hr>
    <script src="/tinymce/tinymce-5.1.5.min.js"></script>
    <div id="tinymce">${activity.summary}</div>
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

    <h5>高级设置</h5>
    <hr>
    <div class="advanced-config">
      <label for="name">允许外部人员报名</label>
      <div class="switch">
        <c:choose>
          <c:when test="${activity.externalApply}">
              <input id="external-apply" type="checkbox" checked>
            </c:when>
            <c:otherwise>
              <input id="external-apply" type="checkbox">
            </c:otherwise>
        </c:choose>
      </div>
    </div>
    <div class="advanced-config">
      <label for="name">是否是有奖投票</label>
      <div class="switch">
        <c:choose>
          <c:when test="${activity.havePrize}">
              <input id="have-prize" type="checkbox" checked>
            </c:when>
            <c:otherwise>
              <input id="have-prize" type="checkbox">
            </c:otherwise>
        </c:choose>
      </div>
    </div>

    <h5>报名选项</h5>
    <hr>
    <div id="options-container">
      <label for="img-entry">参赛图片</label>
      <div class="switch switch-square">
        <input id="img-entry" type="checkbox" data-index="0">
      </div><br>
      <label for="introduction">详细介绍</label>
      <div class="switch switch-square">
        <input id="introduction" type="checkbox" data-index="1">
      </div><br>
      <%@ include file="../components/options/apply_options.jsp" %>
    </div>
    
    <hr>
    <button class="btn btn-primary" onclick="handleUpdate()">　更新投票　</button>
  </div>

  <span id="aid" class="hidden">${activity.id}</span>
  <span id="options" class="hidden">${activity.options}</span>

  <script src="/js/publish.js"></script>
  <script src="/js/manage.js"></script>

  <%@ include file="../components/message.jsp" %>
  <%@ include file="../components/footer.jsp" %>
</body>

</html>