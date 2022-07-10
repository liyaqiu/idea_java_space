<template>
  <div>
      <input type="button" v-on:click='print' ref="input1" v-bind:value="msg">
      <input type="text" v-model="msg" ref="input2">
  </div>
</template>

<script>
    //export Vue.extend({})
    export default {
      name:'Person',
      data(){
        return {
          msg: 'eric'
        }
      },
      methods: {
        print(){
          console.log(this)
        }
      }
    }
</script>

<style>
  
</style>