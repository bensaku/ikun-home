<template>
    <div style="display: flex;width:650px; height: 40px;">
    <span style="font-size: 20px; line-height: 40px;">发起时间：</span>
    <el-date-picker
    value-format="yyyy-MM-dd"
      v-model="dateRange"
      type="daterange"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期">
    </el-date-picker>
    <button  class="affairbutton"  @click="fetchData" >查询</button>
    <button  @click="showModal" class="affairbutton2" >新增</button>

    <div class="modal-overlay" v-if="isModalVisible">
    <div class="modal" style="height: 630px; width: 450px;">
          <h2 class="modal-title">新增投票</h2>
          <div class="modal-row">
            <label class="modal-label">项目名称：</label>
            <input class="modal-input" type="text" v-model="vote_name" />
          </div>
          <div class="modal-row">
            <label class="modal-label">项目金额：</label>
            <input class="modal-input" type="text" v-model="vote_expend" />
          </div>
          <div class="modal-row">
            <label id="end-time" style="width: 80px;"  class="modal-label">截止日期：</label>
            <el-date-picker
            value-format="yyyy-MM-dd"
            class="modal-row-input custom-date-picker"
            style="height: 40px; width: 240px;"
      v-model="vote_endtime"
      type="date"
      placeholder="选择日期">
    </el-date-picker>
          </div>
          <div class="modal-row">
          <label class="modal-label">项目描述：</label>
          </div>
          <div class="textarea-row">
          <textarea class="modal-row-textarea" v-model="vote_detail"></textarea>
          </div>  
          
          <div class="modal-buttons">
            <button class="modal-button1" @click="doinsert">确认</button>
            <button class="modal-button2" @click="closeModal">取消</button>
          </div>
        </div>
      </div>
  </div>

</template>

<script>

export default {
data() {
      return {
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        value1: '',
        value2: '',
        isModalVisible: false,
        vote_name:'',
        vote_expend:'',
        vote_endtime:'',
        vote_detail: '',
        dateRange: [],

      };
  },
  methods: {
    fetchData() {
      if (this.dateRange.length === 2) {
        const startDate = this.dateRange[0];
        const endDate = this.dateRange[1];
        const flag = false;
        this.$emit('searchVote', { startDate, endDate,flag });
      }
    },
      showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    },
    doinsert() {
        if (!this.vote_name) {
          this.$message.error("请输入项目名称！");
          return;
        }
        if (!this.vote_expend) {
          this.$message.error("请输入项目金额！");
          return;
        }
        if (!this.vote_endtime) {
          this.$message.error("请输入截止日期！");
          return;
        }
        if (!this.vote_detail) {
          this.$message.error("请输入项目描述！");
          return;
      }
      const requestData = {
        voteName: this.vote_name,
        voteExpend: this.vote_expend,
        voteEndtime: this.vote_endtime,
        voteDetail: this.vote_detail,
      };
      this.$axios.post('http://localhost:8090/api/vote/insertVote', requestData)
        .then(response => {
          if (response.data == '插入成功') {
          // 请求成功后的处理
          console.log(response.data);
          // 清空输入框
          this.vote_name = '';
          this.vote_expend = '';
          this.vote_endtime = '';
          this.vote_detail = '';
          // 关闭模态框
            this.isModalVisible = false;
          }
          else if (response.data == '截止日期需在当前时间之后')
          {
            this.$message.error("截止日期需在当前时间之后");
      
          }
          else {
          this.$message.error("插入失败");
          }

          
        }
        )
        .catch(error => {
          this.$message.error(this.vote_endtime);
          // 请求失败后的处理
          console.error(error);
        });
        this.$router.go(0);
      }
    }

  };
</script>

<style>
   .rotated1 {
  transform: rotate(45deg);
  font-size: 15px;
  border: 2px dashed #E87D00;
  border-radius: 5px;
}

