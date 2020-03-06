<template>
  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>成绩公布</el-breadcrumb-item>
      <el-breadcrumb-item>添加成绩公布</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    <div class="offer-manage-card offer-b-card">-->
    <el-card class="box-card">
      <el-form :model="examTipsInputVo">
        <el-form-item label="考试标题">
          <el-input v-model="examTipsInputVo.examTipsTitle"></el-input>
        </el-form-item>
        <el-form-item label="成绩公布地址">
          <el-input v-model="examTipsInputVo.examScoresUrl"></el-input>
        </el-form-item>
        <el-form-item label="成绩公布">
          <el-col :span="11">
            <el-date-picker
              v-model="examTipsInputVo.examScoresTime"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="公布详情">
          <el-input type="textarea" v-model="examTipsInputVo.examScoresComment"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "AddExamScoreOpen",
    data(){
      return {
        examTipsInputVo: {
          examTipsTitle:this.$route.query.examTipsTitle,
          id:this.$route.query.examId,
          examScoresTime:'',
          examScoresComment:'',
          examScoresUrl:'',
        }
      }
    },
    mounted:function(){
      this.initMyExamDetails();
    },
    methods:{
      onSubmit: function () {
        let inputVo = this.examTipsInputVo;
        this.$axios({
          method: "post",
          url: "/examTips/addExamScores",
          headers: {
            //"Content-Type": "multipart/form-data"
            'Content-Type': 'application/json;charset=UTF-8'
          },
          withCredentials: true,
          data: JSON.stringify(inputVo)
        }).then((res) => {
          console.log(res);
        });
      },
      initMyExamDetails: function (){
        this.examTipsTitle=this.$route.query.examTipsTitle
      }
    }
  }
</script>

<style scoped>

</style>
