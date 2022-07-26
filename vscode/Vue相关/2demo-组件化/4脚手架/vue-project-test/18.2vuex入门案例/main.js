//引入完成版的Vue
/* import Vue from 'vue/dist/vue'
import App from './App.vue'

Vue.config.productionTip = false

new Vue({
  template: `<App></App>`,
  components:{App}
}).$mount('#app') */


import Vue from 'vue'
import App from './App.vue'

/* 第一步，安装 npm i vuex@3*/
/* 第二步，创建store对象，在store文件夹中*/
/* 第三步使用 */
/* import store from './store/index.js' */
/* 因为index.js是脚手架默认会读取的，所以可以简写 */
import store from './store'

Vue.config.productionTip = false


new Vue({
  el:'#app',
  render: h => h(App),
  store,
  beforeCreate() {
    Vue.prototype.$bus = this
  }
})



