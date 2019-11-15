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
  </div>
</template>

<script>
    import { mapState } from "vuex";
    export default {

        data(){
            return{
                name :this.GLOBAL.name
            }
        },
        // mounted() {
        //     this.initFunction();
        // },
        computed:{
            ...mapState(["powerLinks"])
        },
        activated() {
                this.getRequest(`/role/queryUserFunction`)
                    .then(res => {
                        let functionNodes = res.data.data || [];
                        this.$store.commit("setFunctionNodes", functionNodes);
                        // 页面功能权限
                        let powerLinks = [];
                        // 按钮功能权限
                        let nodeTypeTwo = [];
                        let navList = this.navList;
                        functionNodes.forEach(page => {
                            if (page.url) {
                                powerLinks.push(page.url);
                            }
                            if (page.type == 3) {
                                navList.push(page);
                            }
                            page.childNodes.forEach(node => {
                                if (node.type == 2) {
                                    nodeTypeTwo.push(node.name);
                                }

                                if (node.url) {
                                    powerLinks.push(node.url);
                                }
                            });
                        });
                        powerLinks.splice(1, 0, ...this.powerLinks);
                        this.$store.commit("setNodeTypeTwo", nodeTypeTwo);
                        console.log("aaaaa"+powerLinks)
                        this.$store.commit("setPowerLinks", powerLinks);
                        this.$store.commit("setNavList", navList);
                    });
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
            // initFunction(){
            //     return this.getRequest(`/role/queryUserFunction`)
            //         .then(res => {
            //             let functionNodes = res.data.data || [];
            //             this.$store.commit("setFunctionNodes", functionNodes);
            //             // 页面功能权限
            //             let powerLinks = [];
            //             // 按钮功能权限
            //             let nodeTypeTwo = [];
            //             let navList = this.navList;
            //             functionNodes.forEach(page => {
            //                 if (page.url) {
            //                     powerLinks.push(page.url);
            //                 }
            //                 if (page.type == 3) {
            //                     navList.push(page);
            //                 }
            //                 page.childNodes.forEach(node => {
            //                     if (node.type == 2) {
            //                         nodeTypeTwo.push(node.name);
            //                     }
            //
            //                     if (node.url) {
            //                         powerLinks.push(node.url);
            //                     }
            //                 });
            //             });
            //             powerLinks.splice(1, 0, ...this.powerLinks);
            //             this.$store.commit("setNodeTypeTwo", nodeTypeTwo);
            //             this.$store.commit("setPowerLinks", powerLinks);
            //             this.$store.commit("setNavList", navList);
            //         });
            // }
        },
    };
</script>

<style scoped>

</style>
