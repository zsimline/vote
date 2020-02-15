// 名称映射
const nameMap = {
  sex: {
    1: '男',
    2: '女',
    0: '未知'
  },
  province: {
    'Zhejiang': '浙江',
    'Hebei': '河北'
  }
}

/**
 * 画投票者性别比例图
 */
function gatherSex() {
  const chartOpts = {
    title: {
      text: '投票者性别统计',
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
      type: 'scroll',
      orient: 'vertical',
      right: 30,
      top: 20,
      bottom: 20,
      data: [],
    },
    series: [
      {
        name: '性别',
        type: 'pie',
        radius: '55%',
        center: ['40%', '50%'],
        data: [],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };

  get(`/api/gather/by_sex?aid=${glStatus.aid}`)
    .then(data => {
      data.forEach(element => {
        let sex = {
          name: nameMap.sex[element[0]],
          value: element[1]
        };
        chartOpts.legend.data.push(sex.name);
        chartOpts.series[0].data.push(sex);
        const chart = echarts.init($('#gather-sex')[0]);
        chart.setOption(chartOpts);
      });
    })
}

/**
 * 画投票者省份分布图
 */
function gatherProvince() {
  const chartOpts = {
    title: {
      text: '投票者省份统计',
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
      type: 'scroll',
      orient: 'vertical',
      right: 30,
      top: 20,
      bottom: 20,
      data: [],
    },
    series: [
      {
        name: '性别',
        type: 'pie',
        radius: ['50%', '70%'],
        avoidLabelOverlap: false,
        label: {
          normal: {
            show: false,
            position: 'center'
          },
          emphasis: {
            show: true,
            textStyle: {
              fontSize: '30',
              fontWeight: 'bold'
            }
          }
        },
        labelLine: {
          normal: {
            show: false
          }
        },
        data: [],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  };

  get(`/api/gather/by_province?aid=${glStatus.aid}`)
    .then(data => {
      data.forEach(element => {
        let province = {
          name: nameMap.province[element[0]],
          value: element[1]
        };
        chartOpts.legend.data.push(province.name);
        chartOpts.series[0].data.push(province);
        window.chartOpts = chartOpts;
        const chart = echarts.init($('#gather-province')[0]);
        chart.setOption(chartOpts);
      });
    })
}

/**
 * 画关于投票数量的日历图
 */
function gatherDate() {
  const chartOpts = {
    title: {
      top: 30,
      left: 'center',
      text: '投票日历图'
    },
    tooltip: {},
    visualMap: {
      min: 0,
      max: 10000,
      type: 'piecewise',
      orient: 'horizontal',
      left: 'center',
      top: 65,
      textStyle: {
        color: '#000'
      }
    },
    calendar: {
      top: 120,
      left: 30,
      right: 30,
      cellSize: ['auto', 13],
      range: '2020',
      itemStyle: {
        borderWidth: 0.5
      },
      yearLabel: { show: false }
    },
    series: {
      type: 'heatmap',
      coordinateSystem: 'calendar',
      data: []
    }
  };

  get(`/api/gather/by_date?aid=${glStatus.aid}`)
    .then(data => {
      data.forEach(element => {
        element[0] = echarts.format.formatTime('yyyy-MM-dd', new Date(element[0]));
        chartOpts.series.data.push(element);
        const chart = echarts.init($('#gather-date')[0]);
        chart.setOption(chartOpts);
      })
    })
}

gatherSex();
gatherProvince();
gatherDate();
