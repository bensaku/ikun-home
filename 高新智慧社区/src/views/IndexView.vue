<template>
  <div id="app">
    <TopNav style="position:fixed; width:100%; left: 0%;z-index: 2;">
    </TopNav>
    <LeftNav style="position:fixed;width:13%; left:0; top: 60px; z-index: 3;">
    </LeftNav>

    <div style="position:absolute;right:0;top:60px;min-height: 100vh;width:87%;z-index: 1;background-color: #f2f2f2;">

      <IndexSlider style="margin: 20px auto;"></IndexSlider>


      <div style="display: flex; margin: auto;width: 1150px;">
        <div class="card" style="float: left; width: 370px;height: 300px;">
          <div id="chart-container" style="width: 400px;height: 300px;"></div>
        </div>
        <div class="card" style="float: left; width: 700px;height: 300px;margin-left: 25px;">
          <div ref="parent" style="position: relative;width: 700px;height: 300px;">
      <canvas ref="myCanvas" style="position: absolute; top: 0; left: 0;"></canvas>
    </div>
        </div>
      </div>

      <div style="display: flex; margin: auto;width: 1150px;">
        <div class="card" style="float: left; width: 720px;height: 420px;">
          <div style="display: flex; margin-bottom: 20px;" >
            <img src="../assets/img/声音.png" style="width: 50px;height: 50px; margin-top: 15px;" alt="">
            <div style="width:200px;font-size: 30px;line-height: 120px;margin-left: 30px;margin-top:-25px;margin-bottom: 30px ;color: #BA2D35;">通知</div>
          </div>
          <div style="margin-top: 80px;margin-left: -200px;">
              <h>社区论坛将进行维护，预计在下周三进行，届时可能会出现短暂的访问中断</h>
              <hr>
              <h>本周六举行社区活动，请大家准时参加</h>
              <hr>
              <h>新的社区指南已发布，鼓励大家积极参与讨论和分享</h>
              <hr>
              <h>社区推出新的会员计划，享受更多福利和特权，欢迎了解详情</h>
              <hr>
              <h>本周日小区广场将拆除kun哥雕塑</h>
              <hr>
              <h>感谢各位社区成员的辛勤付出，我们的社区变得更加繁荣</h>
              <hr>
              <h>热烈欢迎坤坤市书记下周二来我小区进行指导工作</h>
              <hr>
              <h>感谢本小区居民王珂获得优秀市民奖</h>
              <hr>
              <h>请期待2025年新的小区建立</h>
              <hr>
          </div>
        </div>
        <div class="card" style="float: left; width: 350px;height: 420px;margin-left: 25px;">
          <div style="float: left; width: 300px; height: 420px;margin-top: 35px;" ref="pieChart"></div>
        </div>
      </div>

      <!-- <IndexNotice></IndexNotice> -->
    </div>

  </div>
</template>
  index.vue
