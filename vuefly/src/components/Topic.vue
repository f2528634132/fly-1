<template>
  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>论坛话题管理</el-breadcrumb-item>
      <el-breadcrumb-item>话题管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    <div class="offer-manage-card offer-b-card">-->
    <el-card class="box-card">
      <div v-for="item in 7">
      <el-row style="font-size: 15px;margin: 0px 35px" :id="item">
        <el-col :span="1" style="margin-right: 25px">
          <div>
            <el-avatar :size="80" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          </div>
        </el-col>
        <el-col :span="22">
          <el-row>
            <el-col span="23">
            小可爱
            <el-tag effect="dark" size="mini" style="margin-left: 10px">楼 主</el-tag>
            <el-divider direction="vertical"></el-divider>
            2020-3-6
            <el-divider direction="vertical"></el-divider>
            22赞
            </el-col>
            <el-col span="1"><el-button type="text">删除</el-button></el-col>
          </el-row>
          <el-row style="font-size: 13px;">各位社区的技术大佬们大家好，我是一名普通本科院校计算机科学与技术专业的大四本科生，主要做后端开发。
            大学期间，学校比较普通，玩的人也比较多，学习氛围也一般，自己从大一下册开始慢慢自学技术吧，期间从课本的C语言到自己去拓展C++，然后自学Java，然后框架、组价等，大二暑期开始了第一段实习旅程。在学习和实习期间很多时候浮于表面，没有深入某个技术。也走了很多弯路，正好曾现在空闲时间，待
          </el-row>
          <el-divider></el-divider>
        </el-col>
      </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
    export default {
        name: "Topic"
    }
</script>

<style scoped>

</style>
