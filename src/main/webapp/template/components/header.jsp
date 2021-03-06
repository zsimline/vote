<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>

<div class="navbar navbar-inverse">
  <!-- 导航条头部 -->
  <div class="navbar-header">
    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-collapse-02"></button>
    <a class="navbar-brand" href="/index">
      <i class="fa fa-fire"></i>
      <span>鹿鸣投票</span>
    </a>
  </div>

  <!-- 左侧导航条 -->
  <div class="navbar-collapse collapse navbar-collapse-01">
    <ul class="nav navbar-nav navbar-left">
      <li>
        <a href="/index">首页</a>
      </li>
      <li>
        <a href="/vote/publish">发布投票</a>
      </li>
      <li>
        <a href="/vote/manage">管理中心</a>
      </li>
      <li>
        <a href="https://mxsyx.site">关于我们</a>
      </li>
    </ul>

    <!-- 右侧导航条 -->
    <ul class="nav navbar-nav navbar-right">
      <li class="user">
        <a href="/user/login">
          <i class="fa fa-user"></i>
            <% if (request.getSession().getAttribute("uid") != null) { %>
              <span>已登录</span>
            <% } else { %>
              <span>登录/注册</span>
            <% } %>
          <i class="fa fa-caret-down"></i>
        </a>
        <ul>
          <li>
            <a href="/user/profile">
              <i class="fa fa-user"></i>
              <span>个人中心</span>
            </a>
          </li>
          <li>
            <a href="javascript:handleLogout()">
              <i class="fa fa-power-off"></i>
              <span>退出</span>            
            </a>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</div>

<script>
  // 处理账户注销
  function handleLogout() {
    get('/api/user/logout')
      .then(data => {
        if (!(data%100)) {
          window.location.reload();
        } else {
          alert(data.codeDesc);
        }
      })
  }
</script>