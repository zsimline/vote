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
    })
    .done(data => {
      resolve(data);
    })
    .fail(err => {
      reject(err);
    })
  });
}
