<!--<template>
  <div>
    <el-menu :default-active="this.$route.path"
             router
             mode="horizontal"
             class="el-menu-demo" @select="handleSelect">
      <el-menu-item v-for="(item,i) in navList"
                    :key="i"
                    :index="item.name">
        <template slot="title">
          <span> {{ item.navItem }}</span>
        </template>
      </el-menu-item>
    </el-menu>
    <router-view />
  </div>
</template>-->

<template>
  <div>
    <el-col :span="2">
      <el-menu
        :default-active="this.$route.path"
        router mode="horizontal"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
        <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
          <template slot="title">
            <i class="el-icon-s-platform"></i>
            <span> {{ item.navItem }}</span>
          </template>

        </el-menu-item>


      </el-menu>
    </el-col>

    <router-view     class="menu-right"/>

  </div>
</template>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
/*  .el-submenu [class^=el-icon-] {
    vertical-align: middle;
    margin-right: 5px;
    width: 24px;
    text-align: right;
    font-size: 20px;
  }*/
  .menu-right{
    margin-left:200px;
  }
</style>

<script>
    export default {
        data () {
            return {
                navList: [
                    { name: '/', navItem: '节目发布' },
                    { name: '/Content', navItem: '内容制作' },
                    { name: '/Material', navItem: '素材管理' },
                    { name: '/SplitScreen', navItem: '分屏模板' },
                    { name: '/EmMa', navItem: '设备管理' },
                    { name: '/Log', navItem: '日志管理' },
                    { name: '/Emergency', navItem: '紧急发布记录' }
                ]
            }
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>
