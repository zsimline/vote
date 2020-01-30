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
  introductions: []
}

get('/api/vote/data_apply?aid=ef3d7491468d4549a7516911703d7dfb')
  .then(data => {
    data.forEach((element, index) => {
      if (element.imgEntry) {
        element.imgEntry = `<img src=${element.imgEntry} title="点击我查看大图" class="table-img" onclick="showImage(${index})">`;
        tbOpts.images.push(element.imgEntry);
      }
      if (element.introduction) {
        tbOpts.introductions.push(element.introduction);
        element.introduction = `<a href="javascript:showDescription(${index})" title="点击我查看详细描述">查看</a>`
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
  openModal('userdef', tbOpts.introductions[index], ()=>{});
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

