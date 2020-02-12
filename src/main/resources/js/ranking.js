// 全局状态量
const glStatus = {
  entrys: [],
  nextPage: 1,
  lock: false,
}

/**
 * 从服务器请求更多的条目数据
 * 并将获取到条目数据添加到页面中
 */
function loadMoreEntry() {
  get(`/api/vote/data_ranking?aid=${$('#aid').text()}&page=${glStatus.nextPage}`)
    .then(data => {
      flushEntrys(data);
      appendEntry(data);
      glStatus.nextPage = data.length === 50 ? ++glStatus.nextPage : -1;
      glStatus.lock = false;
    });
}

function appendEntry(data) {
  data.forEach(element=> {
    if (element.introduction) {
      var htmlTitle = `<span class="entry-title" onclick="showIntroduction(${element.index})">${element.number}. ${element.title}</span>`
    } else {
      var htmlTitle = `<span class="entry-title">${element.number}. ${element.title}</span>`
    }

    const template = `<div class="ranking">
      <span class="rid rid-color-${element.index % 7}">${element.index + 1}</span>
      ${htmlTitle}
      <span class="entry-acquisition">取得 ${element.acquisition} 票</span>
      </div>
    `

    $('#container-ranking').append(template);
  });
}

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
  openModal('userdef', glStatus.entrys[index].introduction);
}


loadMoreEntry();
