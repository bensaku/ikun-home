<template>
  <div class="body2">
    <div
      class="main"
      style="
        margin: 100px 350px 100px 300px;
        background-color: #fff;
        width: 600px;
        height: 400px;
        padding: 20px;
        border-radius: 40px;
        background-color: rgba(255, 255, 255, 0.323);
        box-shadow: 0px 0px 10px gray;
        backdrop-filter: blur(8px);
      "
    >
      <div style="margin: 20px 0; text-align: center; font-size: 24px">
        <b>实名认证</b>
      </div>
      <el-form
        ref="form"
        :model="formData"
        label-width="80px"
        class="main_form"
      >
      <el-row>
        <el-col :span="12">
        <el-form-item label="姓名" class="label1">
          <template #label>
            <span style="color: #000000;">姓名<span class = "el-form-item__required">*</span></span>
          </template>
          <el-input style="width: 130px;" class="input" v-model="formData.name" placeholder="请输入姓名"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="性别" style="margin-left: 10px;" class="label1">
          <template #label>
            <span style="color: #000000;">性别<span class = "el-form-item__required">*</span></span>
          </template>
          <el-select style="width: 130px;" class="input" v-model="formData.sex" placeholder="请选择性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      </el-row>
        <el-form-item label="民族" class="label1">
          <template #label>
            <span style="color: #000000;">民族<span class = "el-form-item__required">*</span></span>
          </template>
          <el-col :span="6">
            <el-select
              v-model="formData.nation"
              placeholder="请选择民族"
              class="input"
            >
              <el-option label="汉族" value="汉族"></el-option>
              <el-option label="其他" value="其他"></el-option>
            </el-select>
          </el-col>
          <el-form-item label="地址" style="margin-left: 80px;" class="label1">
            <template #label>
            <span style="color: #000000;">地址<span class = "el-form-item__required">*</span></span>
          </template>
            <el-col :span="50">
              <el-input v-model="formData.addr" class="input" placeholder="请输入地址" style="width: 230px;"></el-input>
            </el-col>
          </el-form-item>
        </el-form-item>
        <el-form-item label="身份证号" class="label1">
          <template #label>
            <span style="color: #000000;">身份证号<span class = "el-form-item__required">*</span></span>
          </template>
          <el-co :span="15">
            <el-input
              v-model="formData.id"
              placeholder="请输入身份证号"
              class="input"
              style="width: 440px;"
            ></el-input>
          </el-co>
        </el-form-item>
        <el-form-item label="截至日期" class="label1">
          <template #label>
            <span style="color: #000000;">截至日期<span class = "el-form-item__required">*</span></span>
          </template>
          <el-col :span="20">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="formData.duetime"
              class="input"
              style="width: 440px;"
            ></el-date-picker>
          </el-col>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit" style="margin-left: 140px;" class="button1">
            确认
          </el-button>
          <el-button @click="backshimin" class="button2">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formData: {
        name: '',
        sex: '',
        nation: '',
        addr: '',
        id: '',
        duetime: ''
      }
    };
  },
  methods: {
    onSubmit() {
      if (!this.formData.name) {
        this.$message.error('请输入姓名');
        return;
      }
      if (!this.formData.sex) {
        this.$message.error('请输入性别');
        return;
      }
      if (!this.formData.nation) {
        this.$message.error('请确认民族');
        return;
      }
      if (!this.formData.addr) {
        this.$message.error('请输入地址');
        return;
      }
      if (!this.formData.id) {
        this.$message.error('请输入身份证号');
        return;
      }
      if (!this.formData.duetime) {
        this.$message.error('请输入截至日期');
        return;
      }

      const data = {
        userNo: this.$store.state.userData.username,
        ...this.formData
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
            this.$message.error('身份已认证为家属');
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
    backshimin() {
      this.$router.push('/RealName');
    }
  }
};
</script>

<style lang="scss" scoped>
.body2 {
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
.body2 .main {
  opacity: 0.75;
}
.body2 .input {
  background-color: rgba(255, 255, 255, 0.323);
}
.body2 .main_form >>> .el-form-item__label {
  text-align: right;
  vertical-align: middle;
  float: left;
  font-size: 14px;
  color: #000;
  line-height: 40px;
  padding: 0 12px 0 0;
  box-sizing: border-box;
}
.body2 .userid {
  width: 435px;
}

.el-form-item__required {
  color: red;
  margin-left: 5px;
}

.el-form-item.is-required:not(.is-no-asterisk) .el-form-item__label:after {
  content: "*";
  color: red;
  margin-left: 5px;
}
.body2 .button1{
  border-radius: 10px;
  &:hover{
      background-color: blue; /* 鼠标移上去时的颜色为蓝色 */
    }
}
.body2 .button2{
  border-radius: 10px;
  &:hover{
    background-color: rgb(255, 0, 0); /* 鼠标移上去时的颜色为蓝色 */
    }
}
.body2 .main_form .label1 {
  font-weight: bold;
  color: #ffffff !important;
}
</style>
