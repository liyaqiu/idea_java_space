<template>
  <div class="right">
      <h3>名字:{{$route.params.name}}</h3>
      <h4>价格:{{$route.params.price}}</h4>
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
          height: 80%;
      }
  }
</style>