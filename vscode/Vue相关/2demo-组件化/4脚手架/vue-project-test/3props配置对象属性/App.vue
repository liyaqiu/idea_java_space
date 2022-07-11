<template>
  <div>
    
    <Person name='eric' sex='男' v-bind:age="18" :fun='fun' :arr='arr'/>
  </div>
</template>

<script>
    import Person from './components/Person.vue'
    export default {
        name:'App',
        components:{Person},
        data() {
          return {
            arr:[1,2,3]
          }
        },
        methods: {
          fun(vc){
            console.log('我是被某个子组件调用',vc,this.arr)
          }
        },
    }
</script>

<style>
  
</style>