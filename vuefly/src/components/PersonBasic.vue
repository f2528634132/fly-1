<template>
  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>信息设置</el-breadcrumb-item>
      <el-breadcrumb-item>基础信息</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    <div class="offer-manage-card offer-b-card">-->
    <el-card class="box-card">
      <el-tabs tab-position="right" style="margin-right: 200px">
        <el-tab-pane label="基本信息">
          <div style="margin: 50px 250px">
            <div style="font-size: xx-large;margin: 20px 0"><i class="el-icon-postcard" style="margin-right: 10px"></i>{{item.name}}</div>
            <div style="margin: 20px 0"><i class="el-icon-user" style="margin-right: 10px"></i>登录账号：{{item.userName}}</div>
            <div style="margin: 20px 0"><i class="el-icon-female" style="margin-right: 10px"></i>性别：{{item.sex}}</div>
            <div style="margin: 20px 0"><i class="el-icon-date" style="margin-right: 10px"></i>生日：{{item.birthday}}</div>
            <div style="margin: 20px 0"><i class="el-icon-ice-cream-round" style="margin-right: 10px"></i>年龄：{{item.age}}</div>
            <div style="margin: 20px 0"><i class="el-icon-mobile-phone" style="margin-right: 10px"></i>手机号码：{{item.phone}}</div>
            <div style="margin: 20px 0"><i class="el-icon-school" style="margin-right: 10px"></i>学历：{{item.education}}</div>
            <div style="margin: 20px 0"><i class="el-icon-scissors" style="margin-right: 10px"></i>行业：{{item.industry}}</div>
            <div style="margin: 20px 0"><i class="el-icon-suitcase" style="margin-right: 10px"></i>在职状态：{{item.employStatus}}</div>
            <div style="margin: 20px 0"><i class="el-icon-s-flag" style="margin-right: 10px"></i>意向考试：
            <div v-for="i in item.wantWhat "style="margin: 10px 120px" >{{i}}</div></div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="修改信息">
          <el-form v-model="item">
          <div style="margin: 50px 250px">
            <el-form-item><i class="el-icon-postcard" style="margin-right: 10px"></i>昵称：<el-input v-model="item.name"></el-input></el-form-item>
            <el-form-item><i class="el-icon-user" style="margin-right: 10px"></i>登录账号：<el-input v-model="item.userName"></el-input></el-form-item>
            <el-form-item><i class="el-icon-female" style="margin-right: 10px"></i>性别：<el-input v-model="item.sex"></el-input></el-form-item>
            <el-form-item><i class="el-icon-date" style="margin-right: 10px"></i>生日<el-input v-model="item.birthday"></el-input></el-form-item>
            <el-form-item><i class="el-icon-ice-cream-round" style="margin-right: 10px"></i>年龄：<el-input v-model="item.age"></el-input>
            </el-form-item>
            <el-form-item><i class="el-icon-mobile-phone" style="margin-right: 10px"></i>手机号码：<el-input v-model="item.phone"></el-input></el-form-item>
            <el-form-item><i class="el-icon-school" style="margin-right: 10px"></i>学历：<el-input v-model="item.education"></el-input></el-form-item>
            <el-form-item><i class="el-icon-scissors" style="margin-right: 10px"></i>行业：<el-input v-model="item.industry"></el-input></el-form-item>
            <el-form-item><i class="el-icon-suitcase" style="margin-right: 10px"></i>在职状态：<el-input v-model="item.employStatus"></el-input></el-form-item>
            <el-form-item><i class="el-icon-s-flag" style="margin-right: 10px"></i>意向考试：
              <el-select style="width: 750px" v-model="value" multiple filterable allow-create default-first-option placeholder="请选择考试标签">
                <el-option v-for="selectOption in options" :key="selectOption.value" :label="selectOption.label" :value="selectOption.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">保存修改</el-button>
              <el-button type="info">取消</el-button>
            </el-form-item>
          </div>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="修改密码">
          <el-form v-model="user">
            <div style="margin: 50px 280px">
              <el-form-item><i class="el-icon-key" style="margin-right: 10px"></i>旧密码：<el-input v-model="user.oldPassword"></el-input></el-form-item>
              <el-form-item><i class="el-icon-unlock" style="margin-right: 10px"></i>新密码：<el-input v-model="item.password"></el-input></el-form-item>
              <el-form-item><i class="el-icon-lock" style="margin-right: 10px"></i>确认密码：<el-input v-model="item.passwordAgain"></el-input></el-form-item>
              <el-form-item><i class="el-icon-loading" style="margin-right: 10px"></i>验证码：<el-input v-model="item.mama"></el-input></el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">保存修改</el-button>
                <el-button>取消</el-button>
              </el-form-item>
            </div>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="修改备忘录密码">定时任务补偿</el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        item:{
          userName:'liyi.fu',
          sex:'女',
          birthday:'1997-11-21',
          age:'23',
          name:'我会一直',
          phone:'12631295917',
          education:'本科',
          major:'信息管理与信息系统',
          industry:'互联网',
          employStatus:'应届生',
          wantWhat:['计算机','软件','产品','项目管理']
        },
        options: [{
          value: '计算机',
          label: '计算机'
        }, {
          value: '营养师',
          label: '营养师'
        }, {
          value: '摄影',
          label: '摄影'
        }],
        value: [],
        user:{
          oldPassword:'',
          password:'',
          passwordAgain:'',
          mama:''
        }
      };
    },

  }
</script>

<style scoped>
  .el-input__inner {
    background-color: #f4f6f9;
  }
</style>
<style>

</style>
