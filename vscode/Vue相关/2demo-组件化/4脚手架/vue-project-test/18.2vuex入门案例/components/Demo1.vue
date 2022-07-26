<template>
  <div class="demo1"> 
    <!-- 获取值 -->
    <h1 @click="updateMsg">demo1:{{$store.state.msg}}</h1>
    <h1>demo1:{{$store.getters.computedMsg}}</h1>
  </div>
</template>

<script>

  export default {
    name:'Demo1',
   
    data() {
      return {
        
      }
    },
    methods: {
      updateMsg(){
        this.$store.dispatch('update','Demo1-hello-world')
      }
    },
    mounted() {
      console.log('Demo1',this)
    },
  }
</script>

<style>

</style>