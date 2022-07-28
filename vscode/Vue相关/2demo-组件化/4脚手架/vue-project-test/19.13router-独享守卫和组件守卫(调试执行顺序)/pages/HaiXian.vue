<template>
  <div class="right">
      <img src="../assets/1.webp">
  </div>
</template>

<script>
  
  export default {
    name:'HaiXian',
    beforeCreate(){
      console.log('HaiXian组件创建')
    },
    mounted() {
      console.log('HaiXian组件挂载',this.$route,this.$router)
    },
    beforeDestroy() {
      console.log('HaiXian组件销毁')
    },
    /* 只有路由组件使用的时候才会触发，一般组件使用不会触发 */
    beforeRouteEnter(to,from,next){
        console.log('Enter组件路由守卫')
        next()
    },
    /* 只有路由组件使用的时候才会触发，一般组件使用不会触发 */
    beforeRouteLeave (to, from, next) {
      console.log('Leave 组件路由守卫')
        next()
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