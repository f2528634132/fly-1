<template>
  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>考试信息管理</el-breadcrumb-item>
      <el-breadcrumb-item>成绩公布</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    <div class="offer-manage-card offer-b-card">-->
    <el-card class="box-card">
      <el-main>
        <!--        <el-row>-->
        <!--          <el-button type="primary" round @click="submitClick">主要按钮</el-button>-->
        <!--        </el-row>-->
        <el-table :data="tableData"  border stripe :header-cell-style="{background:'#eef1f6',color:'#606266'}">
          <el-table-column prop="examTipsTitle" label="考试名称" width="320">
          </el-table-column>
          <el-table-column prop="examScoresTime" label="成绩公布时间" width="200">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.examScoresTime }}</span></template>
          </el-table-column>
          <el-table-column prop="examScoresUrl" label="查询成绩地址" width="350">
            <template slot-scope="scope">
              <a :href="scope.row.examScoresUrl" target="_blank" class="buttonText" style="text-decoration:none;" >{{scope.row.examScoresUrl}}</a>
            </template>
          </el-table-column>
          <el-table-column prop="examScoresComment"  label="描述" width="350">
          </el-table-column>
          <el-table-column prop="operation" label="操作" style="margin-left: 20px">
            <template slot-scope="scope">
              <el-row>
                <!--                <el-button type="info" icon="el-icon-folder-add" circle-->
                <!--                           @click.native="$router.push({path:'/MyExamDetails',query:{examId:scope.row.id,status:scope.row.status}})"></el-button>-->
                <!--                           v-if:="showStatus"></el-button>-->
                <!--<el-button type="success" icon="el-icon-check" circle @click="submitClick"></el-button>-->
                <el-button icon="el-icon-more" circle @click.native="$router.push(`/MyExamDetails?id=${scope.row.id}&status=${scope.row.status}`)"></el-button>
                <el-button type="info" icon="el-icon-collection-tag"  circle @click="collection"></el-button>
                <el-button type="danger" icon="el-icon-delete" circle v-on:click="delMyExam(scope.row.id)"></el-button>
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
              :page-sizes="[5,10, 20, 30, 40]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="totalNum">
            </el-pagination>
          </div>
        </el-row>
      </el-main>
    </el-card>
  </div>
</template>

<script>
  import {isObject} from "element-ui";

  export default {
    mounted: function () {
      this.submitClick();

    },

    methods: {
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.pageSize=val;
        this.submitClick()

      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage=val;
        this.submitClick()
      },

      delMyExam: function (Id) {
        alert(Id);
        return this.postRequest(`/myExam/delMyExam?id=${Id}`).then( res => {
          this.submitClick()  })
      },
      submitClick: function () {
        return this.getRequest(`/examTips/examScores?deleted=0&pageNum=${this.currentPage}&pageSize=${this.pageSize}`)
          .then(
            res => {
              console.log(res.data.data.items);
              this.tableData = res.data.data.items || [];
              // console.log(isObject(this.tableData[0]));
              // return str;
              // console.log(str);
              console.log(this.tableData);
              this.totalNum = res.data.data.totalNum || 0;
            },
            err => {
              // console.log(err);
            }
          );
      },

      collection(){
        this.$message({
          message:'收藏成功！',
          type: 'success'
        });
      },

    },

    data() {
      return {
        tableData:[],
        // str:'',
        currentPage: 1,
        pageSize:5,
        totalNum:0,
        name :this.GLOBAL.name,
      }
    }
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

  /*[class*=" el-icon-"], [class^=el-icon-] {*/
  /*  font-size: larger;*/
  /*}*/
  .el-icon-folder-add {
    font-size: larger;
  }

  .el-pagination {
    white-space: nowrap;
    padding: 30px 300px;
    color: #303133;
    font-weight: 700;
  }
</style>
