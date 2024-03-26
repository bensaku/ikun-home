<template>
  <div id="app">
        <TopNav style="position:fixed; width:100%; left: 0%;z-index: 2;">
        </TopNav>
        <LeftNav style="position:fixed;width:13%; left:0; top: 60px; z-index: 3;">
        </LeftNav>
    <div style="position:absolute;right:0;top:60px;min-height: 100vh;width:87%;z-index: 1;background-color: #f2f2f2;">

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
        <div class="card-right niu">
        <h2 class="card-title   niu-title">
          我管理的家庭
          <button class="increase_btn" @click="showModal">新增家人</button>
        </h2>
        <div style="height: 2px; background-color: rgba(97, 97, 97, 0.647); margin-right: 10px;"></div>
        </div>

        

    


    <div v-for="myfamily in myfamilys" :key="myfamily.famId" >
      <div v-if="myfamily.userType!==null" id="family_card">
      <!-- 第一个 div 的内容 -->
      <div class="card" id="myfamily_card" style="width: 600px; margin-top: 20px;">
        <div class="card-left">
        <h2 class="card-title">
          <input class="card-input1" v-model="myfamily.famName" :readonly="isReadOnly"/>
        </h2>
        <div class="card-data">
          <div class="row" style="margin-top: 50px;">
            <label for="leftData1">身份证号:</label>
            <input class="card-input" type="text" v-model="myfamily.famId" :readonly="isReadOnly"/>
          </div>
        </div>
      </div>
      <div style="width: 2px; background-color: rgba(97, 97, 97, 0.647); margin-right: 10px;"></div>
      <div class="card-right">
        <h2 class="card-title">
          账户信息
          <button class="image-button" style="margin-left: 120px;" @click="showModal2(myfamily.famId)">
        <img class="card-icon"  src="../assets/img/叉.png"/>
          </button>
        </h2>
          <div class="row" style="margin-top: 45px;">
            <label for="leftData1">账号:</label>
            <input class="card-input" type="text" v-model="myfamily.famNo" :readonly="isReadOnly"/>
          </div>
      </div>
      </div>
      </div>

      <div v-else id="myfamily_card_unregistered">
      <!-- 第二个 div 的内容 -->
      <div class="card" id="family_card_unregistered" style="width: 600px; margin-top: 20px;">
        <div class="card-left">
        <h2 class="card-title">
          <input class="card-input1" v-model="myfamily.famName" :readonly="isReadOnly"/>
        </h2>
        <div class="card-data">
          <div class="row" style="margin-top: 50px;">
            <label for="leftData1">身份证号:</label>
            <input class="card-input" type="text" v-model="myfamily.famId" :readonly="isReadOnly"/>
          </div>
        </div>
      </div>
      <div style="width: 2px; background-color: rgba(97, 97, 97, 0.647); margin-right: 10px;"></div>
      <div class="card-right">
        <h2 class="card-title4">
          账户未注册
          <img class="card-icon" src="../assets/img/307感叹号-三角框.png"/>
          <button class="image-button" style="margin-left: 48px;" @click="showModal2(myfamily.famId)">
          <img class="card-icon" src="../assets/img/叉.png"/>
          </button>
        </h2>
          <div class="row" style="margin-top: 45px;">
            <label for="leftData1">请注册并完成实名认证</label>
          </div>
      </div>
      </div>
      </div>
    </div>
    

    <div class="card-right niu" style="margin-top: 40px;">
        <h2 class="card-title  niu-title">
          我加入的家庭
        </h2>
        <div style="height: 2px; background-color: rgba(97, 97, 97, 0.647); margin-right: 10px;"></div>
    </div>

    <div v-for="family in familys" :key="family.famId" >

      <div v-if="family.famNo===userNo"></div>

      <div v-else-if="family.userType===null" id="family_card_unregistered">
      <!-- 第一个 div 的内容 -->
      <div class="card" id="problem_card_finish" style="width: 600px; margin-top: 20px;">
        <div class="card-left">
        <h2 class="card-title">
          <input class="card-input1" v-model="family.famName" :readonly="isReadOnly"/>
        </h2>
        <div class="card-data">
          <div class="row" style="margin-top: 50px;">
            <label for="leftData1">身份证号:</label>
            <input class="card-input" type="text" v-model="family.famId" :readonly="isReadOnly"/>
          </div>
        </div>
      </div>
      <div style="width: 2px; background-color: rgba(97, 97, 97, 0.647); margin-right: 10px;"></div>
      <div class="card-right">
        <h2 class="card-title4">
          账户未注册
          <img class="card-icon" src="../assets/img/307感叹号-三角框.png"/>
        </h2>
          <div class="row" style="margin-top: 45px;">
            <label for="leftData1">请注册并完成实名认证</label>
          </div>
      </div>
    </div>
      </div>

      <div v-else id="family_card">
      <!-- 第二个 div 的内容 -->
      <div class="card" id="family_card_finish" style="width: 600px; margin-top: 20px;">
        <div class="card-left">
        <h2 class="card-title">
          <input class="card-input1" v-model="family.famName" :readonly="isReadOnly"/>
          <div v-if="family.userType==='业主' || family.userType==='业委会'">
          <img class="card-icon" src="../assets/img/业主.png"/>
          </div>
        </h2>
        <div class="card-data">
          <div class="row" style="margin-top: 50px;">
            <label for="leftData1">身份证号:</label>
            <input class="card-input" type="text" v-model="family.famId" :readonly="isReadOnly"/>
          </div>
        </div>
      </div>
      <div style="width: 2px; background-color: rgba(97, 97, 97, 0.647); margin-right: 10px;"></div>
      <div class="card-right">
        <h2 class="card-title">
          账户信息
        </h2>
          <div class="row" style="margin-top: 45px;">
            <label for="leftData1">账号:</label>
            <input class="card-input" type="text" v-model="family.famNo" :readonly="isReadOnly"/>
          </div>
      </div>
    </div>
      
      </div>
    </div>


    

    
    <div class="modal-overlay" v-if="isModalVisible2">
    <div class="modal" style="height: 150px;">
        <h2 class="modal-title">确认删除该家人?</h2>  
        <div class="modal-buttons" style="margin-top: 60px;">
          <button class="modal-button1" @click="dodelete">确认</button>
          <button class="modal-button2" @click="closeModal2">取消</button>
        </div>
    </div>
    </div>
    
    <div class="modal-overlay" v-if="isModalVisible">
    <div class="modal"  style="height: 300px;">
        <h2 class="modal-title">新增家人信息</h2>
        <div class="modalrow" style="margin-top: 40px;">
            <label class="modal-label">身份证号:</label>
            <input class="modal-input" type="text" v-model="insert_id"/>
        </div>
        <div class="modal-row">
            <label class="modal-label">姓名:</label>
            <input class="modal-input" type="text" v-model="insert_name"/>
        </div>   
        <div class="modal-buttons">
          <button class="modal-button1" @click="doinsert">确认</button>
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
    name: 'app',
  components: {
       TopNav,
       LeftNav
     },
    data() {
      return {
        userNo:this.$store.state.userData.username,
        famId:'',
        isReadOnly: true,
        isModalVisible: false,
        isModalVisible2: false,
        insert_id:'',
        insert_name:'',
        myfamilys:[],
        familys: [],
        overlay:'',
        
      };
    },
    created() {
    this.fetchData();
    },
    methods: {  
      showModal() {
      this.isModalVisible = true;
    },
    showModal2(famId) {
      this.isModalVisible2 = true;
      this.famId=famId
    },
    closeModal() {
      this.isModalVisible = false;
    },
    closeModal2() {
      this.isModalVisible2 = false;
    },
    dodelete(){
      this.$axios.delete('http://localhost:8090/api/family/deleteFam',{params: {famId: this.famId}})
      .then(response => {
        this.$message.success(response.data);
        this.$router.go(0);
        })
        .catch(error => {
          console.error(error);
        });
    },
    fetchData() {
      this.$axios.get('http://localhost:8090/api/myfamily/getByUserNo',{
        params: {userNo: this.userNo}
    })
        .then(response => {
          this.myfamilys = response.data;
        })
        .catch(error => {
          console.error(error);
        });


      this.$axios.get('http://localhost:8090/api/myfamily/getByFamNo',{
        params: {userNo: this.userNo}
    })
        .then(response => {
          this.familys = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    doinsert() {
      const family = {
      famId: this.insert_id,
      userNo: this.userNo,
      famName: this.insert_name,
    };

        if (!this.insert_id) {
          this.$message.error("请输入身份证号！");
          return;
        }else if (!this.insert_name) {
          this.$message.error("请输入姓名！");
          return;
        }else{
          this.$axios.post('http://localhost:8090/api/family/addFam', family)
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
    }
  };
  
  </script>
  <style>
    @import "../assets/style/modal.css";
    @import "../assets/style/card.css";

.increase_btn{
  margin-left: 280px;
  background-color: #2f89c5; /* 蓝色背景 */
  border-radius: 15px; /* 圆角矩形 */
  border: none; /* 无边框 */
  font-size: 20px;
  color: #fff; /* 字体颜色为白色 */
  padding: 10px 15px; /* 调整内边距增加按钮大小 */
  transition: background-color 0.3s ease-in-out;
  cursor: pointer;
}

.increase_btn:hover {
  background-color: #a9c7e8; /* 淡蓝色背景 */
}

.insert-input {
    width: calc(50% - 8px);
    margin-bottom: 8px;
    border-radius: 5px;
    width: 60%;
    height: 20px;
  }
  .image-button {
  background-color: #ffffff; /* 白色背景 */
  border: none; /* 无边框 */
  padding: 0; /* 去除按钮内边距 */
  cursor: pointer; /* 鼠标指针样式为手型 */
}

.niu{
  margin-top:30px;
  margin-left:40px ;
}
.niu-title{
  font-size: 40px;
  color: #667561;  
}

  </style>
  