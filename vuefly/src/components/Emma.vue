<template>
  <div class="new-topic-wrapper new-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <!-- <el-breadcrumb separator="/">
      <el-breadcrumb-item center :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item center>内容管理</el-breadcrumb-item>
      <el-breadcrumb-item center :to="{ path: '/topic-manage' }">话题管理</el-breadcrumb-item>
      <el-breadcrumb-item center>{{topicId ? "编辑话题" : "创建话题"}}</el-breadcrumb-item>
    </el-breadcrumb>-->
    <div class="offer-b-card new-topic-card">
      <div class="offer-b-title new-topic-title"> 创建话题</div>
      <el-form
        label-position="left"
        ref="newTopicForm"
        label-width="80px"
      >
        <el-form-item label="标题" prop="title">
          <el-input placeholder="请输入标题" v-model="newTopicForm.title" :disabled="ifEdit"></el-input>
        </el-form-item>
        <el-form-item prop="content" class="new-description" label="介绍">
          <el-input
            type="textarea"
            placeholder="请输入话题介绍"
            max-length="120"
            class="h136"
          ></el-input>
         <!-- <span class="counter">{{newTopicForm.content.length}}/120</span>-->
        </el-form-item>
        <!-- <el-form-item label="发布范围" prop="rangeId">
          <el-select v-model="newTopicForm.rangeId" placeholder="请选择" multiple class="w375">
            <el-option label="上海" value="444:上海"></el-option>
            <el-option label="北京" value="333:北京"></el-option>
            <el-option label="深圳" value="111:深圳"></el-option>
            <el-option label="广州" value="222:广州"></el-option>
          </el-select>
        </el-form-item>-->
        <el-form-item label="启用" prop="isShow">
          <el-switch
            v-model="newTopicForm.isShow"
            active-color="#FFCF10"
            inactive-color="#ececec"
            :active-value="1"
            :inactive-value="0"
          ></el-switch>
        </el-form-item>
        <el-form-item class="releasing">
          <el-button
            type="primary">
<!--            :loading="releasing"
            @click="releaseTopic('newTopicForm')"-->
           发布</el-button>
          <router-link :to="{ path: '/Home'}">
            <el-button>取消</el-button>
          </router-link>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<!--<script>

    export default {
        name: "offer-new-topic",
        data() {
            return {
                newTopicForm: {
                    content: "", // (string, optional): 内容 ,
                    id: "", // (string, optional): 主键ID：新增无，编辑必传 ,
                    isShow: "", // (integer, optional): 是否启用：0-否，1-是 ,
                    picUrl: "", // (string, optional): 图片链接 ,
                    // rangeId: [], // (string, optional): 发布范围-组织机构ID，用逗号隔开 ,
                    // rangeName: "", // (string, optional): 发布范围-组织机构名称，用逗号隔开 ,
                    title: "", // (string, optional): 标题 ,
                    typeId: "" // (integer, optional): 类型：1-链接，2-图文
                },
                picUrl: "",
                // 新增资讯表单验证规则
                newTopicFormRules: {
                    title: [
                        { required: true, message: "请输入话题标题", trigger: "blur" },
                        { min: 1, max: 32, message: "长度不多于32个字符", trigger: "blur" }
                    ],
                    content: [
                        // { required: true, message: "请输入话题介绍", trigger: "blur" },
                        { min: 1, max: 120, message: "长度不多于120个字符", trigger: "blur" }
                    ],
                    // rangeId: [
                    //   { required: true, message: "请选择发布范围", trigger: "blur" }
                    // ],
                    isShow: [{ required: true, message: "请选择是否启用", trigger: "blur" }]
                },
                detailLoading: false, // 详情数据获取状态
                releasing: false // 发布中状态
            };
        },
        components: {
            UploadImage
        },
        activated() {
            if (this.topicId) {
                this.detailLoading = true;
                this.$http
                    .get(`${this.domain}getTopic`, {
                        id: this.topicId
                    })
                    .then(res => {
                        let topicDetail = res.data;
                        Object.entries(this.newTopicForm).forEach(([key, value]) => {
                            this.newTopicForm[key] = topicDetail[key];
                        });
                        // let rangeIdArr = topicDetail.rangeId.split(",");
                        // let rangeNameArr = topicDetail.rangeName.split(",");
                        // this.newTopicForm.rangeId = rangeIdArr.map((i, index) => {
                        //   return `${i}:${rangeNameArr[index]}`;
                        // });
                        this.detailLoading = false;
                        this.picUrl = this.newTopicForm.picUrl;
                        this.$refs.newTopicForm.clearValidate();
                    });
            } else {
                this.resetForm();
            }
        },
        methods: {
            releaseTopic(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.releasing = true;
                        // let rangeIdArr = this.newTopicForm.rangeId.map(i => {
                        //   return i.split(":")[0];
                        // });
                        // let rangeNameArr = this.newTopicForm.rangeId.map(i => {
                        //   return i.split(":")[1];
                        // });
                        // let newTopicForm = Object.assign({}, this.newTopicForm, {
                        //   rangeId: rangeIdArr.join(","),
                        //   rangeName: rangeNameArr.join(",")
                        // });
                        this.$http.post(`${this.domain}saveTopic`, this.newTopicForm).then(
                            res => {
                                // console.log(res);
                                this.releasing = false;
                                this.$notify({
                                    type: "success",
                                    message: "发布成功。"
                                });
                                this.resetForm();
                                this.$router.push("/Home");
                            },
                            err => {
                                this.releasing = false;
                            }
                        );
                    } else {
                        // console.log("error submit!!");
                        return false;
                    }
                });
            },
            // 重置表单数据
            resetForm() {
                Object.entries(this.newTopicForm).forEach(([key, value]) => {
                    this.newTopicForm[key] = "";
                });
                this.newTopicForm.isShow = 1;
                this.picUrl = "";
                this.$nextTick(_ => {
                    this.$refs.newTopicForm.clearValidate();
                });
            }
        },
        computed: {
            ...mapState(["domain"]),
            // 被编辑的资讯id
            topicId() {
                return this.$route.params.id || null;
            },
            // 是否编辑
            ifEdit() {
                return !(this.topicId == undefined);
            }
        },
        watch: {
            picUrl() {
                this.newTopicForm.picUrl = this.picUrl;
                this.$refs.newTopicForm.validateField("picUrl");
            }
        }
    };
</script>-->

<!--<style lang="stylus">
  .select-image-dialog.new-topic {
    .el-dialog__body {
      .image-container {
        .el-radio-group {
          .image {
            height: 90px;
          }
        }
      }
    }
  }
</style>-->

