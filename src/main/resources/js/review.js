/**
 * 处理审核报名
 */

$(function () {
  $('#table').basictable({
    breakpoint: 768
  });

  $("#table").reponsetable({
    id: "table",
    operation: "editer",
    type: "numbers",
    colum: [
      { "field": "Index", "title": "编号" },
      { "field": "Name", "title": "姓名" },
      { "field": "Age", "title": "年龄" },
      { "field": "Gender", "title": "性别" },
      { "field": "Height", "title": "身高" },
      { "field": "Province", "title": "省份" },
      { "field": "Sport", "title": "市级" }
    ],
    data: [
      { "Index": 1, "Name": "张三", "Age": 15, "Gender": "男", "Height": 189, "Province": "河北", "Sport": "唐山市" },
      { "Index": 2, "Name": "李四", "Age": 15, "Gender": "男", "Height": 189, "Province": "河北", "Sport": "邯郸市" },
      { "Index": 3, "Name": "王五", "Age": 15, "Gender": "男", "Height": 189, "Province": "河北", "Sport": "沧州市" },
      { "Index": 4, "Name": "赵六", "Age": 15, "Gender": "男", "Height": 189, "Province": "河北", "Sport": "邢台市" },
      { "Index": 5, "Name": "陈七", "Age": 15, "Gender": "女", "Height": 189, "Province": "河北", "Sport": "保定市" },
    ]
  });
});

function exportExcel() {
  const tableobj = $("#table").data("tableObj");
  reponse.JSONToCSVConvertor(tableobj, true, "人员表格");
}

function reloadTable() {
  const data = [
    { "Name": "1111", "Age": 15, "Gender": "1", "Height": 189, "Province": "1", "Sport": "1" },
    { "Name": "2222", "Age": 15, "Gender": "2", "Height": 2, "Province": "2", "Sport": "2" },
    { "Name": "3333", "Age": 15, "Gender": "4", "Height": 189, "Province": "3", "Sport": "3" }
  ]
  reponse.reloadtable(data, "table");
}

function edittr(a, e) {
  const tr = $(a).parent().parent();
  reponse.resiverowdata(tr, "table");
  const rowdata = $("#table").data("rowdata");

  layer.open({
    type: 1,
    title: '编辑人员信息',
    closeBtn: 1,
    area: '516px',
    skin: '#fff',
    shadeClose: true,
    content: $('#edit-content'),
    btn: ["保存", "关闭"],
    btn1: function (index, layero) {
      const Name = $("#Name").val();
      const Age = $("#Age").val();
      const Gender = $("#Gender").val();
      const Height = $("#Height").val();
      const Province = $("#Province").val();
      const Sport = $("#Sport").val();
      const obj = {
        "Name": Name,
        "Age": Age,
        "Gender": Gender,
        "Height": Height,
        "Province": Province,
        "Sport": Sport
      };
      reponse.editsavetr(obj, "table");
      layer.close(index);
    }, btn2: function (index, layero) {
      layer.close(index);
    }
  });
}

function deletetr(a, e) {
  const tr = $(a).parent().parent();
  reponse.deletetr(tr, e);
}