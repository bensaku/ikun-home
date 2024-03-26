<template>
    <div class="container01">
      <div class="border">
        <div class="border-box">
          <h2 class="verification-title"><b>实名认证</b></h2>
          <div class="button-container01">
            <button class="big-button left-button01" @click="shoudongshuru">手动输入身份信息</button>
            <button class="big-button right-button01" @click="zidongtianxie">自动识别身份证照片</button>
          </div>
          <button class="cancel-button01" @click="backregister">取消</button>
        </div>
      </div>
    </div>
  </template>
  <script>
  export default {
    methods: {
      shoudongshuru() {
        this.$router.push('/Manual');
      },
      zidongtianxie(){
        this.$router.push('/AutoFill');
      },
      backregister(){
        this.$router.push('/');
      },
    },
  };
  </script>
  
  <style>
  .container01 {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.4);
    background-image: url(../assets/img/68410857.png);
    background-size: cover;
  }
  
  .container01 .border {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .container01 .border-box {
    width: 1350px;
    height: 560px;
    position: relative;
    border: 0;
    border-radius: 40px; /* 设置圆角半径 */
    padding: 20px;
    text-align: center;
    background-color: rgba(255, 255, 255, 0.323);
    box-shadow: 0px 0px 10px gray;
    backdrop-filter: blur(8px);
    margin: -10px;
  }
  
  .container01 .verification-title {
    font-size: 40px;
    margin: 0 0 20px;
    color: #ffffff;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
  }
  
  .container01 .button-container01 {
    display: flex;
    justify-content: space-between;
    margin-top: 50px;
    margin-left: 20px;
    margin-right: 20px;
    margin-bottom: 20px;
    border-radius: 10px;
    width: 1300px;
    height: 200px;
  }
  .container01 .big-button:hover {
  background-color: #3f8ae0; /* 鼠标悬停时的背景颜色 */
}
.container01 .big-button {
    flex: 1;
    padding: 16px;
    background-color: rgba(255, 255, 255, 0.323);
    color: #000000;
    border: 0;
    border-radius: 4px;
    font-size: 18px;
    cursor: pointer;
  }
  .container01 .left-button01:hover {
  background-color: #3d7dd8; /* 鼠标悬停时的背景颜色 */
}
.container01 .left-button01 {
  color: #000000;
  margin-right: 50px;
  border-radius: 20px;
  font-size: 35px;
}
.container01 .right-button01:hover {
  background-color: #3d7dd8; /* 鼠标悬停时的背景颜色 */
}
.container01 .right-button01 {
  color: #000000;
  margin-left: 50px;
  border-radius: 20px;
  font-size: 35px;
}

.container01 .cancel-button01 {
    position: absolute;
    width: 250px;
    height: 50px;
    bottom: 40px;
    right: 35px;
    border-radius: 50px;
    background-color: #ffffff;
    color: #34975c;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    font-size: 20px;
    font-weight: bold;
  }
  
  .container01 .cancel-button01:hover {
  background-color: #d60000; /* 鼠标悬停时的背景颜色 */
}
  </style>
  