.rotated2 {
  transform: rotate(45deg);
  font-size: 15px;
  border: 2px dashed #A30014;
  border-radius: 5px;
}
    
    .modal2 {
      z-index: 3;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    width: 360px;
    height: 530px;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  }
  
  .modal2 .modal-label1 {
    text-align: center;
    font-size: 25px;
    color: #667561;
    font-weight: bold;
    margin-top: 2px;
    margin-bottom: 10px;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
  }
  .modal2 .modal-row {
    display: flex;
    align-items: center;
    margin-top: 20px;
    margin-bottom: 20px;
  }
  .modal2 .row-m {
    display: flex;
    align-items: center;
    margin-top: 20px;
    margin-bottom: 20px;
  }
  .modal2 .modal-row-label1 {
    flex: 0 0 100px;
    font-weight: bold;
    color: #667561;
  }
  .modal2 .modal-row-label2 {
    flex: 0 0 100px;
    font-weight: bold;
    color: #667561;
  }
  .modal2 .modal-row-label3 {
    flex: 0 0 100px;
    font-weight: bold;
    color: #667561;
    margin-left: 55px;
  }
  /*下拉框*/
  .modal2 .modal-content {
    display: flex;
    align-items: center;
    margin-top: 20px;
    margin-bottom: 20px;
  }
  .modal2 .dropdownlabel{
    flex: 0 0 100px;
    font-weight: bold;
    color: #667561;
  }
  .modal2 .dropdownselect{
    flex: 1;
    height: 25px;
    margin-left: -15px;
    margin-right: 55px;
    border-radius: 4px;
    border: 2px solid #9c9c9c;
  }
  .modal2 .modal-row-input {
    flex: 1;
    height: 35px;
    margin-left: -15px;
    margin-right: 55px;
    border-radius: 4px;
    border: 2px solid #9c9c9c;
  
  }
  .modal2 .modal-row-input1 {
    flex: 1;
    margin-left: 15px;
    width: 50px;
    border-radius: 4px;
    border: 2px solid #9c9c9c;
  }
  
  .modal2 .modal-row-unit {
    margin-left: 5px;
  }
  
  .modal2 .modal-buttons {
    text-align: center;
    margin-bottom: 0%;
    border-radius: 5px;
  }
  
  .modal2 .modal-button1 {
    margin: 10px;
    margin-bottom: 5px;
    background-color: #667561;
    color: #fff;
  }
  .modal2 .modal-button1:hover{
    background-color: #2e312d;
  }
  .modal2 .modal-button2 {
    margin: 10px;
    margin-bottom: 10px;
  }

  
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~车位 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    .modal3 {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    width: 360px;
    height: 320px;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  }
  
  .modal3 .modal-label1 {
    text-align: center;
    font-size: 25px;
    color: #667561;
    font-weight: bold;
    margin-top: 2px;
    margin-bottom: 10px;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
  }
  .modal3 .modal-row {
    display: flex;
    align-items: center;
    margin-top: 20px;
    margin-bottom: 20px;
  }
  .modal3 .row-m {
    display: flex;
    align-items: center;
    margin-top: 20px;
    margin-bottom: 20px;
  }
  .modal3 .modal-row-label1 {
    flex: 0 0 100px;
    font-weight: bold;
    color: #667561;
  }
  .modal3 .modal-row-label2 {
    flex: 0 0 100px;
    font-weight: bold;
    color: #667561;
  }
  .modal3 .modal-row-label3 {
    flex: 0 0 100px;
    font-weight: bold;
    color: #667561;
    margin-left: 55px;
  }
  .modal3 .modal-row-input {
    flex: 1;
    height: 25px;
    margin-left: -15px;
    margin-right: 55px;
    border-radius: 4px;
    border: 2px solid #9c9c9c;
  }
  .modal3 .modal-row-input1 {
    flex: 1;
    margin-left: 15px;
    width: 50px;
    border-radius: 4px;
    border: 2px solid #9c9c9c;
  }
  
  .modal3 .modal-row-unit {
    margin-left: 5px;
  }
  
  .modal3 .modal-buttons {
    text-align: center;
    margin-bottom: 0%;
    border-radius: 5px;
  }
  
  .modal3 .modal-button1 {
    margin: 10px;
    margin-bottom: 5px;
    background-color: #667561;
    color: #fff;
  }
  .modal3 .modal-button1:hover{
    background-color: #2e312d;
  }
  .modal3 .modal-button2 {
    margin: 10px;
    margin-bottom: 10px;
  }
  .modal-lastrow{
    display: flex;
    align-items: center;
    margin-top: 20px;
    margin-bottom: 20px;
  }

  .modal-row-textarea {
    width: 100%;
    height: 180px;
    margin-top: 5px;
    margin-bottom: 10px;
    border-radius: 5px;
  }
    </style>
<style scope>

.custom-date-picker .el-input {
  height: 40px !important; /* 修改日期选择框的高度 */
}

.affairbutton,.affairbutton2{
    margin-left:20px ;
    border: 0;
    width: 80px;
    border-radius:10px;
    background-color:#15A850;
    color: white;
}
.affairbutton:hover,.affairbutton2:hover{

    background-color:#14bb68;
}
</style>
