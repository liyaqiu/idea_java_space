import { getProductDetailRequest,addGoodsToCartRequest } from '@/api'
import {genAnonymousToken} from '@/utils/tokenUtil'
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
        },
        async addGoodsToCart(context, {goodsId,buyNum}) {
            console.log('addGoodsToCart',goodsId,buyNum)
            const result = await addGoodsToCartRequest(goodsId,buyNum)
            if (result.code === 200) {
                return "添加成功"
            }else{
                throw new Error('添加失败')
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
        productDetail: {},
        userToken:genAnonymousToken() //默认使用匿名token
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
        spuSaleAttrList(state) {
            return state.productDetail.spuSaleAttrList || []
        },
        
    }
}