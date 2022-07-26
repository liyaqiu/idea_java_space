import Vue from 'vue'
import Vuex from 'vuex'

/* 使用vuex插件 */
Vue.use(Vuex)

import userOptions from './user'
import shopOptions from './shop'

/* 创建并暴露store实例对象 */
export default new Vuex.Store({
    modules:{
        userOptions,
        shopOptions
    }
})