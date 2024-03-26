<template>
    <div>
        <div style="display: flex; margin-bottom: 20px;" >
            <img src="../assets/img/声音.png" style="width: 120px; " alt="">
            <div style="width:200px;font-size: 60px;line-height: 120px;margin-left: 30px;margin-bottom: 30px ;color: #BA2D35;">通知</div>
        </div>
        <div style="display: flex; width: 1560px;" v-for="(row, rowIndex) in rows" :key="rowIndex " >
            <NoticeBlock v-for="(item, columnIndex) in row" :key="columnIndex" :data="item" :notice="noticeData[rowIndex * 3 + columnIndex]" />
        </div>
    </div>
</template>

<script>

import NoticeBlock from './NoticeBlock.vue';
export default {
    data() {
    return {
        noticeData: [
            '本周六举行社区活动，请大家准时参加',
            '本周日小区广场将拆除kun哥雕塑',
            '新的社区指南已发布，鼓励大家积极参与讨论和分享',
            '社区论坛将进行维护，预计在下周三进行，届时可能会出现短暂的访问中断',
            '社区推出新的会员计划，享受更多福利和特权，欢迎了解详情',
            '感谢各位社区成员的辛勤付出，我们的社区变得更加繁荣'],
    };
  },
    computed: {
    rows() {
      const items = [
        { id: 1, name: 'Item 1' },
        { id: 2, name: 'Item 2' },
        { id: 3, name: 'Item 3' },
        { id: 4, name: 'Item 4' },
        { id: 5, name: 'Item 5' },
        { id: 6, name: 'Item 6' },
      ];

      const rows = [];
      const itemsPerRow = 3;
      const rowCount = Math.ceil(items.length / itemsPerRow);

      for (let i = 0; i < rowCount; i++) {
        const start = i * itemsPerRow;
        const end = start + itemsPerRow;
        rows.push(items.slice(start, end));
      }

      return rows;
    }
  },
   components: {
        NoticeBlock
    }
}
</script>

