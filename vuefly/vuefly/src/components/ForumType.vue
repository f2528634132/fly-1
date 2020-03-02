<template>
  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>论坛话题管理</el-breadcrumb-item>
      <el-breadcrumb-item>论坛分类</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    <div class="offer-manage-card offer-b-card">-->
    <el-card class="box-card">
    </el-card>
  </div>
</template>

<script>
    export default {
        name: "ForumType"
    }
</script>

<style scoped>

</style>
