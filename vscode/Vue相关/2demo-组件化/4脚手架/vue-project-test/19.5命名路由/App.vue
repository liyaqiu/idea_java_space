<template>
  <div class="box">
      <div class="left">
          <ul>
              <!-- 原始写法 -->
              <!-- <li><a href="./haixian.html" class="active_a">生鲜页</a></li>
              <li><a href="./yifu.html">衣服页</a></li> -->

            <!-- 利用vue router来实现路由 -->
            <li><router-link to="/haixian" active-class="active_a">生鲜页</router-link></li>

            <!-- 命名路由，原始写法 -->
            <!-- <li><router-link to="/yifu" active-class="active_a">衣服页</router-link></li> -->

            <!-- 此种方式使用命令路由虽然可以，但是地址栏改变了 -->
            <!-- <li><router-link to="yifuyifuyifu" active-class="active_a">衣服页</router-link></li> -->
            <!-- 命令路由，改版写法 -->
            <li><router-link :to="{name:'yifuyifuyifu'}" active-class="active_a">衣服页1</router-link></li>
          </ul>
      </div>
      <!-- 触发路由后需要展示的组件位置 -->
      <router-view></router-view>
  </div>
</template>

<script>

  export default {
    name:'App',
   
    data() {
      return {
        
      }
    },
  }
</script>

<style lang='less' >
  *{
      margin: 0;
      padding: 0;
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