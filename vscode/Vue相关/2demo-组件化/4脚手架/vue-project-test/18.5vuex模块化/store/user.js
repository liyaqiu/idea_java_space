export default {
    namespaced:true,
    /* 
        用于响应组件中的动作
        store.dispatch 调用的是actions的方法
     */
    actions:{
        updateUserName(context,value){
            console.log('updateUser',context,value)
            context.commit('UPDATE_USER_NAME',value)
        }
    },
    /* 
        用于操作数据state
        store.commit 调用的是mutations的方法
        */
    mutations:{
        UPDATE_USER_NAME(state,value){
            console.log('UPDATE_USER',state,value)
            state.userName = value
        }
    },
    state:{
        userName:'eric',
        userSex:'男'
    },
    getters:{
        userAddress(){
            return '北京市广场路30号'
        }
    }
}