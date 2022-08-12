import {getCategoryListRequest,getBannerListRequest,getFloorListRequest} from '@/api'

export default {
    namespaced:true, //使用命名空间，不然全部方法都在同一个集合中
    /* 
        用于响应组件中的动作
        store.dispatch 调用的是actions的方法
     */
    actions:{
        async getCategoryList(context){
            const result = await getCategoryListRequest()
            if(result.code === 200){
                context.commit('GET_CATEGORY_LIST',result.data)
            }
        },
        async getBannerList(context){
            const result = await getBannerListRequest()
            if(result.code === 200){
                context.commit('GET_BANNER_LIST',result.data)
            }
        },
        async getFloorList(context){
            const result = await getFloorListRequest()
            if(result.code === 200){
                context.commit('GET_FLOOR_LIST',result.data)
            }
        }
    },
    /* 
        用于操作数据state
        store.commit 调用的是mutations的方法
        */
    mutations:{
        GET_CATEGORY_LIST(state,result){
            state.categoryList = result
        },
        GET_BANNER_LIST(state,result){
            state.bannerList = result
        },
        GET_FLOOR_LIST(state,result){
            state.floorList = result
        }
    },
    state:{
        categoryList:[],
        bannerList:[],
        floorList:[],
    },
    getters:{
        /* homeproducer(){
            return '华为分部'
        } */
    }
}