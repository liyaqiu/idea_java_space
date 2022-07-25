<template>
  <div> 
      <button @click="getMessage1">发ajax请求</button>
      <button @click="getMessage2">发ajax请求</button>
  </div>
</template>


<script>
  import axios from 'axios'
  export default {
    name:'App',
    methods: {
      getMessage1(){
        axios.get('http://192.168.0.109:8080/server1/test1')
        .then(
            response =>{
              console.log(response.data)
            },
            error =>{
              console.log(error.message)
            }
        )
      },
      getMessage2(){
        axios.get('http://192.168.0.109:8080/server2/test1')
        .then(
            response =>{
              console.log(response.data)
            },
            error =>{
              console.log(error.message)
            }
        )
      }
    },
  }
</script>

<style>

</style>