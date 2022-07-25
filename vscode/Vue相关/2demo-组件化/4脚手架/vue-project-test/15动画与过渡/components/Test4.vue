<template>
  <div>
    <button @click="isShow = !isShow">{{ isShow }}</button>
    <transition-group 
        name="animate__animated animate__bounce" 
        appear
        enter-active-class="animate__fadeInDown"
        leave-active-class="animate__fadeOut"
    >
         <h1 v-show="isShow" key="asdasd">使用第三方库1{{ msg }}</h1>
         <h1 v-show="!isShow" key="wqe12">使用第三方库2{{ msg }}</h1>
    </transition-group>
  </div>
</template>

<script>
    import 'animate.css';
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
    
</style>