<template>
    <div class="block" style="width:1200px;">
      <el-carousel height="400px" type="card" style="">
        <el-carousel-item v-for="item in items" :key="item" style="border-radius: 20px;box-shadow: 0px 0px 20px gray;">
        <el-image :src="item.imageUrl" style="width:600px;height: 400px;" alt="Carousel Image" /></el-carousel-item>
      </el-carousel>
    </div>
  </template>

  <script>
  export default {
    data() {
      return {
        items: [
          { imageUrl: require('../assets/img/小区2.jpg'), title: 'Image 1' },
          { imageUrl: require('../assets/img/小区3.jpg'), title: 'Image 2' },
          { imageUrl: require('../assets/img/小区3.jpeg'), title: 'Image 3' },
          { imageUrl: require('../assets/img/小区4.jpg'), title: 'Image 4' }
        ]
      };
    }
  };
  </script>
  
  <style>
    .el-carousel__item h3 {
      color: transparent;
      font-size: 14px;
      opacity: 0.75;
      line-height: 150px;
      margin: 0;
    }
  
    .el-carousel__item:nth-child(2n) {
       background-color: transparent;
    }
    
    .el-carousel__item:nth-child(2n+1) {
       background-color: transparent;
    }
  </style>
  