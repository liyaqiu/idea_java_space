import {getCategoryListRequest,getBannerListRequest} from '@/api'

export default {
    namespaced:true,
    /* 
        用于响应组件中的动作
        store.dispatch 调用的是actions的方法
     */
    actions:{
        async categoryList(context){
            const result = await getCategoryListRequest()
            if(result.code === 200){
                context.commit('CATEGORY_LIST',result.data)
            }
        },
        async bannerList(context){
            const result = await getBannerListRequest()
            if(result.code === 200){
                context.commit('BANNER_LIST',result.data)
            }
        }
    },
    /* 
        用于操作数据state
        store.commit 调用的是mutations的方法
        */
    mutations:{
        CATEGORY_LIST(state,result){
            state.categoryListData = result
        },
        BANNER_LIST(state,result){
            state.bannerListData = result
        }
    },
    state:{
        categoryListData:[],
        bannerListData:[],
    },
    getters:{
        /* producer(){
            return '华为分部'
        } */
    }
}