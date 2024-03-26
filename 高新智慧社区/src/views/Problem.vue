<template>
  <div>
    <TopNav style="position:fixed; width:100%; left: 0%;z-index: 2;">
    </TopNav>
    <LeftNav style="position:fixed;width:13%; left:0; top: 60px; z-index: 3;">
    </LeftNav>
    <div style="
        position: absolute;
        right: 0;
        top:60px;
        width: 87%;
        z-index: 1;
        min-height: 100vh;
        background-color: #f2f2f2;
      ">

<div class="background-container">
      <vue-particles
  color="#70B603"
  :particleOpacity="0.6"
  :particlesNumber="100"
  shapeType="circle"
  :particleSize="5"
  linesColor="#0000ff"
  :linesWidth="1"
  :lineLinked="true"
  :lineOpacity="0.6"
  :linesDistance="80"
  :moveSpeed="1.3"
  :hoverEffect="true"
  hoverMode="grab"
  :clickEffect="false"
  clickMode="push"
/>
  </div>

      <div class="card_faqi" id="problem_card_faqi">
        <div class="card-left">
          <h2 class="card-title2">
            发起问题
            <img class="card-icon" src="../assets/img/问题 (2).png" />
          </h2>
          <div class="card-data_faqi">
            <h>点击右侧按钮发起问题</h>
          </div>
        </div>
        <div class="card-right">
          <button class="card-button" @click="showModal">
            <img class="card-button-image" src="../assets/img/增加.png" />
          </button>
        </div>
      </div>

      <div v-for="problem in problems" :key="problem.problemNo">
        <!-- 第一个 div 的内容 -->
        <div v-if="problem.problemState === '已处理'" id="problem_card">
          <div class="problem-card" id="problem_card_finish">
            <div class="card-left">
              <h2 class="card-title">
                我的问题
                <img class="card-icon" src="../assets/img/问题 (1).png" />
              </h2>
              <div class="card-data">
                <div class="row">
                  <label for="leftData1">问题编号:</label>
                  <input class="card-input" type="text" v-model="problem.problemNo" :readonly="isReadOnly" />
                </div>
                <div class="row">
                  <label for="leftData1">问题类型:</label>
                  <input class="card-input" type="text" v-model="problem.problemTtpe" :readonly="isReadOnly" />
                </div>
                <div class="row">
                  <label for="leftData1">问题描述:</label>
                  <textarea class="card-input2" type="text" v-model="problem.problemDetail"
                    :readonly="isReadOnly"></textarea>
                </div>
              </div>
            </div>
            <div class="card-right">
              <h2 class="card-title">
                目前状态:
                <input class="card-input1" v-model="problem.problemState" :readonly="isReadOnly" />
              </h2>
              <div class="row">
                <label for="leftData1">上报时间:</label>
                <input class="card-input" type="text" v-model="problem.problemReporttime" :readonly="isReadOnly" />
                <label for="leftData1">解决时间:</label>
                <input class="card-input" type="text" v-model="problem.problemFinishtime" :readonly="isReadOnly" />
              </div>
              <div class="row">
                <label for="leftData1">附件:</label>
                <img class="card-icon" src="../assets/img/附件 (1).png" />
              </div>
            </div>
          </div>
        </div>

        <!-- 第二个 div 的内容 -->
        <div v-else id="problem_card_weiwancheng">
          <div class="problem-card" id="problem_card_weiwancheng">
            <div class="card-left">
              <h2 class="card-title">
                我的问题
                <img class="card-icon" src="../assets/img/问题 (1).png" />
              </h2>
              <div class="card-data">
                <div class="row">
                  <label for="leftData1">问题编号:</label>
                  <input class="card-input" type="text" v-model="problem.problemNo" :readonly="isReadOnly" />
                </div>
                <div class="row">
                  <label for="leftData1">问题类型:</label>
                  <input class="card-input" type="text" v-model="problem.problemType" :readonly="isReadOnly" />
                </div>
                <div class="row">
                  <label for="leftData1">问题描述:</label>
                  <textarea class="card-input2" type="text" v-model="problem.problemDetail"
                    :readonly="isReadOnly"></textarea>
                </div>
              </div>
            </div>
            <div class="card-right">
              <h2 class="card-title3">
                目前状态:
                <input class="card-input4" v-model="problem.problemState" :readonly="isReadOnly" />
              </h2>
              <div class="row">
                <label for="leftData1">上报时间:</label>
                <input class="card-input" type="text" v-model="problem.problemReporttime" :readonly="isReadOnly" />
                <label for="leftData1">解决时间:</label>
                <input class="card-input" type="text" v-model="problem.problemFinishtime" :readonly="isReadOnly" />
              </div>
              <div class="row">
                <label for="leftData1">附件:</label>
                <img class="card-icon" src="../assets/img/附件 (1).png" />
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="modal-overlay" v-if="isModalVisible">
        <div class="modal" style="height: 500px;">
          <h2 class="modal-title">问题</h2>
          <div class="modal-row">
            <label class="modal-label">问题描述：</label>
          </div>
          <div class="textarea-row">
            <textarea class="modal-row-textarea" v-model="problemdetail" placeholder="请输入问题详情"></textarea>
          </div>
          <div class="file-upload">
            <el-upload class="upload-demo" action="http://localhost:8090/api/problem/uploadFile"
              :on-success="handleSuccess" :on-preview="handlePreview" :on-remove="handleRemove"
              :before-remove="beforeRemove" multiple :limit="1" :on-exceed="handleExceed" :file-list="fileList">
              <el-button size="small" type="success">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">请按照“用户名-问题名字”来命名文件,上传文件最大大小为50MB</div>
            </el-upload>
          </div>
          <div class="modal-buttons">
            <button class="modal-button1" @click="submitProblem">确认</button>
            <button class="modal-button2" @click="closeModal">取消</button>
          </div>
        </div>
      </div>



    </div>
  </div>
