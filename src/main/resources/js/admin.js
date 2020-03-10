$('.nav-left-container').on('click', 'li', function () {
  $(this).find('.glyphicon-menu-right').removeClass('glyphicon-menu-right').addClass('glyphicon-menu-down');
  $(this).addClass('active').children('.nav-left-container-small').slideDown()

  $(this).siblings().removeClass('active').children('.nav-left-container-small').slideUp()
  $(this).siblings().find('.glyphicon-menu-down').removeClass('glyphicon-menu-down').addClass('glyphicon-menu-right ');
})

function switchUserStatus(which, uid) {
  get(`/api/admin/user_status?uid=${uid}`)
    .then(data => {
      if (!(data % 100)) {
        if (data.codeDesc === 'true') {
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


function switchUserPrivilege(which, uid) {
  get(`/api/admin/user_privilege?uid=${uid}`)
    .then(data => {
      if (!(data % 100)) {
        if (data.codeDesc === 'true') {
          $($(which).parent().parent().children()[3]).text('管理员');
        } else {
          $($(which).parent().parent().children()[3]).text('普通用户');
        }
      }
    })
    .catch(err => {
      console.error(err);
    })
}