<template>
  <div>
      <h1>{{introduction}}</h1>
      <h2>姓名:{{name}}</h2>
      <h2>年龄:{{myAge}}</h2>
      <h2>性别:{{sex}}</h2>
      <button @click='addAge'>点击修改年龄</button>
      <button @click='father'>调用父回调，并且往数组添加东西</button>
  </div>
</template>

<script>
    //export Vue.extend({})
    export default {
      name:'Person',
      data(){
        console.log(this)
        return {
          introduction:'如下是今年优秀的人',
          myAge:this.age ////如果修改传递参数，必须复制一份进行修改
        }
      },
      methods: {
        addAge(){
          this.myAge++ 
        },
        father(){
          this.arr.push(4)
          this.fun(this)
        }
      },
      //方式1
      props:['name','age','sex','fun','arr'],
      //方式2
      /* props:{
        name:String,
        age:Number,
        sex:String
      } */
      //方式3
      /* props:{
        name:{
          type:String,
          require:true
        },
        age:{
          type:Number,
          default:100
        },
        sex:String //可以组合方式写
      } */
    }
</script>

<style>
  
</style>