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
      <el-row style="margin-bottom: 50px">
        <el-col span="8" style="margin-left: 350px">
          <el-input placeholder="请输入内容" v-model="query">
            <el-button slot="append" icon="el-icon-search" @click="queryPageByName"></el-button>
          </el-input></el-col><el-col span="6">
        <el-button type="primary" style="margin-left: 500px" round @click.native="$router.push('/AddExam')">添加机构信息</el-button></el-col>
      </el-row>
      <div class="block"  style="margin-bottom: 20px; margin-left:40px; width:100%">
        <el-row :gutter="20">
          <el-col span="11" v-for="fit in ec" :key="fit">
              <el-card  shadow="hover" :body-style="{ padding: '0px' }" style="margin-bottom: 50px;width: 550px;height: 200px;background: #d9ecff" >
                    <el-row><el-col span="10"> <el-avatar shape="square" :size="200" :fit="fill" :src="src"></el-avatar></el-col>
                    <el-col span="14">
                      <div style="font-size: xx-large;margin: 20px 0">{{fit.name}}</div>
                      <div style="margin: 10px 0"><i class="el-icon-s-opportunity" style="margin-right: 10px"></i>话题：{{fit.age}}</div>
                      <div style="margin: 20px 0"><i class="el-icon-chat-dot-round" style="margin-right: 10px"></i>参与人数：{{fit.birthday}}</div>
                      <div style="margin: 20px 0"><i class="el-icon-view" style="margin-right: 10px"></i>浏览量：{{fit.birthday2}}</div>
                    </el-col></el-row>
        </el-card>
          </el-col>

        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
    export default {
        name: "ForumType",

      data(){
          return {
            src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
            currentDate: new Date(),
            ec:[
              {name:'英语',
               age:'22',
               birthday:'2020',birthday2:'124323'},
              {name:'中小学教师资格',
                age:'23',
                birthday:'2024',birthday2:'124323'},
              {name:'计算机',
                age:'23',
                birthday:'2024',birthday2:'124323'},
              {name:'考公',
                age:'23',
                birthday:'2024',birthday2:'124323'},
            ]
          }
      },
      mounted:function(){

      },
      methods: {}
    }
</script>

<style scoped>

</style>
