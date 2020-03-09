<template>
  <div class="offer-manage-wrapper">
    <div style="width: 100%;height: 26px;"></div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>广告栏目管理</el-breadcrumb-item>
      <el-breadcrumb-item>机构栏目</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    <div class="offer-manage-card offer-b-card">-->
    <el-tabs type="border-card" @tab-click="addInstitutionInit">
      <el-tab-pane label="新增机构课程" name="0">
        <div class="form_box" style="margin-left: 50px;margin-top: 40px">
        <el-form :inline="true" :model="courseInputVo" ref="courseFormRef">
          <el-form-item label="课程名称" class="small_form_item" prop="courseTitle">
            <el-input v-model="courseInputVo.courseTitle" placeholder="课程名称"></el-input>
          </el-form-item>
          <el-form-item label="上课形式" class="small_form_item" prop="courseType">
            <el-select v-model="courseInputVo.courseType" placeholder="线上/线下">
              <el-option label="线上" value="线上"></el-option>
              <el-option label="线下" value="线下"></el-option>
              <el-option label="线上+线下" value="线上+线下"></el-option>
            </el-select>
          </el-form-item><br>
          <el-form-item label="课程所属" class="small_form_item" prop="institutionType">
            <el-select v-model="courseInputVo.institutionType" placeholder="自有课程/第三方合作">
              <el-option label="自有课程" value="0"></el-option>
              <el-option label="第三方合作" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="所属机构" class="small_form_item" prop="institution">
            <el-input v-model="courseInputVo.institution" placeholder="所属机构"></el-input>
          </el-form-item><br>
          <el-form-item label="开课时间" class="small_form_item" prop="courseTime">
              <el-col>
                <el-date-picker
                  v-model="courseInputVo.courseTime"
                  type="date"
                  placeholder="选择日期">
                </el-date-picker>
              </el-col>
            </el-form-item>
          <el-form-item label="课程价格" class="small_form_item" prop="coursePrice">
              <el-input v-model="courseInputVo.coursePrice" placeholder="￥"></el-input>
            </el-form-item><br>
          <el-form-item label="详情链接" prop="courseUrl">
            <el-input v-model="courseInputVo.courseUrl" placeholder="详情描述站外链接" style="width: 500px"></el-input>
          </el-form-item><br>
          <el-form-item label="课程封面" prop="coursePicture" style="margin-bottom:0px;height: 250px">
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
          </el-form-item><br>
          <el-form-item label="课程描述" prop="courseComment">
            <el-input
              type="textarea"
              placeholder="请输入内容"
              v-model="courseInputVo.courseComment"
              maxlength="300"
              show-word-limit style="width: 500px">
            </el-input>
          </el-form-item><br>
          <el-form-item>
            <el-button type="primary" @click="addInstitution">立即创建</el-button>
            <el-button type="info" @click="resetCourseForm">重置</el-button>
          </el-form-item>
        </el-form>
        </div>
      </el-tab-pane>
      <el-tab-pane name="1">
        <span slot="label"><i class="el-icon-date"></i> 自有课程</span>
         <el-table  :data="tableData0" ref="tableData" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
           <el-table-column prop="coursePicture" label="封面" >
               <template slot-scope="scope">
                 <el-avatar shape="square" :size="90" fit="contain" :src="scope.row.coursePicture"></el-avatar>
               </template>
           </el-table-column>
           <el-table-column prop="courseTitle" label="课程名称" >
           </el-table-column>
           <el-table-column prop="courseType" label="上课形式" >
           </el-table-column>
           <el-table-column prop="institution" label="授课团队" >
           </el-table-column>
           <el-table-column prop="courseTime" label="开班时间" >
           </el-table-column>
           <el-table-column prop="coursePrice" label="课程价格" >
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
      <el-tab-pane label="第三方课程">
        <el-table  :data="thirdTable" ref="tableData" :header-cell-style="{background:'#eef1f6',color:'#606266'}">
          <el-table-column prop="coursePicture" label="封面" >
            <template slot-scope="scope">
              <el-avatar shape="square" :size="90" fit="contain" :src="scope.row.coursePicture"></el-avatar>
            </template>
          </el-table-column>
          <el-table-column prop="courseTitle" label="课程名称" >
          </el-table-column>
          <el-table-column prop="courseType" label="上课形式" >
          </el-table-column>
          <el-table-column prop="institution" label="所属机构" >
          </el-table-column>
          <el-table-column prop="courseTime" label="开班时间" >
          </el-table-column>
          <el-table-column prop="coursePrice" label="课程价格" >
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
          tableData0:[],
          thirdTable:[],
          courseInputVo:{
            coursePicture: '',
            courseTitle:'',
            courseType:'',
            institutionType:'',
            institution:'',
            courseTime:'',
            coursePrice:'',
            status:0,
            courseUrl:'',
            courseComment:'',
          },
        }
      },
      methods: {
        resetCourseForm (){
            this.$refs.courseFormRef.resetFields()
        },
        handleSuccess(response){
          // console.log(response)
        },
        beforeUpload(file){
          let fd = new FormData();
          fd.append('file', file);
          this.$http.post('file/uploadFile', fd).then((res) => {
            this.courseInputVo.coursePicture=res.data.msg;
          })
          return true
         },
        addInstitution(){
          let courseInputVo=this.courseInputVo;
          this.$axios({
            method: "post",
            url: "/institution/edit",
            headers: {
              'Content-Type': 'application/json;charset=UTF-8'
            },
            withCredentials: true,
            data: JSON.stringify(courseInputVo)
          }).then((res) => {
            console.log(res);
          });
         },
          addInstitutionInit(tab){
            if (tab.name==='0'){
              this.resetCourseForm();
            }else if (tab.name==='1'){
              this.getRequest(`/institution/queryPageBySelf?pageNum=1&pageSize=10`)
                .then(
                  res => {
                       console.log(res)
                       this.tableData0 = res.data.data.items || [];
                       this.totalNum0 = res.data.data.totalNum || 0;
                  },)
              this.getRequest(`/institution/queryPageByThird?pageNum=1&pageSize=10`)
                .then(
                  res => {
                    console.log(res)
                    this.thirdTable = res.data.data.items || [];
                    this.totalNum1 = res.data.data.totalNum || 0;
                  },)
            }
           },
        changeStatus(id,status){
          alert("修改"+id+"的状态为"+status);
          this.putRequest(`/institution/status?status=${status}&id=${id}`)
        },

        deleteExam: function (Id) {
          alert(Id);
          return this.getRequest(`/institution/editDeleted?deleted=1&id=${Id}`).then( res => {
            this.submitClick()  })
        },
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
