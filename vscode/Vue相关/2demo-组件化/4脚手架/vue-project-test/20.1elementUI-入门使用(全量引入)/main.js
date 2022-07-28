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


Vue.config.productionTip = false

/* 完整引入引入ElementUI,并且使用 */
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
/* 使用参考文档 */
/* https://element.eleme.cn/#/zh-CN/component/button */

new Vue({
  el:'#app',
  render: h => h(App),
  beforeCreate() {
    Vue.prototype.$bus = this
  }
})


