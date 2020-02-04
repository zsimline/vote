// 报名选项
const applyOptions = {
  title: {
    selector: "#title",
    errTip: "标题不能为空", 
  },
  name: {
    selector: "#name",
    errTip: "真实姓名不能为空",
  },
  sex: {
    selector: "input[name='sex']:checked",
    errTip: "真实性别不能为空",
  },
  age: {
    selector: "#age",
    errTip: "真实年龄不能为空",
  },
  telephone: {
    selector: "#telephone",
    errTip: "手机号码不能为空",
  },
  email: {
    selector: "#email",
    errTip: "电子邮件不能为空",
  },
  school: {
    selector: "#school",
    errTip: "学校名称不能为空",
  },
  company: {
    selector: "#company",
    errTip: "公司名称不能为空",
  },
  address: {
    selector: "#address",
    errTip: "收货地址不能为空",
  },
};

/**
 * 处理表单提交
 * 获取表单数据并校验
 */
function handleSubmit() {
  // 判断用户是够勾选同意协议
  if (!$('#lisence').is(':checked')) {
    openModal('error', '请勾选 [我同意投票服务条款]');
    return ;
  }

  // 校验表单并追加数据
  checkFactory.formData = new FormData();  
  if (checkFactory.check()) {
    uploadData(checkFactory.formData);
  }
}

/**
 * 校验详细介绍是否为空
 * 校验通过后将数据追加到容器中
 *
 * @return true/flase 校验成功/失败
 */
function checkIntroduction() {
  if (window.tinyMCE === undefined) {
    return true;
  }

  const introduction = tinyMCE.activeEditor.getContent();
  if (introduction === '') {
    openModal('error', '详细介绍不能为空');
    return false;
  } else {
    this.formData.append('introduction', introduction);
    return true;
  }
}

/**
 * 校验参赛图片是否为空
 * 校验通过后将数据追加到容器中
 *
 * @return true/false 校验成功/失败
 */
function checkImgEntry() {
  const imgEntry = $('#img-entry').val();
  if (imgEntry === undefined) {
    return true;
  } else if (imgEntry === '') {
    openModal('error', '参赛图片不能为空');
    return false;
  } else {
    this.formData.append('imgEntry', $('#img-entry').prop('files')[0]);
    return true;
  }
}

/**
 * 校验其它可选项是否为空
 * 校验通过后将数据追加到容器中
 *
 * @return true/false 校验成功/失败
 */
function checkApplyOptions() {
  const keys =  Object.keys(applyOptions);
  for (let i = 0; i < keys.length; i++) {
    const value = $(applyOptions[keys[i]].selector).val();
    if (value === undefined) {
      continue;
    } else if (value === '') {
      openModal('error', applyOptions[keys[i]].errTip);
      return false;
    } else {
      this.formData.append(keys[i], value);
    }
  }

  return true;
}

// 创建校验工厂
const checkFactory = {
  formData: null,
  check: function() {
    for (let i = 0; i < this.functions.length; i++) {
      if (!this.functions[i]()) {
        return false;
      }
    }
    return true;
  }
}
// 绑定校验函数
// 使校验函数的this指针指向checkFactory
checkFactory.functions = [
  checkImgEntry.bind(checkFactory),
  checkIntroduction.bind(checkFactory),
  checkApplyOptions.bind(checkFactory),
];

/**
 * 上传报名数据
 * 
 * @param {FormData} formData
 */
function uploadData(formData) {
  post(`/api/vote/apply?aid=${$('#aid').text()}`, formData)
    .then(data => {
      if (!data.code) {
        console.log(data);
      } else {
        openModal('error', data.codeDesc);
      }
    })
    .catch(err => {
      console.error(err);
    });
}
