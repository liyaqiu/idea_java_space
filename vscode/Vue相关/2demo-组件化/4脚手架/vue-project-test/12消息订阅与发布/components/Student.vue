<template>
  <div>
    Student姓名<span>{{name}}</span>
    Student年龄<span>{{age}}</span>
  </div>
</template>

<script>
  import pubsub from 'pubsub-js'
  export default {
    name:'Student',
    data() {
      return {
        name:'eric',
        age:'31'
      }
    },
    
    mounted() {
      /* this.$bus.$on('personTransNameToStudent',(value)=>{
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
      }) */
      this.id = pubsub.subscribe('personTransNameToStudent',(queueName,value)=>{
          this.name = value
          console.log(queueName,value)
      })
      console.log(this.id)
      this.id = pubsub.subscribe('personTransNameToStudent',(queueName,value)=>{
          this.name = value
          console.log(queueName,value)
      })
      console.log(this.id)
      this.id = pubsub.subscribe('personTransNameToStudent',(queueName,value)=>{
          this.name = value
          console.log(queueName,value)
      })
      console.log(this.id)
    },
    beforeDestroy() {
      //this.$bus.$off(['personTransNameToStudent'])
      pubsub.unsubscribe(this.id)
    },
  }
</script>

<style scoped>
  
</style>