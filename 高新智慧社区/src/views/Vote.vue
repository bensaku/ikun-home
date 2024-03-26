<template>
  <div>
    <TopNav style="position:fixed; width:100%; left: 0%;z-index: 2;">
    </TopNav>
    <LeftNav style="position:fixed;width:13%; left:0; top: 60px; z-index: 3;">
    </LeftNav>

    <div
      style="position: absolute; right: 0; top:60px;min-height: 100vh; width: 87%; z-index: 1;background-color: #f2f2f2;">

      <div class="background-container">
        <vue-particles color="#70B603" :particleOpacity="0.6" :particlesNumber="100" shapeType="circle" :particleSize="5"
          linesColor="#0000ff" :linesWidth="1" :lineLinked="true" :lineOpacity="0.6" :linesDistance="80" :moveSpeed="1.3"
          :hoverEffect="true" hoverMode="grab" :clickEffect="false" clickMode="push" />
      </div>

      <!-- 展示卡片循环 -->
      <div v-for="vote in votes" :key="vote.voteNo">
        <div>
          <!-- 投票中 -->
          <div class="voting-card" @click="showDetail(vote)">
            <!-- 标题栏 -->
            <div class="text">
              <!-- 左侧图标 -->
              <div class="left-title" v-if="vote.voteResult === '进行'">
                <img src="../assets/img/voting.png" width="85" height="85">
              </div>

              <div class="left-title" v-if="vote.voteResult === '通过'">
                <img src="../assets/img/pass.png" width="85" height="85">
              </div>

              <div class="left-title" v-if="vote.voteResult === '否决'">
                <img src="../assets/img/notpass.png" width="85" height="85">
              </div>

              <!-- 右侧标题 -->
              <div class="right-title">{{ vote.voteName }}</div>
            </div>

            <!-- 详细信息 -->
            <div class="info">
              <!-- 左侧文字信息 -->
              <div class="details">
                <div style="margin: 0;">截至日期：{{ vote.voteEndtime }}</div>
                <div style="float: left; height: 2.6em; overflow: hidden;">
                  {{ vote.voteDetail }}
                </div>

                <div class="investment">事项预计投资：<span class="amount">{{ vote.voteExpend }}</span>元</div>
              </div>
              <!-- 右侧投票区 -->
              <div class="voting" @click.stop>
                <!-- 进行中的投票 -->
                <div class="buttons" v-if="vote.voteResult === '进行'">
                  <template>
                    <button class="vote-button up" @click="voteCancel(vote.voteNo, '支持')"
                      v-if="getVoteStateByUserNo(vote.voteNo) === '支持'">
                      <img src="../assets/img/uped.png" alt="up icon" width="60" height="60">
                    </button>
                    <button class="vote-button up" @click="voteFuc(vote.voteNo, '支持')" v-else>
                      <img src="../assets/img/up.png" alt="up icon" width="60" height="60">
                    </button>
                  </template>

                  <template>
                    <button class="vote-button down" @click="voteCancel(vote.voteNo, '反对')"
                      v-if="getVoteStateByUserNo(vote.voteNo) === '反对'">
                      <img src="../assets/img/downed.png" alt="down icon" width="60" height="60">
                    </button>
                    <button class="vote-button down" @click="voteFuc(vote.voteNo, '反对')" v-else>
                      <img src="../assets/img/down.png" alt="down icon" width="60" height="60">
                    </button>
                  </template>
                </div>

                <!-- 已结束的投票 -->
                <div class="buttons" v-else>
                  <template>
                    <button class="vote-button up" @click="voteOverMessage"
                      v-if="getVoteStateByUserNo(vote.voteNo) === '支持'">
                      <img src="../assets/img/uped.png" alt="up icon" width="60" height="60">
                    </button>
                    <button class="vote-button up" @click="voteOverMessage" v-else>
                      <img src="../assets/img/up.png" alt="up icon" width="60" height="60">
                    </button>
                  </template>

                  <template>
                    <button class="vote-button down" @click="voteOverMessage"
                      v-if="getVoteStateByUserNo(vote.voteNo) === '反对'">
                      <img src="../assets/img/downed.png" alt="down icon" width="60" height="60">
                    </button>
                    <button class="vote-button down" @click="voteOverMessage" v-else>
                      <img src="../assets/img/down.png" alt="down icon" width="60" height="60">
                    </button>
                  </template>
                </div>
                <!-- 显示投票结果 -->
                <span>支持率：</span>
                <div :style="{ color: textColor, fontSize: textSize }" class="vote-percentage"
                  v-if="vote.voteSupport === 0 && vote.voteAgainst === 0">
                  暂时无人投票
                </div>
                <div :style="{ color: textColor, fontSize: textSize }" class="vote-percentage" v-else>
                  {{ Math.round((vote.voteSupport / (vote.voteSupport + vote.voteAgainst)) * 100) }}%
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>



      <!-- 详细信息窗口 -->
      <div class="modal-overlay" v-if="voteTmp.voteShowDitail === true">
        <template>
          <div class="details-card">
            <div class="text">

              <!-- 左侧图标 -->
              <div class="left-title" v-if="voteTmp.voteResultTmp === '进行'">
                <img src="../assets/img/voting.png" width="85" height="85">
              </div>

              <div class="left-title" v-if="voteTmp.voteResultTmp === '通过'">
                <img src="../assets/img/pass.png" width="85" height="85">
              </div>

              <div class="left-title" v-if="voteTmp.voteResultTmp === '否决'">
                <img src="../assets/img/notpass.png" width="85" height="85">
              </div>

              <div class="right-title">{{ voteTmp.voteNameTmp }}</div>
              <div class="close-details">
                <button class="close-button" @click="closeDetail">
                  <img src="../assets/img/叉叉.png" alt="chacha" width="40" height="40">
                </button>
              </div>
            </div>


            <div class="content">
              <div class="details">
                <span style="margin: 0; color: red;" @click.stop>截至日期：{{ voteTmp.voteEndtimeTmp }}</span>
                <p style="float: left; " @click.stop>{{ voteTmp.voteDetailTmp }}</p>
              </div>
            </div>

            <div class="bottom-div">
              <!-- 投资 -->
              <p class="investment" @click.stop>事项预计投资：<span class="amount">{{ voteTmp.voteExpendTmp }}</span>元</p>

              <!-- 投票区 -->
              <div class="voting">
                <!-- 两个按钮 -->
                <!-- 进行中的投票 -->
                <div class="buttons" v-if="voteTmp.voteResultTmp === '进行'">
                  <template>
                    <button class="vote-button up" @click="voteCancel(voteTmp.voteNoTmp, '支持')"
                      v-if="getVoteStateByUserNo(voteTmp.voteNoTmp) === '支持'">
                      <img src="../assets/img/uped.png" alt="up icon" width="60" height="60">
                    </button>
                    <button class="vote-button up" @click="voteFuc(voteTmp.voteNoTmp, '支持')" v-else>
                      <img src="../assets/img/up.png" alt="up icon" width="60" height="60">
                    </button>
                  </template>

                  <template>
                    <button class="vote-button down" @click="voteCancel(voteTmp.voteNoTmp, '反对')"
                      v-if="getVoteStateByUserNo(voteTmp.voteNoTmp) === '反对'">
                      <img src="../assets/img/downed.png" alt="down icon" width="60" height="60">
                    </button>
                    <button class="vote-button down" @click="voteFuc(voteTmp.voteNoTmp, '反对')" v-else>
                      <img src="../assets/img/down.png" alt="down icon" width="60" height="60">
                    </button>
                  </template>
                </div>

                <!-- 已结束的投票 -->
                <div class="buttons" v-else>
                  <template>
                    <button class="vote-button up" @click="voteOverMessage"
                      v-if="getVoteStateByUserNo(voteTmp.voteNoTmp) === '支持'">
                      <img src="../assets/img/uped.png" alt="up icon" width="60" height="60">
                    </button>
                    <button class="vote-button up" @click="voteOverMessage" v-else>
                      <img src="../assets/img/up.png" alt="up icon" width="60" height="60">
                    </button>
                  </template>

                  <template>
                    <button class="vote-button down" @click="voteOverMessage"
                      v-if="getVoteStateByUserNo(voteTmp.voteNoTmp) === '反对'">
                      <img src="../assets/img/downed.png" alt="down icon" width="60" height="60">
                    </button>
                    <button class="vote-button down" @click="voteOverMessage" v-else>
                      <img src="../assets/img/down.png" alt="down icon" width="60" height="60">
                    </button>
                  </template>
                </div>
                <!-- 显示投票结果 -->
                <span>支持率：</span>
                <div :style="{ color: textColor, fontSize: textSize }" class="vote-percentage"
                  v-if="voteTmp.voteSupportTmp === 0 && voteTmp.voteAgainstTmp === 0">
                  暂时无人投票
                </div>
                <div :style="{ color: textColor, fontSize: textSize }" class="vote-percentage" v-else>
                  {{ Math.round((voteTmp.voteSupportTmp / (voteTmp.voteSupportTmp + voteTmp.voteAgainstTmp)) * 100) }}%
                </div>
              </div>
            </div>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import TopNav from '../components/TopNav.vue'
