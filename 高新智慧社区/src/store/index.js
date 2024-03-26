import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate"

Vue.use(Vuex)
 

export default new Vuex.Store({
  state: {
    userData: null, // 将userData状态初始值设为null
  },
  getters: {
  },
  mutations: {
    setUserData(state, userData) {
      state.userData = userData;
    },

  },
  actions: {
  },
  modules: {
  },
  plugins: [createPersistedState()]
})
