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
    selector: "input[name='sex']:checked",
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
  introductions: [],
  images: [],
  activeTr: 0,
}

function fetchTableData() {
  get('/api/vote/data_apply?aid=ef3d7491468d4549a7516911703d7dfb')
  .then(data => {
    initTable(data);
  })
  .catch(err => {
    console.error(err);
  });
}

fetchTableData();

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
    { "field": "status", "title": "审核状态" },
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

$("#table").initialize(tbOpts);

/**
 * 初始化表格并填充表格数据
 * 
 * @param {object[]} data 表格行数据数组
 */
function initTable(data) {
  data.forEach((element, index) => {
    element.index = index;
  })
  tableData = JSON.parse(JSON.stringify(data));

  // 填充表格数据
  data.forEach((element, index) => {
    if (element.imgEntry) {
      element.imgEntry = `<img src=${element.imgEntry} title="点击我查看大图" class="table-img" onclick="showImage(${index})">`;
    }
    if (element.introduction) {
      element.introduction = `<a href="javascript:showDescription(${index})" title="点击我查看详细描述">查看</a>`
    }
  })
  tbOpts.data = data;
  basicTable.reload(tbOpts.data, 'table');
}

/**
 * 刷新单一行数据
 * 
 * @param {string} rowDataStr 行数据字符串
 * @param {string} append 是否为追加数据
 */
 function flushSingleRow(rowDataStr, append=false) {
  const rowData = JSON.parse(rowDataStr);

  if (rowData.imgEntry) {
    rowData.imgEntry = `<img src=${rowData.imgEntry} title="点击我查看大图" class="table-img" onclick="showImage(${rowData.index})">`;
  }
  if (rowData.introduction) {
    rowData.introduction = `<a href="javascript:showDescription(${rowData.index})" title="点击我查看详细描述">查看</a>`
  }
  
  if (append) {
    tbOpts.data.push(rowData);
  } else {
    tbOpts.data[rowData.index] = rowData;
  }

  basicTable.reload(tbOpts.data, "table");
}

/**
 * 编辑报名信息
 * 
 * @param {ThisType} which 那一行
 */
function editApplyInfo(which) {
  const tr = $(which).parent().parent();
  
  // 获取当前行信息
  const rowData = tableData[tr.children().first().text()]
  
  // 同步表格行中的值到编辑框
  Object.keys(applyOptions).forEach(key => {
    $(applyOptions[key].selector).val(rowData[key]);
  })

  // 如果存在详细介绍
  // 则将详细介绍的内容同步到富文本编辑器中
  if(rowData.introduction) {
    tinyMCE.activeEditor.setContent(rowData.introduction);
  }

  // 设置活动行为当前编辑行
  tbOpts.activeTr = rowData.index;

  $('#editModal').modal();
}

/**
 * 处理更新报名信息时的表单校验以及数据提交
 */
function updateApplyInfo() {
  if (!checkTitle()) {
    return ;
  }

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
 * 上传报名更新数据
 * 
 * @param {FormData} formData
 * @param {string} id
 */
function uploadUpadteData(formData, id) {
  post(`/api/vote/apply_update?aid=${$('#aid').text()}&id=${id}`, formData)
    .then(data => {
      if (!(data.code % 100)) {
        openModal('success', data.codeDesc);
        flushTable(formData, data);
      } else {
        openModal('error', data.codeDesc);
      }
    })
    .catch(err => {
      openModal('error', '发布投票失败')
    });
}


/**
 * 校验标题是否为空
 * 校验通过后将数据追加到容器中
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
function checkIntroduction() {
  if (window.tinyMCE === undefined) {
    return true;
  }

  const introduction = tinyMCE.activeEditor.getContent();
  if (introduction === '') {
    openModal('error', '详细介绍不能为空');
    return false;
  } else {
    this.formData.append('introduction', introduction);
    return true;
  }
}

/**
 * 校验参赛图片是否为空
 * 校验通过后将数据追加到容器中
 *
 * @return true/false 校验成功/失败
 */
function checkImgEntry() {
  const imgEntry = $('#img-entry').val();
  if (imgEntry === undefined) {
    return true;
  } else if (imgEntry === '') {
    openModal('error', '参赛图片不能为空');
    return false;
  } else {
    this.formData.append('imgEntry', $('#img-entry').prop('files')[0]);
    return true;
  }
}

/**
 * 刷新表格
 * 
 * @param {FormData} formData 要刷新的行数据
 * @param {Object} resData 响应数据
 */
function flushTable(formData, resData) {
  const keys = formData.keys();
  while (key = keys.next().value) {
    // 更新了图片时需要重新加载图片
    if (key === 'imgEntry') {
      tableData[tbOpts.activeTr].imgEntry = resData.extraStr;
      continue;
    }
    tableData[tbOpts.activeTr][key] = formData.get(key);
  }
  flushSingleRow(JSON.stringify(tableData[tbOpts.activeTr]));
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
  openModal('userdef', tableData[index].introduction, ()=>{});
}

/**
 * 显示图片大图
 * 
 * @param {number} index  表格行索引
 */
function showImage(index) {
  const img = `<img src="${tableData[index].imgEntry}">`
  openModal('userdef', img, ()=>{});
}
