<template>
  <div class="box"> 
    <!-- 默认插槽 -->
    <Demo1>
      <div>
        <a href="#" v-for="(el,index) in arrs1" :key="index">{{el}}</a>
      </div>
      <!-- template不会显示在页面 -->
      <template>
        <div>
          <a href="#" v-for="(el,index) in arrs2" :key="index">{{el}}</a>
        </div>
      </template>
    </Demo1>

    <!-- 具名插槽 -->
    <Demo2>
      <div slot="two">
        <a href="#" v-for="(el,index) in arrs1" :key="index">{{el}}</a>
      </div>
      <!-- template不会显示在页面 -->
      <!-- <template slot="one"> -->
      <template v-slot:one>
        <div>
          <a href="#" v-for="(el,index) in arrs2" :key="index">{{el}}</a>
        </div>
      </template>
    </Demo2>

    <!-- 作用域插槽 -->
    <Demo3>
      
      <!-- template不会显示在页面 -->
      <!-- 新语法用了报错 v-slot:one -->
      <!-- <template slot="one" slot-scope="obj1"> -->
      <template slot="one" slot-scope="{arrs3}"> <!-- 解构赋值语法 -->
        <div>
          <a href="#" v-for="(el,index) in arrs3" :key="index">{{el}}</a>
        </div>
      </template>
      <template slot="two" scope="obj">
        <div>
          <a href="#" v-for="(el,index) in obj.arrs4" :key="index">{{el}}</a>
        </div>
      </template>
    </Demo3>
  </div>
</template>

<script>
  import Demo1 from './components/Demo1'
  import Demo2 from './components/Demo2'
  import Demo3 from './components/Demo3'

  export default {
    name:'App',
    components:{Demo1,Demo2,Demo3},
    data() {
      return {
        arrs1:['百度','头条','美团'],
        arrs2:['新浪','360','滴滴'],
      }
    }
  }
</script>

<style>
  a{
    margin: 0 10px;
  }
</style>