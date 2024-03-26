 
  <template>
    <div class="sidebar">
      <img src="../assets/img/logo.png" style="width:90%;height: auto; margin:0 auto;margin-top: 25px; margin-bottom: 70px;" alt="">
      <ul>
        <li v-for="(item, index) in menuItems" :key="index" :class="{ active: item.active }">
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
        menuItems: [
          { label: '首页', url: '/indexYe', icon: require('../assets/img/首页.png') },
          { label: '事项处置管理', url: '/affair', icon: require('../assets/img/常见问题／相关问题.png')},
          { label: '投票统计管理', url: '/count', icon: require('../assets/img/投票.png') },
          { label: '投诉建议管理', url: '/suggestion', icon: require('../assets/img/朋友.png') }
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
  

  