/**
 * 处理审核报名
 */

const tbOpts = {
  id: "table",
  operation: "editer",
  type: "numbers",
  colum: fetchColumn(),
  data: [],
  images: [],
  descriptions: []
}

get('/api/vote/data_apply?aid=156c5b6e68a749b2907d888627e4f426')
  .then(data => {
    data.forEach((element, index) => {
      if (element.imgAddr) {
        element.imgAddr = `<img src=${element.imgAddr} title="点击我查看大图" class="table-img" onclick="showImage(${index})">`;
        tbOpts.images.push(element.imgAddr);
      }
      if (element.description) {
        tbOpts.descriptions.push(element.description);
        element.description = `<a href="javascript:showDescription(${index})" title="点击我查看详细描述">查看</a>`
      }
    })
    reponse.reloadtable(data, "table");
  })
  .catch(err => {
    console.error(err);
  });


function fetchColumn() {
  const column = [
    { "field": "number", "title": "编号" },
    { "field": "title", "title": "标题" },
    { "field": "imgAddr", "title": "图片" },
    { "field": "description", "title": "描述" },
    { "field": "whoAdd", "title": "添加者" },
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

$(function () {
  $('#table').basictable({
    breakpoint: 768
  });
  $("#table").reponsetable(tbOpts);
});

/**
 * 导出表格为Excel
 */
function exportExcel() {
  const tableobj = $("#table").data("tableObj");
  reponse.JSONToCSVConvertor(tableobj, true, "人员表格");
}

/**
 * 删除表格行
 * 
 * @param {*} a 
 * @param {*} e 
 */
function deletetr(a, e) {
  const tr = $(a).parent().parent();
  reponse.deletetr(tr, e);
}


function showDescription(index) {
  openModal('userdef', tbOpts.descriptions[index], ()=>{});
}

function showImage(index) {
  openModal('userdef', tbOpts.images[index], ()=>{});
}


function edittr(which) {
  const tr = $(which).parent().parent();
  reponse.resiverowdata(tr, "table");
  const rowData = $("#table").data("rowdata");
  Object.keys(rowData).forEach(key => {
    $(`#${key}`).val(rowData[key]);
  })
  $('#editModal').modal();



  //reponse.editsavetr(obj, "table");
}

const applyOptions = {
  name: {
    id: "#name",
    errTip: "真实姓名不能为空"
  },
  sex: {
    id: "#sex",
    errTip: "真实性别不能为空"
  },
  age: {
    id: "#age",
    errTip: "真实年龄不能为空"
  },
  telephone: {
    id: "#telephone",
    errTip: "手机号码不能为空"
  },
  email: {
    id: "#email",
    errTip: "电子邮件不能为空"
  },
  school: {
    id: "#school",
    errTip: "学校名称不能为空"
  },
  company: {
    id: "#company",
    errTip: "公司名称不能为空"
  },
  address: {
    id: "#address",
    errTip: "收货地址不能为空"
  },
}