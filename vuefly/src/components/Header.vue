<template>
  <div class="offer-home-wrapper">
    <offer-header></offer-header>
    <div class="offer-home-container">
      <offer-nav-menu ></offer-nav-menu>
      <div class="offer-home-content">
        <keep-alive>
          <router-view v-if="pageOk"></router-view>
        </keep-alive>
      </div>
      <div>{{pageOk}}</div>
    </div>
  </div>
</template>
<script>
    import OfferNavMenu from "../base/menu";
    import OfferHeader from "../base/header";
    import { mapState } from "vuex";

    export default {
        name: "offer-home",
        data() {
            return {
                // 需要进行权限控制的页面
                needControlLinks: [
                    "/offer-manage",
                    "/index",
                    "/course-center",
                    "/course-manage",
                    "/report-analysis",
                    "/communication",
                    "/data-statistics",
                    "/more-service",
                    "/news-manage",
                    "/topic-manage",
                    "/qa-manage",
                    "/activity-manage",
                    "/message-manage",
                    "/banner-manage",
                    "/data-statistics",
                    "/company-manage",
                    "/asset-consumption"
                ]
            };
        },
        components: {
            OfferNavMenu,
            OfferHeader
        },
        computed: {
            ...mapState(["powerLinks"]),
            pageOk() {
                if (
                    this.powerLinks.indexOf(this.$route.path) < 0
                ) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    };
</script>
<style lang="stylus">
  .offer-home-container {
    min-width: 1066px;

    .offer-home-content {
      overflow: hidden;
      padding: 0 26px;
    }
  }
</style>
