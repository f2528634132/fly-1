<template>
  <div class="nav-menu-wrapper" :class="[isCollapse ? 'is-collapse': '']" >
    <div class="nav-menu" :class="[isCollapse ? 'is-collapse': '']">
      <el-menu
        :collapse="isCollapse"
        :collapse-transition="false"
        class="el-menu-vertical-demo"
        :default-active="$route.path"
        :router="true"
      >
        <template v-for="(menu, index) in menuArr">
          <!-- 有子菜单 -->
          <el-submenu
            :index="index+''"
            :key="index"
            v-if="!menu.url"
            :class="{'is-active': menu.childNodes.map(l => {return l.url}).includes($route.path)}"
          >
            <template slot="title">
<!--              <svg-icon-->
<!--                :iconClass="menu.childNodes.map(l => {return l.url}).includes($route.path) ? menu.icon + '-active' : menu.icon"-->
<!--              ></svg-icon>-->
              <div class="menu-text" :title="menu.name">{{menu.name}}</div>
              <div v-if="isCollapse" class="float-right right-icon">
<!--                <svg-icon iconClass="arrow-right"></svg-icon>-->
              </div>
            </template>
            <!-- <el-menu-item-group :key="index" v-for="(item,index) in menu.childNodes"> -->
            <!-- <router-link :to="item.url" v-if="item.type != 2"> -->
            <el-menu-item :index="item.url" :key="index" v-for="(item,index) in menu.childNodes" v-if="item.type != 2">
              <div class="menu-text">{{item.name}}</div>
            </el-menu-item>
            <!-- </router-link> -->
            <!-- </el-menu-item-group> -->
          </el-submenu>
          <!-- 没有子菜单 -->
          <!-- <router-link class="link" :key="index" :to="menu.url" v-else> -->
          <el-menu-item :index="menu.url" :key="index" class="hover-nav-bg" v-else>
<!--            <svg-icon-->
<!--              :iconClass="$route.path.includes(menu.url) ? menu.icon + '-active' : menu.icon"-->
<!--            ></svg-icon>-->
            <div class="menu-text">{{menu.name}}</div>
            <div class="float-right right-icon">
<!--              <svg-icon iconClass="arrow-right"></svg-icon>-->
            </div>
          </el-menu-item>
          <!-- </router-link> -->
        </template>
      </el-menu>
      <i :class="isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'" @click="isCollapse = !isCollapse"></i>
    </div>
  </div>
</template>
<script>
    import { mapState } from "vuex";

    export default {
        data() {
            return {
                isCollapse: false,
                menuAll: [
                    {
                        name: "首页",
                        url: "/Home",
                        icon: "index",
                        childNodes: []
                    }
                ]
            };
        },
        computed: {
            ...mapState(["functionNodes"]),
            menuArr() {
                this.menuAll = []
                console.log(this.functionNodes[0])
                let arr = this.functionNodes.filter(i => {
                    return i.type == 0
                })
                this.menuAll.splice(1, 0, ...arr);
                return this.menuAll
            }
        }
    };
</script>

<style lang="stylus">
  /*@import '~common/style/variable.styl'*/

  .nav-menu-wrapper
    width: 226px;
    float left;
    height: calc(100vh - 69px);
    &.is-collapse
      width 80px

    .nav-menu
      background $color-background-d
      position fixed
      left 0
      z-index 1001
      width 226px
      height calc(100vh - 69px)
      box-shadow 0 0 10px rgba(0,0,0,.12)
      transition all .3s
      [class^=el-icon]
        font-size 24px
        display inline-block
        width 100%
        text-align center
        height 58px
        line-height 58px
        border-top 1px solid $color-line-b
        cursor pointer
        position absolute
        bottom 0
      &.is-collapse
        width 80px
        overflow hidden
        .el-menu-vertical-demo
          width 80px
          .el-menu-item, .el-submenu__title
            text-align center
            .svg-icon
              margin 0
              width 100%
              display inline-block
            .menu-text, .right-icon
              opacity 0
              display none
      .el-menu-vertical-demo
        border: none;
        width: 226px
        overflow auto
        height calc(100vh - 127px )
        transition all .01s .3s
        padding-bottom: 10px;
        .svg-icon
          margin-left 14px
          margin-right 24px
          font-size 24px
        .right-icon
          position absolute
          right 20px
          top 0
          .svg-icon
            margin 0
            font-size 12px
            color #909399
        .menu-text
          color $color-text
          font-size $font-size-medium
          display:inline-block;
        .el-menu-item
          position relative
          height 58px
          border-top 8px solid #fff
          border-bottom 8px solid #fff
          line-height 42px
          &:focus, &:hover
            background #F8F8F8;
            border-top 8px solid #F8F8F8
            border-bottom 8px solid #F8F8F8
          &.is-active
            color inherit
            background #fff
            border-top 8px solid #fff
            border-bottom 8px solid #fff
            box-shadow 0 0 8px rgba(0,0,0,0.08)
            position relative
            z-index 1
            &:before
              content ""
              position absolute
              width 5px
              height 43px
              background $color-theme
              left 0
              top 50%
              transform translateY(-50%)
        .el-submenu
          position relative
          &.is-active
            border none
            box-shadow 0 0 10px rgba(0,0,0,.12)
            z-index 1
            .el-submenu__title
              position relative
              box-shadow 0 0 10px rgba(0,0,0,.12)
              z-index 1
              &:before
                content ""
                position absolute
                width 5px
                height 43px
                background $color-theme
                left 0
                top 50%
                transform translateY(-50%)
          .el-menu-item
            text-align center
            &.is-active
              background #ececec
              box-shadow none
              border-top 8px solid #ececec
              border-bottom 8px solid #ececec
              &:before
                background #ececec
          .el-submenu__title
            &:hover
              background #F8F8F8;
  .el-menu--vertical
    width 98px
    overflow hidden
    margin-left 5px
    box-shadow 0 0 10px rgba(0,0,0,.12)
    .el-menu--popup-right-start
      width 98px
      margin 0
      .el-menu-item__title
        display none
      .el-menu-item
        height 34px
        line-height 34px
        width 98px
        &.is-active
          background $color-background-g
          color $color-text
        &:focus, &:hover
          background #f8f8f8
</style>
