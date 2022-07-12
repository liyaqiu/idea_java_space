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

import myplugins from './plugins/myplugin.js'


Vue.config.productionTip = false


Vue.use(myplugins,['参数1','参数2'])//使用插件

new Vue({
  el:'#app',
  render: h => h(App),
})



