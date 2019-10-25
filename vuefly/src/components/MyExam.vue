<template>
  <el-container style="height: 100%; border: 0px solid #eee">

    <el-header style="text-align: right; font-size: 15px">

      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>查看</el-dropdown-item>
          <el-dropdown-item>新增</el-dropdown-item>
          <el-dropdown-item>删除</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>傅荔怡</span>
    </el-header>

    <el-container>
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-menu"></i>首页</template>
            <el-menu-item-group>
              <el-menu-item index="1-1" @click.native="$router.push('/Home')">ExamTips</el-menu-item>
              <el-menu-item index="1-2">论坛话题</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-user"></i>考试管理</template>
            <el-menu-item-group>
              <!--            <template slot="title">分组一</template>-->
              <el-menu-item index="1-1">我的报名</el-menu-item>
              <el-menu-item index="1-2">备忘录</el-menu-item>
            </el-menu-item-group>
            <!--          <el-menu-item-group title="分组2">-->
            <!--            <el-menu-item index="1-3">选项3</el-menu-item>-->
            <!--          </el-menu-item-group>-->
            <el-submenu index="1-4">
              <template slot="title">我的收藏</template>
              <el-menu-item index="1-4-1">论坛收藏</el-menu-item>
              <el-menu-item index="1-4-2">广告收藏</el-menu-item>
            </el-submenu>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>信息设置</template>
            <el-menu-item-group>
              <el-menu-item index="2-1">基础信息</el-menu-item>
              <el-menu-item index="2-2">修改密码</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group>
              <el-menu-item index="2-3">修改二级密码</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title"><i class="el-icon-message"></i>消息管理</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-main>
        <el-table :data="tableData">
          <el-table-column prop="examTipsTitle" label="考试名称" width="200">
          </el-table-column>
          <el-table-column prop="signupBegintime" label="报名开始时间" width="150">
          </el-table-column>
          <el-table-column prop="signupEndtime" label="报名结束时间" width="150">
          </el-table-column>
          <el-table-column prop="signupBegintime" label="考试开始时间" width="150">
          </el-table-column>
          <el-table-column prop="signupEndtime" label="考试结束时间" width="150">
          </el-table-column>
          <el-table-column prop="examUrl" label="报名地址" width="150">
          </el-table-column>
          <el-table-column prop="operation" label="操作" style="margin-left: 20px">
            <el-row>
              <el-button icon="el-icon-more" circle @click.native="$router.push('/MyExamDetails')"></el-button>
              <el-button type="info" icon="el-icon-folder-add" circle @click.native="$router.push('/MyExamDetails')"></el-button>
              <el-button type="danger" icon="el-icon-delete" circle></el-button>
            </el-row>
          </el-table-column>
        </el-table>
        <el-row>
          <div class="block">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage4"
              :page-sizes="[100, 200, 300, 400]"
              :page-size="100"
              layout="total, sizes, prev, pager, next, jumper"
              :total="400">
            </el-pagination>
          </div>
        </el-row>
      </el-main>

    </el-container>
  </el-container>
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

  .el-submenu [class^=el-icon-] {
    vertical-align: middle;
    margin-right: 5px;
    width: 24px;
    text-align: right;
    font-size: 20px;
  }

  /*[class*=" el-icon-"], [class^=el-icon-] {*/
  /*  font-size: larger;*/
  /*}*/
  .el-icon-folder-add{
    font-size: larger;
  }

  .el-pagination {
    white-space: nowrap;
    padding: 30px 300px;
    color: #303133;
    font-weight: 700;
  }
</style>

<script>
  export default {
    methods: {
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      }
    },
    data() {
      const item = {
        examTipsTitle: '全国计算机等级考试',
        signupBegintime: '2019-11-2',
        signupEndtime: '2019-11-12',
        examUrl: 'www.baidu.com'

      };
      return {
        tableData: Array(20).fill(item),
        currentPage1: 5,
        currentPage2: 5,
        currentPage3: 5,
        currentPage4: 4
      }
    }
  };
</script>