import LeftNav from '../components/LeftNav.vue'
export default {
  components: {
    TopNav,
    LeftNav
  },
  data() {
    return {
      votes: [],
      voteUsers: [],
      voteTmp: {
        voteNoTmp: "",
        voteNameTmp: "",
        voteEndtimeTmp: "",
        voteDetailTmp: "",
        voteExpendTmp: "",
        voteAgainstTmp: "",
        voteSupportTmp: "",
        voteResultTmp: "",
        voteShowDitail: false,
      },
    };
  },
  created() {
    this.getAllVotes();
    this.getVoteUserByUserNo(this.$store.state.userData.username);
  },
  computed: {
    textColor() {
      return this.supportPrecentage >= 80 ? 'rgb(112,182,3)' : 'rgb(163,0,20)';
    },
    textSize() {
      return `${1 + (this.supportPrecentage / 100) * 2}em`;
    }
  },
  methods: {
    // 发送获取所有投票记录的请求
    async getAllVotes() {
      try {
        const response = await this.$axios.get('http://localhost:8090/api/vote/getVotes');


        this.votes = response.data;
      } catch (error) {
        console.error('Error retrieving votes:', error);
      }
    },

    // 通过userNo获取VoteUser表
    getVoteUserByUserNo(userNo) {
      this.$axios.post(`http://localhost:8090/api/voteUser/doVote/${userNo}`)
        .then(response => {

          this.voteUsers = response.data;
        }).catch(error => {
          // 处理错误
          //alert(error.response.data); // 输出错误消息
          this.$message.error(error.response.data);
        });

    },

    // 投票方法
    voteFuc(no, type) {
      var voteNo = no;
      var userNo = this.$store.state.userData.username;
      var voteType = type;

      this.$axios.post(`http://localhost:8090/api/vote/doVote/${userNo}/${voteNo}/${voteType}`)
        .then(response => {
          this.$message(response.data);
          this.getAllVotes();
          this.getVoteUserByUserNo(this.$store.state.userData.username);
        })
        .catch(error => {
          // 处理错误
          //alert(error.response.data); // 输出错误消息
          this.$message.error(error.response.data);
        });
    },

    // 取消投票方法
    voteCancel(voteNo, voteType) {
      var userNo = this.$store.state.userData.username;

      this.$axios.post(`http://localhost:8090/api/vote/cancelVote/${userNo}/${voteNo}/${voteType}`)
        .then(response => {
          this.$message(response.data);

          this.getAllVotes();
          this.getVoteUserByUserNo(this.$store.state.userData.username);
        })
        .catch(error => {
          // 处理错误
          //alert(error.response.data); // 输出错误消息
          this.$message.error(error.response.data);
        });

    },

    // 点击卡片时将被点击卡片的数据全部传入到tmp数据中
    showDetail(vote) {
      this.voteTmp.voteNoTmp = vote.voteNo;
      this.voteTmp.voteNameTmp = vote.voteName;
      this.voteTmp.voteEndtimeTmp = vote.voteEndtime;
      this.voteTmp.voteDetailTmp = vote.voteDetail;
      this.voteTmp.voteAgainstTmp = vote.voteAgainst;
      this.voteTmp.voteSupportTmp = vote.voteSupport;
      this.voteTmp.voteExpendTmp = vote.voteExpend;
      this.voteTmp.voteResultTmp = vote.voteResult;
      this.voteTmp.voteShowDitail = true;
      console.log("tmp值设置已完成");
      console.log(this.voteTmp.voteNoTmp);
    },

    closeDetail() {
      this.voteTmp.voteShowDitail = false;
      this.voteTmp.voteNoTmp = "";
      this.voteTmp.voteNameTmp = "";
      this.voteTmp.voteEndtimeTmp = "";
      this.voteTmp.voteDetailTmp = "";
      this.voteTmp.voteAgainstTmp = "";
      this.voteTmp.voteSupportTmp = "";
      this.voteTmp.voteExpendTmp = "";
      this.voteTmp.voteResultTmp = "";
    },

    getVoteStateByUserNo(voteNo) {
      for (let voteUserNo in this.voteUsers)
        if (this.voteUsers[voteUserNo].voteNo === voteNo) {
          return this.voteUsers[voteUserNo].voteState;
        }
      return "未投票";
    },

    // 投票结束提示
    voteOverMessage() {
      this.$message.error("该投票已结束 !");
    }
  },
};
</script>

