<template>
  <div>
    <div class="header-wrapper">
      <header class="header">
        <div class="logo center">
          <img class="svg-icon" :src="imgUrl" />
        </div>

<!--    <el-header style="text-align: right; font-size: 15px">-->
      <!--    <el-header>-->
      <!--        <span class="font-face" style="margin-left: 15px">ExamTips</span>-->
        <div class="section-right">
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>个人信息</el-dropdown-item>
          <el-dropdown-item>修改密码</el-dropdown-item>
          <el-dropdown-item @click.native="ClickOut">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>{{name}}</span>
        </div>
<!--    </el-header>-->
    <!--    cnm<router-view/>-->
      </header>

    </div>
  </div>
</template>

<script>
    import { mapState } from "vuex";
    export default {

        data(){
            return{
                name :this.GLOBAL.name,
                imgUrl:'../static/images/logo.png'
            }
        },
        // mounted() {
        //     this.initFunction();
        // },
        computed:{
            ...mapState(["powerLinks"])
        },
        activated() {
                this.getRequest(`/role/queryUserFunction`)
                    .then(res => {
                        let functionNodes = res.data.data || [];
                        this.$store.commit("setFunctionNodes", functionNodes);
                        // 页面功能权限
                        let powerLinks = [];
                        // 按钮功能权限
                        let nodeTypeTwo = [];
                        let navList = this.navList;
                        functionNodes.forEach(page => {
                            if (page.url) {
                                powerLinks.push(page.url);
                            }
                            if (page.type == 3) {
                                navList.push(page);
                            }
                            page.childNodes.forEach(node => {
                                if (node.type == 2) {
                                    nodeTypeTwo.push(node.name);
                                }

                                if (node.url) {
                                    powerLinks.push(node.url);
                                }
                            });
                        });
                        powerLinks.splice(1, 0, ...this.powerLinks);
                        this.$store.commit("setNodeTypeTwo", nodeTypeTwo);
                        console.log("aaaaa"+powerLinks)
                        this.$store.commit("setPowerLinks", powerLinks);
                        this.$store.commit("setNavList", navList);
                    });
        },
        methods: {
            ClickOut:function () {
                return  this.getRequest(`/auth/logout`).then(
                    res=>(
                        this.$router.push({
                            path:`/Login`
                        })
                    ))
            }

        },
    };
</script>

