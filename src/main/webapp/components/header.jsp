<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>

<div class="navbar navbar-inverse">
  <!-- 导航条头部 -->
  <div class="navbar-header">
    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-collapse-02"></button>
    <a class="navbar-brand" href="#">
      <i class="fa fa-fire"></i>&nbsp;&nbsp;鹿鸣投票
    </a>
  </div>

  <!-- 左侧导航条 -->
  <div class="navbar-collapse collapse navbar-collapse-01">
    <ul class="nav navbar-nav navbar-left">
      <li>
        <a href="#">首页</a>
      </li>
      <li>
        <a href="#">发布投票</a>
      </li>
      <li>
        <a href="#">管理中心</a>
      </li>
      <li>
        <a href="#">关于我们</a>
      </li>
    </ul>

    <!-- 右侧导航条 -->
    <ul class="nav navbar-nav navbar-right">
      <li class="user">
        <a href="#">
          <i class="fa fa-user"></i>
          &nbsp;已登录&nbsp;
          <i class="fa fa-caret-down"></i>
        </a>
        <ul>
          <li>
            <a href="#">
              <i class="fa fa-user"></i>
              个人中心
            </a>
          </li>
          <li>
            <a href="#">
              <i class="fa fa-power-off"></i>
              退出
            </a>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</div>
