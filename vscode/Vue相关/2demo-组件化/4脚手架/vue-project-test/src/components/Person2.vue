<template>
  <div>
      <h2 class="style1">{{msg}}</h2>
     
  </div>
</template>

<script>
    //export Vue.extend({})

    export default {
      name:'Person',
      data(){
        return {
          msg:'组件2'
        }
      }
    }
</script>

<style lang="css" scoped>
/* <style lang="less"> */  
  .style1{
    background-color: red;
  }
</style>