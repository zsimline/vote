// 全局状态量
const glStatus = {
  ids: new Set(),
  entrys: [],
  maximum: 3,
  nextPage: 1,
  lock: false,
}

/**
 * 切换活动简介显示
 * 
 * @param {ThisType} which 箭头
 */
function switchSummaryShow(which) {
  const acSummary = $('#ac-summary');
  if (acSummary.hasClass('hidden')) {
    acSummary.removeClass('hidden');
    switchCssClass(which, 'fa-angle-double-down', 'fa-angle-double-up');
  } else {
    acSummary.addClass('hidden');
    switchCssClass(which, 'fa-angle-double-up', 'fa-angle-double-down');
  }
}

/**
 * 利用innerHTML的方式添加条目数据到页面中
 * 
 * @param {Array} data 条目数组
 */
function appendEntry(data) {
  data.forEach(element=> {
    if (element.introduction) {
      var htmlTitle = `<h3 class="entry-title" onclick="showIntroduction(${element.index})">${element.number}. ${element.title}</h3>`;
      var htmlImgEntry = element.imgEntry ? `<img src="${element.imgEntry}" onclick="showIntroduction(${element.index})">`  : `<img src="${element.imgEntry}">`;
    } else {
      var htmlTitle = `<span class="entry-title>${element.number}. ${element.title}</span>`;
      var htmlImgEntry = element.imgEntry ? `<img src="${element.imgEntry}">` : '';
    }

    const template = `
      <div class="entry">        
        ${htmlImgEntry}
        ${htmlTitle}
        <button class="btn btn-inverse" onclick="handleSelect(${element.id},this)">
          <i class="fa fa-check""></i> 选择
        </button>
        <span class="entry-acquisition">${element.acquisition}票</span>
      </div>
    `
    $('#container-entry').append(template);
  });
}

/**
 * 处理选择
 * 
 * @param {id} id 条目编号
 * @param {ThisType} which 按钮
 */
function handleSelect(id, which) {
  if (glStatus.ids.has(id)) {
    glStatus.ids.delete(id);
    switchCssClass(which, 'btn-default', 'btn-inverse');
    $(which).html('<i class="fa fa-check""></i> 选择');
    $('#tool-box span em').text(`${glStatus.ids.size}`)
  } else if (glStatus.ids.size + 1 > glStatus.maximum) {
    openModal('error', '超出最多选择的数量');
  } else {
    glStatus.ids.add(id);
    switchCssClass(which, 'btn-inverse', 'btn-default');
    $(which).html('<i class="fa fa-check"></i> 已选');
    $('#tool-box span em').text(`${glStatus.ids.size}`)
  }
}

/**
 * 切换标签上的CSS类
 * 
 * @param {ThisType} which 要切换的标签
 * @param {string} from 现在的类名
 * @param {srring} to 要切换到的类名
 */
function switchCssClass(which, from, to) {
  $(which).removeClass(from);
  $(which).addClass(to);
} 

// 为页面添加滚动条事件，当页面滚动到接近底部时，
// 加载并添加更多的条目到页面中
$(window).on('scroll', () => {
  const offsetTop = document.documentElement.offsetHeight - window.pageYOffset;
  
  // 满足加载更多条目的条件是当前不是最后一页
  // 且上一次加载已经完成
  if (offsetTop < 1200 && glStatus.nextPage != -1 && !glStatus.lock) {
    glStatus.lock = true;
    loadMoreEntry();
  }
});

/**
 * 从服务器请求更多的条目数据
 * 并将获取到条目数据添加到页面中
 */
function loadMoreEntry() {
  get(`/api/vote/data_entry?aid=${$('#aid').text()}&page=${glStatus.nextPage}`)
    .then(data => {
      flushEntrys(data);
      appendEntry(data);
      glStatus.nextPage = data.length === 20 ? ++glStatus.nextPage : -1;
      glStatus.lock = false;
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

/**
 * 处理提交投票
 */
function handleSubmit() {
  const postData = {
    aid: $('#aid').text(),
    ids: Array.from(glStatus.ids)
  }

  postJSON('/api/vote/action',  postData)
    .then(data => {
      console.log(data);
    });
}

// 页面初始化
glStatus.lock = true;
loadMoreEntry();
