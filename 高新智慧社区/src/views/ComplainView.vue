<template>
  <div id="app">
    <TopNav style="position:fixed; width:100%; left: 0%;z-index: 2;"></TopNav>
    <LeftNavYe style="position:fixed; width:13%; left:0; top:60px; ;z-index: 3;"></LeftNavYe>
    
    <div style="position:absolute;right:0;top:60px;width:87%;min-height: 100vh;z-index: 1;background-color: #f2f2f2;">
      <div style="position: absolute;left:40px;top: 30px; color: #667561;font-size: 60px;font-weight: bold;">投诉建议管理</div>
      <SearchComplain @searchComplaint="fetchData" style="position:absolute;top:140px;right:30px;"></SearchComplain>
      <div style="position:absolute;top:190px;right: 3%;left: 5%;border: 1px solid rgba(155, 155, 155, 0.3);"></div>
      <div style="position:absolute; top:220px; left: 7%;">
        <TableComplain v-if="flag"></TableComplain>
      </div>
      <div style="position:absolute; top:220px; left: 7%;">
        <TableComplaintSearch :tableData="tableData" v-if="!flag"></TableComplaintSearch>
      </div>
    </div>
  </div>
</template>

<script>
import TopNav from '../components/TopNavYe.vue'
import LeftNavYe from '../components/LeftNavYe.vue'
import TableComplain from '../components/TableComplain.vue';
import SearchComplain from '../components/SearchComplain.vue';
import TableComplaintSearch from '@/components/TableComplaintSearch.vue';
export default {
  components: {
    TopNav,
    LeftNavYe,
    TableComplain,
    SearchComplain,
    TableComplaintSearch
  },
  data() {
    return {
      flag: true,
      tableData: []
    };
  },
  methods: {
    fetchData(searchParams) {
      const { startDate, endDate, flag } = searchParams;
      // 根据查询条件请求数据并更新表格数据
      this.flag = flag,
        this.$axios
          .get(`http://localhost:8090/api/complaint/getComplaintsByDateRange?complaintReporttime=${startDate}&complaintFinishtime=${endDate}`)
          .then((response) => {
            this.tableData = response.data;
          })
          .catch((error) => {
            console.error(error);
          });

    },
  },

}
</script>

<style>
body {
  margin: 0;
  padding: 0;
}</style>