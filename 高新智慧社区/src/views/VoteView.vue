<template>
  <div id="app">
    <TopNav style="position:fixed; width:100%; left: 0%;z-index: 2;"></TopNav>
    <LeftNavYe style="position:fixed; width:13%; left:0; top:60px; ;z-index: 3;"></LeftNavYe>
    <div style="position:absolute;right:0;top:60px;min-height: 100vh;width:87%;z-index: 1;background-color: #f2f2f2;">
      <div style="position: absolute;left:40px;top: 30px; color: #667561;font-size: 60px;font-weight: bold;">投票统计监管</div>
      <SearchVote @searchVote="fetchData" style="position:absolute;top:140px;right: 30px;"></SearchVote>
      <div style="position:absolute;top:190px;right: 3%;left: 5%;border: 1px solid rgba(155, 155, 155, 0.3);"></div>
      <div style="position:absolute; top:220px; left: 7%;">
        <TableVote v-if="flag"></TableVote>
      </div>
      <div style="position:absolute; top:220px; left: 7%;">
        <TableVoteSearch :tableData="tableData" v-if="!flag"></TableVoteSearch>
      </div>
    </div>
  </div>
</template>

<script>
import TopNav from '../components/TopNavYe.vue'
import LeftNavYe from '../components/LeftNavYe.vue'
import TableVote from '../components/TableVote.vue';
import TableVoteSearch from '../components/TableVoteSearch.vue';
import SearchVote from '../components/SearchVote.vue';
export default {
  components: {
    TopNav,
    LeftNavYe,
    TableVote,
    SearchVote,
    TableVoteSearch
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
          .get(`http://localhost:8090/api/vote/getVotesByDateRange?voteStarttime=${startDate}&voteEndtime=${endDate}`)
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