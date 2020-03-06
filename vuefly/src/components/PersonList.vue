<template>
  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>账户管理</el-breadcrumb-item>
      <el-breadcrumb-item>个人用户</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    <div class="offer-manage-card offer-b-card">-->
    <el-card class="box-card" style="height: 720px">
      <el-table
        ref="multipleTable"
        :data="tableData"  stripe
        tooltip-effect="dark"     height="600"
        style="width: 100%" :header-cell-style="{background:'#eef1f6',color:'#606266'}"
        @selection-change="handleSelectionChange" >
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column  fixed prop="userName" label="用户名"  width="150">
         </el-table-column>
        <el-table-column prop="name" label="昵称"  width="150"></el-table-column>
        <el-table-column prop="phone" label="手机号"  width="200">
          <template slot-scope="scope">
          <i class="el-icon-mobile-phone"></i>
          <span style="margin-left: 10px">{{ scope.row.phone }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="sex" label="性别"  width="100"></el-table-column>
        <el-table-column prop="age" label="年龄"  width="100"></el-table-column>
        <el-table-column prop="education" label="学历"  width="120"></el-table-column>
        <el-table-column prop="major" label="专业"  width="200"></el-table-column>
        <el-table-column prop="industry" label="行业"  width="150"></el-table-column>
        <el-table-column prop="employStatus" label="是否在职"  width="150"></el-table-column>
        <el-table-column prop="examNumberColl" label="收藏考试" sortable  width="150">
          <template slot-scope="scope">
            <i class="el-icon-star-off" ></i>
            <span style="margin-left: 10px">{{ scope.row.examNumberColl }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="examNumberSignUp" label="已报名考试" sortable  width="150">
        </el-table-column>
        <el-table-column prop="collCourse" label="收藏课程" sortable  width="150">
        </el-table-column>
        <el-table-column fixed="right" label="操作"  width="150">
          <i class="el-icon-more"></i>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
    export default {
        name: "PersonList",
      data(){
          let item={
            userName:'liyi.fu',
            sex:'女',
            age:'23',
            name:'我会一直',
            phone:'12631295917',
            education:'本科',
            major:'信息管理与信息系统',
            industry:'互联网',
            employStatus:'应届生',
            examNumberColl:'15',
            examNumberSignUp:'8',
            collCourse:'13',
          }
          return{
            tableData: Array(6).fill(item),
          }
      },
    }
</script>

<style scoped>

</style>
