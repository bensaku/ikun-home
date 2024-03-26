<template>
    <div class="login clearfix full-background">
      <div class="login-wrap2">
      
      <div class="footer2">
        <span class="text">温馨提示</span>
        <hr>
        <div>
          <div class="saying">
            <div>
              <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了方便居民参与管理小区、管理自己的房屋及车辆等内容，高新智慧社区开通了该管理系统，无需安装客户端和插件，为了获得更好的体验建议使用Chrome、Firefox等浏览器。</p>
            </div>
            <div>
              <p></p>
            </div>
            <div>
              <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登录方式：居民、业委会使用统一认证登陆，用户可在该页面进行注册账号和密码，登陆后先进行房屋绑定再进行其他操作。</p>
            </div>
            <div>
              <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如需要修改密码，可通过发送邮件给注册邮箱填写验证码找回。</p>
            </div>
            <div>
              <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请在右端按要求输入账号及密码登录使用该系统。</p>
            </div>
            <div>
              <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开发者电话： 0551-12345678</p>
            </div>
          </div>

        </div>
      </div>
    </div>
      <div class="login-wrap">
        <el-row type="flex" justify="center">
          <el-form ref="loginForm" :model="user" status-icon label-width="80px">
            <h3>登录</h3>
            <hr>
            <div class="icon-container">
                <img src="../assets/img/用户名.png" alt="Custom Icon" class="icon" />
                <input type="text" v-model="user.username" placeholder="请输入用户名" class="input" />
            </div>
            <div class="spacer"></div> <!-- 间隔元素 -->
            <div class="icon-container">
                <img src="../assets/img/密码.png" alt="Custom Icon" class="icon" />
                <input type="password" v-model="user.password" placeholder="请输入密码" class="input" />
            </div>
            <div class="spacer"></div> <!-- 间隔元素 -->
              <el-button type="primary" icon @click="dologin()">登录</el-button>
            <div class="spacer"></div> <!-- 间隔元素 -->
            <div style="display: flex;  justify-content: space-between;">
                <span>
                    <a class="my-link" @click="$router.push('/RegisterView');">立即注册</a>
                </span>
                <span>
                  <a class="my-link" @click="$router.push('/Retrieve');">找回密码</a>
                </span>
            </div>
          </el-form>
        </el-row>
      </div>
      <div class="footer">
        <img src="../assets/img/logo.png"
      style="width:240px;height: auto; margin:0 auto;margin-top:20px;margin-right: 15px; margin-bottom: 40px;" alt="">
        <span class="text">高新智慧社区管理系统</span>
      </div>

  </div>
  </template>
   
<script>
import { sha256 } from "js-sha256"; // 导入SHA-256加密算法库

  export default {
    name: "login",
    data() {
      return {
        user: {
          username: "",
          password: "",
        },
      };
    },
    methods: {

      dologin() {
        if (!this.user.username) {
          this.$message.error("请输入用户名！");
          return;
        } 
        if (!this.user.password) {
          this.$message.error("请输入密码！");
          return;
        }
        
        // 对密码进行加密（使用SHA-256算法）
        const encryptedPassword = sha256(this.user.password);
        const data = {
          userNo: this.user.username,
          userPassword: encryptedPassword,
        };
        
        // 发送POST请求到后端登录接口
        this.$axios.post('http://localhost:8090/api/user/login', data)
          .then(response => {
            // 登录成功，处理返回的响应数据
            // 其他操作，例如保存token到本地存储或Vuex状态管理、跳转到其他页面等
            if(response.data=="账号未实名"){
              this.$message.error("账号未实名认证，请先进行实名认证。");
              const userData = {
                username: this.user.username,
                mail: this.user.email,
                usertype: response.data.userType,
                name:''
              };

              // 提交 mutation 更新 userData 状态
              this.$store.commit('setUserData',userData);
              
              setTimeout(() => {
                    // 设置延迟时间为1秒（1000毫秒）
                    this.$router.push('/RealName'); // 执行页面跳转
                    }, 1000); // 设置延迟时间为1秒（1000毫秒）
            }
            else if(response.data=="密码错误"){
              this.$message.error("登录失败，账号或密码错误。");
            }
            else{
              this.$message.success("登录成功！");
              
              const userData = {
                username: this.user.username,
                mail: this.user.email,
                usertype: response.data.userType,
                name: response.data.name,
              };

              // 提交 mutation 更新 userData 状态
              this.$store.commit('setUserData',userData);
              this.$router.push('/index');
            }
          })
          .catch(error => {
            // 登录失败，处理错误信息
            console.log(error.response); // 输出登录失败的错误信息
            // 其他操作，例如显示错误提示等
            this.$message.error(error.response);
          });
          
      }
    }
  };
