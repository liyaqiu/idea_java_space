<template>
  <div class="box"> 
    <Demo/>
  </div>
</template>

<script>

  import Demo from './components/Demo'
  export default {
    name:'App',
    components:{Demo},
    data() {
      return {
      }
    },
    mounted() {
      console.log('demo',this)
    },
  }
</script>

<style>
  
</style>