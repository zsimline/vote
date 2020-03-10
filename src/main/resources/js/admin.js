$('.nav-left-container').on('click', 'li', function () {
  $(this).find('.glyphicon-menu-right').removeClass('glyphicon-menu-right').addClass('glyphicon-menu-down');
  $(this).addClass('active').children('.nav-left-container-small').slideDown()

  $(this).siblings().removeClass('active').children('.nav-left-container-small').slideUp()
  $(this).siblings().find('.glyphicon-menu-down').removeClass('glyphicon-menu-down').addClass('glyphicon-menu-right ');
})

function freezeUser(which, uid, status) {
  get(`/api/admin/user_freeze?uid=${uid}&status=${status}`)
    .then(data => {
      if (!(data % 100)) {
        if (status === 'y') {
          $($(which).parent().parent().children()[4]).html("<i class=\"fa fa-check-circle\" style=\"color:green\"></i>")
        } else {
          $($(which).parent().parent().children()[4]).html("<i class=\"fa fa-times-circle\" style=\"color:red\"></i>")
        }
      }
    })
    .catch(err => {
      console.error(err);
    })
}

function deleteActivity(which, aid) {
  get(`/api/admin/activity_delete?aid=${aid}`)
    .then(data => {
      if (!(data % 100)) {
        $($(which).parent().parent().children()[5]).html("<i class=\"fa fa-times-circle\" style=\"color:red\"></i>")
        $(which).on('click',  () => {});
        $(which).css('color', '#898989');
      }
    })
    .catch(err => {
      console.error(err);
    })
}