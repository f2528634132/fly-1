import axios from 'axios';
import store from '@/vuex/store.js'
import { MessageBox } from 'element-ui'
import common from 'helper/common'
let alertFn = MessageBox.alert || store._vm.$alert || store._vm.UI.alert;
// console.log(store)
//webpack 环境变量，构建时自动替换现网动态请求域名前缀
// axios.defaults.baseURL = process.env.dynamicDomain;
// axios.defaults.timeout = 6000;
axios.defaults.withCredentials = true;
// import Promise from 'es6-promise'
// Promise.polyfill()
const logout = function(){
  const selfHost = window.location.host
  store.dispatch("setToken", null);
  store.dispatch("setUserInfo", {});
  store.commit("setNavList", [
    {
      url: "/personal-info",
      icon: "userinfo",
      name: "个人信息",
      type: 1
    },
    {
      url: "/",
      icon: "log",
      name: "操作日志",
      type: 1
    }
  ]);
  store.commit("setPowerLinks", ["/index"]);
  store.commit("setNodeTypeTwo", []);
  const at = common.getCookie("at");
  const rt = common.getCookie("rt");
  common.removeAllCookie();
  if (at && rt) {
    window.location = `https://rd5.zhaopin.com/login?bkurl=${encodeURIComponent('https://offer.zhaopin.com/#/index')}`;
  } else {
    window.location = `http://${selfHost}/#/login`
  }
}
export default {
  get: function (url, data, header, responseType) {
    // header的相同部分
    let commonHeader = {
      'Authorization': store.state.token,
      // 'AuthRole': store.state.roleId || ''
    }
    let headers = Object.assign({}, commonHeader, header)
    let datas = Object.assign({}, data, {time:new Date().getTime()})
    let lastResponseType = responseType ? responseType : '';
    return new Promise(function (resolve, reject) {
      if (!store.state.token && (url.indexOf('enterpriseAccountLogin') < 0 && url.indexOf('register') < 0)) return false
      axios.get(url, {
        params: datas,
        headers: headers,
        responseType: lastResponseType
      }).then(function (response) {
        if(response.data.type == "FeatureCollection") {
          resolve(response.data)
          return
        }
        let code = response.data.errCode
        if (lastResponseType == 'blob') {
          resolve(response.data);
        } else if (code === 0) {
          resolve(response.data);
        } else if (code === 100) {
          resolve(response.data)
        } else if (code === 3014) {
          alertFn && alertFn('您的服务已到期/下线，如需继续使用，请联系顾问或在【offerkit】服务号提交申请', {
            title: '通知',
            showConfirmButton: true,
            callback: action => {
              logout();
            }
          })
          reject(response.data)
        } else {
          alertFn && alertFn(response.data.errMsg || '未知错误', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: response.data.errMsg || '未知错误',
          //   type: 'warning'
          // })
          reject(response.data);
        }
      }).catch(function (error) {
        reject(error);
        if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          alertFn && alertFn(error.response.data.errMsg || '未知错误', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: error.response.data.errMsg || '未知错误',
          //   type: 'error'
          // })
          // console.log(error.response.data);
          // console.log(error.response.status);
          // console.log(error.response.headers);
        } else if (error.request) {
          // The request was made but no response was received
          // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
          // http.ClientRequest in node.js
          alertFn && alertFn('The request was made but no response was received', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: 'The request was made but no response was received',
          //   type: 'error'
          // })
          console.log(error.request);
        } else {
          // Something happened in setting up the request that triggered an Error
          // console.log('Error', error.message);
          alertFn && alertFn('Something happened in setting up the request that triggered an Error', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: "Something happened in setting up the request that triggered an Error",
          //   type: 'error'
          // })
        }
        // console.log(error.config);
      });
    });
  },
  delete: function (url, data, header, responseType) {
    // header的相同部分
    let commonHeader = {
      'Authorization': store.state.token,
      // 'AuthRole': store.state.roleId || ''
    }
    let headers = Object.assign({}, commonHeader, header)
    let lastResponseType = responseType ? responseType : 'json';
    // console.log(responseType)
    return new Promise(function (resolve, reject) {
      if (!store.state.token && url.indexOf('enterpriseAccountLogin') < 0) return false
      axios(url, {
        method: 'delete',
        url: url,
        params: data,
        headers: headers,
        responseType: lastResponseType
      }).then(function (response) {
        let code = response.data.errCode
        if (code === 0) {
          resolve(response.data);
        } else if (code === 100) {
          resolve(response.data)
        } else if (code === 3014) {
          alertFn && alertFn('您的服务已到期/下线，如需继续使用，请联系顾问或在【offerkit】服务号提交申请', {
            title: '通知',
            showConfirmButton: true,
            callback: action => {
              logout();
            }
          })
          reject(response.data)
        } else {
          // console.log(response.data)
          alertFn && alertFn(response.data.errMsg || '未知错误', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: response.data.errMsg || '未知错误',
          //   type: 'warning'
          // })
          reject(response.data);
        }
      }).catch(function (error) {
        reject(error);
        if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          alertFn && alertFn(error.response.data.errMsg || '未知错误', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: error.response.data.errMsg || '未知错误',
          //   type: 'error'
          // })
          // console.log(error.response.data);
          // console.log(error.response.status);
          // console.log(error.response.headers);
        } else if (error.request) {
          // The request was made but no response was received
          // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
          // http.ClientRequest in node.js
          alertFn && alertFn('The request was made but no response was received', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: 'The request was made but no response was received',
          //   type: 'error'
          // })
          console.log(error.request);
        } else {
          // Something happened in setting up the request that triggered an Error
          alertFn && alertFn('Something happened in setting up the request that triggered an Error', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: "Something happened in setting up the request that triggered an Error",
          //   type: 'error'
          // })
          // console.log('Error', error.message);
        }
        // console.log(error.config);
      });
    });
  },
  post: function (url, data, header, responseType) {
    // header的相同部分
    let commonHeader = {
      'Authorization': store.state.token,
      // 'AuthRole': store.state.roleId || ''
    }
    let headers = Object.assign({}, commonHeader, header)
    let lastResponseType = responseType ? responseType : '';
    return new Promise(function (resolve, reject) {
      if (!store.state.token && url.indexOf('enterpriseAccountLogin') < 0) return false
      axios({
        method: 'post',
        url: url,
        data: data,
        headers: headers,
        responseType: lastResponseType
      }).then(function (response) {
        let code = response.data && response.data.errCode
        if (url.indexOf('attachmentClient') > -1 || lastResponseType == 'blob') {
          resolve(response.data);
        } else if (code === 0) {
          resolve(response.data);
        } else if (code === 100) {
          resolve(response.data)
        } else if (code === 3014) {
          alertFn && alertFn('您的服务已到期/下线，如需继续使用，请联系顾问或在【offerkit】服务号提交申请', {
            title: '通知',
            showConfirmButton: true,
            callback: action => {
              logout();
            }
          })
          reject(response.data)
        }else {
          // console.log(response.data)
          alertFn && alertFn(response.data.errMsg || '未知错误', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: response.data.errMsg || '未知错误',
          //   type: 'warning'
          // })
          reject(response.data);
        }
      }).catch(function (error) {
        reject(error);
        if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          alertFn && alertFn(error.response.data.errMsg || '未知错误', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: error.response.data.errMsg || '未知错误',
          //   type: 'error'
          // })
          // console.log(error.response.data);
          // console.log(error.response.status);
          // console.log(error.response.headers);
        } else if (error.request) {
          // The request was made but no response was received
          // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
          // http.ClientRequest in node.js
          alertFn && alertFn('The request was made but no response was received', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: 'The request was made but no response was received',
          //   type: 'error'
          // })
          console.log(error.request);

        } else {
          // Something happened in setting up the request that triggered an Error
          // console.log('Error', error.message);
          alertFn && alertFn('Something happened in setting up the request that triggered an Error', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: "Something happened in setting up the request that triggered an Error",
          //   type: 'error'
          // })
        }
        // console.log(error.config);
      });
    });
  },
  put: function (url, data, header, responseType) {
    // header的相同部分
    let commonHeader = {
      'Authorization': store.state.token,
      // 'AuthRole': store.state.roleId || ''
    }
    let headers = Object.assign({}, commonHeader, header)
    let lastResponseType = responseType ? responseType : '';
    // console.log(responseType)
    return new Promise(function (resolve, reject) {
      if (!store.state.token && url.indexOf('enterpriseAccountLogin') < 0) return false
      axios(url, {
        method: 'put',
        url: url,
        data: data,
        headers: headers,
        responseType: lastResponseType
      }).then(function (response) {
        let code = response.data.errCode
        if (code === 0) {
          resolve(response.data);
        } else if (code === 100) {
          resolve(response.data)
        } else if (code === 3014) {
          alertFn && alertFn('您的服务已到期/下线，如需继续使用，请联系顾问或在【offerkit】服务号提交申请', {
            title: '通知',
            showConfirmButton: true,
            callback: action => {
              logout();
            }
          })
          reject(response.data)
        }else {
          // console.log(response.data)
          alertFn && alertFn(response.data.errMsg || '未知错误', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: response.data.errMsg || '未知错误',
          //   type: 'warning'
          // })
          reject(response.data);
        }
      }).catch(function (error) {
        reject(error);
        if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          alertFn && alertFn(error.response.data.errMsg || '未知错误', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: error.response.data.errMsg || '未知错误',
          //   type: 'error'
          // })
          // console.log(error.response.data);
          // console.log(error.response.status);
          // console.log(error.response.headers);
        } else if (error.request) {
          // The request was made but no response was received
          // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
          // http.ClientRequest in node.js
          alertFn && alertFn('The request was made but no response was received', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: 'The request was made but no response was received',
          //   type: 'error'
          // })
          console.log(error.request);
        } else {
          // Something happened in setting up the request that triggered an Error
          alertFn && alertFn('Something happened in setting up the request that triggered an Error', {
            title: '错误信息',
            customClass: 'forbidden',
            showConfirmButton: false,
          })
          // Notification({
          //   message: "Something happened in setting up the request that triggered an Error",
          //   type: 'error'
          // })
          // console.log('Error', error.message);
        }
        // console.log(error.config);
      });
    });
  }
}
