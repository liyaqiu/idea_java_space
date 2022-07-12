<template>
  <div>
    Student姓名<span>{{name}}</span>
    Student年龄<span>{{age}}</span>
    
  </div>
</template>

<script>
  export default {
    name:'Student',
    data() {
      return {
        name:'eric',
        age:'31'
      }
    },
    mounted() {
      //支持绑定多个，一旦销毁会全部销毁
      this.$bus.$on('personTransNameToStudent',(value)=>{
        this.name = value
        console.log(1)
      })
      this.$bus.$on('personTransNameToStudent',(value)=>{
        this.name = value
        console.log(2)
      })
      this.$bus.$on('personTransNameToStudent',(value)=>{
        this.name = value
        console.log(3)
      })
    },
    beforeDestroy() {
      this.$bus.$off(['personTransNameToStudent'])
    },
  }
</script>

<style scoped>
  
</style>