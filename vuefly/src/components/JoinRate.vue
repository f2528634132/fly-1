<template>
  <div class="offer-manage-wrapper">
    <!--  <div>-->
    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>数据统计</el-breadcrumb-item>
      <el-breadcrumb-item>数据报表</el-breadcrumb-item>
    </el-breadcrumb>

    <!--      卡片视图区-->
    <el-card class="box-card">
      <div style="margin-top: 50px;margin-left: 50px; margin-bottom: 50px">
        <!--        为Echarts准备一个具备大小（宽高）的Dom-->
        <div id="main" style="width: 1200px;height:400px;"></div>
      </div>
    </el-card>
  </div>
</template>

<script>
  import echarts from 'echarts'
    export default {
        name: "JoinRate",
      data(){
        return{
        }
      },
      created () {
      },
      //只要执行了mounted函数，此时页面上的元素，已经被渲染完毕了！
      async mounted() {
        // 3. 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        const {data:res1} = await this.$http.get('examTips/queryExamType/statistics')
        var examType=[];
        for (var i=0;i<res1.data.length;i++){
          examType.push(res1.data[i].examTypeName)
        }
        // console.log(examType)

        // 4.准备数组配置项
        var option = {
          title: {
            text: '考生参考情况'
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross',
              label: {
                backgroundColor: '#6a7985'
              }
            }
          },
          legend: {
            data: ['计算机等级', '全国英语等级', '中小学教师资格', '软件专业', '会计从业']
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              boundaryGap: false,
              data: ['点击量', '收藏量', '报名量', '参考人数', '缺考人数']
            }
          ],
          yAxis: [
            {
              type: 'value'
            }
          ],
          series: [
            {
              name: '计算机等级',
              type: 'line',
              stack: '总量',
              areaStyle: {},
              data: [2120, 1932, 1191, 1134, 590]
            },
            {
              name: '全国英语等级',
              type: 'line',
              stack: '总量',
              areaStyle: {},
              data: [3220, 1982, 1291, 1234, 90]
            },
            {
              name: '软件专业',
              type: 'line',
              stack: '总量',
              areaStyle: {},
              data: [2150, 1932, 1201, 1154, 690]
            },
            {
              name: '会计从业',
              type: 'line',
              stack: '总量',
              areaStyle: {},
              data: [2320, 1932, 1501, 1334, 530]
            },
            {
              name: '中小学教师资格',
              type: 'line',
              stack: '总量',
              label: {
                normal: {
                  show: true,
                  position: 'top'
                }
              },
              areaStyle: {},
              data: [2820, 1732, 1601, 934, 80]
            }
          ]
        };

        //  5.展示数据
        myChart.setOption(option)

      }
    }
</script>

<style scoped>

</style>
