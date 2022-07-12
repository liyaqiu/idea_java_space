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
      this.$bus.$on('personTransNameToStudent',(value)=>{
        this.name = value
        console.log(value)
      })
      this.$bus.$on('personTransNameToStudent',(value)=>{
        this.name = value
        console.log(value)
      })
      this.$bus.$on('personTransNameToStudent',(value)=>{
        this.name = value
        console.log(value)
      })
    },
    beforeDestroy() {
      this.$bus.$off(['personTransNameToStudent'])
    },
  }
</script>

<style scoped>
  
</style>