import Vue from 'vue'
import Vuex from 'vuex'

/* 使用vuex插件 */
Vue.use(Vuex)

/* 创建并暴露store实例对象 */
export default new Vuex.Store({
    /* 
        用于响应组件中的动作
        store.dispatch 调用的是actions的方法
     */
    actions:{
        update(context,value){
            console.log('update',context,value)
        }
    },
    /* 
        用于操作数据state
        store.commit 调用的是mutations的方法
     */
    mutations:{
        UPDATE(state,value){
            console.log('UPDATE',state,value)
        }
    }
})