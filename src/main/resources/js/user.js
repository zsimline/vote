/**
 * 处理与用户相关的操作
 * 如用户注册、登录、更改密码等
 */

/**
 * 处理用户注册
 * 校验表单并向后端发送数据
 */
function handleRegister() {
  // 获取表单数据
  const password = $('#password').val();
  const email = $('#email').val();
  const organization = $('#organization').val();

  if (password === '') {
    showMsg('error', '密码不能为空');
    return;
  }
  if (email === '') {
    showMsg('error', '电子邮件不能为空');
    return;
  }
  if (organization === '') {
    showMsg('error', '所属组织不能为空');
    return;
  }

  const postData = {
    'password': password,
    'email': email,
    'organization': organization
  };

  showMsg('info', '注册中请等待...', -1);
  // 向服务器提交数据
  postJSON('/api/user/register', postData)
    .then(data => {
      if (!(data.code % 100)) {
        showMsg('success', data.codeDesc, -1);
        setTimeout(() => {
          window.location.href="/user/login";
        }, 1200)
      } else {
        showMsg('error', data.codeDesc);
      }
    })
    .catch(err => {
      console.error(err);
    });
}

/**
 * 处理用户登录
 * 校验表单并向后端发送数据
 */
function handleLogin() {
  const email = $('#email').val();
  const password = $('#password').val();
  
  if (email === '') {
    showMsg('error', '电子邮件不能为空');
    return;
  }
  if (password === '') {
    showMsg('error', '密码不能为空');
    return;
  }

  const postData = {    
    'email': email,
    'password': password
  };
  
  // 向服务器提交数据
  showMsg('info', '正在登录..')
  postJSON('/api/user/login', postData)
    .then(data => {
      if (!(data.code % 100)) {
        showMsg('success', data.codeDesc);
        setTimeout(() => {
          window.location.href = window.location.href.split('referer=')[1] || '/index';
        }, 400);
      } else {
        showMsg('error', data.codeDesc);
      }
    })
    .catch(err => {
      console.error(err);
    });
}

/**
 * 处理账户激活
 */
function handleActivation() {
  const activationAddress = `/api/user/activation?email=${$('#email').text()}&code=${$('#code').text()}`;
  showMsg('info', '认证中请稍等...')
  get(activationAddress)
    .then(data => {
      if (!(data.code % 100)) {
        $('.activation:first').removeClass('hidden');
      } else {
        $('.activation:last').removeClass('hidden');
      }
      hideMsg();
    })
    .catch(err => {
      console.log(err);
    });
}

function resetPassword() {
  const email = $('#pwdforgetMoalBody input').val();
  if (email === '') {
    showMsg("请输入正确的电子邮件地址"); return ;
  }

  get(`/api/user/pwdforget?email=${email}`)
    .then(data => {
      if (!(data % 100)) {
        showMsg('success', data.codeDesc);
      } else {
        showMsg('error', data.codeDesc);
      }
    })
    .catch(err  => {
      console.error(err);
    })
}

function updateUserInfo() {
  const postData = {
    id: $('#uid').text(),
    password: $('#password').val(),
    organization: $('#organization').val(),
  }

  if (postData.id === '') {
    showMsg('error', '未知uid'); return ;
  } else if (postData.organization === '') {
    showMsg('error', '所属组织不能为空');
  } else if (postData.password === '') {
    showMsg('error', '用户密码不能为空');
  } else {
    postJSON(`/api/user/info_update`, postData)
      .then(data => {
        if (!(data % 100)) {
          showMsg('success', data.codeDesc);
          setTimeout(() => {
            window.location.href = '/user/login'
          }, 400);
        } else {
          showMsg('error', data.codeDesc);
        }
      })
  }
}