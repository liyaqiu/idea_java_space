import { getProductDetailRequest } from '@/api'

export default {
    namespaced: true, //使用命名空间，不然全部方法都在同一个集合中
    /* 
        用于响应组件中的动作
        store.dispatch 调用的是actions的方法
     */
    actions: {
        async getProductDetail(context, goodsId) {
            //console.log('getProductDetail 发起请求',goodsId)
            const result = await getProductDetailRequest(goodsId)
            if (result.code === 200) {
                context.commit('GET_PRODUCT_DETAIL', result.data)
            }
        }
    },
    /* 
        用于操作数据state
        store.commit 调用的是mutations的方法
        */
    mutations: {
        GET_PRODUCT_DETAIL(state, value) {
            state.productDetail = value
        }
    },
    state: {
        productDetail: {}
    },
    getters: {
        //分类信息展示
        categoryView(state) {
            return state.productDetail.categoryView || {}
        },
        skuInfo(state) {
            return state.productDetail.skuInfo || {}
        },
        skuImageList(state) {
            return state.productDetail.skuInfo?.skuImageList || []
        },
    }
}