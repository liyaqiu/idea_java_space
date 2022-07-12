<template>
  
  <div> 
    <button @click="method1">我触发我自己的事件</button>
    <Person ref='personRef'/>
  </div>

</template>

<script>

  import Person from './components/Person'

  export default {
    name:'App',
    components:{Person},
    methods: {
      method1(){
        this.$emit('mymyclick','hello-world')
      }
    },
    mounted() {
        /* this.$refs.personRef.$once('myclick',function(msg){  只触发一次*/
        this.$refs.personRef.$on('myclick',function(msg){
            console.log(msg)
        }) //给子组件绑定事件

        /* this.$refs.personRef.off('myclick') //解绑事件
        this.$refs.personRef.off(['myclick','myclick1']) //解绑多事件
        this.$refs.personRef.off() //解绑所有事件 */

        this.$on('mymyclick',function(msg){
          console.log('自己触发',msg)
        })//自己给自己绑定事件
    }
  }
</script>

<style>

</style>