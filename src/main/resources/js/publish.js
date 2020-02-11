const postData = {
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
  options: null,
}

/**
 * 处理创建投票
 * 获取表单数据并校验
 */
function handleSubmit() {
  // 判断用户是够勾选同意协议
  if (!$('#lisence').is(':checked')) {
    openModal('error', '请勾选[我同意投票服务条款]');
    return ;
  }

  // 校验各个表单成功后
  // 上传表单中的数据
  if (verifyBaseConfig() && verifyImage() && verifyTime()) {
    uploadFile($('#img-main').prop('files')[0])
      .then(fileUrl => {
        postData.imgMain = fileUrl;
        uploadPostData(postData);
      })
      .catch(msg => {
        openModal('error', msg)
      }) 
  }
}

/**
 * 校验基本配置
 * 确保基本配置非空
 */
function verifyBaseConfig() {
  // 获取基本配置数据
  const title = $('#title').val();
  const suffix = $('#suffix').val();
  const quantifier = $('#quantifier').val();
  const maximum = $('#maxium').val();
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
    openModal('error', '投票标题不能为空');
    return false;
  }
  if (suffix === '') {
    openModal('error', '条目称谓不能为空');
    return false;
  }
  if (quantifier === '') {
    openModal('error', '条目量词不能为空');
    return false;
  }
  if (maximum === '') {
    openModal('error', '一次最多选择不能为空');
    return false;
  }
  if (summary === '') {
    openModal('error', '活动描述不能为空');
    return false;
  }

  postData.title = title;
  postData.suffix = suffix;
  postData.quantifier = quantifier;
  postData.maximum = maximum;
  postData.summary = summary;
  postData.options = options;

  return true;
}


/**
 * 校验图片
 * 确保图片非空且格式正确
 */
function verifyImage() {
  const imgMain = $('#img-main').val();
  if (imgMain === '') {
    openModal('error', '请上传宣传图片');
    return false;
  }

  // 检验图片文件大小
  // 当前的限制是最大为1M
  if ($('#img-main').prop('files')[0].size > 1048576) {
    openModal('error', '请上传小于1M的图片');
    return false;
  }

  // 校验图片文件格式
  // 当前的限制是只能为jpg或png格式
  if (!imgMain.endsWith('.jpg') && !imgMain.endsWith('.png')) {
    openModal('error', '图片只能为jpg或png格式');
    return false;
  }

  return true;
}

/**
 * 校验投票/报名的开始与结束时间
 * 确保时间非空且顺序正确
 */
function verifyTime() {
  // 获取投票/报名的开始与结束时间
  const voteTimeStart = $('#vote-time-start').val();
  const voteTimeEnd = $('#vote-time-end').val();
  const applyTimeStart = $('#apply-time-start').val();
  const applyTimeEnd = $('#apply-time-end').val();

  // 验证时间非空
  if (voteTimeStart === '') {
    openModal('error', '投票开始时间不能为空');
    return false;
  }
  if (voteTimeEnd === '') {
    openModal('error', '投票截止时间不能为空');
    return false;
  }
  if (applyTimeStart === '') {
    openModal('error', '报名开始时间不能为空');
    return false;
  }
  if (applyTimeEnd === '') {
    openModal('error', '报名截止时间不能为空');
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
    openModal('error', '投票截止时间不能早于投票开始时间');
    return false;
  }
  if (applyTimeStampStart >= applyTimeStampEnd) {
    openModal('error', '报名截止时间不能早于报名开始时间');
    return false;
  }
  if (applyTimeStampStart > voteTimeStampStart) {
    openModal('error', '报名开始时间不能晚于投票开始时间');
    return false;
  }
  if (applyTimeStampEnd > voteTimeStampEnd) {
    openModal('error', '报名截止时间不能晚于投票截止时间');
    return false;
  }

  postData.voteTimeStart = voteTimeStart;
  postData.voteTimeEnd = voteTimeEnd;
  postData.applyTimeStart = applyTimeStart;
  postData.applyTimeEnd = applyTimeEnd;

  return true;
}

/**
 * 上传报名数据
 * 
 * @param {Object} postData 报名数据
 */
function uploadPostData(postData) {
  postJSON('/api/vote/publish', postData)
    .then(data => {
      if (!(data.code % 100)) {
        openModal('success', data.codeDesc, () => {
          window.location.href = '/vote/manage';
        });
      } else {
        openModal('error', data.codeDesc);
      }
    })
    .catch(err => {
      console.error(err);
    });
}

/**
 * 上传文件
 * 
 * @param {File} file 文件
 * @return Promise
 */
function uploadFile(file) {
  const formData = new FormData();
  formData.append('file.jpg', file);

  return new Promise((resolve, reject) => {
    post('/api/vote/file_upload', formData)
      .then(data => {
        // 上传成功后返回其URL
        if (!(data.code % 100)) {
          resolve(data.codeDesc);
        } else {
          reject(data.codeDesc);
        }
      })
      .catch(err => {
        console.error(err);
      });
    });
}