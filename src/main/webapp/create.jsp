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
  <script src="js/flatui-checkbox-0.0.3.js"></script>
  <script src="js/bootstrap-switch-1.3.js"></script>
  <title>创建投票</title>
</head>

<body>
  <%@ include file="components/header.jsp" %>

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
      <label for="img-name">宣传图片</label>
      <input type="file" id="img-name" class="form-control input-sm">
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
    <div id="tinymce"></div>

    <h5>其它选项</h5>
    <hr>
    <div id="options">
      <label for="name">人物姓名</label>
      <div class="switch switch-square">
        <input id="name" type="checkbox" data-index="0" data-toggle="switch" />
      </div><br>
      <label for="sex">人物性别</label>
      <div class="switch switch-square">
        <input id="sex" type="checkbox" data-index="1" data-toggle="switch" />
      </div><br>
      <label for="age">人物年龄</label>
      <div class="switch switch-square">
        <input id="age" type="checkbox" data-index="2" data-toggle="switch" />
      </div><br>
      <label for="telephone">手机号码</label>
      <div class="switch switch-square">
        <input id="telephone" type="checkbox" data-index="3" data-toggle="switch" />
      </div><br>
      <label for="email">电子邮件</label>
      <div class="switch switch-square">
        <input id="email" type="checkbox" data-index="4" data-toggle="switch" />
      </div><br>
      <label for="wechat">微信号码</label>
      <div class="switch switch-square">
        <input id="wechat" type="checkbox" data-index="5" data-toggle="switch" />
      </div><br>
      <label for="school">学校名称</label>
      <div class="switch switch-square">
        <input id="school" type="checkbox" data-index="6" data-toggle="switch" />
      </div><br>
      <label for="classdesc">班级描述</label>
      <div class="switch switch-square">
        <input id="classdesc" type="checkbox" data-index="7" data-toggle="switch" />
      </div><br>
      <label for="company">公司名称</label>
      <div class="switch switch-square">
        <input id="company" type="checkbox" data-index="8" data-toggle="switch" />
      </div><br>
      <label for="address">收货地址</label>
      <div class="switch switch-square">
        <input id="address" type="checkbox" data-index="9" data-toggle="switch" />
      </div><br>
    </div>

    <hr>

    <label class="checkbox" for="lisence">
      <input type="checkbox" id="lisence" data-toggle="checkbox">
      我同意投票<a href="./license.jsp">服务条款</a>
    </label>

    <button class="btn btn-primary" onclick="handleSubmit()">　发布投票　</button>
  </div>

  <!-- 时间拾取器配置 -->
  <script type="text/javascript">
    $(function () {
      const options = { locale: 'zh-cn', format:"YYYY-MM-DD hh:mm" };
      $('#vote-time-start').datetimepicker(options);
      $('#vote-time-end').datetimepicker(options);
      $('#signup-time-start').datetimepicker(options);
      $('#signup-time-end').datetimepicker(options);
    });
  </script>

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

  <script src="js/create.js"></script>
  <%@ include file="components/modal.jsp" %>
  <%@ include file="components/footer.jsp" %>
</body>

</html>