<template>
  <div>
    <h1 v-on:click='showMessage' ref="h1">{{msg}}</h1>
    <Person ref="person"/>
  </div>
</template>

<script>
    import Person from './components/Person.vue'
    export default {
        name:'App',
        components:{Person},
        data() {
          return {
            msg:'欢迎来到主组件'
          }
        },
        methods: {
          showMessage(){
            console.log(this)
            /* for(let attr in this.$refs){
              if(this.$refs[attr].$refs){
                console.log('VC对象',this.$refs[attr])
              }else{
                console.log('标签对象',this.$refs[attr].innerHTML)
              }
            } */
          }
        },
    }
</script>

<style>
  
</style>