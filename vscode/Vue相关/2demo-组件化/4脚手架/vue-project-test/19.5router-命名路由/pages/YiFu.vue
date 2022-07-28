<template>
  <div class="right">
      <img src="../assets/2.webp">
  </div>
</template>

<script>
  
  export default {
    name:'YiFu',
    mounted() {
      console.log('YiFu组件挂载',this.$route,this.$router)
    },
    beforeDestroy() {
      console.log('YiFu组件销毁')
    },
  }
</script>

<style lang='less' scoped>
  .right{
      box-sizing: border-box;
      float: left;
      width: 400px;
      height: 100%;
      img{
          width: 100%;
          height: 100%;
      }
  }
</style>