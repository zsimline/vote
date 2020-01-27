function handleRegister() {
  // 获取表单数据
  const nickname = $('#nickname').val();
  const password = $('#password').val();
  const email = $('#email').val();
  const organization = $('#organization').val();

  // 校验表单是否为空
  if (nickname === '') {
    openModal('error', '昵称不能为空');
    return;
  }
  if (password === '') {
    openModal('error', '密码不能为空');
    return;
  }
  if (email === '') {
    openModal('error', '电子邮件不能为空');
    return;
  }
  if (organization === '') {
    openModal('error', '所属组织不能为空');
    return;
  }

  // TODO - 密码强度校验
  // TODO - 电子邮件合法性校验

  const postData = {
    'nickname': nickname,
    'password': password,
    'email': email,
    'organization': organization
  };

  // 向服务器提交数据
  postJSON('/api/user/register', JSON.stringify(postData))
    .then(data => {
      if (!(data.code % 100)) {
        openModal('success', data.codeDesc);
      } else {
        openModal('error', data.codeDesc);
      }
    })
    .catch(err => {
      openModal('error', '注册账户失败')
    });
}


function handleLogin() {
  const email = $('#email').val();
  const password = $('#password').val();
  
  if (email === '') {
    openModal('error', '电子邮件不能为空');
    return;
  }
  if (password === '') {
    openModal('error', '密码不能为空');
    return;
  }

  const postData = {    
    'email': email,
    'password': password
  };

  // 向服务器提交数据
  postJSON('/api/user/login', JSON.stringify(postData))
    .then(data => {
      if (!(data.code % 100)) {
        openModal('success', data.codeDesc);
        setTimeout(() => {
          window.location.href = "/"
        }, 1800)
      } else {
        openModal('error', data.codeDesc);
      }
    })
    .catch(err => {
      openModal('error', '登录账户失败')
    });
}
