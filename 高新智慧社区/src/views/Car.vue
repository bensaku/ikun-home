<template>
  <div>
    <TopNav style="position:fixed; width:100%; left: 0%;z-index: 2;">
    </TopNav>
    <LeftNav style="position:fixed;width:13%; left:0; top: 60px; z-index: 3;">
    </LeftNav>
    <div style="position:absolute;top:60px;right:0;width:87%;min-height: 100vh;z-index: 1; background-color: #f2f2f2;">
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

      <div v-if="this.$store.state.userData.usertype === '家属'">

        <div class="card_faqi" id="car_card_tianjia">
          <div class="card-left">
            <h2 class="card-title4">
              您尚未绑定房屋
              <img class="card-icon" src="../assets/img/307感叹号-三角框.png" />
            </h2>
            <div class="card-data_faqi">
              <h>绑定房屋后方可添加车辆，点击右侧按钮转跳至绑定房屋页面</h>
            </div>
          </div>
          <div class="card-right">
            <button class="card-button" @click="$router.push('/House');">
              <img class="card-button-image" src="../assets/img/房屋.png" />
            </button>
          </div>
        </div>

      </div>

      <div v-else>
        <div class="card_faqi" id="car_card_tianjia">
          <div class="card-left">
            <h2 class="card-title2">
              添加车辆
              <img class="card-icon" src="../assets/img/汽车 (1).png" />
            </h2>
            <div class="card-data_faqi">
              <h>点击右侧按钮添加车辆</h>
            </div>
          </div>
          <div class="card-right">
            <button class="card-button" @click="showModal">
              <img class="card-button-image" src="../assets/img/增加.png" />
            </button>

          </div>
        </div>


        <div v-for="car in cars" :key="car.carPort">

<div class="card" id="car_card_guoqi">
  <div class="card-left">
  <h2 class="card-title">
    我的车辆
    <img class="card-icon" src="../assets/img/汽车.png"/>
  </h2>
  <div class="card-data">
    <div class="row">
      <label for="leftData1">车辆信息</label>
    </div>
    <div class="spacer"></div> <!-- 间隔元素 -->
    <div class="row">
      <label for="leftData1">车辆型号:</label>
      <input class="card-input" type="text" v-model="car.carDetail" :readonly="isReadOnly"/>
    </div>
    <div class="row">
      <label for="leftData1">车牌号:</label>
      <input class="card-input" type="text" v-model="car.carNo" :readonly="isReadOnly"/>
    </div>
    <div class="row">
      <label for="leftData1">已绑定的房屋:</label>
      <input class="card-input" style="width: 180px;" type="text" v-model="car.house[0]" :readonly="isReadOnly"/>
      <a href="#" class="my-link" @click="showModal3(car.house)">详情</a>
    </div>
  </div>
</div>
<div class="card-right">
  <div v-if="isCarValid(car.carTime)">
    <h2 class="card-title">
    我的车位
    <img class="card-icon" src="../assets/img/停车场.png"/>
    </h2>
  </div>
  <div v-else-if="isCarExpired(car.carTime)">
    <h2 class="card-title4">
    我的车位
    <img class="card-icon" src="../assets/img/停车场 (1).png"/>
    <div class="rotated2">已到期</div>
    </h2>
  </div>
  <div v-else>
    <h2 class="card-title3">
    我的车位
    <img class="card-icon" src="../assets/img/停车场 (2).png"/>
    <div class="rotated1">即将到期</div>
    </h2>
  </div>
    <div class="row">
      <label for="leftData1">车位信息</label>
    </div>
    <div class="spacer"></div> <!-- 间隔元素 -->
    <div class="row">
      <label for="leftData1">位置:</label>
      <input class="card-input" type="text" v-model="car.carPosition" :readonly="isReadOnly"/>
      <label for="leftData1">编号:</label>
      <input class="card-input" style="width: 40px;" type="text" v-model="car.carPort" :readonly="isReadOnly"/>
    </div>
    <div class="row">
      <label for="leftData1">到期时间:</label>
      <input class="card-input" type="text" v-model="car.carTime" :readonly="isReadOnly"/>
      <a href="#" class="my-link" @click="showModal2(car)">续租</a>
    </div>
    <div class="row">
      <button style="background-color: #5aa2d2;cursor: pointer;color: #fff; border: none; border-radius: 10px;" 
      @click="showModal4(car.house,car.carPort)">绑定更多房屋</button>
    </div>
