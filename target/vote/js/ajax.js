/**
 * 请求JSON类型数据
 * @param {string} url 请求的资源地址
 */
function get(url) {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: url,
      type: 'GET',
      dataType: 'json',
      cache: false,
      success: data => {
        resolve(data);
      },
      error: err => {
        reject(err);
      }
    })
  });
}

/**
 * 向后端提交表单数据
 * @param {string} url 提交到的地址
 * @param {object} data 提交的数据
 */
function post(url, data) {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: url,
      type: 'POST',
      data: data,
      contentType: false,
      processData: false,
      dataType: 'json',
      cache: false,
      success: data => {
        resolve(data);
      },
      error: err => {
        reject(err);
      }
    })
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
