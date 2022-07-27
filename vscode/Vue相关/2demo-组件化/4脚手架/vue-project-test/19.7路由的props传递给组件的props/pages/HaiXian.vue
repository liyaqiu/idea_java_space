<template>
  <div class="right">
      <!-- <h3>名字:{{$route.params.name}}</h3>
      <h4>价格:{{$route.params.price}}</h4> -->
      <h3>名字:{{name}}</h3>
      <h4>价格:{{price}}</h4>
      <img src="../assets/1.webp">
  </div>
</template>

<script>
  
  export default {
    name:'HaiXian',
    props:['name','price'],
    mounted() {
      console.log('HaiXian组件挂载',this,this.$route,this.$router)
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
          height: 80%;
      }
  }
</style>