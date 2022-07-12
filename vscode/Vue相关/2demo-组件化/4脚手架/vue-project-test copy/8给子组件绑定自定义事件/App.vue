<template>
  <div>
    <!-- <Person @myClick.once='myMthod'/> 给子组件绑定事件，只能触发一次-->
    <Person @myClick='myMthod'/>
   <!--  <Person v-on:myClick='myMthod'/> v-on或者@-->
  </div>
</template>

<script>

  import Person from './components/Person'

  export default {
    name:'App',
    components:{Person},
    methods: {
      myMthod(msg){
        console.log(msg)
      }
    },
  }
</script>

<style>

</style>