<template>
  <el-container style="height: 100%; border: 0px solid #eee">
    <el-container>
      <el-main>
        <!--        <el-row>-->
        <!--          <el-button type="primary" round @click="submitClick">主要按钮</el-button>-->
        <!--        </el-row>-->
        <el-table :data="tableData">
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
            <template slot-scope="scope">
              <a :href="scope.row.examUrl" target="_blank" class="buttonText" style="text-decoration:none;" >{{scope.row.examUrl}}</a>
            </template>
          </el-table-column>
          <el-table-column prop="status" :formatter="statusFormat" label="考试状态" width="150">
            <!--            <template slot-scope="scope">-->
            <!--              <router-link  to={`/MyExamDetails?id=${scope.row.id}&status=${scope.row.status}`}>-->
            <!--             <router-link  to="{path:'/MyExamDetails',query:{id:scope.row.id,status:scope.row.status}}">-->
            <!--                <span v-if="scope.row.status===1">未报名</span>-->
            <!--                <span v-if="scope.row.status===2">已报名</span>-->
            <!--                <span v-if="scope.row.status===3">已考试</span>-->
            <!--                <span v-if="scope.row.status===4">已过期</span>-->
            <!--                <span v-if="scope.row.status===5">未考试</span>-->
            <!--              </router-link>-->
            <!--            </template>-->
          </el-table-column>
          <el-table-column prop="operation" label="操作" style="margin-left: 20px">
            <template slot-scope="scope">
              <el-row>
                <!--                <el-button type="info" icon="el-icon-folder-add" circle-->
                <!--                           @click.native="$router.push({path:'/MyExamDetails',query:{examId:scope.row.id,status:scope.row.status}})"></el-button>-->
                <!--                           v-if:="showStatus"></el-button>-->
                <!--<el-button type="success" icon="el-icon-check" circle @click="submitClick"></el-button>-->
                <el-button type="info" icon="el-icon-more" circle @click.native="$router.push(`/MyExamDetails?id=${scope.row.id}&status=${scope.row.status}`)"></el-button>
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

    </el-container>
  </el-container>
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
        // console.log(this.filterForm);
        // let str='';
        return this.getRequest(`/myExam/MyCollectExam?deleted=0&status=1&pageNum=${this.currentPage}&pageSize=${this.pageSize}`)
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

      statusFormat: function (row,column) {
        // let status =row.status();
        // let statusW="未缴费";
        // if (status==0){
        //   statusW='未报名';
        // }else if(status==1){
        //   statusW='已报名';
        // }else if(status==2){
        //   statusW='已过期';
        // }else if(status==3){
        //   statusW='待考试';
        // }else if(status==4){
        //   statusW='已考试';
        // }else{
        //   statusW='未考试';
        // }
        switch (row.status) {
          case 1:
            return '未报名';
            break;
          case 2:
            return '已报名';
            break;
          case 3:
            return '已考试';
            break;
          case 4:
            return '已过期';
            break;
          case 5:
            return '未考试';
            break;
        }
        // return statusW;
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
