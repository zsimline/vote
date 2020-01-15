// 通用模态框
const commonModal = $('#commonModal');

// 模态框内容
const modalBody = $('.modal-body');

/**
 * 处理表单提交
 */
function handleSubmit() {
  // 获取表单数据
  const title = $('#title').val();
  const imgAddr = $('#img-addr').val();
  const suffix = $('#suffix').val();
  const quantifier = $('#quantifier').val();
  const maxium = $('#maxium').val();
  const voteTimeStart = $('#vote-time-start').val();
  const voteTimeEnd = $('#vote-time-end').val();
  const signUpTimeStart = $('#signup-time-start').val();
  const signUpTimeEnd = $('#signup-time-end').val();



  if (title === '') {
    $('.modal-body').text('标题不能为空');
    $('#commonModal').modal();
  }
}