</div>

<div class="card-btnimg">
  <button class="card-bensaku-button" @click="showimg">
    <img class="card-button-image" src="../assets/img/汽车定位 (1).png"/>
  </button>
  <label style="margin-left: 20px; font-size: 3px; color: rgb(100, 100, 100);">显示车位图</label>
</div>
</div>



      </div>



</div>

<div class="modal-overlay" v-if="isImgVisible">
  <div style="margin-right: -130px; margin-top: 410px; z-index: 9999;">
    <button class="modal-button2" @click="closeimg">关闭</button>
</div>
  <img src="../assets/img/车位图.jpg"/>
</div>





<div class="modal-overlay" v-if="isImgVisible">
  <div style="margin-right: -130px; margin-top: 410px; z-index: 9999;">
    <button class="modal-button2" @click="closeimg">关闭</button>
</div>
  <img src="../assets/img/车位图.jpg"/>
</div>



<div class="modal-overlay" v-if="isModalVisible">
<div class="modal"  style="height: 600px; width: 430px;">
      <h2 class="modal-title">新增车辆信息</h2>
      <div class="modal-row">
        <label class="modal-label">车牌号：</label>
        <input class="modal-input" type="text" v-model="car_no4" maxlength="8"/>
      </div>
      <div class="modal-row">
        <label class="modal-label">车辆型号：</label>
        <input class="modal-input" type="text" v-model="car_detail4" />
      </div>
      <div class="modal-row">
        <label for="dropdown" class="modal-label">选择房屋：</label>
        <select id="dropdown" v-model="selectedOption1" class="modal-select">
          <option value="" disabled selected hidden>请选择您的房屋</option>
          <option v-for="option in allHouse" :key="option.houseNo" :value="option.houseNo">{{ option.housePlot+option.houseBuilding+option.houseUnit+option.houseNumber }}</option>
        </select>
      </div>
      <div class="modal-row">
        <label for="dropdown2" class="modal-label">选择车位：</label>
        <select id="dropdown2" v-model="selectedOption2" class="modal-select" @change="setCharge">
          <option value="" disabled selected hidden>请选择车位号</option>
          <option v-for="option in usableCarPort" :key="option.carPort" :value="option.carPort">{{ option.carPort }}</option>
        </select>
      </div>
      <div class="modal-row">
        <label class="modal-label">租赁年限:</label>
        <input class="modal-shortinput" type="text" v-model="car_time4" />
        <span class="modal-label">年</span>
      </div>
      <div class="modal-row" style="margin-left: 10px;">
        <label class="modal-label">金额:</label>
        <label class="modal-chargelabel">{{ selectedOptionCharge }}</label>
        <span class="modal-label">元/年</span>
      </div>  
      <div class="modal-buttons">
        <button class="modal-button1" @click="doinsert">确认</button>
        <button class="modal-button2" @click="closeModal">取消</button>
      </div>
    </div>
</div>


<div class="modal-overlay" v-if="isModalVisible2">
<div class="modal" style="height: 500px;">
      <h2 class="modal-title">续租车辆信息</h2>
      <div class="modal-row">
        <label class="modal-label">车牌号：</label>
        <label class="modal-showlabel">{{tempCar.carNo}}</label>
      </div>
      <div class="modal-row">
        <label class="modal-label">车辆型号：</label>
        <label class="modal-showlabel">{{tempCar.carDetail}}</label>
      </div>
      <div class="modal-row">
        <label class="modal-label">车位号：</label>
        <label class="modal-label">{{tempCar.carPort}}</label>
      </div>
        <div class="modal-row">
        <label class="modal-label">续租年限:</label>
        <input class="modal-shortinput" type="text" v-model="car_time5" />
        <span class="modal-label">年</span>
        </div>
        <div class="modal-row">
        <label class="modal-label">金额:</label>
        <label class="modal-chargelabel">{{ tempCar.carCharge }}</label>
        <span class="modal-label">元/年</span>
        </div>
      <div class="modal-buttons">
        <button class="modal-button1" @click="doincrease">确认</button>
        <button class="modal-button2" @click="closeModal2">取消</button>
      </div>
