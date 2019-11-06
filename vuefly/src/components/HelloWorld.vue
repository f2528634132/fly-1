<template>
  <div class="gwc-home-wrapper">
    <gwc-header></gwc-header>
    <div class="gwc-home-container">
      <gwc-nav-menu></gwc-nav-menu>
      <div class="gwc-home-content">
        <router-view v-if="$route.meta.unKeepAlive"></router-view>
        <keep-alive v-else>
          <router-view></router-view>
        </keep-alive>
      </div>
    </div>
  </div>
</template>
<script>
  import GwcNavMenu from "base/menu";
  import GwcHeader from "base/header";
  import { mapState } from "vuex";

  export default {
    name: "gwc-home",
    components: {
      GwcNavMenu,
      GwcHeader
    },
    computed: {
      ...mapState(["userInfo", "domain", "menus"])
    },
    methods:{
      getPower(){
        this.$http.get(`${this.domain}user/permission/current/code`).then(res => {
          let power = res.data || [];
          this.$store.dispatch("setPower", power || []);
        });
      }
    },
    created() {
      if (this.userInfo) {
        if (this.menus.length === 0) {
          this.$http.get(`${this.domain}user/menu/current/list`).then(res => {
            let data = res.data || [];
            this.$store.dispatch("setMenus", data || []);
            this.getPower()
          });
        }
      } else {
        router.push({
          path: "/login"
        });
      }
    }
  };
</script>
<style lang="stylus">
  .gwc-home-container {
    min-width: 1366px;

    .gwc-home-content {
      overflow: hidden;
      padding: 0 26px;
    }
  }
</style>
