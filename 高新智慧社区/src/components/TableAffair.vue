<template>
  <div style="width:100%;">
    <el-table :data="tableData" border style="width: 100%" :default-sort="{ prop: 'problemNo', order: 'descending' }">
      <el-table-column prop="problemNo" label="问题编号" sortable width="120">
      </el-table-column>
      <el-table-column prop="problemType" label="问题类型" sortable width="120">
      </el-table-column>
      <el-table-column prop="problemState" label="处理状态" sortable width="120">
      </el-table-column>
      <el-table-column prop="problemReporttime" label="上报时间" sortable width="200">
      </el-table-column>
      <el-table-column prop="problemFinishtime" label="处理时间" width="200">
      </el-table-column>
      <el-table-column prop="problemDetail" label="问题描述" width="400">
      </el-table-column>
      <el-table-column prop="userNo" label="上报用户" width="100">
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <a class="upload-link" href="#" @click="handleClick(scope.row)" type="text" size="small">下载附件</a>
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
    axios.get('http://localhost:8090/api/problem/getAll')
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
      selectedRow: null, // 选中的行数据
      tableData: [],

    }
  },
  methods: {
    handleClick(row) {
      this.selectedRow = row;
      const problemNo = this.selectedRow.problemNo;
      window.open("http://localhost:8090/api/problem/getProblemFile?id="+problemNo)
  },
},};
</script>

<style>
.upload-link{
  text-decoration: none;
  color: rgb(75, 165, 254);
  font-size:  11px;
  margin-right: 10px;
}
.upload-link:hover{
  color:rgb(107, 177, 254);
}
</style>