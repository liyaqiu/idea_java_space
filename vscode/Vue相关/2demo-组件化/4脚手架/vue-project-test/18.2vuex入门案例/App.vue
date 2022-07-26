<template>
  <div class="box"> 
    <Demo1/>
    <Demo2/>
  </div>
</template>

<script>

  import Demo1 from './components/Demo1'
  import Demo2 from './components/Demo2'

  export default {
    name:'App',
    components:{Demo1,Demo2},
    data() {
      return {
        
      }
    },
  }
</script>

<style>

</style>