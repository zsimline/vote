// 全局状态量
const glStatus = {
  entrys: [],
  nextPage: 1,
  lock: false,
  aid: $('#aid').text()
}

/**
 * 从服务器请求更多的条目数据
 * 并将获取到条目数据添加到页面中
 */
function loadMoreEntry() {
  if (glStatus.nextPage === -1) {
    alert('没有更多数据了'); return ; 
  }
  showTip('数据加载中...');

  get(`/api/vote/data/ranking?aid=${glStatus.aid}&page=${glStatus.nextPage}`)
    .then(data => {
      flushEntrys(data);
      appendEntry(data);
      glStatus.lock = false;
      if (data.length === 50) {
        ++glStatus.nextPage;
        hideTip();
      } else {
        glStatus.nextPage = -1;
        showTip('已经到底了～');
      }
    });
}

/**
 * 利用innerHTML的方式添加排名数据到页面中
 * 
 * @param {Array} data 条目数组
 */
function appendEntry(data) {
  data.forEach(element=> {
    if (element.introduction) {
      var htmlTitle = `<span class="ranking-title" onclick="showIntroduction(${element.index})">${element.number}. ${element.title}</span>`
    } else {
      var htmlTitle = `<span class="ranking-title">${element.number}. ${element.title}</span>`
    }

    const template = `<div class="ranking">
      <span class="rid rid-color-${element.index % 7}">${element.index + 1}</span>
      ${htmlTitle}
      <span class="ranking-acquisition">取得 ${element.acquisition} 票</span>
      </div>
    `

    $('#container-ranking').append(template);
  });
}

/**
 * 将最新获得的条目建立索引
 * 并将这些数据添加进条目数组总
 * 
 * @param {Array} data 请求到的条目数组
 */
function flushEntrys(data) {
  let index = glStatus.entrys.length;
  data.forEach(element => {
    element.index = index++;
  });
  glStatus.entrys.push.apply(glStatus.entrys, data);
}

/**
 * 显示详细介绍
 * 
 * @param {id} index 条目索引
 */
function showIntroduction(index) {
  openModal('userdef', glStatus.entrys[index].introduction, glStatus.entrys[index].title);
}

// 页面初始化
glStatus.lock  = true;
loadMoreEntry();
