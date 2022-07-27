<template>
  <div class="right">
      <img src="../assets/1.webp">
  </div>
</template>

<script>
  
  export default {
    name:'HaiXian',
    mounted() {
      console.log('HaiXian组件挂载',this.$route,this.$router)
    },
    beforeDestroy() {
      console.log('HaiXian组件销毁')
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