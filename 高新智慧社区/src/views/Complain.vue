<template>
  <div>
    <TopNav style="position:fixed; width:100%; left: 0%;z-index: 2;">
    </TopNav>
    <LeftNav style="position:fixed;width:13%; left:0; top: 60px; z-index: 3;">
    </LeftNav>
    <div style="
        position: absolute;
        right: 0;
        width: 87%;
        z-index: 1;
        min-height: 100vh;
        background-color: #f2f2f2;
        top:60px;
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

      <!-- 发起投诉 -->
      <div class="card_faqi" id="tousu_card_faqi">
        <div class="card-left">
          <h2 class="card-title2">
            发起投诉
            <img class="card-icon" src="../assets/img/tousu2.png" />
          </h2>
          <div class="card-data_faqi">
            <h>点击右侧按钮发起投诉</h>
          </div>
        </div>
        <div class="card-right">
          <button class="card-button" @click="showModal">
            <img class="card-button-image" src="../assets/img/增加.png" />
          </button>
          <div class="modal-overlay" v-if="isModalVisible">
            <div class="modal">
              <h2 class="modal-title">投诉</h2>
              <div class="modal-row">
                <label class="modal-label">详细描述：</label>
              </div>
              <div class="textarea-row">
                <textarea class="modal-row-textarea" v-model="complaintContent"></textarea>
              </div>

              <div class="modal-buttons">
                <button class="modal-button1" @click="submitComplaint">
                  确认
                </button>
                <button class="modal-button2" @click="closeModal">取消</button>
              </div>
            </div>
          </div>
        </div>
      </div>


      <div v-for="complaint in complaints" :key="complaint.complaintNo">
        <div v-if="complaint.complaintState === '已处理'">
          <div class="card" id="tousu_card_wancheng">
            <div class="card-left">
              <h2 class="card-title">
                历史投诉
                <img class="card-icon" src="../assets/img/tousu.png" />
              </h2>
              <div class="card-data">
                <div class="row">
                  <label for="leftData1">投诉编号:</label>
                  <input class="card-input" type="text" v-model="complaint.complaintNo" :readonly="isReadOnly" />
                </div>
                <div class="row">
                  <label for="leftData1">详情:</label>
                  <textarea class="card-input2" type="text" v-model="complaint.complaintDetail" :readonly="isReadOnly" />
                </div>
              </div>
            </div>
            <div class="card-right">
              <h2 class="card-title">
                目前状态:
                <input class="card-input1" v-model="complaint.complaintState" />
              </h2>
              <div class="row">
                <label for="leftData1">上报时间:</label>
                <input class="card-input" type="text" v-model="complaint.complaintReporttime" :readonly="isReadOnly" />
                <p></p>
                <label for="leftData1">解决时间:</label>
                <input class="card-input" type="text" v-model="complaint.complaintFinishtime" :readonly="isReadOnly" />
              </div>
              <div class="row">
                <label for="leftData1">反馈:</label>
                <textarea class="card-input3" type="text" v-model="complaint.complaintFeedback" :readonly="isReadOnly" />
              </div>
            </div>
          </div>
        </div>
        <!-- 完成 -->



        <div v-else-if="complaint.complaintState === '未处理'">
          <div class="card" id="tousu_card_weiwancheng">
            <div class="card-left">
              <h2 class="card-title">
                历史投诉
                <img class="card-icon" src="../assets/img/tousu.png" />
              </h2>
              <div class="card-data">
                <div class="row">
                  <label for="leftData1">投诉编号:</label>
                  <input class="card-input" type="text" v-model="complaint.complaintNo" :readonly="isReadOnly" />
                </div>
                <div class="row">
                  <label for="leftData1">详情:</label>
                  <textarea class="card-input2" type="text" v-model="complaint.complaintDetail" :readonly="isReadOnly" />
                </div>
              </div>
            </div>
            <div class="card-right">
              <h2 class="card-title3">
                目前状态:
                <input class="card-input4" v-model="complaint.complaintState" :readonly="isReadOnly" />
              </h2>
              <div class="row">
                <label for="leftData1">上报时间:</label>
                <input class="card-input" type="text" v-model="complaint.complaintReporttime" :readonly="isReadOnly" />
                <label for="leftData1">解决时间:</label>
                <input class="card-input" type="text" v-model="complaint.complaintFinishtime" :readonly="isReadOnly" />
              </div>
              <div class="row">
                <label for="leftData1">反馈:</label>
                <textarea class="card-input3" type="text" v-model="complaint.complaintFeedback" :readonly="isReadOnly" />
              </div>
            </div>
          </div>
        </div>
        <!-- 未完成 -->



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
    LeftNav
  },
  data() {
    return {
      userNo: this.$store.state.userData.username,
      rightTitle: "已处理",
      rightTitle2: "未处理",
      leftData1: "0001",
      leftData2: "狗太吵了",
      rightData1: "2022/6/12",
      rightData2: "2002/6/16",
      rightData3: "抱歉打扰，已解决",
      leftData2_1: "0003",
      leftData2_2: "邻居半夜装修",
      rightData2_1: "2022/6/15",
      rightData2_2: "2022/6/18",
      rightData2_3: "抱歉打扰，已解决",
      isReadOnly: true,
      isModalVisible: false,
      complaintContent: "",
      complaints: [],
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
      this.$axios.get('http://localhost:8090/api/complaint/getByUserNo', {
        params: { userNo: this.$store.state.userData.username, }
      })
        .then(response => {
          this.complaints = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    submitComplaint() {
      // 处理投诉提交逻辑
      const complaint = {
        userNo: this.$store.state.userData.username,
        complaintDetail: this.complaintContent,
      };
      if (!this.complaintContent) {
        this.$message.error("请输入投诉详情！");
        return;
      } else {
        this.$axios.post('http://localhost:8090/api/complaint/addCom', complaint)
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
</style>
