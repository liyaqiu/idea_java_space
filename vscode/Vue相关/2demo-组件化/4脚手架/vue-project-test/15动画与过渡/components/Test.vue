<template>
  <div>
    <button @click="isShow = !isShow">{{ isShow }}</button>
    <transition>
         <h1  v-show="isShow">动画案例1{{ msg }}</h1>
    </transition>
    <transition name="demo1">
         <h1  v-show="isShow">动画案例2{{ msg }}</h1>
    </transition>
    <transition name="demo2" appear>
         <h1  v-show="isShow">动画案例3{{ msg }}</h1>
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
    }

    .v-enter-active{
         animation: move 1s linear forwards;
    }
    .v-leave-active{
         animation: move 1s linear forwards reverse;
    }
    .demo1-enter-active{
         animation: move 1s linear ;
    }
    .demo1-leave-active{
         animation: move 1s linear  reverse;
    }
    .demo2-enter-active{
         animation: move 1s linear ;
    }
    .demo2-leave-active{
         animation: move 1s linear  reverse;
    }
    @keyframes move {
        0%{
            transform: translateX(-100%);
        }
        100% {
            transform: translateX(0px);
        }
    }
</style>