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
