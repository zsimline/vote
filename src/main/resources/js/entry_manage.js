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
    selector: "#sex",
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
  colum: [
    { "field": "index", "title": "index" },
    { "field": "number", "title": "编号" },
    { "field": "isFreeze", "title": "状态" },
    { "field": "title", "title": "标题" },
    { "field": "imgEntry", "title": "参赛图片" },
    { "field": "introduction", "title": "详细介绍" },
    { "field": "acquisition", "title": "取得投票" },
  ],
  data: [],
  activeTr: -1,
}

function fetchTableData() {
  get(`/api/vote/data/entry?aid=${$('#aid').text()}&page=${$('#page').text()}`)
    .then(data => {
      initTableData(data);
      initTable(tableData);
    })
    .catch(err => {
      console.error(err);
    });
}

/**
 * 初始化表格数据
 *
 * @param {Onject[]} data 从服务器返回的原始数据
 */
function initTableData(data) {
  data.forEach((element, index) => {
    element.index = index;
  });
  tableData = data;
}

/**
 * 初始化表格结果
 * 同时填充表格数据
 * 
 * @param {object[]} tableData 表格行数据数组
 */
function initTable(tableData) {
  // 初始化表格结构
  $("#table").initialize(tbOpts);

  // 需要填充到表格中的数据
  const data = JSON.parse(JSON.stringify(tableData));

  // 填充表格数据
  data.forEach((element, index) => {
    if (element.imgEntry) {
      element.imgEntry = `<img src=${element.imgEntry} title="点击我查看大图" class="table-img" onclick="showBigImage(${index})">`;
    }
    if (element.introduction) {
      element.introduction = `<a href="javascript:showDescription(${index})" title="点击我查看详细描述">查看</a>`
    }
    element.isFreeze = element.isFreeze === false ? '正常' : '已冻结';
  });
  basicTable.reload(data, 'table');
  tbOpts.data = data;
}

/**
 * 刷新表格
 * 
 * @param {Object} resData 响应数据
 */
function flushTable(resData) {
  resData.index = tbOpts.activeTr;
  tableData[tbOpts.activeTr] = resData;
  initTable(tableData);
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
  openModal('userdef', tableData[index].introduction);
}

/**
 * 显示图片大图
 * 
 * @param {number} index  表格行索引
 */
function showBigImage(index) {
  const img = `<img src="${tableData[index].imgEntry}">`
  openModal('userdef', img);
}

/**
 * 当用户输入要跳转的页码时，
 * 跳转到指定的页面
 */
function jumpByPage() {
  const to = $('#page-jump').val();
  if (to === '') {
    openModal('error', '请输入要跳转到的页码数');
  } else {
    window.location.href = `/vote/entry_manage?aid=${$('#aid').text()}&page=${to}`;
  }
}

function freeze(which) {
  const tr = $(which).parent().parent();
  const index = tr.children().first().text();
  const rowData = tableData[index];
  tbOpts.activeTr = index;

  get(`/api/vote/entry/freeze?aid=${$('#aid').text()}&id=${rowData.id}`)
    .then(data => {
      if (!data.code) {
        flushTable(data);
      } else {
        openModal('error', data.codeDesc);
      }
    })
    .catch(err => {
      console.error(err);
    });
}

fetchTableData();
