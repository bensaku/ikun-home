<template>
    <div style="width:100%;">
        <el-table
      :data="tableData" 
      border
      style="width: 100%"
      :default-sort = "{prop: 'date', order: 'descending'}"
      >
      <el-table-column
        prop="problemNo"
        label="问题编号"
        sortable
        width="120">
      </el-table-column>
      <el-table-column
        prop="problemType"
        label="问题类型"
        sortable
        width="120">
      </el-table-column>
      <el-table-column
        prop="problemState"
        label="处理状态"
        sortable
        width="120"
        >
      </el-table-column>
      <el-table-column
        prop="problemReporttime"
        label="上报时间"
       sortable
       width="200">
      </el-table-column>
      <el-table-column
        prop="problemFinishtime"
        label="处理时间"
        width="200">
      </el-table-column>
      <el-table-column
        prop="problemDetail"
        label="问题描述"
        width="400">
      </el-table-column>
      <el-table-column
        prop="userNo"
        label="上报用户"
        width="100"
        >
      </el-table-column>
      <el-table-column
   
      label="操作"
      width="150">
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">下载附件</el-button>
        <el-button type="text" size="small">提醒物业</el-button>
      </template>
    </el-table-column>
    </el-table>
    </div>

  </template>
  
  <script>
  export default {
      props: {
    tableData: {
      type: Array,
      required: true
    }
  },
    methods: {
      formatter(row,) {
        return row.address;
      }
    }
  }
  </script>