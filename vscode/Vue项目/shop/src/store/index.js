import Vue from 'vue'
import Vuex from 'vuex'

/* 使用vuex插件 */
Vue.use(Vuex)

import home from './home'
import search from './search'
import detail from './detail'
import shopCart from './shopCart'

/* 创建并暴露store实例对象 */
export default new Vuex.Store({
    modules:{
        home,
        search,
        detail,
        shopCart,
    }
})