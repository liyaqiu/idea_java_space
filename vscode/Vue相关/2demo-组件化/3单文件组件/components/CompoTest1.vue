<template>
  <div>
    <button id="bt">{{msg}}</button>
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
      }
    }
</script>

<style>
  #bt{
    border: 1px yellow;
  }
</style>