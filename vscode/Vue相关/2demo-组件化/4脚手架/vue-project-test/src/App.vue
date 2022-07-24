<template>
  <div class="box"> 
    <button @click="isShow=!isShow">{{isShow}}</button>
    <transition>
         <h1 v-show="isShow">你好{{msg}}</h1>
    </transition>

  </div>
</template>


<script>
  export default {
    name:'App',
    data() {
      return {
        msg:'eric',
        isShow:false
      }
    },
  }
</script>

<style scoped>
  .box{
    width: 500px;
    height: 500px;
    border: 1px solid red;
    margin: 0 auto;
    /* overflow: hidden; */
  }

  h1{
    transform: translateX(-200px);
  }
  .v-enter-active{
    animation: come 1s linear forwards;
  }
  .v-leave-active{
    animation: go 1s linear forwards;
  }

  @keyframes come {
    100%{
      transform: translateX(200px);
    }
  }
  @keyframes go {
    100%{
      transform: translateX(-200px);
    }
  }

</style>