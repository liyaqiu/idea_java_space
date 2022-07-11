<template>
  <div>
    <h1>uuid:{{nanoid()}}</h1>

    <input type="text" @change="show()">
    
    <Person/>
    <Person2/>
  </div>
</template>

<script>
    import Person2 from './components/Person2'
    import Person from './components/Person'

    import {nanoid} from 'nanoid'

    export default {
        name:'App',
        components:{Person2,Person},
        data() {
          console.log(this)
          return {
            nanoid
          }
        },
        methods: {
          show(){
            console.log(1)
          }
        },
    }


</script>

<style>
  
</style>