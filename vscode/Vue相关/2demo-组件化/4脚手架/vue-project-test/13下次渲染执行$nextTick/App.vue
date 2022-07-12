<template>
  <div> 
    <input type="text" v-show="isshow" :value="msg" ref='input1'>
    <button @click="inputOnfocus">点击获取焦点</button>
  </div>
</template>

<script>

  

  export default {
    name:'App',
    data() {
      return {
        msg:'eric',
        isshow:false
      }
    },
    methods: {
      inputOnfocus(){
        console.log(this)
        this.isshow = !this.isshow
        this.$nextTick(()=>{ //等渲染以后在执行
          this.$refs.input1.focus()
        })
      }
    },
  }
</script>

<style>

</style>