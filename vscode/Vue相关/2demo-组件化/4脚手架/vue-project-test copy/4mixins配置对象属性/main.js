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

import {globalConfig,localConfig} from './mixinjs/common-mixin.js'

Vue.config.productionTip = false


Vue.mixin(globalConfig)

new Vue({
  el:'#app',
  render: h => h(App),
})



