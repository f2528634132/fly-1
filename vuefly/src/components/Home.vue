<template>
<!--  <el-container style="height: 945px; border: 0px solid #eee">-->
  <div>
  <div style="width: 100%;height: 26px;"></div>
    <el-card class="box-card">
    <el-container>
        <el-main>
          <el-tabs :tab-position="top">
            <el-tab-pane label="最新考试">
              <el-table :data="tableData" ref="tableData" border  stripe :header-cell-style="{background:'#eef1f6',color:'#606266'}">
                <el-table-column prop="id" label="考试编号" width="120">
                </el-table-column>
                <el-table-column prop="examTipsTitle" label="考试名称" width="250">
                </el-table-column>
                <el-table-column prop="signupBegintime" label="报名开始时间" width="160">
                  <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.signupBegintime }}</span></template>
                </el-table-column>
                <el-table-column prop="signupEndtime" label="报名结束时间" width="160">
                  <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.signupEndtime }}</span></template>
                </el-table-column>
                <el-table-column prop="examBegintime" label="考试开始时间" width="160">
                  <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.examBegintime }}</span></template>
                </el-table-column>
                <el-table-column prop="examEndtime" label="考试结束时间" width="160">
                  <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.examEndtime }}</span></template>
                </el-table-column>
                <el-table-column prop="examUrl" label="报名地址" width="350">
                  <template slot-scope="scope">
                    <a :href="scope.row.examUrl" target="_blank" class="buttonText" style="text-decoration:none;" >{{scope.row.examUrl}}</a>
                  </template>
                </el-table-column>
                <!--            <el-table-column prop="clickNumber" label="热度" width="160">-->
                <!--            </el-table-column>-->
                <!--            <el-table-column prop="id" label="考试Id" width="160">-->
                <!--            </el-table-column>-->
                <el-table-column prop="operation" label="操作">
                  <template slot-scope="scope">
                    <el-row>
                      <!--                <el-button type="info" icon="el-icon-search" circle></el-button>-->
                      <el-button  type="primary" icon="el-icon-more" circle @click.native="$router.push('/MyExamDetails')"></el-button>
                      <!--                <el-button id="examId" type="primary" icon="el-icon-plus" circle v-on:click="joinMyExam(scope.row.id)" ></el-button>-->
                      <!--                <el-button type="success" icon="el-icon-check" circle></el-button>-->
                      <!--                <el-button type="info" icon="el-icon-message" circle></el-button>-->
                      <el-tooltip class="item" effect="dark" content="加入我的收藏" placement="top" :enterable="false">
                        <el-button type="warning" icon="el-icon-star-off" circle v-on:click="joinMyExam(scope.row.id)"></el-button></el-tooltip>
                      <el-button type="danger" icon="el-icon-delete" circle></el-button>
                    </el-row>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="热门考试">好热好热</el-tab-pane>
            <el-tab-pane label="推荐考试"><i class="el-icon-info"></i>
              <el-button type="text">建议完善个人信息，系统将会为你推荐考试</el-button>

              <el-row :gutter="100">
                <el-col :span="8"  v-for="c in 5">
              <el-card class="box-card" shadow="hover" style="margin-top: 20px" id="c">
                <div slot="header" class="clearfix">
                  <span>同专业都在考的证</span>
                  <el-button style="float: right; padding: 3px 0" type="text">赶紧报名</el-button>
                </div>
                <div v-for="o in 4" :key="o">
                  <el-button type="text"><span style="font-size: 14px">{{'计算机等级考试 ' + o +'级'}}</span></el-button>
                </div>
              </el-card>
             </el-col>
            </el-row>


            </el-tab-pane>
          </el-tabs>

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
  </el-card>
  </div>
</template>

<script>

  // import { mapState } from "vuex";
  // import $axios from "vue-resource";

  export default {

    data() {
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
        counter:0,
        name :this.GLOBAL.name,
        activeIndex: '1',
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
              this.tableData = res.data.data.items || [];
              this.totalNum= res.data.totalNum || 0;
            },
            err => {
              // console.log(err);
            }
          );
      },
      handleSelect(key, keyPath) {
      },
      handleSizeChange(val) {
        this.pageSize=val;
        this.submitClick();
      },
      handleCurrentChange(val) {
        this.currentPage=val;
        this.submitClick();
      },
      ClickOut:function () {
        return  this.getRequest(`/auth/logout`).then(
          res=>(
            this.$router.push({
              path:`/Login`
            })
          ))
      },
      submitClick: function () {
        // console.log(this.filterForm);
        //alert(currentPage1,pageSize);
        return  this.getRequest(`/examTips/queryPageByName?deleted=0&pageNum=${this.currentPage}&pageSize=${this.pageSize}`)
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

