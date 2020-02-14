/**
 * 删除投票
 * @param {String} aid 活动ID
 */
function deleteActivity(aid) {
  const status = confirm("您确定删除此投票吗？此操作不可逆！！！");

  if (status) {
    showMsg('info', '正在删除此投票...', -1);
    get(`/api/vote/activity/remove?aid=${aid}`)
      .then(data => {
        if (!(data.code % 100)) {
          showMsg('success', data.codeDesc);
          setTimeout(() => {
            window.location.reload();
          },0)
        } else {
          showMsg('error', data.codeDesc);
        }
      })
      .catch(err => {
        console.error(err);
      });
  }
}
