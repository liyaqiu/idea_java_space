import Vue from 'vue'
import Vuex from 'vuex'

/* 使用vuex插件 */
Vue.use(Vuex)

/* 创建并暴露store实例对象 */
export default new Vuex.Store({
    /* 用于响应组件中的动作 */
    actions:{},
    /* 用于操作数据state */
    mutations:{},
    /* 用于存储数据 */
    state:{}
})