<style scoped>
/* 卡片总体样式 */
.voting-card {
  margin: 0 auto;
  background-color: #fff;
  border: none;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
  padding: 10px 30px 10px 10px;
  width: 700px;
  height: auto;
  border-radius: 30px;
  margin-top: 40px;
}

/* 卡片标题样式 */
.text {
  display: flex;
  flex: 1;
  flex-direction: row;
  text-align: left;
}

.right-title {
  font-size: 1.7em;
  margin-right: 15px;
}

.left-title {
  margin-right: 20px;
}

/* 下侧栏 */
.info {
  display: flex;
  align-items: center;
  margin-top: 10px;
  justify-content: flex-start;
  margin: 0px 0px;
  padding-left: 20px;
}

/* 左侧详情栏 */
.details {
  flex: 2.5;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  text-align: left;
}

.investment {
  color: red;
  font-weight: bold;
  padding-left: 20px;
}

.amount {
  font-size: 2em;
}

/* 右侧投票区样式 */
.voting {
  justify-content: center;
  flex: 1;
  display: flex;
  align-items: center;
  display: flex;
  flex-direction: column;
}

.buttons {
  margin-bottom: 20px;
}

.vote-button {
  background-color: transparent;
  border: none;
  cursor: pointer;
  transition: transform 0.2s;
}

.vote-button.up:hover {
  transform: scale(1.1);
}

.vote-button.down:hover {
  transform: scale(1.1);
}

.vote-percentage {
  margin-left: 10px;
  font-weight: bold;
}

/* 详情卡片的样式 */
.details-card {
  background-color: #fff;
  border: none;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
  padding: 10px 30px 10px 10px;
  width: 700px;
  height: auto;
  border-radius: 30px;
  margin-bottom: 40px;
  display: flex;
  flex-direction: column;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.content {
  padding-left: 20px;
}

.close-details {
  margin-left: auto;
}

.bottom-div {
  display: flex;
  flex-direction: row;
}

.close-button {
  background-color: transparent;
  cursor: pointer;
  border: none;
  transform: 0.2s;
}

.close-button :hover {
  transform: scale(1.1);
}
</style>