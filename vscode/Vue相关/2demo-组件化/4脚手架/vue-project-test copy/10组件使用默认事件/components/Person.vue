<template>
  <div class="div">
    <button @click="method1">点我传递参数给父组件</button>
  </div>
</template>

<script>
  export default {
    name:'Person',
    data() {
      return {
        msg:'我是子组件的信息'
      }
    },
    methods: {
      method1(){
        console.log('触发事件')
        this.$emit('click',this.msg) //触发组件事件
      }
    },
  }
</script>

<style scoped>
  .div{
    width: 100px;
    height: 100px;
    border: seagreen;
    background-color: azure;
  }
</style>