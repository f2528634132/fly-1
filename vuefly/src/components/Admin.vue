<template>
  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>考试信息管理</el-breadcrumb-item>
      <el-breadcrumb-item>考试信息</el-breadcrumb-item>
    </el-breadcrumb>
<!--    <div class="offer-manage-card offer-b-card">-->
    <el-card class="box-card">
      <el-main>
        <el-row>
            <el-input placeholder="请输入内容">
              <el-select v-model="select" slot="prepend" placeholder="请选择">
                <el-option label="ALL" value="1"></el-option>
                <el-option label="计算机" value="2"></el-option>
                <el-option label="外语" value="3"></el-option>
                <el-option label="证券" value="4"></el-option>
                <el-option label="会计" value="5"></el-option>
                <el-option label="其他" value="6"></el-option>
              </el-select>
              <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
            <el-button type="primary" style="margin-left: 500px" round @click.native="$router.push('/AddExam')">添加考试</el-button>
        </el-row>
        <el-table :data="tableData" ref="tableData" header-row-class-name="center" border stripe>
          <el-table-column prop="id" label="编号" width="60" >
          </el-table-column>
          <el-table-column prop="examTipsTitle" label="考试名称" width="250">
          </el-table-column>
          <el-table-column prop="signupBegintime" label="报名开始时间" width="150">
          </el-table-column>
          <el-table-column prop="signupEndtime" label="报名结束时间" width="150">
          </el-table-column>
          <el-table-column prop="examBegintime" label="考试开始时间" width="150">
          </el-table-column>
          <el-table-column prop="examEndtime" label="考试结束时间" width="150">
          </el-table-column>
          <el-table-column prop="examUrl" label="报名地址" width="320">
            <template slot-scope="scope">
              <a :href="scope.row.examUrl" target="_blank" class="buttonText" style="text-decoration:none;" >{{scope.row.examUrl}}</a>
            </template>
          </el-table-column>
          <el-table-column label="状态" prop="status"  width="80">
            <!--              拿到这一行的所有数据scope.row-->
            <template slot-scope="scope">
                <el-switch v-model="scope.row.status" :active-value="1" :inactive-value="0" @change="changeStatus(scope.row.id,scope.row.status)"></el-switch>
            </template>
          </el-table-column>
          <el-table-column prop="operation" label="操作" >
            <template slot-scope="scope">
            <el-row>
<!--              <el-button icon="el-icon-search" circle></el-button>-->
              <el-button type="primary" icon="el-icon-edit" circle @click.native="$router.push({path:'/ExamEdit',query:{examId:scope.row.id}})"></el-button>
<!--              <el-button id="examId" type="primary" icon="el-icon-edit" circle v-on:click="editMyExam(scope.row.id)" ></el-button>-->
              <el-tooltip class="item" effect="dark" content="添加成绩公布信息" placement="top" :enterable="false">
                <el-button type="success" icon="el-icon-check" circle @click.native="$router.push({path:'/AddExamScoreOpen',query:{examId:scope.row.id,examTipsTitle:scope.row.examTipsTitle}})"></el-button></el-tooltip>
<!--              <el-button type="info" icon="el-icon-message" circle></el-button>-->
<!--              <el-button type="danger"  icon="el-icon-delete" circle></el-button>-->
              <el-tooltip class="item" effect="dark" content="删除该成绩有可能影响数据统计，可修改状态停止考生报名" placement="top" :enterable="false">
                <el-button type="danger" icon="el-icon-delete" circle v-on:click="deleteExam(scope.row.id)"></el-button></el-tooltip>
            </el-row>
            </template>
          </el-table-column>
        </el-table>
        <el-row>
          <div class="block" style="text-align:center;">
            <el-pagination @size-change="sizeChange"
                           @current-change="currentChange"
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
  export default {
    mounted() {
      this.submitClick();
    },
    methods: {
      sizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.pageSize = val;
        this.submitClick();
      },
      currentChange(val) {
        console.log(`当前页: ${val}`);
        this.currentPage = val;
        this.submitClick();
      },
      changeStatus(id,status){
        alert("修改"+id+"的状态为"+status);
        this.getRequest(`/examTips/editStatus?status=${status}&id=${id}`)
      },
      // queryPage() {
      //     this._后台方法名(自己命名).(this.params).then(result => {
      //         // 一般后天用字符串，前段用json所以这里涉及到字符串的转换
      //         //这个语法就将后台传来的字符串转换成前段可以截取的json
      //         let data = JSON.parse(result);
      //         // 看后台的返回值，一般出现0就是成功，出现500就是失败了
      //         if (data.status == 0) {//上面自己定义的一个数组将这个数组接收我们后台传入的数据
      //             this.tableData = data.result.list;//这个地方不一定有list,要看数据反馈的是否有层级
      //             this.tableData.forEach(item => {
      //                 item.RQ = item.RQ.substr(0, 10)
      //             })
      //             this.page.totalRecords = data.result.totalRecords;//分页
      //
      //         }
      //
      //     })
      // },
      // editMyExam:function(examId){
      //   alert(examId);
      //   this.$router.push({
      //     path: '/ExamEdit',
      //     query: {'id':examId}
      //   })
      //
      // },
      deleteExam: function (Id) {
        alert(Id);
        return this.getRequest(`/examTips/editDeleted?deleted=1&id=${Id}`).then( res => {
          this.submitClick()  })
      },

      submitClick: function () {
        // console.log(this.filterForm);
        return  this.getRequest(`/examTips/queryPage?pageNum=${this.currentPage}&pageSize=${this.pageSize}&deleted=${this.deleted}`)
          .then(
            res => {
              this.tableData = res.data.data.items || [];
              this.totalNum = res.data.data.totalNum || 0;
            },
            err => {
              // console.log(err);
            }
          );
      }
    },
    data() {
      return {
        // tableData: Array(6).fill(item),
        currentPage: 1, // 当前页
        pageSize: 5, // 每页回答数量
        deleted: 0, // 回答总数
        totalNum: 0,
        tableData: [],
        input3:'',
        select:'',
        name :this.GLOBAL.name,
        // page: {
        //     currentPage: 1, //当前页
        //     pageSize: 5, //每页条数,  默认10条
        //     totalNum: 0, //总条数
        // }
      }
    }
  };
</script>
<style lang="stylus" scoped>
  @import '~common/style/variable.styl'

  .offer-manage-wrapper
    .upgrade-notice-wrapper .text-box
      margin: 62px auto;
    .set-icon
      color: #C0C4CC;
      margin-left 5px;
      cursor pointer;
    .offer-manage-card
      margin-bottom 25px
      .offer-manage-tab
        .el-tabs__item
          padding 0 20px

  .offer-b-card {
            background: #fff;
            border-radius: 5px;
            -webkit-box-shadow: 4px 4px 10px 0 rgba(0,0,0,0.12);
            box-shadow: 4px 4px 10px 0 rgba(0,0,0,0.12);
            padding: 0 28px 0 28px;
    }
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
  .el-input-group {
    width: 50%;
    margin-left: 20px;
    margin-bottom: 20px;
  }
  .el-select {
    width: 130px;
  }

</style>
<style>
  .el-pagination .el-select {
    width: 130px;
  }
  .el-input-group__prepend {
    padding: 5px 27px;
    width: 62px;
  }
  .el-pagination {
    padding: 20px 23px;
  }
</style>



