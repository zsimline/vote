/**
 * 报名管理相关操作
 */

// 报名选项
const applyOptions = {
  title: {
    selector: "#title",
    errTip: "标题不能为空", 
  },
  name: {
    selector: "#name",
    errTip: "真实姓名不能为空",
  },
  sex: {
    selector: "#sex",
    errTip: "真实性别不能为空",
  },
  age: {
    selector: "#age",
    errTip: "真实年龄不能为空",
  },
  telephone: {
    selector: "#telephone",
    errTip: "手机号码不能为空",
  },
  email: {
    selector: "#email",
    errTip: "电子邮件不能为空",
  },
  school: {
    selector: "#school",
    errTip: "学校名称不能为空",
  },
  company: {
    selector: "#company",
    errTip: "公司名称不能为空",
  },
  address: {
    selector: "#address",
    errTip: "收货地址不能为空",
  },
};

// 与服务器同步的表格数据 
let tableData = null;

// 表格配置选项
const tbOpts = {
  id: "table",
  operation: "editer",
  type: "numbers",
  colum: generateColum(),
  data: [],
  activeTr: -1,
}

function fetchTableData() {
  get(`/api/vote/data_apply?aid=${$('#aid').text()}&status=${$('#status').text()}&page=${$('#page').text()}`)
    .then(data => {
      initTableData(data);
      initTable(tableData);
    })
    .catch(err => {
      console.error(err);
    });
}

/**
 * 获取页面中隐藏标签的数据
 * 根据数据生成表格的列
 * 
 * @return 表格列数组
 */
function generateColum() {
  const columns = [
    { "field": "index", "title": "index" },
    { "field": "id", "title": "编号" },
    { "field": "title", "title": "标题" },
  ];
  
  const tableColumns = Array.from($('.table-column'));
  tableColumns.forEach(element => {
    columns.push({
      "field": element.dataset.field, 
      "title": element.dataset.title
    });
  });

  return columns;
}

/**
 * 初始化表格数据
 *
 * @param {Onject[]} data 从服务器返回的原始数据
 */

function initTableData(data) {
  data.forEach((element, index) => {
    element.index = index;
  });
  tableData = data;
}

/**
 * 初始化表格结果
 * 同时填充表格数据
 * 
 * @param {object[]} tableData 表格行数据数组
 */
function initTable(tableData) {
  // 初始化表格结构
  $("#table").initialize(tbOpts);

  // 需要填充到表格中的数据
  const data = JSON.parse(JSON.stringify(tableData));

  // 填充表格数据
  data.forEach((element, index) => {
    if (element.imgEntry) {
      element.imgEntry = `<img src=${element.imgEntry} title="点击我查看大图" class="table-img" onclick="showBigImage(${index})">`;
    }
    if (element.introduction) {
      element.introduction = `<a href="javascript:showDescription(${index})" title="点击我查看详细描述">查看</a>`
    }
  });
  basicTable.reload(data, 'table');
  tbOpts.data = data;
}

/**
 * 刷新表格
 * 
 * @param {Object} resData 响应数据
 */
function flushTable(resData, append=false) {
  if (append) {
    tbOpts.activeTr = tableData.length;
  }
  resData.index = tbOpts.activeTr;
  tableData[tbOpts.activeTr] = resData;
  initTable(tableData);
}

/**
 * 新增或更新报名信息
 */
function saveOrUpdate() {
  if (tbOpts.activeTr == -1) {
    handleApplyInfoAdded();
  } else {
    handleApplyInfoChanged();
  }
}

/**
 * 为更新报名做准备
 * 同步要编辑的行中的数据到编辑框中
 * 
 * @param {ThisType} which 那一行
 */
function readyToUpdate(which) {
  clearEditModal();

  const tr = $(which).parent().parent();

  // 获取当前行数据，当前行索引存储在隐藏列中
  const rowData = tableData[tr.children().first().text()]
  
  // 同步表格行中的值到编辑框
  Object.keys(applyOptions).forEach(key => {
    $(applyOptions[key].selector).val(rowData[key]);
  })

  // 如果存在详细介绍
  // 则将详细介绍的内容同步到富文本编辑器中
  if(window.tinyMCE !== undefined) {
    tinyMCE.activeEditor.setContent(rowData.introduction);
  }

  // 设置活动行为当前编辑行
  tbOpts.activeTr = rowData.index;

  $('#editModal').modal();
}

/**
 * 为新增报名做准备
 */
function readyToAppend() {
  // 清空并打开编辑框
  clearEditModal();
  $('#editModal').modal();

  // 活动行为-1时说明此时在新增行
  tbOpts.activeTr = -1;
}

/**
 * 处理报名信息变更
 * 校验、封装新的报名数据
 */
