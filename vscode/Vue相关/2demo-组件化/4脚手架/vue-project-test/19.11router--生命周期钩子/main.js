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

/* 引入Router */
import VueRouter from 'vue-router'
/* 引入路由器配置 */
import router from './router'

Vue.config.productionTip = false

/* 使用Router */
Vue.use(VueRouter)

new Vue({
  el:'#app',
  render: h => h(App),
  router, /* 使用路由器配置 */
  beforeCreate() {
    Vue.prototype.$bus = this
  }
})



