<template>
  <div>
    <button id="bt" v-on:click='print'>{{msg}}</button>
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
      },
      methods: {
        print(){
          console.log(Object.getOwnPropertyDescriptors(this))
        }
      },
    }
</script>

<style>
  #bt{
    border: 1px seagreen;
  }
</style>