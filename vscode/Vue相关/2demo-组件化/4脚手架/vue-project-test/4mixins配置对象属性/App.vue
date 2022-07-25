<template>
  <div>
    <Person/>
    <Person2/>
  </div>
</template>

<script>
    import Person from './components/Person.vue'
    import Person2 from './components/Person2.vue'
    
    export default {
        name:'App',
        components:{Person,Person2}
    }
</script>

<style>
  
</style>