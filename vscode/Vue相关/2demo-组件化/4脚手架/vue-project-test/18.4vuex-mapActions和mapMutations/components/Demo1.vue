<template>
  <div class="demo1"> 
    <p @click="updateMsg">方式1调用actions中的方法</p>
    <p @click="update(updateVal)">方式1调用actions中的方法</p>

    <p @click="updateMsgCommit">方式1调用mutations中的方法</p>
    <p @click="UPDATE(updateVal)">方式1调用mutations中的方法</p>
  </div>
</template>

<script>
  import {mapActions,mapMutations} from 'vuex'
  export default {
    name:'Demo1',
   
    data() {
      return {
        updateVal:'Demo1-hello-world'
      }
    },
    methods: {
      updateMsg(){
        this.$store.dispatch('update',this.updateVal)
      },
      /* 对象写法 */
      /* ...mapActions({update:'update'}), */
      /* 数组写法 封装了$store.dispatch，调用actions对象中的方法*/
      ...mapActions(['update']),

      updateMsgCommit(){
        this.$store.commit('UPDATE',this.updateVal)
      },
      /* 对象写法 */
      /* ...mapMutations({UPDATE:'UPDATE'}), */
      /* 数组写法 封装了$store.commit，调用mutations对象中的方法*/
      ...mapMutations(['UPDATE']),
    },
    mounted() {
      console.log('Demo1',this)
    },
  }
</script>

<style>

</style>