<template>
    <div class="menu-wrapper">
      <div class="menu">
        <el-menu :collapse="isCollapse" class="el-menu-vertical-demo" :default-active="$route.path">
          <template v-for="(menu,index) in menus ">
<!--            有子菜单-->
            <el-submenu
              :index="index+''"
              :key="index"
              v-if="menu.list.length"
              :class="{
                'is-active':menu.list.map(l=>{
                      return l.url;
                }).includes($route.path)
              }">
              <template slot="title">
                <svg-icon :iconClass="menu.list.map(l=>{
                       return l.url;
                }).includes($route.path)?menu.icon+'-active' :menu.icon"></svg-icon>
                <div class="menu-text">{{menu.name}}</div>
                <div v-show="isCollapse" class="float-right right-icon">
                  <svg-icon iconClass="arrow-right"></svg-icon>
                </div>
              </template>
              <el-menu-item-group :key="index" v-for="(item,index)in menu.list">
                <router-link :to="item.url">
                  <el-menu-item :index="item.url" :key="item.url">
                    <div class="menu-text">{{ item.name }}</div>
                  </el-menu-item>
                </router-link>
              </el-menu-item-group>
            </el-submenu>
            <router-link class="link" :key="index" :to="menu.url" v-else>
              <el-menu-item :index="menu.url">
                <svg-icon  :iconClass="$route.path.includes(menu.url) ? menu.icon + '-active' : menu.icon"></svg-icon>
                <div class="menu-text">{{ menu.name }}</div>
                <div class="float-right right-icon">
                  <svg-icon iconClass="arrow-right"></svg-icon>
                </div>
              </el-menu-item>
            </router-link>
          </template>
        </el-menu>

      </div>
    </div>
</template>

<script>
  import { mapState } from "vuex";
    export default {
      data() {
        return {
          isCollapse: false
        };
      },
      computed: {
        ...mapState(["domain", "menus"])
      }
    }
</script>

<style lang="stylus">
  @import '~common/style/variable.styl';

  .nav-menu-wrapper {
    width: 226px;
    float: left;
    height: calc(100vh - 69px);

    .menu {
      background: $color-background-d;
      position: fixed;
      left: 0;
      z-index: 4;
      width: 226px;
      height: calc(100vh - 69px);
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.12);
      overflow: auto;
      .el-menu-vertical-demo {
        border: none;
        margin-top: 25px;
        width: 100%;
        .svg-icon {
          margin-left: 14px;
          margin-right: 24px;
          font-size: 24px;
        }
        .menu-text {
          color: $color-text;
          font-size: $font-size-medium;
          display: inline-block;
        }

        .right-icon {
          position: absolute;
          right: 20px;
          top: 0;

          .svg-icon {
            margin: 0;
            font-size: 12px;
            color: #909399;
          }
        }
        .el-menu-item, .el-submenu__title {
          height: 58px;
          border-top: 8px solid #fff;
          border-bottom: 8px solid #fff;
          line-height: 42px;
          position: relative;

          &:hover, &:focus {
            background: #fff;
            border-top: 8px solid #fff;
            border-bottom: 8px solid #fff;
          }

          &.is-active {
            color: inherit;
          }
        }

        .router-link-active {
          box-shadow: 0 0 10px rgba(0, 0, 0, 0.12);
          display: inline-block;
          width: 100%;
          position: relative;
          z-index: 1;

          .el-menu-item {
            border-top: 8px solid #fff;
            border-bottom: 8px solid #fff;
            line-height: 42px;
            box-shadow: 5px 0 0 0 #1759ad inset;
          }
        }

        .el-submenu.is-active {
          z-index: 1;
          position: relative;
          box-shadow: 0 0 10px rgba(0, 0, 0, 0.12);

          .el-submenu__title {
            box-shadow: 5px 0 0 0 #1759ad inset;
            display: inline-block;
            width: 100%;
          }
        }

        .el-menu-item-group {
          .menu-text {
            padding-left: 42px;
            color: #aaa;
          }

          .router-link-active {
            box-shadow: none;

            .el-menu-item {
              background: #ececec;
              box-shadow: none;
              border-top: 8px solid #ececec;
              border-bottom: 8px solid #ececec;
            }
          }
        }

        .el-menu-item-group__title {
          padding: 0;
        }
      }
    }
  }

  .el-menu--vertical {
    .el-menu-item-group__title {
      display: none;
    }

    .el-menu-item {
      &:hover {
        background: $color-background-g;
      }

      &.is-active {
        color: $color-theme;
      }
    }
  }
</style>

