<template>
    <div class="background1">
    <div class="register-container">
        <h1 class="register-title">用户注册</h1>
        <form @submit.prevent="registerUser" class="register-form">
            <div class="form-group">
                <img src="../assets/img/用户名.png" alt="User Icon" class="icon">
                <input class="transparent-input" type="text" id="username" v-model="username" placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <img src="../assets/img/密码.png" alt="User Icon" class="icon">
                <input class="transparent-input" type="password" id="password" v-model="password" placeholder="请输入密码">
            </div>
            <div class="form-group">
                <img src="../assets/img/密码2.png" alt="User Icon" class="icon">
                <input class="transparent-input" type="password" id="confirmPassword" v-model="confirmPassword" placeholder="请确认密码">
            </div>
            <div class="form-group">
                <img src="../assets/img/邮箱.png" alt="User Icon" class="icon">
                <input class="transparent-input" type="text" id="email" v-model="email" placeholder="请输入邮箱">
            </div>
            <div class="buttons">
                <button type="submit" class="register-button">注册</button>
                <button type="button" class="quxiao-button" @click="backlogin">取消</button>
            </div>
            
        </form>
    </div>
</div>
</template>




<script>
import { sha256 } from "js-sha256"; // 导入SHA-256加密算法库
export default {
    name: 'RegisterView',
    data() {
        return {
            username: '',
            password: '',
            confirmPassword: '',
            email: ''
        };
    },
    methods: {
        registerUser() {
            // 在此处编写注册用户的逻辑
            // 可以使用 this.username、this.password、this.confirmPassword 和 this.phone 访问用户输入的值
            if (!this.username) {
                this.$message.error("请输入用户名！");
                return;
            } 
            if (!this.password) {
                this.$message.error("请输入密码！");
                return;
            }
            if (!this.confirmPassword) {
                this.$message.error("请确认密码！");
                return;
            } 
            if (!this.email) {
                this.$message.error("请输入邮箱！");
                return;
            }
            const encryptedPassword = sha256(this.password);
            const data2 = {
                userNo: this.username,
                userPassword: encryptedPassword,
                userMail: this.email,
            };
            const userData = {
                username: this.username,
              };

              // 提交 mutation 更新 userData 状态
              this.$store.commit('setUserData',userData);
            if(this.password==this.confirmPassword){
                this.$axios.post('http://localhost:8090/api/user/register', data2)
                .then(response => {
                // 登录成功，处理返回的响应数据
                //console.log(response.data2); // 输出登录成功的信息
                // 其他操作，例如保存token到本地存储或Vuex状态管理、跳转到其他页面等
                if(response.data=="注册成功"){
                    this.$message.success("注册成功，请实名认证后登录。");
                    setTimeout(() => {
                    // 设置延迟时间为1秒（1000毫秒）
                    this.$router.push('/RealName'); // 执行页面跳转
                    }, 1000); // 设置延迟时间为1秒（1000毫秒）
                }
                else{

                    this.$message.error("您注册的账号已存在");
                }
                })
            }else{
                this.$message.error("您确认的密码不一致");
            }
            
        },

        backlogin(){
            this.$router.push('/');
        },
        clearInput(field) {
            // 清空输入框的值
            this[field] = '';
        }
    }
};
</script>
<style>


.background1 {
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

.background1 .register-title {
  color: white;
  text-align: center;
  margin-bottom: 20px;
  text-shadow: 1px 1px 2px #444444;
}

.background1 .register-container {
    max-width: 400px;
    min-width: 300px;
    margin: auto;
    padding: 20px;
    border-radius: 15px;
    background-color: rgba(255, 255, 255, 0.3);
    box-shadow: 0px 0px 10px gray;
}

.background1 .icon {
  position: absolute;
  top: 50%;
  left: 10px;
  transform: translateY(-50%);
  width: 20px; /* 调整图标的宽度 */
  height: 20px; /* 调整图标的高度 */
}

.background1 .transparent-input {
  background-color: transparent;
  padding-left: 30px;
  margin-left: 50px;
}

.background1 .transparent-input::placeholder {
  color: white; /* 提示文字颜色为白色 */
}

.background1 h1 {
    text-align: center;
    color: white;

}

.background1 .register-form {
    display: flex;
    flex-direction: column;
}

.background1 .form-group {
    margin-bottom: 20px;
    position: relative;
}

.background1 label {
    font-weight: bold;
    position: absolute;
    top: 50%;
    left: 10px;
    transform: translateY(-50%);
    pointer-events: none;
    transition: 0.2s ease-out;
}

.background1 .register-form input[type="text"],
.background1 .register-form input[type="password"] {
    padding: 10px;
    border-radius: 4px;
    border: 1px solid #ccc;
    transition: box-shadow 0.3s ease;
    transition: transform 0.3s ease;
    
}

.background1 .register-form input[type="text"]:focus,
.background1 .register-form input[type="password"]:focus {
  box-shadow: 0 0 5px 2px rgba(0, 0, 0, 0.3);
  transform: scale(1.02);
}

.background1 .active+label {
    top: -10px;
    left: 5px;
    font-size: 12px;
    background-color: #fff;
    padding: 0 4px;
}



.background1 .register-button {
    margin-left: 50px;
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    width: 65px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: box-shadow 0.3s ease, transform 0.3s ease;
}

.background1 .register-button:hover {
  box-shadow: 0 0 5px 2px rgba(255, 255, 255, 0.5);
  transform: scale(1.02);
}

.background1 .register-button:hover {
    background-color: #0056b3;
}

.background1 .quxiao-button{
    margin-left: 50px;
    width: 65px;
    padding: 10px;
    background-color: #ffffff;
    color: #000000;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: box-shadow 0.3s ease, transform 0.3s ease;
}

.background1 .quxiao-button:hover{
    box-shadow: 0 0 5px 2px rgba(255, 255, 255, 0.5);
  transform: scale(1.02);
  background-color: #ff0000;
  
}



</style>


