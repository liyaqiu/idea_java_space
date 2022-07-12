<template>
  <div>
      <h2>globalmsg{{globalmsg}}</h2>
      <h2>localmsg{{localmsg}}</h2>
     
  </div>
</template>

<script>
    //export Vue.extend({})
    import {localConfig} from '../mixinjs/common-mixin.js'

    export default {
      name:'Person',
      data(){
        console.log(this)
        return {
        }
      },
      mixins:[localConfig]
    }
</script>

<style>
  
</style>