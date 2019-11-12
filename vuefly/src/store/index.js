import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const store = new Vuex.Store({

  state: {
    // 存储token
    Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
    functionNodes: [], // 初始权限数据
    powerLinks: [], // 页面权限
    nodeTypeTwo: [], // 按钮权限
    navList: [] //页面权限
  },

  mutations: {
    // 修改token，并将token存入localStorage
    changeLogin (state, user) {
      state.Authorization = user.Authorization;
      localStorage.setItem('Authorization', user.Authorization);
    },
    setFunctionNodes(state, functionNodes) {
      state.functionNodes = functionNodes
    },
    setNodeTypeTwo(state, nodeTypeTwo) {
      state.nodeTypeTwo = nodeTypeTwo
    },
    setPowerLinks(state, powerLinks) {
      state.powerLinks = powerLinks
    },
    setNavList(state, navList) {
      state.navList = navList
    }
  }
});

export default store;
