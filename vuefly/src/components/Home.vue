<template>

  <el-container style="height: 945px; border: 0px solid #eee">

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
<!--        <el-menu :default-openeds="['1', '3']">-->
        <el-menu >
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-menu"></i>首页</template>
            <el-menu-item-group>
              <el-menu-item index="1-1">ExamTips</el-menu-item>
              <el-menu-item index="1-2">论坛话题</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-user"></i>考试管理</template>
            <el-menu-item-group>
              <!--            <template slot="title">分组一</template>-->
              <el-menu-item index="1-1" @click.native="$router.push('/MyExam')">我的报名</el-menu-item>
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
          <el-row>
<!--            <el-button type="primary" round @click="submitClick()">主要按钮</el-button>-->
            <el-button type="warning" round>热门考试</el-button>
            <el-button type="danger" round>提示考试</el-button>
          </el-row>
          <el-table :data="tableData" ref="tableData">
            <el-table-column prop="id" label="考试编号" width="120">
            </el-table-column>
            <el-table-column prop="examTipsTitle" label="考试名称" width="200">
            </el-table-column>
            <el-table-column prop="signupBegintime" label="报名开始时间" width="160">
            </el-table-column>
            <el-table-column prop="signupEndtime" label="报名结束时间" width="160">
            </el-table-column>
            <el-table-column prop="signupBegintime" label="考试开始时间" width="160">
            </el-table-column>
            <el-table-column prop="signupEndtime" label="考试结束时间" width="160">
            </el-table-column>
            <el-table-column prop="examUrl" label="报名地址" width="350">
            </el-table-column>
<!--            <el-table-column prop="clickNumber" label="热度" width="160">-->
<!--            </el-table-column>-->
<!--            <el-table-column prop="id" label="考试Id" width="160">-->
<!--            </el-table-column>-->
            <el-table-column prop="operation" label="操作">
              <template slot-scope="scope">
                <el-row>
                <el-button icon="el-icon-search" circle></el-button>
                <el-button icon="el-icon-more" circle @click.native="$router.push('/MyExamDetails')"></el-button>
                <el-button id="examId" type="primary" icon="el-icon-plus" circle v-on:click="joinMyExam(scope.row.id)" ></el-button>
                <!--                <el-button type="success" icon="el-icon-check" circle></el-button>-->
<!--                <el-button type="info" icon="el-icon-message" circle></el-button>-->
                <el-button type="warning" icon="el-icon-star-off" circle v-on:click="counter += 1"></el-button>
                <el-button type="danger" icon="el-icon-delete" circle></el-button>
              </el-row>
              </template>
            </el-table-column>
          </el-table>
          <el-row>
            <div class="block">
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5,10, 20, 50, 100]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="totalNum">
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

  .el-pagination {
    white-space: nowrap;
    padding: 30px 300px;
    color: #303133;
    font-weight: 700;
  }
</style>

<script>

  // import { mapState } from "vuex";
  // import $axios from "vue-resource";

  export default {

    data() {
      // const item = {
      //   examTipsTitle: '全国计算机等级考试',
      //   signupBegintime: '2019-11-2',
      //   signupEndtime: '2019-11-12',
      //   examUrl: 'www.baidu.com',
      //   counter:0
      // };
      return {
        //tableData: Array(20).fill(item),
        currentPage: 1,
        currentPage2: 5,
        currentPage3: 5,
        currentPage4: 4,
        examTipsTitle: '',
        signupBegintime: '',
        signupEndtime: '',
        examUrl: '',
        totalMessage:0,
        totalNum:0,
        tableData: [],
        pageSize:5,
        // projectForm: {
        //   deleted:0,
        //   size: 10,
        //   page: 1
        // },
        counter:0
      }
    },
    // computed: {
    //   ...mapState(["domain", "userInfo"])
    // },
    // components: {},
    // activated() {
    //   this.initDatas();
    // },
      mounted:function(){
          this.submitClick();
      },
    methods: {
        joinMyExam: function (examId) {
        alert(examId);
            return  this.postRequest(`/myExam/joinMyExam?examId=${examId}`)
                .then(
                    res => {
                        console.log(res.data.data.items);
                        this.tableData = res.data.data.items || [];
                        console.log(this.tableData);
                        this.totalNum= res.data.totalNum || 0;
                    },
                    err => {
                        // console.log(err);
                    }
                );
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.pageSize=val;
        this.submitClick();
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage=val;
        this.submitClick();
      },
      //  initDatas(){
      //   this.$http
      //     .get(`${this.domain}examTips/queryPage`,this.projectForm)
      //     .then(
      //       res=>{
      //         console.log(res.data.data.items);
      //         this.tableData = res.data.data.items || [];
      //         console.log(this.tableData);
      //         this.totalMessage = res.data.totalNum || 0;
      //       })
      // //   $axios.get(
      // //     `/examTips/queryPage?deleted=0&pageNum=1&pageSize=10` ).then(
      // //            res=>{
      // //              console.log(res.data.data.items);
      // //              this.tableData = res.data.data.items || [];
      // //              console.log(this.tableData);
      // //              this.totalMessage = res.data.totalNum || 0;
      // //            })
      // //    return  this.getRequest(`/examTips/queryPage?deleted=0&pageNum=1&pageSize=10`)
      // //      .then(
      // //        res => {
      // //          console.log(res.data.data.items);
      // //          this.tableData = res.data.data.items || [];
      // //          console.log(this.tableData);
      // //          this.totalMessage = res.data.totalNum || 0;
      // //        },
      // //        err => {
      // //          // console.log(err);
      // //        }
      // //      );
      //
      // },
      submitClick: function () {
        // console.log(this.filterForm);
        //alert(currentPage1,pageSize);
        return  this.getRequest(`/examTips/queryPage?deleted=0&pageNum=${this.currentPage}&pageSize=${this.pageSize}`)
          .then(
            res => {
              console.log(res.data.data.items);
              this.tableData = res.data.data.items || [];
              console.log(this.tableData);
              console.log(res.data.data.totalNum );
              this.totalNum = res.data.data.totalNum || 0;
            },
            err => {
              // console.log(err);
            }
          );
      }
    },
  };
</script>
