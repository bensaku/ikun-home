<template>
  <div>
    <el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" @select="handleSelect"
      background-color="#12A1FF" text-color="#fff" style="border: 0px" active-text-color="#ffd04b">
      
      <el-submenu style="float: right" index="2">
        
        <template slot="title"><img style="height: 30px; margin-right: 10px; font-size: 20px" src="../assets/img/我的.png"
            alt="" />{{ name }}</template>
        
            <div style="display: flex; padding: 10px; color: #ffffff; font-size: 13px">
          <span>登录以: </span>
          <div v-if="identity === '业委会'">
            <input type="radio" v-model="selectedOption" value="option1" @change="viewSelect" /> 业主
          </div>
          <div v-if="identity === '业委会'">
            <input type="radio" v-model="selectedOption" value="option2" @change="viewSelect" />
            业委会
          </div>
          <div style="margin-left: 10px" v-if="identity === '家属'">家属</div>
          <div style="margin-left: 10px" v-if="identity === '业主'">业主</div>
        </div>

        
        <el-menu-item style="display: flex; justify-content: center; align-items: center" index="2-2">退出登录</el-menu-item>
      </el-submenu>


      <el-submenu style="float: right" index="5">
        
        <template slot="title"><img style="height: 30px; margin-right: 10px"
          src="../assets/img/信封_填充.png" alt="" /></template>
        
          <el-menu-item index="5-1" style="text-align: center;"><span >发送邮件</span></el-menu-item>
          <el-menu-item index="5-2"  style="text-align: center;"><span>接受邮件</span></el-menu-item>
    
      </el-submenu>
      
      
      <el-menu-item style="float: right" index="3"><img style="height: 30px; margin-right: 10px"
          src="../assets/img/211铃铛.png" alt="" /></el-menu-item>
    </el-menu>

  </div>
</template>



<script>
export default {
  data() {
    return {
      identity: this.$store.state.userData.usertype,
      activeIndex: "1",
      activeIndex2: "1",
      selectedOption: "option1",
      name: this.$store.state.userData.name,
    };
  },
  created() {
    this.name = this.$store.state.userData.name;
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);

      // 执行跳转到业主页面的逻辑
      this.$router.push("/");
    },

    viewSelect() {
      if (this.selectedOption === "option1") {
        this.$router.push("/index");
      }
      else if (this.selectedOption === "option2") {
        this.$router.push("/indexYe");
      }
    }
  },
};
</script>