<style lang="stylus">
  @import '~common/style/variable.styl';

  .header-wrapper {
    height: 69px;

    .header {
      position: fixed;
      top: 0;
      width: 100%;
      height: 69px;
      background: #fff;
      z-index: 1002;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.12);

      // box-shadow 0 0 4px 0 #dcdcdc
      // border-bottom 1px solid #ececec
      .logo {
        display: inline-block;
        width: 400px;
        line-height: 69px;
        font-size: 54px;
        margin-left: 25px;

        .svg-icon {
          float: left;
          margin-top: 17px;
          margin-right: 19px;
          line-height: 50px;
          height: 40px;
          vertical-align: top;
          font-size: 136px;
        }
      }

      .el-input {
        width: 238px;
        vertical-align: middle;
        margin-left: 21px;
      }

      .section-right {
        display: inline-block;
        vertical-align: middle;
        position: absolute;
        line-height: 69px;
        right: 73px;

        .experience-days {
          display: inline-block;
          margin-right: 28px;
          height: 28px;
          line-height: 26px;
          border: 1px solid #C3C3C3;
          border-radius: 19px;
          background: #ECECEC;
          font-size: 14px;
          color: #585858;
          padding: 0 12px;
        }

        .nav-dot {
          position: absolute;
          left: 137px;
          top: -5px;
        }

        .icon {
          font-size: 23px;
        }

        .section-text {
          font-size: $font-size-medium;
          color: #707070;
          margin-right: 19px;
          font-family: 'Microsoft YaHei';
        }

        .user-info {
          font-size: $font-size-medium;
        }
      }
    }
  }

  .feeling-dialog {
    .el-dialog__body {
      padding: 38px 42px;

      .notice-msg {
        line-height: 25px;
      }

      .el-button--primary.go-submit {
        display: block;
        margin: 0 auto;
        margin-top: 53px;
        padding: 0 64px;
      }
    }
  }

  .question-dialog {
    .question-content {
      width: 100%;
      border-radius: 8px;
      position: relative;

      .question-text-num {
        position: absolute;
        bottom: -5px;
        right: 20px;
        font-size: 14px;
        color: #ccc;
      }

      textarea {
        width: 100%;
        height: 190px;
        box-sizing: border-box;
        padding-top: 15px;
        padding-bottom: 30px;
        font-size: 16px;
        color: #333;
      }
    }

    .el-upload--picture-card, .el-upload-list__item {
      width: 80px;
      height: 80px;
      line-height: 80px;
    }

    .el-form-item__label {
      font-weight: bold;
    }

    .pic-list {
      width: 100%;
      display: flex;
      flex-wrap: wrap;

      .pic-item {
        width: 80px;
        height: 80px;
        position: relative;
        border-radius: 4px;
        margin-right: 8px;

        img {
          width: 100%;
          height: 100%;
        }

        .remove-pic {
          position: absolute;
          top: -5px;
          right: -3px;
          font-size: 16px;
        }
      }

      .add-pic {
        width: 80px;
        height: 80px;
        line-height: 88px;
        text-align: center;
        border-radius: 4px;
        display: block;
        border: 1px dashed #999;
        position: relative;

        .add-pic-icon {
          font-size: 32px;
          color: #ccc;
          margin-top: 5px;
        }
      }
    }

    .tip {
      color: #c3c3c3;
      font-size: $font-size-medium;
    }

    .avatar-uploader .el-upload {
      border: 1px dashed $color-background;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }

    .avatar-uploader-icon {
      font-size: $font-size-medium;
      color: $color-text-a;
      width: 120px;
      height: 120px;
      line-height: 120px;
      text-align: center;
    }

    .avatar {
      width: 333px;
      height: 74px;
      display: block;
    }
  }

  .head-notice-pop {
    padding: 0;

    .notice-card {
      border: none;

      .el-card__header {
        height: 56px;
        line-height: 56px;
        padding: 0 18px;
      }
      .notice-list {
        &::-webkit-scrollbar {
          width:5px;
        }
        &::-webkit-scrollbar-track {
          border-radius:10px;
        }
        &::-webkit-scrollbar-thumb {
          border-radius:10px;
        }
      }
      .notice-title {
        font-size: $font-size-medium-x;
        font-weight: bold;
        color: $color-text;
      }

      .notice-action {
        font-size: $font-size-medium;
        color: $color-text-a;
      }
      .more-notice {
        text-align: center;
        padding: 12px 0;
      }
      .el-card__body {
        padding: 0;

        .notice-item {
          width: 100%;
          height: 70px;
          padding: 10px 18px 0px 18px;
          border-bottom: 1px solid $color-line-b;
          display: flex;

          &:hover {
            background: #F8F8F8
          }
          .notice-type {
            width: 20%;
            display: inline-block;
            width: 45px;
            height: 45px;
            background-color: $color-background;
            border-radius: 50%;
            line-height: 45px;
            text-align: center;
          }

          .notice-content {
            padding-left: 16px;
            width: 80%;
            display: inline-block;
            vertical-align: middle;
          }

          .notice-message {
            color: $color-text;
          }
          .is-read {
            color: $color-text-a;
          }
          .readed {
            color: $color-text-a;
            background-color: #f8f8f8;
          }
          .notice-time {
            color: $color-text-a;
            font-size: $font-size-small;
          }
        }

        .load-more-notice {
          height: 56px;
          line-height: 56px;
          padding: 0 18px;
          text-align: center;
          color: $color-text-a;
        }
      }
    }
  }

  .head-user-pop {
    .user-container {
      .basic-container {
        padding: 21px 22px;
        border-bottom: 1px solid $color-background;

        .pic-inner {
          vertical-align: top;
          overflow: hidden;
          display: inline-block;
          width: 62px;
          height: 62px;
          border-radius: 50%;
        }

        .user-pic {
          width: 100%;
        }

        .user-basic {
          margin-top: 8px;
          display: inline-block;

          .hello {
            color: $color-text;
            font-weight: 700;
            font-size: $font-size-medium;
            display: block;
            margin-bottom: 5px;
          }

          .tag {
            background-color: $color-background-b;
            padding: 2px 8px;
            border-radius: 50px;
            font-size: $font-size-small;
          }
        }
      }

      .quick-enter {
        padding: 21px 22px;

        .quick-item {
          cursor: pointer;
          color: $color-text;
          display: inline-block;
          width: 100%;
          text-align: center;
          margin-bottom: 22px;
        }

        .log-off {
          width: 100%;
          padding: 3px 10px;
          border: 1px solid $color-line-b;
          border-radius: 4px;
          text-align: center;
          color: $color-text-a;
        }
      }
    }
  }
</style>
