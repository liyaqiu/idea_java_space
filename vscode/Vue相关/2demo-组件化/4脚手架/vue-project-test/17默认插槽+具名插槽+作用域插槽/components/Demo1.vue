<template>
  <div> 
    <h1>默认插槽</h1>
    <!-- 写2次就有2份 -->
    <slot>没内容会显示</slot>
    <slot>没内容会显示</slot>
  </div>
</template>

<script>

  

  export default {
    name:'Demo1',
    data() {
      return {
        
      }
    }
  }
</script>

<style scoped lang='less'>
  
</style>