</div>
</div>

      <div class="modal-overlay" v-if="isModalVisible3">
        <div class="modal" style="height: 400px;">
          <h2 class="modal-title">绑定房屋信息</h2>
          <ul>
            <li v-for="hs in houseDetail" :key="hs">
              <label class="modal-showlabel">{{ hs }}</label>
            </li>
          </ul>

          <div class="modal-buttons" style="position: absolute; bottom: 20px;left: 50%; transform: translateX(-50%);">
            <button class="modal-button3" @click="closeModal3">关闭窗口</button>
          </div>
        </div>
      </div>

<div class="modal-overlay" v-if="isModalVisible4">
<div class="modal" style="height: 400px;">
      <h2 class="modal-title">可绑定的房屋</h2>
      <ul>
  <li v-for="option in notInHouseList" :key="option.houseNo"  style="list-style-type: none;">
    <input type="checkbox" :value="option" v-model="selectedItems" style="transform: scale(1.5);">
    <label class="modal-showlabel">{{ option.housePlot+option.houseBuilding+option.houseUnit+option.houseNumber }}</label>
  </li>
</ul>
      
      <div class="modal-buttons" style="position: absolute; bottom: 20px; left: 13%;">
        <button class="modal-button3" style="margin-right: 10px;" @click="dobind">确认绑定</button>
        <button class="modal-button4" style="margin-left: 10px;" @click="closeModal4">取消绑定</button>
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
    LeftNav
  },
  data() {
    return {
      userNo: this.$store.state.userData.username,
      cars: null,
      houseDetial: null,
      allHouse: null,
      usableCarPort: null,
      notInHouseList: [],
      selectedItems: [],
      selectedOptionCharge: '', // 对应的车位费用
      tempCar: null,

  a:false,
  b:true,
  isReadOnly: true,
  isModalVisible: false,
  isModalVisible2: false,
  isModalVisible3: false,
  isModalVisible4: false,
  isImgVisible:false,
  selectedOption1: '',
  selectedOption2: '',

      complaintTitle3: '',

      car_detial4: '',
      car_no4: '',
      car_pote4: '',
      car_time4: '',
      car_charge4: '',
      car_detial5: '',
      car_no5: null,
      car_pote5: '',
      car_time5: '',
      car_charge5: '',

  selectedCarPort:''

};
},
created(){
this.fetchData();
},
methods: {
showimg(){
this.isImgVisible = true;
},
closeimg(){
  this.isImgVisible = false;
},

    isCarValid(carTime) {
      const expirationDate = new Date(carTime);
      const currentDate = new Date();
      const oneMonthLater = new Date();
      oneMonthLater.setMonth(currentDate.getMonth() + 1);

      return expirationDate > currentDate && expirationDate > oneMonthLater;
    },
    isCarExpired(carTime) {
      const expirationDate = new Date(carTime);
      const currentDate = new Date();

      return expirationDate < currentDate;
    },
    setCharge() {
      // 获取选中的车位号对应的车位费用
      const selectedCarPort = this.usableCarPort.find(option => option.carPort === this.selectedOption2);
      if (selectedCarPort) {
        this.selectedOptionCharge = selectedCarPort.carCharge;
      } else {
        this.selectedOptionCharge = ''; // 如果没有对应的车位费用，清空输入框的值
      }
    },
    showModal() {
      this.$axios.get('http://localhost:8090/api/house/getByUserState', {
        params: { userNo: this.userNo }
      })
        .then(response => {
          this.allHouse = response.data;
        })
        .catch(error => {
          console.error(error);
        })

      this.$axios.get('http://localhost:8090/api/carport/getUsableCarPort')
        .then(response => {
          this.usableCarPort = response.data;
        })
        .catch(error => {
          console.error(error);
        })

      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    },
    fetchData() {

      this.$axios.get('http://localhost:8090/api/house/getByUserState', {
        params: { userNo: this.userNo }
      })
        .then(response => {
          this.allHouse = response.data;
        })
        .catch(error => {
          console.error(error);
        })

      this.$axios.get('http://localhost:8090/api/carport/getCar', {
        params: { userNo: this.userNo }
      })
        .then(response => {
          this.cars = response.data;
        })
        .catch(error => {
          console.error(error);
        })
    },
    doinsert() {
    if (!this.car_no4) {
      this.$message.error("请输入车牌！");
      return;
    }
    if (!this.car_detail4) {
      this.$message.error("请输入车辆型号！");
      return;
    }
    if (!this.selectedOption1) {
      this.$message.error("请选择您的房产！");
      return;
    }
    if (!this.selectedOption2) {
      this.$message.error("请输入车位号！");
      return;
    }
    if (!this.car_time4) {
      this.$message.error("请输入续租年限！");
      return;
    } else{
      // 解析 car_time4 字段为整数
      const yearToAdd = parseInt(this.car_time4);

      // 获取当前日期对象
      const currentDate = new Date();

      // 进行日期计算和操作
      currentDate.setFullYear(currentDate.getFullYear() + yearToAdd);

      // 获取计算后的日期结果
      const result = currentDate.toISOString().split("T")[0];


    const insertCar = {
      carPort : this.selectedOption2,
      carNo : this.car_no4,
      carDetail :this.car_detail4,
      carCharge : this.selectedOptionCharge,
      carTime : result,
      houseNo :[this.selectedOption1],
    };
      this.$axios.post('http://localhost:8090/api/carport/addCar',insertCar)
    .then(response => {
      this.$message.success(response.data);
      this.fetchData();
      this.isModalVisible=false;
    })
    .catch(error => {
      this.$message.error(error.response.data);
      console.error(error);
    })
    }
    },
    showModal2(car) {
      this.tempCar = car;
      this.isModalVisible2 = true;
    },
    closeModal2() {
      this.isModalVisible2 = false;
    },
    showModal3(house) {
      this.houseDetail = house;
      this.isModalVisible3 = true;
    },
    closeModal3() {
      this.isModalVisible3 = false;
    },
    showModal4(house,carport) {
      this.selectedItems=[];
      this.selectedCarPort = carport;
      this.notInHouseList=[];
      for (let i = 0; i < this.allHouse.length; i++) {
    const currentHouse = this.allHouse[i];
    const houseInfo = {
      housePlot: currentHouse.housePlot,
      houseBuilding: currentHouse.houseBuilding,
      houseUnit: currentHouse.houseUnit,
      houseNumber: currentHouse.houseNumber,
      houseNo: currentHouse.houseNo
    };

    const searchString = `${houseInfo.housePlot}${houseInfo.houseBuilding}${houseInfo.houseUnit}${houseInfo.houseNumber}`;

    if (!house.includes(searchString)) {
      this.notInHouseList.push(houseInfo);
    }
  }
      this.isModalVisible4 =true;
    },
    closeModal4() {

      this.isModalVisible4 = false;
    },
    doincrease() {
      if (!this.car_time5) {
      this.$message.error("请输入续租年限！");
      return;
    } else{
      // 解析 car_time4 字段为整数
const yearToAdd = parseInt(this.car_time5);

// 获取当前日期对象
let updatedDate = new Date();

if (this.isCarValid(this.tempCar.carTime)) {
  updatedDate = new Date(this.tempCar.carTime);
}

// 进行日期计算和操作
updatedDate.setFullYear(updatedDate.getFullYear() + yearToAdd);

// 获取计算后的日期结果
const result = updatedDate.toISOString().split("T")[0];


    const setCar = {
      carPort : this.tempCar.carPort,
      carTime : result,
    };
      this.$axios.post('http://localhost:8090/api/carport/addTime',setCar)
    .then(response => {
      this.$message.success(response.data);
      this.fetchData();
      this.isModalVisible2=false;
    })
    .catch(error => {
      this.$message.error(error.response.data);
      console.error(error);
    })

    }
  },
  dobind(){
    if (this.selectedItems.length === 0) {
      this.$message.error("请选择要绑定的房屋！");
      return;}
    const insertHouse = {
      carPort : this.selectedCarPort,
      houses: this.selectedItems,
      
    };
    this.$axios.post('http://localhost:8090/api/carport/bindHouse',insertHouse)
    .then(response => {
      this.$message.success(response.data);
      this.fetchData();
      this.isModalVisible4=false;
    })
    .catch(error => {
      this.$message.error(error.response.data);
      console.error(error);
    })
    
  }
}
};

</script>
<style>
@import "../assets/style/card.css";
@import "../assets/style/modal.css";

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
.background-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
}
</style>