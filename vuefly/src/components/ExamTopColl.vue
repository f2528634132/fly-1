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
  //1.导入echarts
  import echarts from 'echarts'
  export default {
    name: 'ExamTopColl',
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

      const {data:res} = await this.$http.get('reports/groupByExam')
      console.log(res)
      var names=[];
      var nums=[];
      for (var i=0;i<res.length;i++){
        names.push(res[i].examTipsTitle)
        nums.push(res[i].sigUpNum)
      }
      console.log(names)
      console.log(nums)
      // 4.准备数组配置项
      var option = {
        title: {
          text: '各考试类型学生关注度'
        },
        tooltip: {},
        legend: {
          data:['各考试类型学生关注度']
        },
        xAxis: {
          data: names
        },
        yAxis: {},
        series: [{
          name: '收藏量',
          type: 'bar',
          data: nums
        }]
      };

      //  5.展示数据
      myChart.setOption(option)

    }
  }
</script>
<style scoped>
</style>
