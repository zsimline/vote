/**
 * 处理表单提交
 * 获取表单数据并校验
 */
function handleSubmit() {
  // 获取表单数据
  const aid = $('#aid').text();
  const title = $('#title').val();
  const imgName = $('#img-name').val();
  const description = tinyMCE.activeEditor.getContent();
  const name = $('#name').val();
  const sex = $(":radio:checked").val();
  const age = $('#age').val();
  const telephone = $('#telephone').val();
  const email = $('#email').val();
  const school = $('#school').val();
  const company = $('#company').val();
  const address = $('#address').val();

  // 校验表单是否为空
  if (title !==undefined && title === '') {
    openModal('error', '标题不能为空');
    return;
  }
  if (imgName !==undefined && imgName === '') {
    openModal('error', '请上传介绍图片');
    return;
  }
  if (description !==undefined && description === '') {
    openModal('error', '详细描述不能为空');
    return;
  }
  if (name !==undefined && name === '') {
    openModal('error', '真实姓名不能为空');
    return;
  }
  if (sex !==undefined && sex === '') {
    openModal('error', '真实性别不能为空');
    return;
  }
  if (age !==undefined && age === '') {
    openModal('error', '真实年龄不能为空');
    return;
  }
  if (telephone !==undefined && telephone === '') {
    openModal('error', '手机号码不能为空');
    return;
  }
  if (email !==undefined && email === '') {
    openModal('error', '电子邮件不能为空');
    return;
  }
  if (school !==undefined && school === '') {
    openModal('error', '学校名称不能为空');
    return;
  }
  if (company !==undefined && company === '') {
    openModal('error', '公司名称不能为空');
    return;
  }
  if (address !==undefined && address === '') {
    openModal('error', '收货地址不能为空');
    return;
  }

  // 判断用户是够勾选同意协议
  if (!$('#lisence').is(':checked')) {
    openModal('error', '请勾选`我同意投票服务条款`');
    return;
  }

  const formData = new FormData();
  formData.append('aid', aid);
  formData.append('title', title);
  formData.append('imgName', $('#img-name').prop('files')[0]);
  formData.append('description', description);

  if (name) {
    formData.append('name', name);
  }
  if (sex) {
    formData.append('sex', sex);
  }
  if (age) {
    formData.append('age', age);
  }
  if (telephone) {
    formData.append('telephone', telephone);
  }
  if (email) {
    formData.append('email', email);
  }
  if (school) {
    formData.append('school', school);
  }
  if (company) {
    formData.append('company', company);
  }
  if (address) {
    formData.append('address', address);
  }

  // 向服务器提交数据
  post('/api/vote/apply', formData)
    .then(data => {
      if (!(data.code % 100)) {
        openModal('success', data.codeDesc);
      } else {
        openModal('error', data.codeDesc);
      }
    })
    .catch(err => {
      openModal('error', '发布投票失败')
    });
}

function handleModalClose() {
}
