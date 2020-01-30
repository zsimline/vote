/**
 * 处理审核报名
 */

const tbOpts = {
  id: "table",
  operation: "editer",
  type: "numbers",
  colum: fetchColumn(),
  data: [],
}

const images = [];
const descriptions = [];

get('/api/vote/data_apply?aid=156c5b6e68a749b2907d888627e4f426')
  .then(data => {
    data.forEach((element, index) => {
      if (element.imgAddr) {
        element.imgAddr = `<img src=${element.imgAddr} onclick="showImage(${index})">`;        
      }
      if (element.description) {
        descriptions.push(element.description);
        element.description = `<a onclick="showDescription(${index})">查看</a>`
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
  openModal('userdef', descriptions[index], ()=>{});
}

function showImage(index) {
  console.log(tbOpts.data[index]);
  openModal('userdef', tbOpts.data[index].imgAddr, ()=>{});
}
