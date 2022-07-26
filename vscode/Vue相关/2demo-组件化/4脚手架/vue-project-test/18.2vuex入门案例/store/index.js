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
            context.dispatch('salt1',value)
        },
        salt1(context,value){
            console.log('salt1',context,value)
            context.dispatch('salt2',{context,value})
        },
        salt2(context,value){
            console.log('salt2',context,value)
            context.commit('UPDATE',value)
        }
    },
    /* 
        用于操作数据state
        store.commit 调用的是mutations的方法
     */
    mutations:{
        UPDATE(state,{context,value}){
            console.log('UPDATE',state,context,value)
            context.commit('UPDATE2',value)
        },
        UPDATE2(state,value){
            console.log('UPDATE2',state,value)
            state.msg = value
        }
    },
    /* 用于存储数据 */
    state:{
        /* 设置共享的数据 */
        msg:'init-hello-wonld'
    },
    getters:{
        computedMsg(state){
            return state.msg+"我是计算属性"
        }
    }
})