<template>
  <div class="box"> 
      <Test/>
      <Test2/>
      <Test3/>
      <Test4/>
  </div>
</template>


<script>
  import Test from './components/Test'
  import Test2 from './components/Test2'
  import Test3 from './components/Test3'
  import Test4 from './components/Test4'
  export default {
    name:'App',
    components:{Test,Test2,Test3,Test4}
  }
</script>

<style scoped>
  .box{
    width: 500px;
   /*  height: 1000px; */
    border: 1px solid red;
    margin: 0 auto;
    /* overflow: hidden; */
  }

</style>