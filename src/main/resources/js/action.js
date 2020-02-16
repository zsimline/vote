// 全局状态量
const glStatus = {
  ids: new Set(),
  entrys: [],
  maximum: parseInt($('#maximum').text()),
  nextPage: 1,
  lock: false,
  aid: $('#aid').text(),
  explainReason: $('#explain-reason').text(),
  havePrize: $('#have-prize').text()
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
    if (element.isFreeze) return ;
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
    $('#entry-container').append(template);
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
    alert('超出最多选择的数量')
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
    showTip('数据加载中...');
  } else {
    if (!glStatus.lock) {
      showTip('已经到底了～');
    }
  }
});


/**
 * 从服务器请求更多的条目数据
 * 并将获取到条目数据添加到页面中
 */
function loadMoreEntry() {
  get(`/api/vote/data/entry?aid=${glStatus.aid}&page=${glStatus.nextPage}`)
    .then(data => {
      flushEntrys(data);
      appendEntry(data);
      glStatus.nextPage = data.length === 20 ? ++glStatus.nextPage : -1;
      glStatus.lock = false;
      hideTip();
    });
}

/**
 * 搜索条目
 */
function searchEntry() {
  const  searchContent = $('#search-content').val();
  if (searchContent === '')  {
    showMsg('error', '搜索内容不能为空', 1500); return ;
  }

  const searchType = parseInt(searchContent) ? 'number' : 'title';
  get(`/api/vote/entry/search?aid=${glStatus.aid}&type=${searchType}&content=${searchContent}`)
    .then(data => {
      if (data.length == 0) {
        showMsg('info', '没有查找到相关内容', 1500);
      } else {
        glStatus.lock = true;
        $('#entry-container').empty();
        flushEntrys(data);
        appendEntry(data);
      }
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

function handleExplainReason() {
  openModal('userdef', '<textarea id="reason">', '请阐述投票理由', () => {
    const reason = $('#reason').val();
    if (reason === '') {
      showMsg('error', '理由不能为空'); return false;
    } else if (reason.length < 10) {
      showMsg('error', '理由不能少于10个字符'); return false;
    } else {
      return true;
    }
  })
}

/**
 * 处理提交投票
 */
function handleSubmit() {
  const postData = {
    aid: glStatus.aid,
    ids: Array.from(glStatus.ids),
  }

  // 发布者设置了阐述理由
  if (glStatus.explainReason === 'true') {
    alert('xxxx');
    if (!handleExplainReason()) return ;
    postData.reason = $('#reason').val();
  }

  postJSON('/api/vote/action',  postData)
    .then(data => {
      if (!(data.code % 100)) {
        alert(data.codeDesc)
        window.location.reload();
      } else {
        alert(data.codeDesc);
      }
    });
}

// 页面初始化
glStatus.lock = true;
loadMoreEntry();
