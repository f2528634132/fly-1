let class2type = {};
"Boolean Number String Function Array Date RegExp Object".split(" ").forEach(item => {
  class2type["[object " + item + "]"] = item.toLowerCase();
})
function type(obj) {
  return obj == null ? String(obj) : class2type[toString.call(obj)] || "object";
}
function convertBase64UrlToBlob(urlData) {
  let arr = urlData.split(',')
  let mime = arr[0].match(/:(.*?);/)[1]
  let bstr = atob(arr[1])
  let n = bstr.length
  let u8arr = new Uint8Array(n)
  while (n--) {
    u8arr[n] = bstr.charCodeAt(n)
  }
  return new Blob([u8arr], { type: mime })
}

// ie不支持classList方法
if (!("classList" in document.documentElement)) {
  Object.defineProperty(HTMLElement.prototype, 'classList', {
    get: function () {
      var self = this;
      function update(fn) {
        return function (value) {
          var classes = self.className.split(/\s+/g),
            index = classes.indexOf(value);

          fn(classes, index, value);
          self.className = classes.join(" ");
        }
      }

      return {
        add: update(function (classes, index, value) {
          if (!~index) classes.push(value);
        }),

        remove: update(function (classes, index) {
          if (~index) classes.splice(index, 1);
        }),

        toggle: update(function (classes, index, value) {
          if (~index)
            classes.splice(index, 1);
          else
            classes.push(value);
        }),

        contains: function (value) {
          return !!~self.className.split(/\s+/g).indexOf(value);
        },

        item: function (i) {
          return self.className.split(/\s+/g)[i] || null;
        }
      };
    }
  });
}

if (!Array.prototype.forEach) {
  Array.prototype.forEach = function (fun /*, thisp*/) {
    var len = this.length;
    if (typeof fun != "function")
      throw new TypeError();

    var thisp = arguments[1];
    for (var i = 0; i < len; i++) {
      if (i in this)
        fun.call(thisp, this[i], i, this);
    }
  };
}

