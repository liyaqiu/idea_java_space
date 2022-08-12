import { getProductListRequest } from '@/api'

export default {
    namespaced: true, //使用命名空间，不然全部方法都在同一个集合中
    /* 
        用于响应组件中的动作
        store.dispatch 调用的是actions的方法
     */
    actions: {
        async getProductList(context, value = {}) {
            const result = await getProductListRequest(value)
            if (result.code === 200) {
                context.commit('GET_PRODUCT_LIST', result.data)
            }
        }
    },
    /* 
        用于操作数据state
        store.commit 调用的是mutations的方法
        */
    mutations: {
        GET_PRODUCT_LIST(state, value) {
            state.productList = value
        }
    },
    state: {
        productList: {}
    },
    getters: {
        attrsList(state) {
            return (state.productList?.attrsList) || {}
        },
        goodsList(state) {
            return (state.productList?.goodsList) || {}
        },
        trademarkList(state) {
            return (state.productList?.trademarkList) || {}
        }
    }
}