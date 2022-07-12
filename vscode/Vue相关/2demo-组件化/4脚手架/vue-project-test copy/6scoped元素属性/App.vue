<template>
  <div>
    <Person/>
    <Person2/>
  </div>
</template>

<script>
    import Person2 from './components/Person2'
    import Person from './components/Person'

    export default {
        name:'App',
        components:{Person2,Person}
    }
</script>

<style>
  
</style>