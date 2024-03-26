<template>
    <div style="width: 100%;">
        <el-table
      :data="tableData"
      border
      style="width: 100%"
      :default-sort = "{prop: 'complaintNo', order: 'descending'}"
      >
      <el-table-column
        prop="complaintNo"
        label="投诉编号"
        sortable
        width="120">
      </el-table-column>
      <el-table-column
        prop="complaintState"
        label="处理状态"
        sortable
        width="120">
      </el-table-column>
      <el-table-column
        prop="complaintReporttime"
        label="上报时间"
       sortable
       width="200">
      </el-table-column>
      <el-table-column
        prop="complaintFinishtime"
        label="处理时间"
        width="200">
      </el-table-column>
      <el-table-column
        prop="complaintDetail"
        label="问题描述"
        width="350">
      </el-table-column>
      <el-table-column
        prop="userNo"
        label="上报用户"
        width="100"
        >
      </el-table-column>
      <el-table-column
        prop="complaintFeedback"
        label="问题反馈"
        width="200">
      </el-table-column>
      <el-table-column

      label="操作"
      width="100">
      <template >
        <el-button type="text" size="small">提醒物业</el-button>
      </template>
    </el-table-column>
    </el-table>
    </div>

  </template>
  
  <script>
  import axios from 'axios';
  export default {
    created() {
  axios.get('http://localhost:8090/api/complaint/getAll')
    .then(response => {
      // 处理成功响应
      this.tableData = response.data; // 将查询结果赋值给tableData数组
    })
    .catch(error => {
      // 处理请求错误
      console.error(error);
    });
},
    data() {
      return {
        tableData: []
      }
    },
    methods: {
      formatter(row,) {
        return row.address;
      }
    }
  }
  </script>