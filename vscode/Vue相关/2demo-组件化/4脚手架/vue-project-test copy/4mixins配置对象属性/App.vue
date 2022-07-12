<template>
  <div>
    <Person name='eric' sex='男' v-bind:age="18"/>
  </div>
</template>

<script>
    import Person from './components/Person.vue'
    
    export default {
        name:'App',
        components:{Person}
    }
</script>

<style>
  
</style>