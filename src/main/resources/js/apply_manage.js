/**
 * 处理审核报名
 */

const tableOptions = {
  id: "table",
  operation: "editer",
  type: "numbers",
  colum: fetchColumn(),
  data: [
    { "number": 1, "title": "张三", "imgAddr": 15, "description": "男", "whoAdd": 189 },
    { "number": 2, "title": "李四", "imgAddr": 15, "description": "男", "whoAdd": 189 },
    { "number": 3, "title": "王五", "imgAddr": 15, "description": "男", "whoAdd": 189 },
    { "number": 4, "title": "赵六", "imgAddr": 15, "description": "男", "whoAdd": 189 },
    { "number": 5, "title": "陈七", "imgAddr": 15, "description": "女", "whoAdd": 189 },
  ]
}


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

  $("#table").reponsetable(tableOptions);
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
