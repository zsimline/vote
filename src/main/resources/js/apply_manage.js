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

get('/api/vote/data_apply?aid=ef3d7491468d4549a7516911703d7dfb')
  .then(data => {
    flushTableData(data);
  })
  .catch(err => {
    console.error(err);
  });


/**
 * 
 * @param {*} data 
 */
function flushTableData(data) {
  data.forEach((element, index) => {
    element.index = index;
  })
  tableData = JSON.parse(JSON.stringify(data));
  flushTable(data);
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

/**
 * 刷新表格数据
 * 
 * @param {object[]} data 表格数据
 */
function flushTable(data) {
  data.forEach((element, index) => {
    if (element.imgEntry) {
      element.imgEntry = ` <img src=${element.imgEntry} title="点击我查看大图" class="table-img" onclick="showImage(${index})">`;
    }
    if (element.introduction) {
      element.introduction = `<a href="javascript:showDescription(${index})" title="点击我查看详细描述">查看</a>`
    }
  })
  reponse.reloadtable(data, "table");
}

/**
 * 编辑报名信息
 * 
 * @param {ThisType} which 那一行
 */
function editTr(which) {
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
  Object.keys(applyOptions).forEach(key => {
    tableData[tbOpts.activeTr][key] = $(applyOptions[key].selector).val();
  });

  data = JSON.parse(JSON.stringify(tableData));
  flushTable(data);
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
  } else {
    this.formData.append('title', title);
    return true;
  }
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

// 初始化表格
$("#table").reponsetable(tbOpts);


/**
 * 导出表格为Excel
 */
function exportExcel() {
  const tableobj = $("#table").data("tableObj");
  reponse.JSONToCSVConvertor(tableobj, true, "人员表格");
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
