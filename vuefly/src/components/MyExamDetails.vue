<template>

<div id="app" style="width: 100%;height: 100%">
  <el-row><el-col :span="12">
  <el-page-header @back="goBack" content="详情页面" @click.native="$router.push('/MyExam')">
  </el-page-header>
  </el-col>
  </el-row>
  <el-row>
    <el-col :span="12" style="margin-top: 30px">
  <el-steps :active="active" finish-status="success" align-center>
    <el-step title="未报名" description="如已报名，请点击下一步"></el-step>
    <el-step title="已报名" description="如已考试，请点击下一步"></el-step>
    <el-step title="已考试" description="请到成绩公布查询成绩！"></el-step>
  </el-steps>
  <el-button style="margin-top: 20px;" @click="next">下一步</el-button>
    </el-col>
  </el-row>
</div>

</template>

<script>
  export default {
    data() {

      if ("4"===this.$route.query.status) {
        this.$route.query.status="1";
      }else if("5"===this.$route.query.status){
        this.$route.query.status="1";
      }

      return {
        active: this.$route.query.status,
        examId: this.$route.query.id
      };
    },
    methods: {
      next() {
        if (this.active++ > 2) {
            this.active = 3;
        }else{
          alert(this.active)
          console.log(this.active);
            return  this.postRequest(`/myExam/editMyExamStatus?id=${this.examId}&status=${this.active}`)
                .then(
                    res => {
                      console.log(res.data.data);
                    }).catch(() => {
                console.log('err');
                    // this.active = this.$route.query.status
                })
                ;
        }

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
