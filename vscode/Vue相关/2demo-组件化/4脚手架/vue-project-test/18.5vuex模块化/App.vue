<template>
  <div class="box"> 
    <User/>
    <hr>
    <Shop/>
  </div>
</template>

<script>

  import User from './components/User'
  import Shop from './components/Shop'

  export default {
    name:'App',
    components:{User,Shop},
    data() {
      return {
        
      }
    },
  }
</script>

<style>

</style>