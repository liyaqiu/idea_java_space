<template>
  <div>
    <CompoTest1></CompoTest1>
    <CompoTest2></CompoTest2>
  </div>
</template>

<script>
    import CompoTest1 from './components/CompoTest1.vue'
    import CompoTest2 from './components/CompoTest2.vue'
    export default {
        name:'App',
        components:{
            CompoTest1,
            CompoTest2
        }
    }
</script>

<style>

</style>