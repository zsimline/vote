// 全局状态量
const glStatus = {
  aid: $('#aid').text(),
  lock: false
}

/**
 * 处理创建投票
 * 获取表单数据并校验
 */
function handlePublish() {
  if (glStatus.lock) return ;

  // 判断用户是够勾选同意协议
  if (!$('#lisence').is(':checked')) {
    showMsg('error', '请勾选[我同意投票服务条款]');
    return ;
  }

  // 校验各个表单成功后
  // 上传表单中的数据
  if (validateFactory.validate()) {
    glStatus.lock = true;
    uploadFile($('#img-main').prop('files')[0])
      .then(fileUrl => {
        validateFactory.postData.imgMain = fileUrl;
        uploadPublishPostData(validateFactory.postData);
      })
      .catch(msg => {
        showMsg('error', msg)
      })
  }
}

/**
 * 处理更新投票信息
 * 获取表单数据并校验
 */
function handleUpdate() {
  if (glStatus.lock) return ;

  // 不更新图片时禁止校验
  if ($('#img-main').val() === '') {
    validateFactory.functions[3] = () => true;
  } else {
    validateFactory.functions[3] = validateImgMain.bind(validateFactory);
  }

  // 校验各个表单成功后
  // 上传表单中的数据
  if (validateFactory.validate()) {
    glStatus.lock = true;
    if ($('#img-main').val() !== '') {
      uploadFile($('#img-main').prop('files')[0])
        .then(fileUrl => {
          validateFactory.postData.imgMain = fileUrl;
          uploadUpdatePostData(validateFactory.postData);
        })
        .catch(msg => {
          showMsg('error', msg)
        }) 
    } else {
      uploadUpdatePostData(validateFactory.postData);
    }
  }
}

/**
 * 初始化日期时间拾取器
 */
function initDatetimePicker() {
  const options = { locale: 'zh-cn', format: "YYYY-MM-DD hh:mm" };
  $('#vote-time-start').datetimepicker(options);
  $('#vote-time-end').datetimepicker(options);
  $('#apply-time-start').datetimepicker(options);
  $('#apply-time-end').datetimepicker(options);
};

/**
 * 校验基本配置
 * 确保基本配置非空
 */
function validateBaseConfig() {
  // 获取基本配置数据
  const title = $('#title').val();
  const suffix = $('#suffix').val();
  const quantifier = $('#quantifier').val();
  const maximum = $('#maxium').val();
  const reasonLength = $('#reason-length').val();
  const summary = tinyMCE.activeEditor.getContent();

  // 获取已经选中的单选按钮
  let options = ''
  const optCheckboxes = Array.from($('input[data-index]'));
  optCheckboxes.forEach(element => {
    if (element.checked) {
      options += element.dataset.index + ',';
    }
  });

  // 校验表单是否为空
  if (title === '') {
    showMsg('error', '投票标题不能为空');
    return false;
  }
  if (suffix === '') {
    showMsg('error', '条目称谓不能为空');
    return false;
  }
  if (quantifier === '') {
    showMsg('error', '条目量词不能为空');
    return false;
  }
  if (maximum === '') {
    showMsg('error', '一次最多选择不能为空');
    return false;
  }
  if (reasonLength === '') {
    showMsg('error', '理由最少字数不能为空');
    return false;
  }
  if (summary === '') {
    showMsg('error', '活动描述不能为空');
    return false;
  }

  this.postData.title = title;
  this.postData.suffix = suffix;
  this.postData.quantifier = quantifier;
  this.postData.maximum = maximum;
  this.postData.reasonLength = reasonLength;
  this.postData.summary = summary;
  this.postData.options = options;

  return true;
}

/**
 * 校验高级设置
 */
function validateAdvancedConfig() {
  this.postData.externalApply = $('#external-apply').is(':checked');
  this.postData.havePrize = $('#have-prize').is(':checked');
  return true;
}

/**
 * 校验图片
 * 确保图片非空且格式正确
 */
function validateImgMain(update=false) {
  if (update) return true;
  const imgMain = $('#img-main').val();
  if (imgMain === '') {
    showMsg('error', '请上传宣传图片');
    return false;
  }

  // 检验图片文件大小
  // 当前的限制是最大为1M
  if ($('#img-main').prop('files')[0].size > 1048576) {
    showMsg('error', '请上传小于1M的图片');
    return false;
  }

  // 校验图片文件格式
  // 当前的限制是只能为jpg或png格式
  if (!imgMain.endsWith('.jpg') && !imgMain.endsWith('.png')) {
    showMsg('error', '图片只能为jpg或png格式');
    return false;
  }

  return true;
}

/**
 * 校验投票/报名的开始与结束时间
 * 确保时间非空且顺序正确
 */
