<template>
  <div class="demo2"> 
    <!-- 获取值 -->
    <h1 @click="updateMsg">demo2:{{$store.state.msg}}</h1>
    <h1>demo2:{{$store.getters.computedMsg}}</h1>
  </div>
</template>

<script>

  export default {
    name:'Demo2',
   
    data() {
      return {
        
      }
    },
    methods: {
      updateMsg(){
        this.$store.commit('UPDATE2','Demo2-hello-world')
      }
    },
    mounted() {
      console.log('Demo2',this)
    },
  }
</script>

<style>

</style>