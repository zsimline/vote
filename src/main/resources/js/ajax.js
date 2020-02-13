/**
 * 请求JSON类型数据
 * 
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
 * 
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
      xhrFields: {
        withCredentials: true
      },
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
 * 向后端提交JSON数据
 * 
 * @param {string} url 提交到的地址
 * @param {object} data 提交的数据
 */
function postJSON(url, data) {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: url,
      type: 'POST',
      data: JSON.stringify(data),
      contentType: 'json',
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
 *
 * @param {string} type 消息类型
 * @param {string} message 消息内容
 * @param {function} cb 处理关闭模态框
 * @param {string} title 模态框标题
 */
function openModal(type, message, cb=()=>{}, title='消息提示') {
  let messageTyped = null;

  if (type === 'success') {
    messageTyped = `<span style="color:green">${message}</span>`;
  } else if (type === 'error') {
    messageTyped = `<span style="color:red">${message}</span>`;
  } else if (type === 'userdef') {
    messageTyped = message;
  }
  
  $('#commonModalButton').bind('click', cb);
  $('#commonModalBody').html(messageTyped);
  $('#commonModalLabel').html(title);
  $('#commonModal').modal();
}