</script>

   
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style lang="scss" scoped>
.login {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgb(255, 255, 255,0.3);
  background-image: url(../assets/img/68410857.png);
  background-size: cover;
  }
  .login .login-wrap {
    background-color: rgba(255, 255, 255, 0.323);
    box-shadow: 0px 0px 10px gray;
    backdrop-filter: blur(8px);
    border: none;
    background-size: cover;
    width: 400px;
    height: 500px;
    margin: 215px auto;
    overflow: hidden;
    padding-top: 10px;
    line-height: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 40px; /* 设置边框的圆角半径为10px */
    margin-left: 5px;
    margin-top: 300px;
  }
   
  .login h3 {
    color: #0babeab8;
    font-size: 35px;
    margin-left: 80px;
    text-shadow: 1px 1px 2px #444444;
  }
  .login hr {
    background-color: #444;
    margin: 20px auto;
  }
   
  

/***********************/
.login .login-wrap2 {
  background-color: rgba(255, 255, 255, 0.323);
    box-shadow: 0px 0px 10px gray;
    backdrop-filter: blur(8px);
    border: none;
    background-size: cover;
    width: 750px;
    height: 500px;
    margin: 215px auto;
    overflow: hidden;
    padding-top: 10px;
    line-height: 20px;
    border-radius: 40px; /* 设置边框的圆角半径为10px */
    margin-left: 100px;
    margin-top: 300px;
  }
.login .footer {
  position: absolute;
  
  top:10%;
  left: 180px;
  
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: transparent;
  color: #ffffff;
  font-size: 40px;
  text-shadow: 1px 1px 2px #444;
}
.login .footer2 .text{
  position: absolute;
  margin-top: -45px;
  left: 300px;
  text-shadow: 1px 1px 2px #444444;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: transparent;
  color: #000000;
  font-size: 30px;
}
.footer2 hr {
    background-color: #444;
    margin-top: 100px;
    width: 520px;
    margin-left: 90px;
  }
.login .icon-container {
  display: flex;
  align-items: center;
}

.login .icon {
  width: 25px; /* 根据需要设置图标的宽度 */
  height: 25px; /* 根据需要设置图标的高度 */
  margin-right: 10px;
}

.login .input {
  width: 200px;
  height: 30px;
  border-radius: 5px;
  border: 1px solid #ffffff; /* 添加边框样式 */
  background-color: transparent;
  color: #ffffff; /* 白色文字 */
}

.login .input::placeholder {
    color: #ffffff; /* 白色文字 */
  }

  .login .spacer {
  width: 30px; /* 设置间隔宽度，根据需要进行调整 */
  height: 30px;
}

  .el-button {
    width: 80%;
    margin-left: 10%;
  }

  .my-link {
    color: white; /* 平时的颜色为白色 */
    text-decoration: none; /* 去除下划线 */
    &:hover{
      color: blue; /* 鼠标移上去时的颜色为蓝色 */
    }
  }
  .login .footer2 .saying{
    margin-left: 20px;
    margin-top: 20px;
    margin-right: 20px;
    color: white; 
  }
 
  </style>