function validateTime() {
  // 获取投票/报名的开始与结束时间
  const voteTimeStart = $('#vote-time-start').val();
  const voteTimeEnd = $('#vote-time-end').val();
  const applyTimeStart = $('#apply-time-start').val();
  const applyTimeEnd = $('#apply-time-end').val();

  // 验证时间非空
  if (voteTimeStart === '') {
    showMsg('error', '投票开始时间不能为空');
    return false;
  }
  if (voteTimeEnd === '') {
    showMsg('error', '投票截止时间不能为空');
    return false;
  }
  if (applyTimeStart === '') {
    showMsg('error', '报名开始时间不能为空');
    return false;
  }
  if (applyTimeEnd === '') {
    showMsg('error', '报名截止时间不能为空');
    return false;
  }

  // 将本地时间转换为时间戳
  const voteTimeStampStart = moment(voteTimeStart).valueOf();
  const voteTimeStampEnd = moment(voteTimeEnd).valueOf();
  const applyTimeStampStart = moment(applyTimeStart).valueOf();
  const applyTimeStampEnd = moment(applyTimeEnd).valueOf();

  // 校验时间
  // 使时间顺序正常
  if (voteTimeStampStart >= voteTimeStampEnd) {
    showMsg('error', '投票截止时间不能早于投票开始时间');
    return false;
  }
  if (applyTimeStampStart >= applyTimeStampEnd) {
    showMsg('error', '报名截止时间不能早于报名开始时间');
    return false;
  }
  if (applyTimeStampStart > voteTimeStampStart) {
    showMsg('error', '报名开始时间不能晚于投票开始时间');
    return false;
  }
  if (applyTimeStampEnd > voteTimeStampEnd) {
    showMsg('error', '报名截止时间不能晚于投票截止时间');
    return false;
  }

  this.postData.voteTimeStart = voteTimeStart;
  this.postData.voteTimeEnd = voteTimeEnd;
  this.postData.applyTimeStart = applyTimeStart;
  this.postData.applyTimeEnd = applyTimeEnd;

  return true;
}

/**
 * 上传活动数据
 * 
 * @param {Object} postData 活动数据
 */
function uploadPublishPostData(postData) {
  showMsg('info', '上传投票信息中...', -1);
  postJSON('/api/vote/activity/publish', postData)
    .then(data => {
      if (!(data.code % 100)) {
        showMsg('success', data.codeDesc);
        setTimeout(() => {
          window.location.href = '/vote/manage';
        },800)
      } else {
        glStatus.lock = false;
        showMsg('error', data.codeDesc);
      }
    })
    .catch(err => {
      console.error(err);
    });
}

/**
 * 上传活动的更新数据
 * 
 * @param {Object} postData 活动数据
 */
function uploadUpdatePostData(postData) {
  showMsg('info', '更新投票信息中...', -1);
  postJSON(`/api/vote/activity/update?aid=${glStatus.aid}`, postData)
    .then(data => {
      if (!(data.code % 100)) {
        showMsg('success', data.codeDesc);
        setTimeout(() => {
          window.location.reload();
        },800)
      } else {
        glStatus.lock = false;
        showMsg('error', data.codeDesc);
      }
    })
    .catch(err => {
      console.error(err);
    });
}

// 创建校验工厂
const validateFactory = {
  postData: {
    title:  null,
    imgMain: null,
    imgData: null,
    suffix: null,
    quantifier: null,
    maximum: null,
    voteTimeStart: null,
    voteTimeEnd: null,
    applyTimeStart: null,
    applyTimeEnd: null,
    summary: null,
    externalApply: null,
    reasonLength: null,
    havePrize: null,
    options: null,
  },
  validate: function() {
    for (let i = 0; i < this.functions.length; i++) {
      if (!this.functions[i]()) {
        return false;
      }
    }
    return true;
  }
}

validateFactory.functions = [
  validateBaseConfig.bind(validateFactory),
  validateTime.bind(validateFactory),
  validateAdvancedConfig.bind(validateFactory),
  validateImgMain.bind(validateFactory),
];

/**
 * 上传文件
 * 
 * @param {File} file 文件
 * @return Promise
 */
function uploadFile(file) {
  const formData = new FormData();
  formData.append('file.jpg', file);
  showMsg('info', '上传图片中...', -1);
  return new Promise((resolve, reject) => {
    post('/api/vote/file_upload', formData)
      .then(data => {
        // 上传成功后返回其URL
        if (!(data.code % 100)) {
          resolve(data.codeDesc);
        } else {
          glStatus.lock = false;
          reject(data.codeDesc);
        }
      })
      .catch(err => {
        console.error(err);
      });
    });
}

// 页面初始化
initDatetimePicker()
