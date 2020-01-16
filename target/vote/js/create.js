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
    openModal('<span style="color:red">投票标题不能为空</span>');
    return ;
  }
  if (imgAddr === '') {
    openModal('<span style="color:red">请上传宣传图片</span>');
    return ;
  }
  if (suffix === '') {
    openModal('<span style="color:red">条目称谓不能为空</span>');
    return ;
  }
  if (quantifier === '') {
    openModal('<span style="color:red">条目量词不能为空</span>');
    return ;
  }
  if (maxium === '') {
    openModal('<span style="color:red">请输入一次最多选择</span>');
    return ;
  }
  if (voteTimeStart === '') {
    openModal('<span style="color:red">投票开始时间不能为空</span>');
    return ;
  }
  if (voteTimeEnd === '') {
    openModal('<span style="color:red">投票截止时间不能为空</span>');
    return ;
  }
  if (signUpTimeStart === '') {
    openModal('<span style="color:red">报名开始时间不能为空</span>');
    return ;
  }
  if (signUpTimeEnd === '') {
    openModal('<span style="color:red">报名截止时间不能为空</span>');
    return ;
  }
  if (description === '') {
    openModal('<span style="color:red">活动描述不能为空</span>');
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
    openModal('<span style="color:red">投票截止时间不能早于投票开始时间</span>');
    return ;
  }
  if (signUpTimeStampStart >= signUpTimeStampEnd) {
    openModal('<span style="color:red">报名截止时间不能早于报名开始时间</span>');
    return ;
  }
  if (signUpTimeStampStart > voteTimeStampStart) {
    openModal('<span style="color:red">报名开始时间不能晚于投票开始时间</span>');
    return ;
  }
  if (signUpTimeStampEnd > voteTimeStampEnd) {
    openModal('<span style="color:red">报名截止时间不能晚于投票截止时间</span>');
    return ;
  }

  // 判断用户是够勾选同意协议
  if (!$('#lisence').is(':checked')) {
    openModal('<span style="color:red">请勾选`我同意投票服务条款`</span>');
    return ;
  }
  
  // 向服务器提交数据
  $.ajax({
    url: 'v2/create',
    type: 'POST',
    data: {
      'title': title,
      'imgAddr': imgAddr,
      'suffix': suffix,
      'quantifier': quantifier,
      'maxium': maxium,
      'voteTimeStart': voteTimeStart,
      'voteTimeEnd': voteTimeEnd,
      'signUpTimeStart': signUpTimeStart,
      'signUpTimeEnd': signUpTimeEnd,
      'description': description,
    },
    contentType: 'json',
    dataType: 'json',
    success: function(data) {
      console.log(data);
    } ,
    error: function(err) {
      console.error(err);
    }
  });
}


function openModal(message) {
  $('#commonModalBody').html(message);
  $('#commonModal').modal();
}
