import Vue from 'vue'
import Vuex from 'vuex'

/* 使用vuex插件 */
Vue.use(Vuex)

/* 创建并暴露store实例对象 */
export default new Vuex.Store({
    /* 用于存储数据 */
    state:{
        /* 设置共享的数据 */
        message:'好消息'
    },
    getters:{
        computedMsg(state){
            return state.message+"我是计算属性"
        }
    }
})