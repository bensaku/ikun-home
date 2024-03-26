<template>
    <div style="display: flex;width:600px; height: 40px;">
    <span style="font-size: 20px; line-height: 40px;">投诉时间：</span>
    <el-date-picker
    value-format="yyyy-MM-dd"
      v-model="dateRange"
      type="daterange"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期">
    </el-date-picker>
    <button  class="affairbutton"  @click="fetchData" >查询</button>
  </div>

</template>

<style>
.affairbutton{
    margin-left:20px ;
    border: 0;
    width: 80px;
    border-radius:10px;
    background-color:#15A850;
    color: white;
}
.affairbutton:hover{

    background-color:#14bb68;
}
</style>


<script>
  export default {
    data() {
      return {
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        value1: '',
        value2: '',
        dateRange:[],
      };
  },
  methods: {
    fetchData() {
      if (this.dateRange.length === 2) {
        const startDate = this.dateRange[0];
        const endDate = this.dateRange[1];
        const flag = false;
        this.$emit('searchComplaint', { startDate, endDate,flag });
      }
    },
    }
  };
</script>
