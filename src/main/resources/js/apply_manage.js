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

get('/api/vote/data_apply?aid=c515c87d5f2a4d80b2d08595a1968cec')
  .then(data => {
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
