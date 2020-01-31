// 报名选项
const applyOptions = {
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

/**
 * 处理管理报名
 */

const tbOpts = {
  id: "table",
  operation: "editer",
  type: "numbers",
  colum: fetchColumn(),
  data: [],
  images: [],
  introductions: []
}

// 请求
get('/api/vote/data_apply?aid=ef3d7491468d4549a7516911703d7dfb')
  .then(data => {
    flushTable(data);
  })
  .catch(err => {
    console.error(err);
  });


/**
 * 刷新表格数据
 * 
 * @param {object} data 表格数据
 */
function flushTable(data) {
  data.forEach((element, index) => {
    if (element.imgEntry) {
      element.imgEntry = `<img src=${element.imgEntry} title="点击我查看大图" class="table-img" onclick="showImage(${index})">`;
      tbOpts.images.push(element.imgEntry);
    }
    if (element.introduction) {
      tbOpts.introductions[element.id] = element.introduction;
      element.introduction = `<a href="javascript:showDescription(${element.id})" title="点击我查看详细描述">查看</a>`
    }
  })
  reponse.reloadtable(data, "table");
}


function fetchColumn() {
  const column = [
    { "field": "id", "title": "编号" },
    { "field": "title", "title": "标题" },
    { "field": "status", "title": "审核状态" },
  ];

  const tableColumns = Array.from($('.table-column'));
  tableColumns.forEach(element => {
    column.push({
      "field": element.dataset.field, 
      "title": element.dataset.title
    });
  });
  
  return column;
}




/**
 * 导出表格为Excel
 */
function exportExcel() {
  const tableobj = $("#table").data("tableObj");
  reponse.JSONToCSVConvertor(tableobj, true, "人员表格");
}


function showDescription(index) {
  openModal('userdef', tbOpts.introductions[index], ()=>{});
}

function showImage(index) {
  openModal('userdef', tbOpts.images[index], ()=>{});
}


/**
 * 编辑报名信息
 * 
 * @param {ThisType} which 那一行
 */
function edittr(which) {
  const tr = $(which).parent().parent();
  reponse.resiverowdata(tr, "table");
  const rowData = $("#table").data("rowdata");
  
  //
  Object.keys(rowData).forEach(key => {
    $(`#${key}`).val(rowData[key]);
  })
  
  // 如果存在详细介绍，则将详细介绍的内容同步到富文本编辑器中
  if(rowData.introduction) {
    tinyMCE.activeEditor.setContent(tbOpts.introductions[rowData.id]);
  }

  $('#editModal').modal();
}

/**
 * 处理更新报名信息时的表单校验以及数据提交
 */
function updateApplyInfo() {
  Object.keys(applyOptions).forEach(key => {
    rowData[key] = $(`#${key}`).val();
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
