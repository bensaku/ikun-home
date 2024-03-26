<template>
  <div class="container02">
    <div class="border">
      <div class="border-box">
        <h2 class="verification-title">实名认证</h2>
        <div class="button-container">
        <div class="button-wrapper">
          <div class="button-text1">身份证人像面</div>
          <label for="idCardFrontInput" class="big-button left-button">
            <img :src="idCardFrontImage || require('../assets/img/增加.png')" />
          </label>
          <input type="file" id="idCardFrontInput" style="display: none;" @change="uploadIDCardFront" />
        </div>
        <div class="button-wrapper">
          <div class="button-text2">身份证国徽面</div>
          <label for="idCardBackInput" class="big-button right-button">
            <img :src="idCardBackImage || require('../assets/img/增加.png')" />
          </label>
          <input type="file" id="idCardBackInput" style="display: none;" @change="uploadIDCardBack" />
       </div></div>
        <div class="data-container">
          <div class="data-row1">
          <div class="data-group">
            <span class="data-label" >姓名：    </span>
            <input class="data-input" readonly v-model="name" />
          </div>
          <div class="data-group">
            <span class="data-label">性别：</span>
            <input class="data-input" readonly  v-model="gender" />
          </div>
          <div class="data-group">
            <span class="data-label">民族：</span>
            <input class="data-input"  readonly v-model="ethnicity" />
          </div>
          <div class="data-group">
            <span class="data-label">住址：</span>
            <input class="data-input"  readonly v-model="address" />
          </div>
          </div>
          <div class="data-row2">
            <div class="data-group">
            <span class="data-label">身份证号：</span>
            <input class="data-input" style="width: 200px;"  readonly v-model="idNumber" />
            </div>
            <div class="data-group">
            <span class="data-label">截至日期：</span>
            <input class="data-input" readonly  v-model="expiryDate" />
          </div>
          </div>
        </div>
        <div class="button-container">
          <button class="auto-button" @click="autofill">开始识别</button>
          <button class="confirm-button" @click="confirmRegistration">确认</button>
          <button class="cancel-button" @click="backshimin">取消</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  data() {
    return {
      name: '',
      gender: '',
      ethnicity: '',
      address: '',
      idNumber: '',
      expiryDate: '',
      idCardFrontImage: null,
      idCardBackImage: null,
    };
  },
  methods: {
    async uploadIDCardFront() {
      const fileInput = document.getElementById('idCardFrontInput');
      const file = fileInput.files[0];

      const canvas = document.createElement('canvas');
      const context = canvas.getContext('2d');

      const image = new Image();
      image.onload = () => {
        // 设置目标宽高（例如设置为200x200）
        const targetWidth = 350;
        const targetHeight = 200;

        // 计算缩放比例
        let scale = 1;
        if (image.width > targetWidth || image.height > targetHeight) {
          scale = Math.min(targetWidth / image.width, targetHeight / image.height);
        }

        // 计算调整后的宽高
        const width = image.width * scale;
        const height = image.height * scale;

        // 在canvas上绘制调整后的图片
        canvas.width = width;
        canvas.height = height;
        context.drawImage(image, 0, 0, width, height);

        // 将canvas转为DataURL，作为调整后的图片
        const adjustedImage = canvas.toDataURL('image/jpeg');

        // 将调整后的图片赋值给idCardFrontImage属性
        this.idCardFrontImage = adjustedImage;
      };

      image.src = URL.createObjectURL(file);
      // 将文件的绝对路径赋值给idCardFrontImagePath
    this.idCardFrontImagePath = fileInput.value;
    },

    async uploadIDCardBack() {
      const fileInput = document.getElementById('idCardBackInput');
      const file = fileInput.files[0];

      const canvas = document.createElement('canvas');
      const context = canvas.getContext('2d');

      const image = new Image();
      image.onload = () => {
        // 设置目标宽高（例如设置为200x200）
        const targetWidth = 350;
        const targetHeight = 200;

        // 计算缩放比例
        let scale = 1;
        if (image.width > targetWidth || image.height > targetHeight) {
          scale = Math.min(targetWidth / image.width, targetHeight / image.height);
        }

        // 计算调整后的宽高
        const width = image.width * scale;
        const height = image.height * scale;

        // 在canvas上绘制调整后的图片
        canvas.width = width;
        canvas.height = height;
        context.drawImage(image, 0, 0, width, height);

        // 将canvas转为DataURL，作为调整后的图片
        const adjustedImage = canvas.toDataURL('image/jpeg');

        // 将调整后的图片赋值给idCardBackImage属性
        this.idCardBackImage = adjustedImage;
      };

      image.src = URL.createObjectURL(file);

      // 将文件的绝对路径赋值给idCardBackImagePath
    this.idCardBackImagePath = fileInput.value;
    },
      //开始识别
      autofill(){
        const formData = new FormData();
        // 添加身份证正面图片到FormData
        const idCardFrontInput = document.getElementById('idCardFrontInput');
        const idCardFrontFile = idCardFrontInput.files[0];
        formData.append('idCardFrontImage', idCardFrontFile);

        // 添加身份证反面图片到FormData
        const idCardBackInput = document.getElementById('idCardBackInput');
        const idCardBackFile = idCardBackInput.files[0];
        formData.append('idCardBackImage', idCardBackFile);
        // 发送POST请求到后端
        this.$axios
        .post('http://localhost:8090/api/recognize', formData)
        .then(response => {
          if (response.data != '照片空白') {

            const result = response.data;
            
            this.name = result.姓名;
            this.gender = result.性别;
            this.ethnicity = result.民族;
            this.address = result.住址;
            this.idNumber = result.身份证号;
            this.expiryDate = result.截止日期;
            this.$message.success("识别成功!");
          }
          else{
              this.$message.error("照片上传失败");
            }
        });
      },

      
      confirmRegistration() {
        if (!this.name) {
        this.$message.error('请先进行识别');
        return;
      }
      if (!this.gender) {
        this.$message.error('请先进行识别');
        return;
      }
      if (!this.ethnicity) {
        this.$message.error('请先进行识别');
        return;
      }
      if (!this.address) {
        this.$message.error('请先进行识别');
        return;
      }
      if (!this.idNumber) {
        this.$message.error('请先进行识别');
        return;
      }
      if (!this.expiryDate) {
        this.$message.error('请先进行识别');
        return;
      }

      const data = {
        userNo: this.$store.state.userData.username,
        name: this.name,
        sex:this.gender,
        nation:this.ethnicity,
        addr:this.address,
        id:this.idNumber,
        duetime:this.expiryDate,
      };

      this.$axios
        .post('http://localhost:8090/api/identity/manual', data)
        .then(response => {
          if (response.data == '该用户已实名') {
            this.$message.success('该用户已完成实名无需操作');
            setTimeout(() => {
              this.$router.push('/');
            }, 1000);
          } else if (response.data == '该身份数据已注册') {
            this.$message.error('该身份数据无法注册账号');
            setTimeout(() => {
              this.$router.push('/');
            }, 1000);
          } else if(response.data == '成为家属'){
            this.$message.success('身份已认证为家属');
            setTimeout(() => {
              this.$router.push('/');
            }, 1000);
          }
          else {
            this.$message.success('实名成功！');
            setTimeout(() => {
              this.$router.push('/');
            }, 1000);
          }
        });
        
      },
      backshimin(){
        this.$router.push('/RealName');
      },

    },
  };
  </script>