function handleApplyInfoChanged() {
  if (!checkTitle()) return ;

  const formData = new FormData();

  Object.keys(applyOptions).forEach(key => {
    const value = $(applyOptions[key].selector).val();
    if (value !== undefined) {
      formData.append(key, value);
    }
  });

  if ($('#img-entry').val() !== '') {
    formData.append('imgEntry', $('#img-entry').prop('files')[0]);
  }
  if (tableData[tbOpts.activeTr].introduction) {
    formData.append('introduction', tinyMCE.activeEditor.getContent());
  }

  uploadUpadteData(formData, tableData[tbOpts.activeTr].id);
}

/**
 * 处理报名信息新增
 * 校验、封装新的报名数据
 */
function handleApplyInfoAdded() {
  const formData = new FormData();

  Object.keys(applyOptions).forEach(key => {
    const value = $(applyOptions[key].selector).val();
    if (value !== undefined) {
      formData.append(key, value);
    }
  });

  if (checkTitle(formData) && checkImgEntry(formData) 
      && checkIntroduction(formData)) {
    uploadAppendData(formData);
  }
}

/**
 * 上传报名更新数据
 * 
 * @param {FormData} formData
 * @param {string} id
 */
function uploadUpadteData(formData, id) {
  post(`/api/vote/apply_update?aid=${$('#aid').text()}&id=${id}`, formData)
    .then(data => {
      if (!data.code) {
        flushTable(data);
      } else {
        openModal('error', data.codeDesc);
      }
    })
    .catch(err => {
      console.error(err);
    });
}

/**
 * 上传新增报名数据
 * 
 * @param {FormData} formData
 */
function uploadAppendData(formData) {
  post(`/api/vote/apply?aid=${$('#aid').text()}`, formData)
    .then(data => {
      if (!data.code) {
        flushTable(data, true);
      } else {
        openModal('error', data.codeDesc);
      }
    })
    .catch(err => {
      console.log(err);
    });
}

/**
 * 校验标题是否为空 
 * 
 * @return true/false 校验成功/失败
 */
function checkTitle() {
  const title = $('#title').val();
  if (title === '') {
    openModal('error', '标题不能为空');
    return false;
  }

  return true;
}

/**
 * 校验详细介绍是否为空
 * 校验通过后将数据追加到容器中
 *
 * @return true/flase 校验成功/失败
 */
function checkIntroduction(formData) {
  if (window.tinyMCE === undefined) return true;

  const introduction = tinyMCE.activeEditor.getContent();
  if (introduction === '') {
    openModal('error', '详细介绍不能为空');
    return false;
  } else {
    formData.append('introduction', introduction);
    return true;
  }
}

/**
 * 校验参赛图片是否为空
 * 校验通过后将数据追加到容器中
 *
 * @return true/false 校验成功/失败
 */
function checkImgEntry(formData) {
  const imgEntry = $('#img-entry').val();
  if (imgEntry === undefined) {
    return true;
  } else if (imgEntry === '') {
    openModal('error', '参赛图片不能为空');
    return false;
  } else {
    formData.append('imgEntry', $('#img-entry').prop('files')[0]);
    return true;
  }
}

/**
 * 导出表格为Excel
 */
function exportExcel() {
  const excelData = JSON.parse(JSON.stringify(tbOpts));
  basicTable.JSONToCSVConvertor(excelData, true, "报名信息表");
}

/**
 * 显示详细介绍
 * 
 * @param {number} index 表格行索引
 */
function showDescription(index) {
  openModal('userdef', tableData[index].introduction);
}

/**
 * 显示图片大图
 * 
 * @param {number} index  表格行索引
 */
function showBigImage(index) {
  const img = `<img src="${tableData[index].imgEntry}">`
  openModal('userdef', img);
}

/**
 * 清空编辑框中内容
 */
function clearEditModal() {
  Object.values(applyOptions).forEach(element => {
    $(element.selector).val('');
  })

  // 清空文件表单中的内容
  $('#img-entry').val('')

  // 清空富文本编辑器中内容
  if (window.tinyMCE !== undefined) {
    tinyMCE.activeEditor.setContent('');
  }
}

/**
 * 当用户输入要跳转的页码时，
 * 跳转到指定的页面
 */
function jumpByPage() {
  const to = $('#page-jump').val();
  if (to === '') {
    openModal('error', '请输入要跳转到的页码数');
  } else {
    window.location.href = `/vote/apply_manage?aid=${$('#aid').text()}&status=${$('#status').text()}&page=${to}`;
  }
}

/**
 * 当用户切换审核状态时，
 * 跳转到对应状态的第一页
 * 
 * @param {string} status 审核状态
 */
function jumpByStatus(status) {
  window.location.href = `/vote/apply_manage?aid=${$('#aid').text()}&status=${status}&page=1`;
}

function approveApply(which) {
  const tr = $(which).parent().parent();
  const rowData = tableData[tr.children().first().text()]

  get(`/api/vote/review?aid=${$('#aid').text()}&id=${rowData.id}&status=y`)
    .then(data => {
      if (!(data.code % 100)) {
        delete tableData[tr.children().first().text()];
        flushTable()
      }
      console.log(data);
    })
}

fetchTableData();
