<template>
  <div>
    <router-link to="/yifu/YiFu_2/details"><img src="../assets/2.webp"></router-link>
    <router-view></router-view>
  </div>
</template>

<script>
  
  export default {
    name:'YiFu_2',
    mounted() {
      console.log('YiFu_2组件挂载',this.$route,this.$router)
    },
    beforeDestroy() {
      console.log('YiFu_2组件销毁')
    },
  }
</script>

<style lang='less' scoped>
  img{
      width: 80%;
      height: 80%;
  }
</style>