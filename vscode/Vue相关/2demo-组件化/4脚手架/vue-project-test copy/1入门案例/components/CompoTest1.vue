<template>
  <div>
    <button id="bt" @click='showThis'>{{msg}}</button>
  </div>
</template>

<script>
    //export Vue.extend({})
    export default {
      name:'CompoTest1',
      data(){
        return {
          msg: '我是模块1'
        }
      },
      methods: {
        showThis(){
          console.log(this)
        }
      },
    }
</script>

<style>
  #bt{
    border: 1px yellow;
  }
</style>