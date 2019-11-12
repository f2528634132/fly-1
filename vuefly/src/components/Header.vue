<template>
  <div>
    <el-header style="text-align: right; font-size: 15px">
      <!--    <el-header>-->
      <!--        <span class="font-face" style="margin-left: 15px">ExamTips</span>-->
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>个人信息</el-dropdown-item>
          <el-dropdown-item>修改密码</el-dropdown-item>
          <el-dropdown-item @click.native="ClickOut">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>{{name}}</span>
    </el-header>
    <router-view/>
  </div>
</template>

<script>
    export default {
        data(){
            return{
                name :this.GLOBAL.name
            }
        },
        methods: {

            ClickOut:function () {
                return  this.getRequest(`/auth/logout`).then(
                    res=>(
                        this.$router.push({
                            path:`/Login`
                        })
                    ))
            }
        },
    };
</script>

<style scoped>

</style>
