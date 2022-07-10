<template>
  <div>
    <button id="bt">{{msg}}</button>
  </div>
</template>

<script>
    //export Vue.extend({})
    export default {
      name:'CompoTest2',
      data(){
        return {
          msg: '我是模块2'
        }
      }
    }
</script>

<style>
  #bt{
    border: 1px seagreen;
  }
</style>