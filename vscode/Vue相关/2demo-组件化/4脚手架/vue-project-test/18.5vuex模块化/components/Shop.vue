<template>
  <div class="user"> 
    <h1>商品信息</h1>
    <h3>商品名:{{$store.state.shopOptions.shopName}}</h3>
    <h3>商品价格:{{$store.state.shopOptions.shopPrice}}</h3>
    <h4>厂商:{{$store.getters['shopOptions/producer']}}</h4>
    <button @click="$store.dispatch('shopOptions/updateShopPrice',6999)">修改价格</button>
    <button @click="$store.commit('shopOptions/UPDATE_SHOP_PRICE',7999)">修改价格</button>
  </div>
</template>

<script>
  import {mapState,mapGetters,mapActions,mapMutations} from 'vuex'
  export default {
    name:'Shop',
    methods: {
      /* ...mapActions('shopOptions',['updateShopPrice']), */
      /* ...mapMutations('shopOptions',['UPDATE_SHOP_PRICE']), */
    },
    computed: {
      /* ...mapState('shopOptions',['shopName','shopPrice']), */
      /* ...mapGetters('shopOptions',['producer']), */
    },
    mounted() {
      console.log('Shop',this)
    },
  }
</script>

<style>

</style>