export default {
  isNumber: function (obj) {
    return type(obj) === "number";
  },
  isArray: function (obj) {
    return type(obj) === "array";
  },
  getCookie: function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
      return unescape(arr[2]);
    } else {
      return null;

    }
  },
  setCookie: function (c_name, value, expiredays) {
    var exdate = new Date()
    exdate.setDate(exdate.getDate() + expiredays)
    document.cookie = c_name + "=" + escape(value) +
      ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString()) + "; path=/; domain=" + ".zhaopin.com";
  },
  delCookie: function (name) {
    // console.log(name)
    var ex = new Date(); ex.setTime(ex.getTime() - 1);
    document.cookie = name + `=0;expires=${+ ex.toUTCString()};path=/; domain=/${".zhaopin.com"}`;
  },
  removeAllCookie: function () {
    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
      var cookie = cookies[i];
      var eqPos = cookie.indexOf("=");
      var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
      document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/";
    }
    if (cookies.length > 0) {
      for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        // var domain = location.host.substr(location.host.indexOf('.'));
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/; domain=" + ".zhaopin.com";
      }
    }
    // var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    // if (keys) {
    // console.log(keys)
    // var ex = new Date(); ex.setTime(ex.getTime() - 1);
    //   for (var i = keys.length; i--;)
    //   console.log(keys[i])
    //   document.cookie = keys[i] + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/; domain=" + ".zhaopin.com";
    // }
  },
  handleError(msg, vm) { // 异常处理
    window.a = msg
    const errList = [{ // 异常转义
      errVal: 'Network Error',
      errMsg: '用户信息异常，请重新登录后再试！'
    }, {
      errVal: '404',
      errMsg: '请求数据失效！'
    }, {
      errVal: 'timeout',
      errMsg: '请求已超时！'
    }]
    // console.log(`%c ${msg}`, 'background: #222; color: #bada55')
    if (vm) {
      let errMsg = `${msg}`
      let alertFn = vm.$alert || vm.UI.alert
      for (let err of errList) {
        if (errMsg.includes(err.errVal)) {
          errMsg = err.errMsg
          break
        }
      }
      alertFn && alertFn(errMsg, {
        title: '错误信息',
        customClass: 'forbidden',
        showConfirmButton: false,
      })
      if (vm.$store && vm.$store.state.spinner) {
        try {
          vm.$store.dispatch('spinnerClose')
        } catch (e) {
          return false
        }
      }
    }
  },
  sureLink(link, arr) {
    let needControlLinks = [
      "/offer-manage",
      "/index",
      "/course-center",
      "/course-manage",
      "/report-analysis",
      "/communication",
      "/data-statistics",
      "/more-service",
      "/news-manage",
      "/topic-manage",
      "/qa-manage",
      "/activity-manage",
      "/message-manage",
      "/banner-manage",
      "/data-statistics"
    ]
    if ((needControlLinks.indexOf(link) > -1) && (arr.indexOf(link) < 0)) {
      return false
    } else {
      return true
    }
  },
  currying(fn) {
    var args = [];
    return function () {
      if (arguments.length === 0) {
        return fn.apply(this, args);
      } else {
        if (args.indexOf(arguments[0]) < 0) {
          [].push.apply(args, arguments);
        }
        // return arguments.callee;
      }
    }
  },
  timeChunk(ary, fun, count) {
    let t;
    // let length = ary.length
    let start = () => {
      ary.forEach((i, index) => {
        if (index < (count || 1)) {
          fun(i)
          ary.shift()
        }
      })
    }
    return function () {
      t = setInterval(function () {
        if (ary.length === 0) {
          return clearInterval(t)
        }
        start()
      }, 1000)
    }
  },
  getInstance: function (fn) {
    var result;
    return function () {
      // console.log(result)
      return result || (result = fn.apply(this, arguments))
    }
  },
  getObjectURL: function (file) {
    let url = null;
    if (window.createObjectURL !== undefined) {
      // basic
      url = window.createObjectURL(file);
    } else if (window.URL !== undefined) {
      // mozilla(firefox)
      url = window.URL.createObjectURL(file);
    } else if (window.webkitURL !== undefined) {
      // webkit or chrome
      url = window.webkitURL.createObjectURL(file);
    }
    return url;
  },
  compressBlob: function (blob, opt) {
    return new Promise((resolve, reject) => {
      if (!(blob instanceof Blob)) {
        throw new Error('Expected blob got ', typeof blob)
      }
      let path = this.getObjectURL(blob)
      let img = new Image()
      img.src = path
      img.onload = function () {
        //  默认按比例压缩
        let w = this.width
        let h = this.height
        let scale = w / h
        w = opt.width || w
        h = opt.height || (w / scale)
        // 默认图片质量为0.7
        let quality = 1048576 / blob.size
        // 生成canvas
        let canvas = document.createElement('canvas')
        canvas.width = w
        canvas.height = h
        let ctx = canvas.getContext('2d')
        ctx.drawImage(this, 0, 0, w, h)
        //  图像质量
        if (opt.quality && opt.quality >= 1 && opt.quality < 0) {
          quality = opt.quality
        }
        let base64 = canvas.toDataURL('images/jpeg')
        resolve(convertBase64UrlToBlob(base64), quality)
      }
    })
  }
}

// export function downImage(src, name = Date.now(), MIME = 'png') {
//   return new Promise(resolve => {
//     let img = new Image()
//     img.src = src
//     let cvs = document.createElement('canvas')
//     let ctx = cvs.getContext('2d')
//     img.addEventListener('load', () => {
//       cvs.width = img.width
//       cvs.height = img.height
//       ctx.drawImage(img, 0, 0)
//       cvs.toBlob(blob => {
//         const link = document.createElement('a')
//         link.href = window.URL.createObjectURL(blob)
//         link.download = `${name}.${MIME}`
//         link.click()
//         resolve(true)
//       }, 'images/' + MIME)
//     })
//     img.addEventListener('error', () => {
//       resolve(false)
//     })
//   })
// }