<style>
.container02 {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.97);
  background-image: url(../assets/img/68410857.png);
  background-size: cover;
}

.container02 .border {
  display: flex;
  justify-content: center;
  align-items: center;
}

.container02 .border-box {
  width: 1350px;
  height: 560px;
  position: relative;
  border: 0;
  border-radius: 40px;
  padding: 20px;
  text-align: center;
  background-color: rgba(255, 255, 255, 0.323);
    box-shadow: 0px 0px 10px gray;
    backdrop-filter: blur(8px);
  margin: -10px;
}

.container02 .verification-title {
  font-size: 40px;
  margin: 0 0 20px;
  color: #ffffff;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.container02 .button-container {
  
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.container02 .button-wrapper {
  width: 428px;
  height: 270px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.container02 .button-text1{
  padding-left: 20%;
  font-size: 30px;
  color: #ffffff;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
  }
.container02 .button-text2{
  padding-right: 20%;
  font-size: 30px;
  color: #ffffff;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}
.container02 .big-button {
  padding: 16px;
  border: 0;
  border-radius: 20px;
  cursor: pointer;
  transition: background-color 0.3s;
  background-color: rgba(255, 255, 255, 0.323);
}

.container02 .big-button:hover {
  background-color: #ffffff;
}

.container02 .left-button {
  width: 450px;
  height: 300px;
  margin-left: 30%;
  color: #fff;
}

.container02 .left-button:hover {
  background-color: #46abda;
}

.container02 .right-button {
  width: 450px;
  height: 300px;
  margin-right: 30%;
  color: #fff;
}

.container02 .right-button:hover {
  background-color: #46abda;
}

.container02 .button-text {
  margin-top: 8px;
}

.container02 .data-row1 {
margin-top: 3%;
display: flex;
justify-content: space-between;
margin-bottom: 8px;
}
.container02 .data-row2 {
margin-bottom: 5%;
display: flex;
justify-content: space-between;
margin-bottom: 8px;
}
.container02 .data-group {
display: flex;
align-items: center;
}
.container02 .data-label{
  text-align: left;
font-size: 25px;
width: 150px;
color: #ffffff;

}
.container02 .data-input{
border: 0;
border-radius: 5px;
font-size: 20px;
width: 120px;
background: transparent;
}
.container02 .data-group:not(:last-child) {
margin-right: 20px;
}

.container02 .button-container {
display: flex;
justify-content: space-between;
}
.container02 .auto-button {
  position: absolute;
  width: 250px;
  height: 50px;
  bottom: 40px;
  right: 35px;
  border-radius: 50px;
  background-color: #81D3F8;
  color:#7f8d55;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  margin-right: 40%;
  font-size: 20px;
  font-weight: bold;
}

.container02 .auto-button:hover {
  background-color: #24812c;
}

.container02 .confirm-button {
  position: absolute;
  width: 250px;
  height: 50px;
  bottom: 40px;
  right: 35px;
  border-radius: 50px;
  background-color: #81D3F8;
  color:#558D7F;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  margin-right: 20%;
  font-size: 20px;
  font-weight: bold;
}

.container02 .confirm-button:hover {
  background-color: #3d7dd8;
}

.container02 .cancel-button {
  position: absolute;
  width: 250px;
  height: 50px;
  bottom: 40px;
  right: 35px;
  border-radius: 50px;
  background-color: #ffffff;
  color: #558D7F;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  font-size: 20px;
  font-weight: bold;
  margin-top: 10%;
}

.container02 .cancel-button:hover {
  background-color: #d60000;
}
</style>