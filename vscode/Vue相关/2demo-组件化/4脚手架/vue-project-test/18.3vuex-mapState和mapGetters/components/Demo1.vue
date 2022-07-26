<template>
  <div class="demo1"> 
    <!-- 简单获取 -->
    <h1>方式1:{{$store.getters.computedMsg}}</h1>
    <h1>方式2:{{computedMsg}}</h1>
    
    <!-- 简单获取 -->
    <h1>方式1:{{$store.state.message}}</h1>
    <h1>方式2:{{message}}</h1>
  </div>
</template>

<script>

  import {mapState,mapGetters} from 'vuex'
  export default {
    name:'Demo1',
   
    data() {
      return {
        
      }
    },
    computed:{
      /* 利用mapState生成的计算属性，映射的是store中的state对象属性 */
      /* 对象写法 */
      /* ...mapState({message:"message"}), */
      /* 数组写法 */
      ...mapState(["message"]),

      /* 利用mapGetters生成的计算属性，映射的是store中的getters对象属性 */
      /* 对象写法 */
      /* ...mapGetters({computedMsg:"computedMsg"}), */
      /* 数组写法 */
      ...mapGetters(["computedMsg"]),
    },
    mounted() {
      console.log('Demo1',this)
    },
  }
</script>

<style>

</style>