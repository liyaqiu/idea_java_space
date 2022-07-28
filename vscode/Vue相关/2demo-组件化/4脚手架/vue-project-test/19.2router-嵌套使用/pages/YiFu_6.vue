<template>
  <img src="../assets/6.webp">
</template>

<script>
  
  export default {
    name:'YiFu_6',
    mounted() {
      console.log('YiFu_6组件挂载',this.$route,this.$router)
    },
    beforeDestroy() {
      console.log('YiFu_6组件销毁')
    },
  }
</script>

<style lang='less' scoped>
  img{
      width: 80%;
      height: 80%;
  }
</style>