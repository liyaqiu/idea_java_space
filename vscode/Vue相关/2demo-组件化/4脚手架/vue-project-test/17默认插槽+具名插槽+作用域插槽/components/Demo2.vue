<template>
  <div> 
    <h1>具名插槽</h1>
    <slot name="one">没内容会显示</slot>
    <slot name="two">没内容会显示</slot>
  </div>
</template>

<script>

  

  export default {
    name:'Demo2',
    data() {
      return {
        
      }
    }
  }
</script>

<style>

</style>