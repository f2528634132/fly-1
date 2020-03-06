<template>
  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 30px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>账户管理</el-breadcrumb-item>
      <el-breadcrumb-item>后台管理员</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    <div class="offer-manage-card offer-b-card">-->
    <el-card class="box-card" style="height: 720px">
<!--      <div style="float: right;margin: 20px 20px;">-->
      <div style="margin-bottom: 10px"><el-button type="primary" >添加管理员</el-button></div>
      <el-table
        ref="multipleTable"
        :data="tableData"
        height="600"   stripe
        tooltip-effect="dark"
        style="width: 100%" :header-cell-style="{background:'#eef1f6',color:'#606266'}"
        @selection-change="handleSelectionChange" >
        <el-table-column
          type="selection"
          width="65">
        </el-table-column>
        <el-table-column prop="id" label="ID"></el-table-column>
        <el-table-column prop="username" label="用户名"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="phone" label="手机号码"></el-table-column>
        <el-table-column  label="操作">
          <template slot-scope="scope">
            <el-button type="primary" plain size="mini">设置权限</el-button>
            <el-button type="warning" plain  size="mini">重置密码</el-button>
            <el-button type="danger" plain  size="mini">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
  let item={
    id:'222',
    username:'SystemAdmin',
    name:'超级管理员',
    phone:'13631295917'
  }
    export default {
        name: "SystemList",
      data(){
          return{
            tableData:Array(20).fill(item)

          }
      },
    }
</script>

<style scoped>

</style>
