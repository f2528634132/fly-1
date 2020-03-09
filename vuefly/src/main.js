// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import axios from 'axios'
import  VueResource  from 'vue-resource'
import store  from "./store"
import utils from "./utils/utils"
import global_ from './components/Global'//引用文件
import 'element-ui/lib/theme-chalk/index.css'
import {getRequest} from './utils/api'
import {postRequest} from './utils/api'
import {deleteRequest} from './utils/api'
import {putRequest} from './utils/api'
import './assets/css/global.css'
Vue.use(ElementUI)
Vue.use(VueResource)
//Vue.use({axios,VueAxios})
// axios.defaults.headers.common['Content-Type'] = 'application/json;charset=UTF-8'

Vue.prototype.$axios = axios;

Vue.prototype.getRequest=getRequest;
Vue.prototype.postRequest=postRequest;
Vue.prototype.deleteRequest=deleteRequest;
Vue.prototype.putRequest=putRequest;
Vue.prototype.GLOBAL = global_ //挂载到Vue实例上面
Vue.config.productionTip = false
Vue.prototype.utils=utils;
// $axios.interceptors.request.use(
//   config => {
//
//   },
//   err => {
//     return Promise.reject(err)
//   })

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store:store,
  components: { App },
  template: '<App/>'
})
