<template>
    <div class="header-wrapper">
      <header class="header">
        <div class="logo center">
          <a :href="" target="_blank" class="target">
            <span style="@font-face" >ExamTips</span>
            <img class="svg-icon" :src="`${path}static/images/nanchang.png`" />
          </a>
        </div>
        <div class="section-right">
          <a class="file-text" :href="`${path}static/_book/index.html`" target="_blank">操作手册</a>
          <el-popover placement="bottom" width="243" trigger="hover" popper-class="head-user-pop">
            <div class="user-container">
              <div class="basic-container">
                <div class="pic-inner"><img src="`${path}static/images/head.png" class="user-pic" /></div>
                <div class="user-basic">
                  <span class="hello">Hello,{{name}}</span>
                </div>
              </div>
              <div class="quick-enter center">
                <el-row>
                  <el-col :span="12"><span class="quick-item" @click="changeInformationBtn">修改信息</span> </el-col>
                  <el-col :span="12"><span class="quick-item" @click="changePassWordBtn">修改密码</span> </el-col>
                </el-row>
                <el-button @click="logout">安全退出</el-button>
              </div>
            </div>
            <span class="pointer user-info" slot="reference">
              <span>{{name}}</span>
              <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
          </el-popover>
        </div>
      </header>
    </div>
</template>

<script>
    export default {
        name: "index"
    }
</script>

<style lang="stylus">
  @import '~common/style/variable.styl';

  @font-face {
    font-family: 'webfont';
    font-display: swap;
    src: url('//at.alicdn.com/t/webfont_935i1cloybo.eot'); /* IE9*/
    src: url('//at.alicdn.com/t/webfont_935i1cloybo.eot?#iefix') format('embedded-opentype'), /* IE6-IE8 */
    url('//at.alicdn.com/t/webfont_935i1cloybo.woff2') format('woff2'),
    url('//at.alicdn.com/t/webfont_935i1cloybo.woff') format('woff'), /* chrome、firefox */
    url('//at.alicdn.com/t/webfont_935i1cloybo.ttf') format('truetype'), /* chrome、firefox、opera、Safari, Android, iOS 4.2+*/
    url('//at.alicdn.com/t/webfont_935i1cloybo.svg#庞门正道标题体2') format('svg'); /* iOS 4.1- */
  }
  .header-wrapper {
    height: 69px;


  .header {
    position: fixed;
    top: 0;
    width: 100%;
    height: 69px;
    background: #fff;
    z-index: 55;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.12);

  .logo {
    display: inline-block;
    width: 400px;
    line-height: 69px;
    font-size: 54px;
    margin-left: 25px;

  .svg-icon {
    float: left;
    margin-top: 20px;
    margin-right: 25px;
    line-height: 50px;
    height: 32px;
    vertical-align: top;
    font-size: 136px;
  }

  }
  .section-right {
      display: inline-block;
      position: absolute;
      line-height: 69px;
      right: 73px;
      .file-text {
        color: #1759ad;
        font-weight: 700;
        margin-right: 15px;
        cursor: pointer;
        font-size: 14px;
      }
      .head-user-pop {
        .user-container {
          .basic-container {
            padding: 21px 22px;
            text-align: center;
            border-bottom: 1px solid $color-background;
            .pic-inner {
              vertical-align: top;
              overflow: hidden;
              display: inline-block;
              width: 62px;
              height: 62px;
              border-radius: 50%;
              .user-pic {
                width: 100%;
              }

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
          }
        }
      }
    }
  }
  }
</style>
