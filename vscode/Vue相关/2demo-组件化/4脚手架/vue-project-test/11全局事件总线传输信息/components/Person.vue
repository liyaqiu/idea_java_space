<template>
  <div>
    Person姓名<span>{{name}}</span>
    Person年龄<span>{{age}}</span>
    <button @click="personTransNameToStudent">点我将名字传递给Student</button>
  </div>
</template>

<script>
  export default {
    name:'Person',
    data() {
      return {
        name:'中国人',
        age:'100'
      }
    },
    methods: {
      personTransNameToStudent(){
        this.$bus.$emit('personTransNameToStudent',this.name)
      }    
    },
  }
</script>

<style scoped>
  
</style>