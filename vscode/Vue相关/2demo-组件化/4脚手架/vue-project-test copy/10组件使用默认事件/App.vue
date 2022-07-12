<template>
  
  <div> 
    <Person ref='personRef' @click.native="fun"/>
    <Person ref='personRef' @click="fun"/> <!-- 使用默认事件也会当成自定义事件 -->
  </div>

</template>

<script>

  import Person from './components/Person'

  export default {
    name:'App',
    components:{Person},
    methods: {
      fun(v){
        console.log(v)
      }
    },
  }
</script>

<style>

</style>