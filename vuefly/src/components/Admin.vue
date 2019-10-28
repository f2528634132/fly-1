<template>
  <el-container style="height: 900px; border: 0px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-menu"></i>考试信息管理</template>
          <el-menu-item-group>
            <!--            <template slot="title">分组一</template>-->
              <el-menu-item index="1-1" >考试信息列表</el-menu-item>
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
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-setting"></i>广告栏目管理</template>
          <el-menu-item-group>
            <template slot="title">我的信息</template>
            <el-menu-item index="2-1">基础信息</el-menu-item>
            <el-menu-item index="2-2">修改密码</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="二级密码">
            <el-menu-item index="2-3">修改二级密码</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-message"></i>论坛管理</template>
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

    <el-container>
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

      <el-main>
        <el-row>
          <el-button round @click.native="$router.push('/Content')">添加考试</el-button>
          <el-button type="primary" round @click="submitClick">主要按钮</el-button>
          <el-button type="success" round>成功按钮</el-button>
          <el-button type="info" round>信息按钮</el-button>
          <el-button type="warning" round>警告按钮</el-button>
          <el-button type="danger" round>危险按钮</el-button>
        </el-row>
        <el-table :data="tableData">

          <el-table-column prop="examTipsTitle" label="考试名称" width="200">
          </el-table-column>
          <el-table-column prop="signupBegintime" label="报名开始时间" width="150">
          </el-table-column>
          <el-table-column prop="signupEndtime" label="报名结束时间"  width="150">
          </el-table-column>
          <el-table-column prop="examUrl" label="报名地址"  width="150">
          </el-table-column>
          <el-table-column prop="operation" label="操作">
            <el-row>
              <el-button icon="el-icon-search" circle></el-button>
              <el-button type="primary" icon="el-icon-edit" circle @click.native="$router.push('/ExamEdit')"></el-button>
              <el-button type="success" icon="el-icon-check" circle></el-button>
              <el-button type="info" icon="el-icon-message" circle></el-button>
              <el-button type="warning" icon="el-icon-star-off" circle></el-button>
              <el-button type="danger"  icon="el-icon-delete" circle></el-button>

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
</style>

<script>
  export default {
    methods: {
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
        submitClick: function () {
            // console.log(this.filterForm);
            return  this.getRequest(`/examTips/queryPage?deleted=0&pageNum=1&pageSize=10`)
                .then(
                    res => {
                        console.log(res.data.data.items);
                        this.tableData = res.data.data.items || [];
                        console.log(this.tableData);
                        this.totalMessage = res.data.totalCount || 0;
                    },
                    err => {
                        // console.log(err);
                    }
                );
        }
    },
    data() {
      const item = {
        examTipsTitle: '全国计算机等级考试1111',
        signupBegintime: '2019-11-2',
        signupEndtime: '2019-11-12',
        examUrl:'www.baidu.com'
      };


//      let arr = Array(20).fill(2)
      // let arr = [1, 2, 3]
      // arr.fill = Array.fill
      // console.log(arr)
      return {
        tableData: Array(6).fill(item),
        currentPage1: 5,
        currentPage2: 5,
        currentPage3: 5,
        currentPage4: 4
      }
    }
  };
</script>
