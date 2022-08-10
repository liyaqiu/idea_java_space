import Vue from 'vue'
import App from './App.vue'

//引入路由器
import router from './router'

Vue.config.productionTip = false

//注册全局组件
import TypeNav from '@/components/TypeNav'
Vue.component(TypeNav.name,TypeNav)

/* 
测试ajax请求
import {getBaseCategoryListRequest} from '@/api'
getBaseCategoryListRequest().then(result=>{
    console.log('result',result)
}) */

/* 引入vuex */
import store from './store'

new Vue({
    el: '#app',
    render: h => h(App),
    router, //使用路由器
    store,//使用vuex仓库
    beforeCreate() {
        Vue.prototype.$bus = this
    }
})