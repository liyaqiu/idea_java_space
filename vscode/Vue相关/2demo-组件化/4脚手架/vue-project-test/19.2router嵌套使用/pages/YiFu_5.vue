<template>
  <img src="../assets/5.webp">
</template>

<script>
  
  export default {
    name:'YiFu_5',
    mounted() {
      console.log('YiFu_5组件挂载',this.$route,this.$router)
    },
    beforeDestroy() {
      console.log('YiFu_5组件销毁')
    },
  }
</script>

<style lang='less' scoped>
  img{
      width: 80%;
      height: 80%;
  }
</style>