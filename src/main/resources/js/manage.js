/**
 * 删除投票
 * @param {String} aid 活动ID
 */
function deleteActivity(aid) {
  const status = confirm("您确定删除此投票吗？此操作不可逆！！！");

  if (status) {
    get(`/api/vote/delete?aid=${aid}`)
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
}
