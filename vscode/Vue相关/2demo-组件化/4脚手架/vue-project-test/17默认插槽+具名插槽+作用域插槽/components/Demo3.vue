<template>
  <div> 
    <h1>作用域插槽</h1>
    <slot name="one" :arrs3="arrs3">没内容会显示</slot>
    <slot name="two" :arrs4="arrs4">没内容会显示</slot>
  </div>
</template>

<script>

  

  export default {
    name:'Demo3',
    data() {
      return {
        arrs3:['百度','头条','美团'],
        arrs4:['新浪','360','滴滴'],
      }
    }
  }
</script>

<style>

</style>