</template>
  
<script>
import TopNav from '../components/TopNav.vue'
import LeftNav from '../components/LeftNav.vue'
export default {
  components: {
    TopNav,
    LeftNav,
  },
  data() {
    return {
      userNo: this.$store.state.userData.username,
      isReadOnly: true,
      isModalVisible: false,
      problemannex: "",
      problemdetail: "",
      problems: [],
      fileList: [],
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    },
    fetchData() {
      this.$axios.get('http://localhost:8090/api/problem/getByUserNo', {
        params: { userNo: this.userNo }
      })
        .then(response => {
          this.problems = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    handleSuccess(response, fileList) {
      console.log(response);
      this.problemannex = response;
      this.fileList = fileList;
    },
    handleRemove() {
      this.$axios.delete('http://localhost:8090/api/problem/deleteFile', { params: { filePath: this.problemannex } })
    },
    submitProblem() {
      // 处理问题提交逻辑
      const problem = {
        userNo: this.userNo,
        problemDetail: this.problemdetail,
        problemAnnex: this.problemannex,
      };
      if (!this.problemdetail) {
        this.$message.error("请输入问题详情！");
        return;
      } else {
        this.$axios.post('http://localhost:8090/api/problem/addPro', problem)
          .then(response => {
            // 处理成功的响应
            //alert(response.data);
            this.$message.success(response.data);
            this.$router.go(0);
          })
          .catch(error => {
            // 处理错误
            //alert(error.response.data); // 输出错误消息
            this.$message.error(error.response.data);
          });
      }
    },
  },
};

</script>
<style>
@import "../assets/style/card.css";
@import "../assets/style/modal.css";

.file-upload {
  display: inline-block;
}

.file-input-label {
  display: inline-block;
  padding: 8px 20px;
  background-color: #70b603;
  color: #fff;
  border-radius: 4px;
  cursor: pointer;
}

.file-input {
  display: none;
}

.select-file-text {
  margin-right: 10px;
}

.selected-file-name {
  margin-top: 10px;
}

</style>
  