<template>
  <div>
    <TopNav style="position: fixed; width: 100%; left: 0%; z-index: 3">
    </TopNav>
    <LeftNav style="position: fixed; width: 13%; left: 0; top: 60px; z-index: 3">
    </LeftNav>


    <div style="
        position: absolute;
        right: 0;
        top: 60px;
        min-height: 100vh;
        width: 87%;
        z-index: 1;
        background-color: #f2f2f2;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
      ">


      <div class="background-container">
        <vue-particles color="#70B603" :particleOpacity="0.6" :particlesNumber="100" shapeType="circle" :particleSize="5"
          linesColor="#0000ff" :linesWidth="1" :lineLinked="true" :lineOpacity="0.6" :linesDistance="80" :moveSpeed="1.3"
          :hoverEffect="true" hoverMode="grab" :clickEffect="false" clickMode="push" />
      </div>
      <div style="position: fixed; right: 120px; top: 110px; z-index: 1; width: 640px;">
        <div id="myChart"></div>
      </div>

      <div style="position: fixed; right: 120px; top: 550px; z-index: 1; width: 640px;">
        <div id="myChart2"></div>
      </div>


      <div class="card_faqi_house" id="tousu_card_faqi">
        <div class="card-left">
          <h2 class="card-title4">
            绑定房屋
            <img class="card-icon" src="../assets/img/house (2).png" />
          </h2>
          <div class="card-data_faqi">
            <h>点击右侧＋进行绑定</h>
          </div>
        </div>
        <div class="card-right">
          <button class="card-button" @click="showModal">
            <img class="card-button-image" src="../assets/img/增加.png" />
          </button>
        </div>
      </div>

      <div v-for="house in houses" :key="house.houseNo">
        <!-- 第一个 div 的内容 -->
        <div v-if="house.houseState === '审核中'" id="family_card">
          <div class="house-card" id="house_card_shenhezhong">
            <div>
              <h2 class="card-title3">
                审核中
                <img class="card-icon" src="../assets/img/house (1).png" />
              </h2>
              <div class="row">
                <label for="leftData1">正在审核信息是否吻合,请稍候</label>
              </div>
              <div class="row">
                <label for="leftData1">房屋信息:</label>
              </div>
              <div class="spacer"></div>
              <!-- 间隔元素 -->
            </div>
            <div style="display: flex">
              <div class="card-left">
                <div class="card-data">
                  <div class="row">
                    <label for="leftData1">小区:</label>
                    <input class="card-input" type="text" v-model="house.housePlot" :readonly="isReadOnly" />
                  </div>
                  <div class="row">
                    <label for="leftData1">楼栋:</label>
                    <input class="card-input" type="text" v-model="house.houseBuilding" :readonly="isReadOnly" />
                  </div>
                </div>
              </div>
              <div class="card-right">
                <div class="row">
                  <label for="leftData1">单元:</label>
                  <input class="card-input" type="text" v-model="house.houseUnit" :readonly="isReadOnly" />
                </div>
                <div class="row">
                  <label for="leftData1">户号:</label>
                  <input class="card-input" type="text" v-model="house.houseNumber" :readonly="isReadOnly" />
                </div>
              </div>
            </div>
          </div>
        </div>

        <div v-else>
          <div class="house-card" id="house_card_yibangding">
            <div>
              <h2 class="card-title">
                已绑定
                <img class="card-icon" src="../assets/img/house (3).png" />
              </h2>
              <div class="row">
                <label for="leftData1">房屋信息:</label>
              </div>
              <div class="spacer"></div>
              <!-- 间隔元素 -->
            </div>
            <div style="display: flex">
              <div class="card-left">
                <div class="card-data">
                  <div class="row">
                    <label for="leftData1">小区:</label>
                    <input class="card-input" type="text" v-model="house.housePlot" :readonly="isReadOnly" />
                  </div>
                  <div class="row">
                    <label for="leftData1">楼栋:</label>
                    <input class="card-input" type="text" v-model="house.houseBuilding" :readonly="isReadOnly" />
                  </div>
                </div>
              </div>
              <div class="card-right">
                <div class="row">
                  <label for="leftData1">单元:</label>
                  <input class="card-input" type="text" v-model="house.houseUnit" :readonly="isReadOnly" />
                </div>
                <div class="row">
                  <label for="leftData1">户号:</label>
                  <input class="card-input" type="text" v-model="house.houseNumber" :readonly="isReadOnly" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-overlay" v-if="isModalVisible">
        <div class="modal" style="height: 420px">
          <h2 class="modal-title">房屋绑定</h2>
          <div class="modal-row">
            <label class="modal-label">小区：</label>
            <select id="dropdown" v-model="plot" class="modal-select">
              <option value="" disabled selected hidden>请选择您的小区</option>
              <option v-for="option in dropdownOptions1" :key="option.value" :value="option.label">
                {{ option.label }}
              </option>
            </select>
          </div>
          <div class="modal-row">
            <label class="modal-label">楼栋：</label>
            <select id="dropdown" v-model="building" class="modal-select">
              <option value="" disabled selected hidden>请选择您的楼栋</option>
              <option v-for="option in dropdownOptions2" :key="option.value" :value="option.value">
                {{ option.value }}
              </option>
            </select>
          </div>
          <div class="modal-row">
            <label class="modal-label">单元：</label>
            <select id="dropdown" v-model="unit" class="modal-select">
              <option value="" disabled selected hidden>请选择您的单元</option>
              <option v-for="option in dropdownOptions3" :key="option.value" :value="option.value">
                {{ option.value }}
              </option>
            </select>
          </div>
          <div class="modal-row">
            <label class="modal-label">户号：</label>
            <select id="dropdown" v-model="number" class="modal-select">
              <option value="" disabled selected hidden>请选择您的户号</option>
              <option v-for="option in dropdownOptions4" :key="option.value" :value="option.value">
                {{ option.value }}
              </option>
            </select>
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
import TopNav from "../components/TopNav.vue";
import LeftNav from "../components/LeftNav.vue";
import echarts from "echarts"; // 引入echarts
export default {
  components: {
    TopNav,
    LeftNav,
  },
  data() {
    return {
      echartsOption: {
        // echarts选项，所有绘图数据和样式都在这里设置
        legend: {
          //图表上方的图例
          data: ["ikun花园", "佩罗高地", "李察尊爵"],
        },
        xAxis: {
          type: "category",
          data: ["2022-01-01", "2022-04-01", "2022-07-01", "2022-10-01", "2023-01-01", "2023-04-01", "2023-07-01"], // x轴数据
          name: "日期", // x轴名称
          nameTextStyle: {
            // x轴名称样式
            fontWeight: 600,
            fontSize: 18,
          },
        },
        yAxis: {
          type: "value",
          name: "市值/元", // y轴名称
          nameTextStyle: {
            // y轴名称样式
            fontWeight: 2000,
            fontSize: 18,
          },
        },
        tooltip: {
          //鼠标放到图上的数据展示样式
          trigger: "axis",
        },
        series: [
          //每条折线的数据系列
          {
            name: "ikun花园",
            data: [7000, 7200, 8000, 6700, 7300, 8200, 9000],
            type: "line",
          },
          {
            name: "佩罗高地",
            data: [6800, 7000, 8300, 9200, 7100, 8800, 9300],
            type: "line",
          },
          {
            name: "李察尊爵",
            data: [7500, 8200, 7700, 7500, 7300, 6800, 6500],
            type: "line",
          },
        ],
      },

      echartsOption2: {
        title: {
          text: '房产优势图'
        },
        legend: {
          data: ['IKUN花园', '李察尊爵', '佩多高地']
        },
        radar: {
          // shape: 'circle',
          indicator: [
            { name: '地理位置', max: 100 },
            { name: '道路交通', max: 100 },
            { name: '生态环境', max: 100 },
            { name: '配套设施', max: 100 },
            { name: '教育资源', max: 100 },
            { name: '医疗资源', max: 100 }
          ]
        },
        series: [
          {
            name: '房产优势对比',
            type: 'radar',
            data: [
              {
                value: [100, 90, 100, 90, 90, 90],
                name: 'IKUN花园'
              },
              {
                value: [90, 80, 100, 70, 100, 100],
                name: '李察尊爵'
              },
              {
                value: [80, 80, 100, 100, 90, 90],
                name: '佩多高地'
              }
            ]
          }
        ]
      },
      house_plot2: "",
      house_building2: "",
      house_unit2: "",
      house_number2: "",
      isReadOnly: true,
      isModalVisible: false,
      building: "",
      unit: "",
      number: "",
      plot: "",
      property: "",
      dropdownOptions1: [
        { label: "ikun花园", value: "ikun物业" },
        { label: "佩罗高地", value: "rock物业" },
        { label: "李察尊爵", value: "star物业" },
      ],
      dropdownOptions2: [
        { value: "一号楼" },
        { value: "二号楼" },
        { value: "三号楼" },
        { value: "四号楼" },
        { value: "五号楼" },
        { value: "六号楼" },
        { value: "七号楼" },
        { value: "八号楼" },
        { value: "九号楼" },
        { value: "十号楼" },
      ],
      dropdownOptions3: [{ value: "一单元" }, { value: "二单元" }],
      dropdownOptions4: [
        { value: "101" },
        { value: "102" },
        { value: "103" },
        { value: "104" },
        { value: "201" },
        { value: "202" },
        { value: "203" },
        { value: "204" },
        { value: "301" },
        { value: "302" },
        { value: "303" },
        { value: "304" },
      ],
      houses: "",
      userNo: this.$store.state.userData.username,
    };
  },
  watch: {
    plot(newPlot) {
      // 当 plot 属性发生变化时触发
      this.updateProperty(newPlot); // 调用 updateProperty 方法，并将新的 plot 值作为参数传递
    },
  },
  created() {
    this.fetchData();
  },
  mounted() {
    let myChart = echarts.init(document.getElementById("myChart"), "light"); // 初始化echarts, theme为light
    myChart.setOption(this.echartsOption); // echarts设置选项

    let myChart2 = echarts.init(document.getElementById("myChart2"), "light"); // 初始化echarts, theme为light
    myChart2.setOption(this.echartsOption2); // echarts设置选项

  },
  methods: {
    updateProperty(newPlot) {
      const option = this.dropdownOptions1.find(
        (option) => option.label === newPlot
      );
      // 并将新的值赋给 property 属性
      this.property = option.value; // 示例：将新的值赋给 property
    },
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    },
    fetchData() {
      this.$axios
        .get("http://localhost:8090/api/house/getByUser", {
          params: { userNo: this.userNo },
        })
        .then((Response) => {
          this.houses = Response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    doinsert() {
      const house = {
        housePlot: this.plot,
        houseBuilding: this.building,
        houseUnit: this.unit,
        houseNumber: this.number,
        houseProperty: this.property,
        userNo: this.userNo,
      };

      if (!this.plot) {
        this.$message.error("请选择小区！");
        return;
      }
      if (!this.building) {
        this.$message.error("请选择楼栋！");
        return;
      }
      if (!this.unit) {
        this.$message.error("请选择单元！");
        return;
      }
      if (!this.number) {
        this.$message.error("请选择户号！");
        return;
      } else {
        this.$axios
          .post("http://localhost:8090/api/house/addHouse", house)
          .then((response) => {
            // 处理成功的响应
            //alert(response.data);
            this.$message.success(response.data);
            this.$router.go(0);
          })
          .catch((error) => {
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

#myChart {
  width: 100%;
  height: 400px;

  background-color: white;
  border-radius: 20px;
  padding: 20px 20px 0px 20px;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.319);
  z-index: 1;
}

#myChart2 {
  width: 100%;
  height: 400px;

  background-color: white;
  border-radius: 20px;
  padding: 20px 20px 0px 20px;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.319);
  z-index: 1;
}
</style>