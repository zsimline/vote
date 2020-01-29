/**
 * 处理审核报名
 */

const tableOptions = {
  id: "table",
  operation: "editer",
  type: "numbers",
  colum: fetchColumn(),
  data: [
    { "Number": 1, "Title": "张三", "ImgName": 15, "Description": "男", "WhoAdded": 189 },
    { "Number": 2, "Title": "李四", "ImgName": 15, "Description": "男", "WhoAdded": 189 },
    { "Number": 3, "Title": "王五", "ImgName": 15, "Description": "男", "WhoAdded": 189 },
    { "Number": 4, "Title": "赵六", "ImgName": 15, "Description": "男", "WhoAdded": 189 },
    { "Number": 5, "Title": "陈七", "ImgName": 15, "Description": "女", "WhoAdded": 189 },
  ]
}


function fetchColumn() {
  const column = [
    { "field": "Number", "title": "编号" },
    { "field": "Title", "title": "标题" },
    { "field": "ImgName", "title": "图片" },
    { "field": "Description", "title": "描述" },
    { "field": "WhoAdded", "title": "添加者" },
  ];

  const tableColumns = Array.from($('.table-column'));
  tableColumns.forEach(element => {
    column.push({
      "field": element.dataset.filed, 
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
