<template>
  <div class="offer-manage-wrapper">

    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>考试信息管理</el-breadcrumb-item>
      <el-breadcrumb-item>考试信息</el-breadcrumb-item>
      <el-breadcrumb-item>编辑考试</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    <div class="offer-manage-card offer-b-card">-->
    <el-card class="box-card" >
<!--  <el-form ref="form" class="forum" :model="examTipsInputVo" label-width="80px">-->
      <el-form :model="examTipsInputVo">
    <el-form-item label="考试标题">
      <el-input v-model="examTipsInputVo.examTipsTitle"></el-input>
    </el-form-item>
    <el-form-item label="报名入口">
      <el-input v-model="examTipsInputVo.examUrl"></el-input>
    </el-form-item>
    <el-form-item label="考试类目">
      <el-select v-model="examTipsInputVo.examTypeId" placeholder="请选择考试类目">
        <el-option label="全国计算机等级考试" value="1"></el-option>
        <el-option label="计算机技术与软件专业技术资格（水平）考试" value="2"></el-option>
        <el-option label="CET大学英语考试" value="3"></el-option>
        <el-option label="全国会计从业资格考试" value="4"></el-option>
        <el-option label="中小学教师资格考试" value="5"></el-option>
        <el-option
          v-for="item in examTypeList"
          :key="item.id"
          :label="item.examName" :value="item.id"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="报名时间">
      <el-col :span="11">
        <el-date-picker
          v-model="examTipsInputVo.value1"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          @input="testClick"
          >
        </el-date-picker>
      </el-col>
    </el-form-item>
    <el-form-item label="考试时间">
      <el-col :span="11">
        <el-date-picker
          v-model="examTipsInputVo.value2"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          >
        </el-date-picker>
      </el-col>
    </el-form-item>
    <el-form-item label="考试详情">
      <el-input type="textarea" v-model="examTipsInputVo.examComment"></el-input>
    </el-form-item>
    <el-form-item label="发布">
      <el-switch v-model="examTipsInputVo.deleted"></el-switch>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">保存修改</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
    </el-card>
  </div>
</template>


<script>
  export default {
    data() {
      return {
        examTipsInputVo: {
          value2:[],
          value1:[],
          examTipsTitle: '',
          examUrl:'',
          examTypeId:'',
          id:this.$route.query.examId,
          signupBegintime: '',
          signupEndtime: '',
          examBegintime: '',
          examEndtime: '',
          deleted: false,
          examComment: '',
        },
        examTypeList:[]
      }
    },
    mounted:function(){
      this.initMyExamDetails();
      this.queryExamTypeList();
    },

    methods: {
      initMyExamDetails:function(){
        return this.getRequest(`/examTips/queryExam?examId=${this.examTipsInputVo.id}`)
          .then(
            res => {
              console.log(res.data.data);
              //重新定义的examTipsInputVo
              // this.examTipsInputVo = res.data.data || [];
              this.examTipsInputVo.examTipsTitle=res.data.data.examTipsTitle;
              this.examTipsInputVo.examUrl=res.data.data.examUrl;
              // this.examTipsInputVo.examTypeId=res.data.data.examTypeId;
              this.examTipsInputVo.examComment=res.data.data.examComment;

              this.examTipsInputVo.examTypeId=String(res.data.data.examTypeId);
              // console.log(this.examTipsInputVo.examTypeId);

               // this.examTipsInputVo.value1[0]=res.data.data.signupBegintime;
               // this.examTipsInputVo.value1[1]=res.data.data.signupEndtime ;
               // this.examTipsInputVo.value2[0]=res.data.data.examBegintime ;
               // this.examTipsInputVo.value2[1]= res.data.data.examEndtime;


              // console.log(this.examTipsInputVo.signupBegintime);

              this.examTipsInputVo.value1 = [res.data.data.signupBegintime, res.data.data.signupEndtime]
              let self = this;
              this.$set(self.examTipsInputVo, "value2", [
                new Date(res.data.data.examBegintime),
                new Date(res.data.data.examEndtime)
              ]);



              console.log(this.examTipsInputVo.value1[0]);
              console.log(this.examTipsInputVo.value2[1]);
              console.log(this.examTipsInputVo.value1);
              console.log(this.examTipsInputVo.value2);

            },
            err => {
              // console.log(err);
            }
          );
      },
      testClick(e){
        this.$nextTick(() => {
          this.$set(this.examTipsInputVo, "value1", [e[0], e[1]]);
          console.log(this.examTipsInputVo.value1)
        });
      },
        queryExamTypeList: function () {
            return  this.getRequest(`/examTips/queryExamTypeList`)
                .then(
                    res => {
                        this.examTypeList = res.data.data || [];
                    },
                    err => {
                        // console.log(err);
                    }
                );
        },


      onSubmit: function () {
        // let examTipsInputVo = new FormData();
        // for (var key in this.formData) {
        //   examTipsInputVo.append(key, this.formData[key]);
        // }
        // console.log( examTipsInputVo.keys().next());
        let inputVo = this.examTipsInputVo;
        inputVo.signupBegintime = inputVo.value1[0];
        inputVo.signupEndtime = inputVo.value1[1];
        inputVo.examBegintime = inputVo.value2[0];
        inputVo.examEndtime = inputVo.value2[1];
        this.$axios({
          method: "post",
          url: "/examTips/editExamTips",
          headers: {
            //"Content-Type": "multipart/form-data"
            'Content-Type': 'application/json;charset=UTF-8'
          },
          withCredentials: true,
           data: JSON.stringify(inputVo)
        //   data: JSON.stringify({
        //     "examBegintime": "2019-10-29T09:54:10.912Z",
        //     "examComment": "test",
        //     "examEndtime": "2019-10-29T09:54:10.912Z",
        //     "examTipsTitle": "test",
        //     "examTypeId": 0,
        //     "examUrl": "test",
        //     "signupBegintime": "2019-10-29T09:54:10.912Z",
        //     "signupEndtime": "2019-10-29T09:54:10.912Z"
        //   })
         }).then((res) => {
          console.log(res);
          return this.$router.push('/Admin')
        });
      }
    }
  }
</script>

<style scoped>
  .forum {
    margin: 80px;
    width: 800px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    -webkit-box-shadow: 0 0 25px #cac6c6;
    box-shadow: 0 0 25px #cac6c6;
  }
  .el-form{
    padding: 35px 35px 15px 35px;
  }
  .el-input {
    position: relative;
    font-size: 14px;
    /* display: inline-block; */
    width: 350px;
  }
  .el-select {
    /* display: inline-block; */
    /* position: relative; */
    width: 500px;
  }
  .el-form-item {
    margin-bottom: 22px;
    width: 60%;
  }
  .el-textarea {
    position: relative;
    display: inline-block;
    width: 100%;
    vertical-align: bottom;
    font-size: 14px;
    height: 100px;
  }
  .el-textarea__inner {
    height: 100px;
  }
</style>