<script>
import TopNav from '../components/TopNav.vue'
import LeftNav from '../components/LeftNav.vue'
import IndexSlider from '../components/IndexSlider.vue'
import * as echarts from 'echarts';
// import IndexNotice from '../components/IndexNotice.vue';
export default {
  data() {
    return {
      option1: {
        title: {
          text: '未来天气'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {},
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              
              yAxisIndex: 'none',
              show: false // 隐藏数据缩放功能图标
            },
            dataView: { readOnly: false,show: false // 隐藏数据视图功能图标
             },
            magicType: { type: ['line', 'bar'],
           },
            restore: {},
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value} °C'
          }
        },
        series: [
          {
            name: '最高温',
            type: 'line',
            data: [10, 11, 13, 11, 12, 12, 9],
            markPoint: {
              data: [
                { type: 'max', name: 'Max' },
                { type: 'min', name: 'Min' }
              ]
            },
            markLine: {
              data: [{ type: 'average', name: 'Avg' }]
            }
          },
          {
            name: '最低温',
            type: 'line',
            data: [1, -2, 2, 5, 3, 2, 0],
            markPoint: {
              data: [{ name: '周最低', value: -2, xAxis: 1, yAxis: -1.5 }]
            },
            markLine: {
              data: [
                { type: 'average', name: 'Avg' },
                [
                  {
                    symbol: 'none',
                    x: '90%',
                    yAxis: 'max'
                  },
                  {
                    symbol: 'circle',
                    label: {
                      position: 'start',
                      formatter: 'Max'
                    },
                    type: 'max',
                    name: '最高点'
                  }
                ]
              ]
            }
          }
        ]
      }
    };
  },
  name: 'app',
  components: {
    TopNav,
    LeftNav,
    IndexSlider,
    // IndexNotice
  },
  mounted() {
    const chartContainer1 = document.getElementById('chart-container');
    const chart1 = echarts.init(chartContainer1);
    chart1.setOption(this.option1);
    const canvas = this.$refs.myCanvas;
    const ctx = canvas.getContext('2d');
    const particles = [];
    const parentElement = this.$refs.parent;


    // 设置画布尺寸
    canvas.width = parentElement.offsetWidth;
    canvas.height = parentElement.offsetHeight;

    // 创建粒子类
    class Particle {
      constructor(x, y, color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.size = Math.random() * 3 + 1; // 粒子大小
        this.baseX = x;
        this.baseY = y;
        this.density = Math.random() * 30 + 1; // 粒子散开速度
        this.directionX = Math.random() * 2 - 1; // x轴方向
        this.directionY = Math.random() * 2 - 1; // y轴方向
      }

      draw() {
        ctx.fillStyle = this.color;
        ctx.beginPath();
        ctx.arc(this.x, this.y, this.size, 0, Math.PI * 2);
        ctx.closePath();
        ctx.fill();
      }

      update() {
        this.x += this.directionX;
        this.y += this.directionY;
      }

      reset() {
        this.x = this.baseX;
        this.y = this.baseY;
      }
    }

    // 创建粒子效果
    function createParticles() {
      const text = '高新管家';
      const textSize = 180;
      const fontFamily = 'Arial';
      ctx.font = `${textSize}px ${fontFamily}`;
      const textWidth = ctx.measureText(text).width;
      const textHeight = textSize;

      ctx.fillStyle = '#ffffff';
      ctx.fillText(text, (canvas.width - textWidth) / 2, (canvas.height + textHeight) / 2);

      const imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
      const data = imageData.data;

      for (let y = 0; y < canvas.height; y += 4) {
        for (let x = 0; x < canvas.width; x += 4) {
          const red = data[(y * canvas.width + x) * 4];
          const green = data[(y * canvas.width + x) * 4 + 1];
          const blue = data[(y * canvas.width + x) * 4 + 2];
          const alpha = data[(y * canvas.width + x) * 4 + 3];

          if (red === 255 && green === 255 && blue === 255 && alpha === 255) {
            const particle = new Particle(x, y, getRandomColor());
            particles.push(particle);
          }
        }
      }
    }

    // 随机获取颜色
    function getRandomColor() {
      const colors = ['#ff4500', '#00ced1', '#ff8c00', '#ba55d3', '#ffd700', '#ff1493'];
      const randomIndex = Math.floor(Math.random() * colors.length);
      return colors[randomIndex];
    }

    // 绘制粒子效果
    function drawParticles() {
      ctx.clearRect(0, 0, canvas.width, canvas.height);

      for (let i = 0; i < particles.length; i++) {
        const particle = particles[i];
        particle.update();
        particle.draw();
      }

      requestAnimationFrame(drawParticles);
    }

    // 处理鼠标移动事件
    let isMouseInsideCanvas = false;
let animationId;
let distance;

canvas.addEventListener('mouseenter', () => {
  isMouseInsideCanvas = true;
  animate();
});

canvas.addEventListener('mouseleave', () => {
  isMouseInsideCanvas = false;
  cancelAnimationFrame(animationId);
});

canvas.addEventListener('mousemove', (event) => {
  if (isMouseInsideCanvas) {
    const rect = canvas.getBoundingClientRect();
      const mouseX = event.clientX - rect.left;
      const mouseY = event.clientY - rect.top;

      for (let i = 0; i < particles.length; i++) {
        const particle = particles[i];
        const distanceX = mouseX - particle.x;
        const distanceY = mouseY - particle.y;
        distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (distance < 50) {
          particle.directionX = distanceX / particle.density;
          particle.directionY = distanceY / particle.density;
        } else {
          particle.reset();
        }
      }
    // ... 在鼠标位于Canvas内部时持续触发的处理代码 ...
  }
});

function animate() {
  if (isMouseInsideCanvas) {
      for (let i = 0; i < particles.length; i++) {
        const particle = particles[i];
          particle.reset();      
      }
    // ... 在鼠标位于Canvas内部时持续触发的处理代码 ...

    animationId = requestAnimationFrame(animate);
  }
}




    // 初始化粒子效果
    createParticles();

    // 绘制粒子效果
    drawParticles();
    // 获取饼状图容器元素
    const chartContainer = this.$refs.pieChart;
    // 初始化图表
    const chart = echarts.init(chartContainer);
    // 图表配置项
    const option = {
      title: {
        text: '小区人员年龄分布',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      grid: {
        top: 60, // 调整顶部的间距
        left: 'center', // 居中显示
        containLabel: true // 包含标题、图例等在内
      },
      series: [
        {
          
          name: 'Access From',
          type: 'pie',
          radius: '50%',
          data: [
            { value: 1048, name: '0~18岁' },
            { value: 735, name: '18~35岁' },
            { value: 580, name: '35~50岁' },
            { value: 484, name: '50~70岁' },
            { value: 300, name: '大于70岁' }
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    // 渲染图表
    chart.setOption(option);
  }
}
</script>
  
<style>
canvas {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 9999;
}

body {
  margin: 0;
  padding: 0;
}

@import "../assets/style/card.css";
@import "../assets/style/modal.css";
</style>