<template>
  <div>
    <button @click="$router.forward()">前进</button>
    <button @click="$router.back()">后退</button>
    <button @click="$router.go(3)">前进2</button>
    <button @click="$router.go(-3)">后退2</button>
    <button @click="testPath('/haixian')">生鲜页</button>
    <button @click="testName('yifuyifu')">衣服页</button>
    <div class="box">
      <div class="left">
          <ul>
              <!-- 原始写法 -->
              <!-- <li><a href="./haixian.html" class="active_a">生鲜页</a></li>
              <li><a href="./yifu.html">衣服页</a></li> -->
            <!-- 利用vue router来实现路由 -->
            <li><router-link to="/haixian" active-class="active_a">生鲜页</router-link></li>
            <li><router-link to="/yifu" active-class="active_a">衣服页</router-link></li>
          </ul>
      </div>
      <!-- 触发路由后需要展示的组件位置 -->
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  export default {
    name:'App',
    data() {
      return {
        
      }
    },
    methods: {
      /* testPath(val){
        this.$router.push({
          path:val,
          query:{name:'eric1111'},
           params:{}
        },result=>{},error=>{}) //解决重复刷新的问题
      }, */
      testPath(val){
        this.$router.push({
          path:val,
         /* query:{name:'eric1111'}, */
           /* params:{} */
        })
      },
      testName(val){
        this.$router.replace({
          name:val,
          /* query:{},
          params:{} */
        })
      }
    },
    mounted() {
      console.log('APP',this)
    },
  }
</script>

<style lang='less' >
  *{
      margin: 0;
      padding: 0;
  }
  button{
    width: 50px;
    height: 50px;
    margin: 20px 20px;
    cursor: pointer;
  }
  .box{
      width: 500px;
      height: 500px;
      border: 1px solid red;
      margin-left: 20px;
      margin-top: 20px;
      .left{
          box-sizing: border-box;
          float: left;
          width: 100px;
          height: 100%;
          border-right: 1px solid red;
          ul{
              display: flex;
              flex-direction: column;
              height: 100%;
          }
          li{
              list-style: none;
              padding-left: 10px;
              padding-top: 20px;
              a{
                  text-decoration: none;
              }
              .active_a{
                background-color: pink;
            }
          }
      }
  }
</style>