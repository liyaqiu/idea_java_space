<template>
  <div>
    <button @click="isShow = !isShow">{{ isShow }}</button>
    <transition-group name="demo4" appear>
         <h1 v-show="isShow" key="asdasd">集合案例1{{ msg }}</h1>
         <h1 v-show="!isShow" key="wqe12">集合案例2{{ msg }}</h1>
    </transition-group>
  </div>
</template>

<script>
    export default {
    data() {
        return {
            msg: "eric",
            isShow: true,
        };
    },
    };
</script>

<style scoped>
    h1 {
        background-color: orange;
    }
     .demo4-enter-active
     ,.demo4-leave-active{
          transition:  1s linear;
     }
     .demo4-enter
    ,.demo4-leave-to{
         transform: translateX(-100%);
    }
    .demo4-enter-to
    ,.demo4-leave{
         transform: translateX(0px);
    }
    
</style>