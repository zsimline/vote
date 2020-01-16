/**
 * 处理表单提交
 * 获取表单数据并校验
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
  const description = tinyMCE.activeEditor.getContent();

  // 校验表单是否为空
  if (title === '') {
    openModal('error', '投票标题不能为空');
    return ;
  }
  if (imgAddr === '') {
    openModal('error', '请上传宣传图片');
    return ;
  }
  if (suffix === '') {
    openModal('error', '条目称谓不能为空');
    return ;
  }
  if (quantifier === '') {
    openModal('error', '条目量词不能为空');
    return ;
  }
  if (maxium === '') {
    openModal('error', '请输入一次最多选择');
    return ;
  }
  if (voteTimeStart === '') {
    openModal('error', '投票开始时间不能为空');
    return ;
  }
  if (voteTimeEnd === '') {
    openModal('error', '投票截止时间不能为空');
    return ;
  }
  if (signUpTimeStart === '') {
    openModal('error', '报名开始时间不能为空');
    return ;
  }
  if (signUpTimeEnd === '') {
    openModal('error', '报名截止时间不能为空');
    return ;
  }
  if (description === '') {
    openModal('error', '活动描述不能为空');
    return ;
  }

  // 将本地时间转换为时间戳
  const voteTimeStampStart = moment(voteTimeStart).valueOf();
  const voteTimeStampEnd = moment(voteTimeEnd).valueOf();
  const signUpTimeStampStart = moment(signUpTimeStart).valueOf();
  const signUpTimeStampEnd = moment(signUpTimeEnd).valueOf();

  // 校验时间
  // 使时间顺序正常
  if (voteTimeStampStart >= voteTimeStampEnd) {
    openModal('error', '投票截止时间不能早于投票开始时间');
    return ;
  }
  if (signUpTimeStampStart >= signUpTimeStampEnd) {
    openModal('error', '报名截止时间不能早于报名开始时间');
    return ;
  }
  if (signUpTimeStampStart > voteTimeStampStart) {
    openModal('error', '报名开始时间不能晚于投票开始时间');
    return ;
  }
  if (signUpTimeStampEnd > voteTimeStampEnd) {
    openModal('error', '报名截止时间不能晚于投票截止时间');
    return ;
  }

  // 判断用户是够勾选同意协议
  if (!$('#lisence').is(':checked')) {
    openModal('error', '请勾选`我同意投票服务条款`');
    return ;
  }
  
  const formData = new FormData()
  formData.append('title', title);
  formData.append('imgAddr', $('#img-addr').prop('files')[0]);
  formData.append('suffix', suffix);
  formData.append('quantifier', quantifier);
  formData.append('maxium', maxium);
  formData.append('voteTimeStart', voteTimeStampStart);
  formData.append('voteTimeEnd', voteTimeStampEnd);
  formData.append('signupTimeStart', signUpTimeStampStart);
  formData.append('signupTimeStart', signUpTimeStampEnd);
  formData.append('description', description);

  // 向服务器提交数据
  post('v2/create', formData)
    .then(data => {
      openModal('success', '发布投票成功')
    })
    .catch(err => {
      openModal('error', '发布投票失败')
    });
}

/**
 * 根据特定的消息类型显示消息
 * @param {string} type 消息类型
 * @param {string} message 消息内容
 */
function openModal(type, message) {
  let messageTyped = null;

  if (type === 'success') {
    messageTyped = `<span style="color:green">${message}</span>`;
  } else if (type === 'error') {
    messageTyped = `<span style="color:red">${message}</span>`;
  }
  
  $('#commonModalBody').html(messageTyped);
  $('#commonModal').modal();
}
