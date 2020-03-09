<template>
  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>广告栏目管理</el-breadcrumb-item>
      <el-breadcrumb-item>教材真题</el-breadcrumb-item>
    </el-breadcrumb>

        <el-tabs type="border-card" @tab-click="InitTutorial" >
          <el-tab-pane>
            <span slot="label"><i class="el-icon-circle-plus-outline" style="margin-right: 5px"></i>新增推荐教材</span>
            <div class="form_box" style="margin-left: 50px;margin-top: 40px">
              <el-form :model="tutorialInputVo" style="width: 500px" ref="tutorialFormRef">
                <el-form-item label="教材名称" prop="tutorialName" >
                  <el-input v-model="tutorialInputVo.tutorialName" placeholder="教材名称" class="small_form_item"></el-input>
                </el-form-item>
                <el-form-item label="出版社" style="padding-left: 12px" prop="tutorialPress">
                  <el-input v-model="tutorialInputVo.tutorialPress" placeholder="出版社" class="small_form_item"></el-input>
                </el-form-item>
                <el-form-item label="教材价格"prop="tutorialPrice">
                  <el-input v-model="tutorialInputVo.tutorialPrice" placeholder="￥" class="small_form_item"></el-input>
                </el-form-item>
                <el-form-item label="教材封面" prop="tutorialPicture" style="margin-right:50px;margin-bottom:0px;height: 250px">
                  <el-upload
                    class="upload-demo"
                    :on-success="handleSuccess"
                    drag
                    :before-upload="beforeUpload"
                    action="1"
                    multiple>
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                  </el-upload>
                </el-form-item>
                <el-form-item label="教材简介" prop="tutorialSynopsis">
                  <el-input
                    type="textarea"
                    placeholder="请输入内容"
                    v-model="tutorialInputVo.tutorialSynopsis"
                    maxlength="300"
                    show-word-limit style="width: 400px">
                  </el-input>
                </el-form-item>
                <el-form-item label="教材详情" prop="tutorialComment">
                  <el-input
                    type="textarea"
                    placeholder="请输入内容"
                    v-model="tutorialInputVo.tutorialComment"
                    maxlength="300"
                    show-word-limit style="width: 400px">
                  </el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="addTutorial">确定添加</el-button>
                  <el-button type="info" @click="resetTutorialForm">重置</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-tab-pane>
          <el-tab-pane name="2" lazy="true">
            <span slot="label"><i class="el-icon-edit" style="margin-right: 5px"></i>线上真题</span>



            <el-button type="primary" size="mini">批量删除</el-button>
            <el-button type="primary" plain size="mini">批量导出</el-button>
            <el-button type="primary" size="mini" @click="dialogFormVisible = true">上传真题<i class="el-icon-upload el-icon--right"></i></el-button>
            <el-dialog title="上传真题" :visible.sync="dialogFormVisible">
              <el-form :model="tutorialInputVo" ref="tutorialFormRef">
                <el-form-item label="真题名称" :label-width="formLabelWidth" style="width: 550px">
                  <el-input v-model="tutorialInputVo.tutorialName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="真题出处" :label-width="formLabelWidth" style="width: 550px">
                  <el-input v-model="tutorialInputVo.tutorialPress" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="上传真题" :label-width="formLabelWidth">
                  <el-upload
                    class="upload-demo" :on-success="handleSuccess"
                    drag :before-upload="beforeUpload"
                    action="1"
                    multiple>
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                    <div class="el-upload__tip" slot="tip" style="margin-left: 50px">可上传jpg/png/word文件，且不超过500kb</div>
                  </el-upload>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogSubmit">确 定</el-button>
              </div>
            </el-dialog>

            <div><a href="http://localhost:8088/static/onebird.jpeg" download="文件.txt">下载</a>
            </div>

            <el-table ref="multipleTable" :data="onlineTableData" tooltip-effect="dark" style="width: 100%;margin-top: 20px"
                      :header-cell-style="{background:'#eef1f6',color:'#606266'}"  @selection-change="handleSelectionChange">
              <el-table-column type="selection" width="55"></el-table-column>
              <el-table-column prop="tutorialName" label="真题名称" >
              </el-table-column>
              <el-table-column prop="tutorialPress" label="真题出处" >
              </el-table-column>
              <el-table-column prop="status" label="状态" >
                <template slot-scope="scope">
                  <el-switch v-model="scope.row.status" :active-value="1" :inactive-value="0" @change="changeStatus(scope.row.id,scope.row.status)"></el-switch>
                </template>
              </el-table-column>
              <el-table-column  align="right">
                <template slot="header" slot-scope="scope">
                <el-input
                  v-model="search"
                  size="mini"
                  placeholder="输入关键字搜索"/>
               </template>
                <template slot-scope="scope">
                  <div style="margin-right: 170px">
                    <el-button size="mini" icon="el-icon-download" type="warning" ></el-button>
                 <el-button
                  size="mini" type="primary">编辑</el-button>
                  <el-button
                    size="mini"
                    type="danger">删除</el-button></div></template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane name="3" lazy="true">
            <span slot="label"><i class="el-icon-reading" style="margin-right: 5px"></i>推荐教材</span>
            <el-table  :data="entityTableData" ref="tableData" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
              <el-table-column prop="tutorialPicture" label="封面" >
                <template slot-scope="scope">
                  <el-avatar shape="square" :size="100" fit="contain" :src="scope.row.tutorialPicture"></el-avatar>
                </template>
              </el-table-column>
              <el-table-column prop="tutorialName" label="教材名称" >
              </el-table-column>
              <el-table-column prop="tutorialPress" label="出版社" >
              </el-table-column>
              <el-table-column prop="tutorialSynopsis" label="简介" >
              </el-table-column>
              <el-table-column prop="tutorialPrice" label="教材价格" >
              </el-table-column>
              <el-table-column prop="tutorialSellNumber" label="销量" >
              </el-table-column>
              <el-table-column prop="status" label="状态" >
                <template slot-scope="scope">
                  <el-switch v-model="scope.row.status" :active-value="1" :inactive-value="0" @change="changeStatus(scope.row.id,scope.row.status)"></el-switch>
                </template>
              </el-table-column>
              <el-table-column label="操作" >
                <i class="el-icon-more"></i>
              </el-table-column>
            </el-table>
          </el-tab-pane>



        </el-tabs>
      </div>
    </template>

    <script>
      export default {
        name: "Institution",
        data() {
          return {
            tutorialInputVo:{
              tutorialName:'',
              tutorialPress:'',
              tutorialPrice:'',
              tutorialPicture:'',
              tutorialSynopsis:'',
              tutorialComment:'',
              status:''
            },
            onlineTableData:[],
            entityTableData:[],
            dialogFormVisible: false,

            multipleSelection: [],
            form: {
              name: '',
              region: '',
              date1: '',
              date2: '',
              delivery: false,
              type: [],
              resource: '',
              desc: ''
            },
            formLabelWidth: '120px',
            search:'',
            tableData:[]
          }
        },
        methods: {
          handleSelectionChange(val) {
            this.multipleSelection = val;
          },
          handleSuccess(response){
          },
          beforeUpload(file){
            let fd = new FormData();
            fd.append('file', file);
            this.$http.post('file/uploadFile', fd).then((res) => {
              this.tutorialInputVo.tutorialPicture=res.data.msg;
              console.log(res.data.msg);
              console.log(this.tutorialInputVo.tutorialPicture);
            })
            return true
          },
          addTutorial(){
            if (this.tutorialInputVo.tutorialPrice == ""){
                this.tutorialInputVo.status=1;
            }else {this.tutorialInputVo.status=0;}
            let tutorialInputVo=this.tutorialInputVo;
                this.$axios({
                  method: "post",
                  url: "/tutorial/edit",
                  headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                  },
                  withCredentials: true,
                  data: JSON.stringify(tutorialInputVo)
                }).then((res) => {
                  console.log(res);
                });
          },
          resetTutorialForm (){
            this.$refs.tutorialFormRef.resetFields()
          },
          dialogSubmit(){
            this.dialogFormVisible = false;
            this.addTutorial();
          },
          InitTutorial(tab){
            if (tab.name==='2'){
              this.getRequest(`/tutorial/queryPageByOnline?pageNum=1&pageSize=10&deleted=1`).then(
                res=>{
                  this.onlineTableData=res.data.data.items||[];
                })}
            else if (tab.name==='3'){
              this.getRequest(`/tutorial/queryPageByEntity?pageNum=1&pageSize=10&deleted=1`).then(
                res=>{
                  this.entityTableData=res.data.data.items||[];
                })}
            this.resetTutorialForm();
          },

        }
      }
    </script>

    <style scoped>
      .el-form-item {
        margin-bottom: 40px;
      }
      .small_form_item {
        width: 400px;
        height: 40px;
      }
    </style>
<style>

</style>

