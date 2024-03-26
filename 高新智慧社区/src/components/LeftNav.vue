 
<template>
  <div class="sidebar">
    <img src="../assets/img/logo.png"
      style="width:90%;height: auto; margin:0 auto;margin-top: 25px; margin-bottom: 70px;" alt="">
    <ul v-if="userIdentity === '业主'">
      <li v-for="(item, index) in menuItems" :key="index" :class="{ active: item.active }">
        <router-link :to="item.url" @click="handleItemClick(index)">
          <img :src="item.icon" alt="Icon">
          {{ item.label }}
        </router-link>
      </li>
    </ul>

    <ul v-if="userIdentity === '业委会'">
      <li v-for="(item, index) in menuItems" :key="index" :class="{ active: item.active }">
        <router-link :to="item.url" @click="handleItemClick(index)">
          <img :src="item.icon" alt="Icon">
          {{ item.label }}
        </router-link>
      </li>
    </ul>

    <ul v-if="userIdentity === '普通用户'">
      <li v-for="(item, index) in normalMenuItems" :key="index" :class="{ active: item.active }">
        <router-link :to="item.url" @click="handleItemClick(index)">
          <img :src="item.icon" alt="Icon">
          {{ item.label }}
        </router-link>
      </li>
    </ul>

    <ul v-if="userIdentity === '家属'">
      <li v-for="(item, index) in normalMenuItems" :key="index" :class="{ active: item.active }">
        <router-link :to="item.url" @click="handleItemClick(index)">
          <img :src="item.icon" alt="Icon">
          {{ item.label }}
        </router-link>
      </li>
    </ul>
  </div>
</template>
  
<script>
export default {
  data() {
    return {
      userIdentity: this.$store.state.userData.usertype,

      normalMenuItems: [
        { label: '首页', url: '/index', icon: require('../assets/img/首页 (1).png') },
        { label: '房屋管理', url: '/house', icon: require('../assets/img/房屋.png') },
      ],

      menuItems: [
        { label: '首页', url: '/index', icon: require('../assets/img/首页 (1).png') },
        { label: '房屋管理', url: '/house', icon: require('../assets/img/房屋.png') },
        { label: '车辆管理', url: '/car', icon: require('../assets/img/车辆.png') },
        { label: '家人管理', url: '/family', icon: require('../assets/img/朋友.png') },
        { label: '问题上报', url: '/problem', icon: require('../assets/img/常见问题／相关问题.png') },
        { label: '预算支出投票', url: '/budget', icon: require('../assets/img/投票.png') },
        { label: '投诉', url: '/complain', icon: require('../assets/img/投诉.png') },
      ],
    };
  },
  methods: {
    handleItemClick(index) {
      this.menuItems.forEach((item, i) => {
        item.active = i === index;
      });
    },
  },
};
</script>
  
<style>
.sidebar {
  background-color: #e7e7e7;
  height: 100%;
  text-align: center;
  padding: 10px 13px;
}

.sidebar ul {
  list-style-type: none;
  margin: 0;
  padding: 0;

}

.sidebar a {
  display: flex;
  font-size: 20px;
  color: #555555;
  height: 50px;
  align-items: center;
  text-decoration: none;
}

.sidebar li {
  margin: 0;
  height: 50px;
  border-radius: 5px;

}

.sidebar ul img {
  width: 30px;
  height: 30px;
  margin-right: 15px;
  margin-left: 30px;
}

.router-link-exact-active {
  font-weight: bold;
  color: #008BEF !important;
  background-color: #B7DAF4;
  font-size: 23px !important;
  border-radius:15px ;
}
</style>
  