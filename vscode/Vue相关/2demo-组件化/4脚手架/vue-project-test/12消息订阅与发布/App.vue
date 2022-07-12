<template>
  <div> 
    <Person/>
    <hr>
    <Student/>
  </div>
</template>

<script>

  import Person from './components/Person'
  import Student from './components/Student'

  export default {
    name:'App',
    components:{Person,Student},
    methods: {
      
    },
  }
</script>

<style>

</style>