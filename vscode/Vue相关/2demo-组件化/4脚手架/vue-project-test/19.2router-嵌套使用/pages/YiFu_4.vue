<template>
  <img src="../assets/4.webp">
</template>

<script>
  
  export default {
    name:'YiFu_4',
    mounted() {
      console.log('YiFu_4组件挂载',this.$route,this.$router)
    },
    beforeDestroy() {
      console.log('YiFu_4组件销毁')
    },
  }
</script>

<style lang='less' scoped>
  img{
      width: 80%;
      height: 80%;
  }
</style>