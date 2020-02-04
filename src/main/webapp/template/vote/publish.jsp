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
  <link rel="stylesheet" href="/css/datetimepicker-4.0.0.min.css">
  <script src="/js/moment-with-locales-2.9.0.js"></script>
  <script src="/js/datetimepicker-4.0.0.js"></script>
  <script src="/js/bootstrap-switch-1.3.js"></script>
  <title>发布投票</title>
</head>

<body>
  <%@ include file="../components/header.jsp" %>

  <!-- 投票配置项 -->
  <div class="container">
    <h5>基本设置</h5>
    <hr>
    <div class="inputcon">
      <label for="title">投票标题</label>
      <input type="text" id="title" class="form-control input-sm" placeholder="请输入投票标题" maxlength="40">
      <span class="extra-tip">投票的标题，最多40个字符</span>
    </div>
    <div class="inputcon">
      <label for="img-main">宣传图片</label>
      <input type="file" id="img-main" class="form-control input-sm">
      <span class="extra-tip">投票页顶部图片，文件小于1M</span>
    </div>
    <div class="inputcon">
      <label for="suffix">条目称谓</label>
      <input id="suffix" type="text" class="form-control input-sm" placeholder="请输入条目称谓" value="选手" maxlength="3">
      <span class="extra-tip">投票条目称谓，如选手、作品等，最多三个汉字</span>
    </div>
    <div class="inputcon">
      <label for="quantifier">条目量词</label>
      <input type="text" id="quantifier" class="form-control input-sm" placeholder="请输入条目量词" value="个" maxlength="1">
      <span class="extra-tip">用于描述选项个数，如个、位等，最多一个汉字</span>
    </div>
    <div class="inputcon">
      <label for="maxium">一次最多选择</label>
      <input type="number" id="maxium" class="form-control input-sm" value="1" min="1" max="100">
      <span class="extra-tip">每个用户最多可同时向几个条目投票，最小值为1，最大值为100</span>
    </div>
    <div class="inputcon">
      <label for="vote-time-start">投票开始时间</label>
      <input type="text" class="form-control input-sm" id="vote-time-start">
      <span class="extra-tip">开始时间后才能投票</span>
    </div>
    <div class="inputcon">
      <label for="vote-time-end">投票截止时间</label>
      <input type="text" class="form-control input-sm" id="vote-time-end">
      <span class="extra-tip">截止时间后不能继续投票</span>
    </div>
    <div class="inputcon">
      <label for="signup-time-start">报名开始时间</label>
      <input type="text" class="form-control input-sm" id="signup-time-start">
      <span class="extra-tip">开始时间后才能报名</span>
    </div>
    <div class="inputcon">
      <label for="signup-time-end">报名截止时间</label>
      <input type="text" class="form-control input-sm" id="signup-time-end">
      <span class="extra-tip">截止时间后不能继续报名</span>
    </div>

    <h5>活动简介</h5>
    <hr>
    <%@ include file="../components/tinymce.jsp" %>

    <h5>报名设置</h5>
    <hr>
    <div class="apply-options">
      <label for="name">禁止外部人员报名</label>
      <div class="switch">
        <input id="c" type="checkbox">
      </div>
    </div>

    <h5>报名选项</h5>
    <hr>
    <%@ include file="../components/options/apply_options.jsp" %>

    <%@ include file="../components/lisence.jsp" %>

    <button class="btn btn-primary" onclick="handleSubmit()">　发布投票　</button>
  </div>

  <!-- 时间拾取器配置 -->
  <script type="text/javascript">
    $(function () {
      const options = { locale: 'zh-cn', format: "YYYY-MM-DD hh:mm" };
      $('#vote-time-start').datetimepicker(options);
      $('#vote-time-end').datetimepicker(options);
      $('#signup-time-start').datetimepicker(options);
      $('#signup-time-end').datetimepicker(options);
    });
  </script>

  <script src="/js/create.js"></script>
  <%@ include file="../components/modal.jsp" %>
  <%@ include file="../components/footer.jsp" %>
</body>

</html>