<template>
  <div class="asset-consumption-wrapper manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <!-- <el-breadcrumb separator="/">
      <el-breadcrumb-item center :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item center>内容管理</el-breadcrumb-item>
      <el-breadcrumb-item center :to="{ path: '/asset-consumption' }">资讯管理</el-breadcrumb-item>
    </el-breadcrumb>-->
    <div class="offer-b-card asset-filter-card">
      <div class="offer-b-title">
        资产消耗
        <!-- <div class="float-right">
          <el-button type="primary" class="search-button" @click="proxyQueryInPage">查询</el-button>
          <el-button @click="resetFilterForm">重置</el-button>
        </div>-->
      </div>
      <el-alert
        class="alert-text"
        title="如需进行资产充值，请联系顾问"
        type="warning"
        show-icon>
      </el-alert>
      <ul class="asset-infos">
        <!-- <li>
          <span>智联币</span>
          <span>1200</span>
        </li> -->
        <li>
          <span>发放offer</span>
          <span>{{values.offerRemainderNum}}/{{values.offerAssetsNum}}</span>
        </li>
        <li>
          <span>短信数</span>
          <span>{{values.msgRemainderNum}}/{{values.msgAssetsNum}}</span>
        </li>
      </ul>
      <!-- 过滤筛选条件 -->
      <el-form :inline="true" :model="filterForm" class="inputs-wrapper">
        <el-form-item prop="time">
          <el-date-picker
            v-model="releaseTime"
            type="daterange"
            prefix-icon="el-icon-time"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="yyyy-MM-dd"
            @change="proxyQueryInPage"
            :picker-options="releaseTimePickerOptions"
          ></el-date-picker>
        </el-form-item>
        <el-form-item prop="time">
          <el-radio-group v-model="filterForm.consumeType" @change="proxyQueryInPage">
            <el-radio label="">全部</el-radio>
            <!-- <el-checkbox label="智联币"></el-checkbox> -->
            <el-radio :label="2">Offer数</el-radio>
            <el-radio :label="1">短信数</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <!-- 列表内容 -->
      <el-table
        ref="assetTable"
        :data="assetTable"
        style="width: 100%"
        height="500"
        v-loading="querying"
      >
        <el-table-column prop="consumeName" label="资源类型" width="280"></el-table-column>
        <el-table-column align="center" prop="consumeNum" label="资源数量"></el-table-column>
        <el-table-column align="center" prop="consumeScene" label="使用场景"></el-table-column>
        <el-table-column align="center" prop="account" label="使用者"></el-table-column>
        <el-table-column align="center" prop="createTime" label="时间" :formatter="fmtTime"></el-table-column>
      </el-table>
      <el-pagination
        class="center"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="filterForm.pageNum"
        :page-sizes="[30, 60, 100, 200, 300]"
        :page-size="filterForm.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalasset"
      ></el-pagination>
    </div>
  </div>
</template>
<script>
    import { mapState } from "vuex";

    export default {
        name: "asset-consumption-manage",
        data() {
            return {
                releaseTimePickerOptions: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    }
                },
                releaseTime: [],
                // 筛选查询入参
                filterForm: {
                    consumeType: "",// (integer, optional): 资产类型：1-短信，2-offer
                    endCreateTime: "",// (string, optional): 结束时间
                    pageNum: 1,// (integer, optional): 当前页
                    pageSize: 30,// (integer, optional): 分页大小
                    startCreateTime: "" // (string, optional): 开始时间
                },
                values: {},
                checkList: [],
                assetTable: [], // 资讯数据
                querying: false, // 数据加载状态
                totalasset: 0 // 资讯总数
            };
        },
        components: {},
        activated() {
            this.proxyQueryInPage();
            this.getValues()
        },
        watch: {
            releaseTime() {
                this.filterForm.startCreateTime =
                    (this.releaseTime && this.releaseTime[0]) || "";
                this.filterForm.endCreateTime =
                    (this.releaseTime && this.releaseTime[1]) || "";
                this.proxyQueryInPage();
            }
        },
        methods: {
            fmtTime(row, column, cellValue) {
                // 处理时间
                return cellValue ? this.$day(cellValue).format("YYYY.MM.DD HH:mm") : "-";
            },
            getValues() {
                this.$http.get(`${this.domain}assetSituation`)
                    .then(res => {
                        // console.log(res)
                        this.values = res.data
                    })
            },
            // 查询资讯列表数据代理
            proxyQueryInPage() {
                this.querying = true;
                if (this.timer) {
                    return;
                }
                this.timer = setTimeout(async () => {
                    await this.queryInPage();
                    clearTimeout(this.timer);
                    this.querying = false;
                    this.timer = null;
                }, 500);
            },
            queryInPage() {
                return this.$http.post(`${this.domain}listConsume`, this.filterForm).then(
                    res => {
                        this.assetTable = res.data.result || [];
                        this.totalasset = res.data.totalCount || 0;
                    },
                    err => {}
                );
            },
            // 修改每页展示资讯的数量
            handleSizeChange(val) {
                this.filterForm.pageSize = val;
                this.proxyQueryInPage();
            },
            // 修改当前页
            handleCurrentChange(val) {
                this.filterForm.pageNum = val;
                this.proxyQueryInPage();
            }
        },
        computed: {
            ...mapState(["domain", "manageOrgs", "userInfo"])
        }
    };
</script>
<style lang="stylus">
  @import '~common/style/variable.styl';

  .asset-consumption-wrapper
    .alert-text
      line-height 31px
      height 31px
      background-color #FFA177
      color #fff
      font-size $font-size-medium
      border-radius 0
      [class*=el-icon]
        color $color-text-r
        line-height 31px
        top 0
        font-size 18px
    .asset-infos
      font-size 0
      padding 15px 0 27px 0
      li
        display inline-block
        font-size 28px
        margin-right 147px
        color #707070
        line-height 40px
        font-weight 500
        span
          display block
          &:first-child
            line-height 24px
            font-size $font-size-medium
            color $color-text-d

</style>
