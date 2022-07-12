<template>
  <div>
      <div id="div2">
          <input type="text" placeholder="请输入后按回车" @keydown.enter="addItem" v-model="inputValue">
      </div>
     
  </div>
</template>

<script>
    //export Vue.extend({})
    export default {
      name:'UserHeader',
      props:['transValueToApp'],
      data() {
        return {
          inputValue:''
        }
      },
      methods: {
        addItem(){
          const value = this.inputValue.trim()
          if(value){
            this.transValueToApp(value)
          }
        }
      },
    }
</script>


<style lang="css" scoped>
/* <style lang="less"> */  
  #div2{
        border: solid rgb(17, 17, 17);
        width: 380px;
        height: 90px;
    }
</style>
