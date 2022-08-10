import {getBaseCategoryListRequest} from '@/api'

export default {
    namespaced:true,
    /* 
        用于响应组件中的动作
        store.dispatch 调用的是actions的方法
     */
    actions:{
        async baseCategoryList(context){
            console.log('baseCategoryList-actions')
            const result = await getBaseCategoryListRequest()
            if(result.code === 200){
                context.commit('BASE_CATEGORY_LIST',result.data)
            }
        }
    },
    /* 
        用于操作数据state
        store.commit 调用的是mutations的方法
        */
    mutations:{
        BASE_CATEGORY_LIST(state,result){
            console.log('BASE_CATEGORY_LIST-mutations',state,result)
            state.categoryList = result
        }
    },
    state:{
        categoryList:[],
    },
    getters:{
        /* producer(){
            return '华为分部'
        } */
    }
}