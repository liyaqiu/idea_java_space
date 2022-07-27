<template>
  <img src="../assets/3.webp">
</template>

<script>
  
  export default {
    name:'YiFu_3',
    mounted() {
      console.log('YiFu_3组件挂载',this.$route,this.$router)
    },
    beforeDestroy() {
      console.log('YiFu_3组件销毁')
    },
  }
</script>

<style lang='less' scoped>
  img{
      width: 80%;
      height: 80%;
  }
</style>