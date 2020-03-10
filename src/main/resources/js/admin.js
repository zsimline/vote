$('.nav-left-container').on('click', 'li', function () {
  $(this).find('.glyphicon-menu-right').removeClass('glyphicon-menu-right').addClass('glyphicon-menu-down');
  $(this).addClass('active').children('.nav-left-container-small').slideDown()

  $(this).siblings().removeClass('active').children('.nav-left-container-small').slideUp()
  $(this).siblings().find('.glyphicon-menu-down').removeClass('glyphicon-menu-down').addClass('glyphicon-menu-right ');
})