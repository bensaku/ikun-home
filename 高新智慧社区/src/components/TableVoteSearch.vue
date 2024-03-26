<template>
    <div style="width: 100%;">
        <el-table
      :data="tableData"
      border
      style="width: 100%"
      :default-sort = "{prop: 'date', order: 'descending'}"
      >
      <el-table-column
        prop="voteNo"
        label="投票编号"
        sortable
        width="150">
      </el-table-column>
      
      <el-table-column
        prop="voteResult"
        label="结果"
        sortable
        width="150">
      </el-table-column>
      <el-table-column
        prop="voteStarttime"
        label="上报时间"
       sortable
       width="150">
      </el-table-column>
      <el-table-column
        prop="voteEndtime"
        label="截止时间"
        width="150">
      </el-table-column>
      <el-table-column
        prop="voteSupport"
        label="支持票数"
        width="100">
      </el-table-column>
      <el-table-column
        prop="voteAgainst"
        label="反对票数"
        width="100">
      </el-table-column>
      <el-table-column
        prop="voteName"
        label="项目名称"
        width="200">
      </el-table-column>
      <el-table-column
        prop="voteDetail"
        label="项目描述"
        width="280">
      </el-table-column>
      <el-table-column
        prop="voteExpend"
        label="项目金额"
        width="200">
      </el-table-column>
      <el-table-column
  
      label="操作"
      width="100">
      <template slot-scope="scope" >
        <el-button type="text" size="small" @click="handleDelete(scope.row)">删除</el-button>
      </template>
    </el-table-column>
    </el-table>
  
    <div class="modal" v-if="isModalVisible2" style="height: 150px;">
          <h2 class="modal-label1">确认删除该家人?</h2>  
          <div class="modal-buttons" style="margin-top: 60px;">
            <button class="modal-button1" @click="dodelete">确认</button>
            <button class="modal-button2" @click="closeModal2">取消</button>
          </div>
      </div>
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
    data() {
      return {
        selectedRow: null, // 选中的行数据
        isModalVisible2: false,
        voteNo:''
      }
    },
    methods: {
     
      handleDelete(row) {
        // 保存选中的行数据
        this.selectedRow = row;
        // 打开删除确认对话框
        this.showModal2();
      },
    
      showModal2() {
        this.isModalVisible2 = true;
      },
      closeModal2() {
        this.isModalVisible2 = false;
        this.selectedRow = null; // 重置选中的行数据
      },
      dodelete() {
    if (this.selectedRow) {
      const voteNo = this.selectedRow.voteNo;
      console.log('Deleting voteNo:', voteNo);
      // 发送请求执行删除操作
      this.$axios
        .delete(`http://localhost:8090/api/vote/deleteVote/${voteNo}`)
        .then(() => {
            this.$router.go(0);
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }
    }
  }
  </script>