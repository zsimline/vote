/**
 * 删除投票
 * @param {String} aid 活动ID
 */
function deleteActivity(aid) {
  get(`v2/delete?aid=${aid}`)
  .then(data => {
    if (!(data.code % 100)) {
      openModal('success', '删除投票成功')
    } else {
      openModal('error', `${data.code} ${data.codeDesc}`);
    }
  })
  .catch(err => {
    openModal('error', '删除投票失败')
  });
}

