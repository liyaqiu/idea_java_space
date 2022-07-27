<template>
  <div>
    11111
  </div>
</template>

<script>
  
  export default {
    name:'Details',
    mounted() {
      console.log('Details组件挂载',this.$route,this.$router)
    },
    beforeDestroy() {
      console.log('Details组件销毁')
    },
  }
</script>

<style lang='less' scoped>

</style>