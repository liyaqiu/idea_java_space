import { getShopCartListRequest } from '@/api'

export default {
    namespaced: true, //使用命名空间，不然全部方法都在同一个集合中
    /* 
        用于响应组件中的动作
        store.dispatch 调用的是actions的方法
     */
    actions: {
        async getShopCartList(context, value = {}) {
            const result = await getShopCartListRequest(value)
            console.log('getShopCartList',result)
            if (result.code === 200) {
                context.commit('GET_SHOP_CART_LIST', result.data[0]?.cartInfoList||[])
            }
        }
    },
    /* 
        用于操作数据state
        store.commit 调用的是mutations的方法
        */
    mutations: {
        GET_SHOP_CART_LIST(state, value) {
            state.shopCartList = value
        }
    },
    state: {
        shopCartList: []
    },
    getters: {
      /*   attrsList(state) {
            return state.productList.attrsList || {}
        }, */
    }
}