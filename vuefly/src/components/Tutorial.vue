<template>
  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>广告栏目管理</el-breadcrumb-item>
      <el-breadcrumb-item>教材真题</el-breadcrumb-item>
    </el-breadcrumb>

        <el-tabs type="border-card">
          <el-tab-pane>
            <span slot="label"><i class="el-icon-edit" style="margin-right: 5px"></i>线上真题</span>
            <el-button type="primary" size="mini">批量删除</el-button>
            <el-button type="primary" plain size="mini">批量导出</el-button>
            <el-button type="primary" size="mini" @click="dialogFormVisible = true">上传真题<i class="el-icon-upload el-icon--right"></i></el-button>
            <el-dialog title="上传真题" :visible.sync="dialogFormVisible">
              <el-form :model="form">
                <el-form-item label="真题名称" :label-width="formLabelWidth" style="width: 550px">
                  <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="真题出处" :label-width="formLabelWidth" style="width: 550px">
                  <el-input v-model="form.region" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="上传真题" :label-width="formLabelWidth">
                  <el-upload
                    class="upload-demo"
                    drag
                    action="https://jsonplaceholder.typicode.com/posts/"
                    multiple>
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                    <div class="el-upload__tip" slot="tip" style="margin-left: 50px">只能上传jpg/png文件，且不超过500kb</div>
                  </el-upload>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
              </div>
            </el-dialog>

            <el-table
              ref="multipleTable"
              :data="tableData"
              tooltip-effect="dark"
              style="width: 100%;margin-top: 20px" :header-cell-style="{background:'#eef1f6',color:'#606266'}"
              @selection-change="handleSelectionChange">
              <el-table-column
                type="selection"
                width="55">
              </el-table-column>
              <el-table-column prop="courseName" label="真题名称" >
              </el-table-column>
              <el-table-column prop="courseType" label="真题出处" >
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
                    <el-button size="mini" icon="el-icon-download" type="warning"></el-button>
                 <el-button
                  size="mini" type="primary">编辑</el-button>
                  <el-button
                    size="mini"
                    type="danger">删除</el-button></div></template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane>
            <span slot="label"><i class="el-icon-reading" style="margin-right: 5px"></i>推荐教材</span>
            <el-table  :data="tableData" ref="tableData" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
              <el-table-column prop="img" label="封面" >
                <template slot-scope="scope">
                  <el-avatar shape="square" :size="100" fit="contain" :src="scope.row.img"></el-avatar>
                </template>
              </el-table-column>
              <el-table-column prop="courseName" label="教材名称" >
              </el-table-column>
              <el-table-column prop="courseType" label="出版社" >
              </el-table-column>
              <el-table-column prop="courseTeam" label="简介" >
              </el-table-column>
              <el-table-column prop="coursePrice" label="教材价格" >
              </el-table-column>
              <el-table-column prop="courseTime" label="销量" >
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

          <el-tab-pane><span slot="label"><i class="el-icon-circle-plus-outline" style="margin-right: 5px"></i>新增推荐教材</span>
            <div class="form_box" style="margin-left: 50px;margin-top: 40px">
              <el-form v-model="courseInputVo" :inline="true" >
                <el-form-item label="课程名称" class="small_form_item">
                  <el-input v-model="courseInputVo.courseName" placeholder="课程名称"></el-input>
                </el-form-item>
                <el-form-item label="上课形式" class="small_form_item">
                  <el-select v-model="courseType" placeholder="线上/线下">
                    <el-option label="线上" value="online"></el-option>
                    <el-option label="线下" value="outline"></el-option>
                    <el-option label="线上+线下" value="twoline"></el-option>
                  </el-select>
                </el-form-item>
              </el-form>
              <el-form v-model="courseInputVo" :inline="true" >
                <el-form-item label="课程所属" class="small_form_item">
                  <el-select v-model="courseOf" placeholder="自有课程/第三方合作">
                    <el-option label="自有课程" value="online"></el-option>
                    <el-option label="第三方合作" value="outline"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="所属机构" class="small_form_item">
                  <el-input v-model="courseInputVo.courseName" placeholder="所属机构"></el-input>
                </el-form-item>
                <el-form  v-model="courseInputVo" :inline="true" >
                  <el-form-item label="开课时间" class="small_form_item">
                    <el-col>
                      <el-date-picker
                        v-model="courseTime"
                        type="date"
                        placeholder="选择日期">
                      </el-date-picker>
                    </el-col>
                  </el-form-item>
                  <el-form-item label="课程价格" class="small_form_item">
                    <el-input v-model="courseInputVo.coursePrice" placeholder="￥"></el-input>
                  </el-form-item>
                </el-form>
              </el-form>
              <el-form>
                <el-form-item label="课程封面">
                  <el-upload
                    class="upload-demo"
                    drag
                    action="https://jsonplaceholder.typicode.com/posts/"
                    multiple>
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                    <div class="el-upload__tip" slot="tip" style="margin-left: 150px">只能上传jpg/png文件，且不超过500kb</div>
                  </el-upload>
                </el-form-item>
                <el-form-item label="课程描述">
                  <el-input
                    type="textarea"
                    placeholder="请输入内容"
                    v-model="courseComment"
                    maxlength="300"
                    show-word-limit style="width: 500px">
                  </el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" >立即创建</el-button>
                  <el-button>取消</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-tab-pane>

        </el-tabs>
      </div>
    </template>

    <script>
      export default {
        name: "Institution",
        data() {
          return {
            tableData:[
              { img: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                courseName:'2020考公通关班',
                courseTeam:'Teacher FLY',
                courseType:'线上',
                courseTime:'2020-12-3',
                coursePrice:'￥199',
                status:1,
              },{
                img: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                courseName:'2020考公通关班',
                courseType:'线上',
                courseTeam:'范坤',
                courseTime:'2020-12-3',
                coursePrice:'￥1',
                status:0,
              }],
            courseInputVo:[{
              img: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
              courseName:'2020考公通关班',
              courseType:'线上',
              courseTeam:'范坤',
              courseTime:'2020-12-3',
              coursePrice:'￥1',
              status:0,
            }],
            courseComment:'',
            multipleSelection: [],
            dialogFormVisible: false,
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
            formLabelWidth: '120px'
          }
        },
        methods: {
          handleSelectionChange(val) {
            this.multipleSelection = val;
          }
        }
      }
    </script>

    <style scoped>
      .el-form-item {
        margin-bottom: 40px;
      }
      .small_form_item {
        width: 380px;
        height: 40px;
      }
    </style>

