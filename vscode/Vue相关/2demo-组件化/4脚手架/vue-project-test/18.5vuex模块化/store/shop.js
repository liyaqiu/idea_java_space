export default {
    namespaced:true,
    /* 
        用于响应组件中的动作
        store.dispatch 调用的是actions的方法
     */
    actions:{
        updateShopPrice(context,value){
            console.log('updateShopPrice',context,value)
            context.commit('UPDATE_SHOP_PRICE',value)
        }
    },
    /* 
        用于操作数据state
        store.commit 调用的是mutations的方法
        */
    mutations:{
        UPDATE_SHOP_PRICE(state,value){
            console.log('UPDATE_SHOP_PRICE',state,value)
            state.shopPrice = value   
        }
    },
    state:{
        shopName:'华为p30',
        shopPrice:'5999'
    },
    getters:{
        producer(){
            return '华为分部'
        }
    }
}