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

let a = 100

new Vue({
  render: h => h(App),
}).$mount('#app')



