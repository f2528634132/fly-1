<template>
  <el-form ref="form" class="forum" :model="form" label-width="80px">
    <el-form-item label="话题标题">
      <el-input v-model="examtitle"></el-input>
    </el-form-item>
    <el-form-item label="所属论坛">
      <el-select v-model="form.region" placeholder="请选择所属论坛">
        <el-option label="全国计算机等级考试" value="1"></el-option>
        <el-option label="计算机技术与软件专业技术资格（水平）考试" value="2"></el-option>
        <el-option label="CET大学英语考试" value="3"></el-option>
        <el-option label="全国会计从业资格考试" value="4"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="话题标签">
      <el-checkbox-group v-model="form.type">
        <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
        <el-checkbox label="地推活动" name="type"></el-checkbox>
        <el-checkbox label="线下主题活动" name="type"></el-checkbox>
        <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item label="是否推广">
      <el-radio-group v-model="form.resource">
        <el-radio label="是"></el-radio>
        <el-radio label="否"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="话题描述">
      <el-input type="textarea" v-model="form.desc"></el-input>
    </el-form-item>
    <el-form-item label="发布">
      <el-switch v-model="form.delivery"></el-switch>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">立即创建</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<style>
  .forum {
    margin: 80px;
    width: 800px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    -webkit-box-shadow: 0 0 25px #cac6c6;
    box-shadow: 0 0 25px #cac6c6;
  }
  .el-input {
    position: relative;
    font-size: 14px;
    /* display: inline-block; */
    width: 80%;
  }
  .el-select {
    /* display: inline-block; */
    /* position: relative; */
    width: 500px;
  }
  .el-form-item {
    margin-bottom: 22px;
    width: 60%;
  }
  .el-textarea {
    position: relative;
    display: inline-block;
    width: 100%;
    vertical-align: bottom;
    font-size: 14px;
    height: 100px;
  }
  .el-textarea__inner {
    height: 100px;
  }
</style>

<script>
  export default {
    data() {
      return {
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        }
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      }
    }
  }
</script>
