<%@ page
  language="java"
  contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8"
%>

<ul class="nav-left-container">
  <li>
    <a href="#">
      <i class="fa fa-user" aria-hidden="true"></i>
      <span>账号相关</span>
      <i class="fa fa-angle-right pull-right" aria-hidden="true"></i>
    </a>
    <ul class="nav-left-container-small">
      <li>
        <a class="J_menuItem" href="#">
          <i class="fa fa-cog" aria-hidden="true"></i>
          <span>修改信息</span>
        </a>
      </li>
      <li>
        <a class="J_menuItem" href="#">
          <i class="fa fa-lock" aria-hidden="true"></i>
          <span>安全中心</span>
        </a>
      </li>
    </ul>
  </li>
  
  <li>
    <a href="#">
      <i class="fa fa-tasks" aria-hidden="true"></i>
      <span>投票相关</span>
      <i class="fa fa-angle-right pull-right" aria-hidden="true"></i>
    </a>
    <ul class="nav-left-container-small">
      <li>
        <a class="J_menuItem" href="#">
          <i class="fa fa-plus" aria-hidden="true"></i>
          <span>发布投票</span>
        </a>
      </li>
    </ul>
  </li>
</ul>
<script>
  $('.nav-left-container').on('click', 'li', function () {
    $(this).find('.glyphicon-menu-right').removeClass('glyphicon-menu-right').addClass('glyphicon-menu-down');
    $(this).addClass('active').children('.nav-left-container-small').slideDown()

    $(this).siblings().removeClass('active').children('.nav-left-container-small').slideUp()
    $(this).siblings().find('.glyphicon-menu-down').removeClass('glyphicon-menu-down').addClass('glyphicon-menu-right ');
  })
</script>