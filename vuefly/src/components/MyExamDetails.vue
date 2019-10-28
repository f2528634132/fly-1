<template>

<div id="app" style="width: 100%;height: 100%">
  <el-row><el-col :span="12">
  <el-page-header @back="goBack" content="详情页面" @click.native="$router.push('/MyExam')">
  </el-page-header>
  </el-col>
  </el-row>
  <el-row>
    <el-col :span="12">
  <el-steps :active="active" finish-status="success">
    <el-step title="未报名"></el-step>
    <el-step title="已报名"></el-step>
    <el-step title="已考试"></el-step>
  </el-steps>
  <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
    </el-col>
  </el-row>
</div>

</template>

<script>
  export default {
    data() {
      return {
        active: 0
      };
    },
    methods: {
      next() {
        if (this.active++ > 2) this.active = 0;
      },
      goBack() {
        console.log('go back');
      }
    }
  }
</script>

<style scoped>
  .el-page-header {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    line-height: 24px;
    margin-bottom: 20px;
  }
  .el-col-12 {
    width: 50%;
    margin-left: 50px;
  }
</style>
