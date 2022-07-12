<template>
  <div>
    <button @click="method1">触发组件绑定事件</button>
    <button @click="unbind">解绑事件</button>
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
        this.$emit('myClick',this.msg) //触发组件事件
      },
      unbind(){
        this.off('myclick') //解绑事件
        this.off(['myclick','myclick1']) //解绑多事件
        this.off() //解绑所有事件
      }
    },
  }
</script>

<style>

</style>