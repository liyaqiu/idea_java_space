<template>
  <div>
    <button @click="isShow = !isShow">{{ isShow }}</button>
    <transition>
         <h1 v-show="isShow">过渡案例1{{ msg }}</h1>
    </transition>
    <transition name="demo2" appear>
         <h1 v-show="isShow">过渡案例2{{ msg }}</h1>
    </transition>
 
     <transition name="demo3" appear>
         <h1 v-show="isShow">过渡案例3{{ msg }}</h1>
    </transition>
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
        transition:  1s linear;
    }

     .v-enter{
         transform: translateX(-100%);
    }
    .v-enter-to{
         transform: translateX(0px);
    }
    .v-leave{
         transform: translateX(0px);
    }
    .v-leave-to{
         transform: translateX(-100%);
    }


    .demo2-enter{
         transform: translateX(-100%);
    }
    .demo2-enter-to{
         transform: translateX(0px);
    }
    .demo2-leave{
         transform: translateX(0px);
    }
    .demo2-leave-to{
         transform: translateX(-100%);
    }

     .demo3-enter
    ,.demo3-leave-to{
         transform: translateX(-100%);
    }
    .demo3-enter-to
    ,.demo3-leave{
         transform: translateX(0px);
    }
    
</style>