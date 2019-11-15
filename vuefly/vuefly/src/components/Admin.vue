<template>
<!--  <el-container>-->
<!--    <el-header style="text-align: right; font-size: 15px">-->
<!--      <el-dropdown>-->
<!--        <i class="el-icon-user-solid" style="margin-right: 15px"></i>-->
<!--        <el-dropdown-menu slot="dropdown">-->
<!--          <el-dropdown-item>个人信息</el-dropdown-item>-->
<!--          <el-dropdown-item>修改密码</el-dropdown-item>-->
<!--          <el-dropdown-item>退出登录</el-dropdown-item>-->
<!--        </el-dropdown-menu>-->
<!--      </el-dropdown>-->
<!--      <span>{{name}}</span>-->
<!--    </el-header>-->
<!--  <el-container style="height: 900px; border: 0px solid #eee">-->
<!--    <el-aside width="220px" style="background-color: rgb(238, 241, 246)">-->
<!--      <el-menu :default-openeds="['1', '3']">-->
<!--        <el-submenu index="1">-->
<!--          <template slot="title"><i class="el-icon-menu"></i>考试信息管理</template>-->
<!--          <el-menu-item-group>-->
<!--            &lt;!&ndash;            <template slot="title">分组一</template>&ndash;&gt;-->
<!--              <el-menu-item index="1-1" >考试信息</el-menu-item>-->
<!--            <el-menu-item index="1-2">成绩公布</el-menu-item>-->
<!--          </el-menu-item-group>-->
<!--        </el-submenu>-->
<!--        <el-submenu index="2">-->
<!--          <template slot="title"><i class="el-icon-orange"></i>论坛话题管理</template>-->
<!--          <el-menu-item index="3-1">论坛分类</el-menu-item>-->
<!--          <el-menu-item index="3-2">话题管理</el-menu-item>-->
<!--        </el-submenu>-->

<!--        <el-submenu index="3">-->
<!--          <template slot="title"><i class="el-icon-aim"></i>广告栏目管理</template>-->
<!--            <el-menu-item index="2-1">机构栏目</el-menu-item>-->
<!--            <el-menu-item index="2-2">教材栏目</el-menu-item>-->
<!--        </el-submenu>-->

<!--        <el-submenu index="4">-->
<!--        <template slot="title"><i class="el-icon-user"></i> 账号管理</template>-->
<!--          <el-menu-item index="2-2">个人用户</el-menu-item>-->
<!--          <el-menu-item index="2-1">管理员</el-menu-item>-->
<!--        </el-submenu>-->
<!--        <el-submenu index="5">-->
<!--          <template slot="title"><i class="el-icon-data-line"></i>数据分析</template>-->
<!--            <el-menu-item index="3-1">报考情况</el-menu-item>-->
<!--            <el-menu-item index="3-2">考试类目</el-menu-item>-->
<!--            <el-menu-item index="3-3">考生分布</el-menu-item>-->
<!--          <el-submenu index="3-4">-->
<!--            <template slot="title">行为分析</template>-->
<!--            <el-menu-item index="3-4-1">报考关注度</el-menu-item>-->
<!--          </el-submenu>-->
<!--          <el-submenu index="3-4">-->
<!--            <template slot="title">相关性分析</template>-->
<!--            <el-menu-item index="3-4-1">报考与考生身份相关性</el-menu-item>-->
<!--          </el-submenu>-->
<!--        </el-submenu>-->
<!--      </el-menu>-->
<!--    </el-aside>-->


  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <!-- <el-breadcrumb separator="/">
      <el-breadcrumb-item center :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item center :to="{ path: '/offer-manage' }">Offer管理</el-breadcrumb-item>
    </el-breadcrumb> -->
    <div class="offer-manage-card offer-b-card">

      <el-main>
        <el-row>
          <div style="margin-top: 10px;">
            <el-input placeholder="请输入内容" v-model="input3" class="input-with-select">
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
          </div>
        </el-row>
        <el-table :data="tableData" ref="tableData" header-row-class-name="center">
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
          <el-table-column prop="operation" label="操作" >
            <template slot-scope="scope">
            <el-row>
              <el-button icon="el-icon-search" circle></el-button>
              <el-button type="primary" icon="el-icon-edit" circle @click.native="$router.push({path:'/ExamEdit',query:{examId:scope.row.id}})"></el-button>
<!--              <el-button id="examId" type="primary" icon="el-icon-edit" circle v-on:click="editMyExam(scope.row.id)" ></el-button>-->
              <el-button type="success" icon="el-icon-check" circle></el-button>
              <el-button type="info" icon="el-icon-message" circle></el-button>
<!--              <el-button type="danger"  icon="el-icon-delete" circle></el-button>-->
              <el-button type="danger" icon="el-icon-delete" circle v-on:click="deleteExam(scope.row.id)"></el-button>
            </el-row>
            </template>
          </el-table-column>
        </el-table>

        <el-row>

<!--          <div class="block">
            <el-pagination
              @size-change="handleSizeChange"
              :current-page="currentPage4"
              :page-sizes="[100, 200, 300, 400]"
              :page-size="100"
              layout="total, sizes, prev, pager, next, jumper"
              :total="400">
            </el-pagination>
          </div>-->
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
<!--    </el-container>-->
      </div>
    </div>
</template>
<style lang="stylus">
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
    margin-left: 120px;
    margin-bottom: 10px;
  }
  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
  /*.el-table td, .el-table th {*/
  /*  text-align: center;*/
  /*}*/
</style>

<script>
  export default {
      mounted() {
          this.submitClick();
      },
    methods: {
        // computed: {
        //     params() {
        //         let param = {
        //             currentPage: this.currentPage,
        //             pageSize: this.pageSize,
        //             totalNum: this.totalNum,
        //             deleted: this.deleted
        //         }
        //         //拷贝，
        //         return Object.assign({}, param);
        //     },
        // },
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
      // const item = {
      //   examTipsTitle: '全国计算机等级考试1111',
      //   signupBegintime: '2019-11-2',
      //   signupEndtime: '2019-11-12',
      //   examUrl:'www.baidu.com'
      // };
//      let arr = Array(20).fill(2)
      // let arr = [1, 2, 3]
      // arr.fill = Array.fill
      // console.log(arr)
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
