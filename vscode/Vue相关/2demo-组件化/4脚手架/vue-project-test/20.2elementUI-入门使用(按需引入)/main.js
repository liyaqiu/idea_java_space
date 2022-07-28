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

/* 
  按需引入ElementUI
    https://element.eleme.cn/#/zh-CN/component/quickstart
    安装库
      npm i element-ui -S
    按需引入安装
      npm install babel-plugin-component -D
    在babel.config.js文件中添加如下信息
      presets: [
        '@vue/cli-plugin-babel/preset',["@babel/preset-env", { "modules": false }]
      ],
      plugins: [
        [
          "component",
          {
            "libraryName": "element-ui",
            "styleLibraryName": "theme-chalk"
          }
        ]
      ]
    在main.js引入库，并且注册到全局组件中
      import { Button, Select } from 'element-ui';
      Vue.component(Button.name, Button);
      Vue.component('HelloWorld', Button);
      Vue.component(Select.name, Select);
    在组件中使用
      <el-button type="primary">主要按钮</el-button>
      <hello-world type="primary">主要按钮</hello-world>
      <HelloWorld type="primary">主要按钮</HelloWorld>
 */

  import { Button, Select } from 'element-ui';
  Vue.component(Button.name, Button);
  Vue.component('HelloWorld', Button);
  Vue.component(Select.name, Select);


new Vue({
  el:'#app',
  render: h => h(App),
  beforeCreate() {
    Vue.prototype.$bus = this
  }
})


