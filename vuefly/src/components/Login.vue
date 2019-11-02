<template>
  <el-form :rules="rules" class="login-container" label-position="left" :model="loginForm"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">ExamTips系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-radio v-model="type" label="1"  >管理员</el-radio>
    <el-radio v-model="type" label="0" >个人</el-radio>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%" @click="submitClick">登录</el-button>
    </el-form-item>
  </el-form>
</template>
<script>

    import { mapMutations } from 'vuex';
  export default{
    data(){
      return {
          type: '0',
        rules: {
          username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          password: [{required: true, message: '请输入密码', trigger: 'blur'}]
        },
        checked: true,
        loginForm: {
          username: 'fk',
          password: '123456'
        },
         userToken:'',
        loading: false
      }
    },
    methods: {
        ...mapMutations(['changeLogin']),
      submitClick: function () {
        let _this = this;
        this.loading = true;
        // this.getRequest('/auth/login?userName='+this.loginForm.username+'&passWord='+this.loginForm.password+'&type='+this.radio).then(resp=> {
          this.getRequest(`/auth/login?userName=${this.loginForm.username}&passWord=${this.loginForm.password}&type=${this.type}`).then(resp=> {
          _this.loading = false;
          if (resp && resp.status == 200) {
            let data = resp.data;
              _this.userToken = data.data.token;
              // 将用户token保存到vuex中

              // this.$store.commit('changeLogin', _this.userToken);
              _this.changeLogin({ Authorization: _this.userToken });
              if (0 === data.data.type){
                this.$router.push({
                    path: '/Home',
                    query: {'id':data.data.id}
                })
            }else{
                this.$router.push({
                    path: '/Admin',
                    query: {'id': data.data.id}

                })
            }
            // _this.$store.commit('login', data.obj);
            // var path = _this.$route.query.redirect;
            /*_this.$router
              .replace({path: path == '/' || path == undefined ? '/home' : path});*/
          }
        });
      }
    }
  }
</script>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
  .el-radio {
    color: #606266;
    cursor: pointer;
    margin-right: 30px;
    margin-bottom: 20px;
    margin-left: 60px;
  }
</style>
