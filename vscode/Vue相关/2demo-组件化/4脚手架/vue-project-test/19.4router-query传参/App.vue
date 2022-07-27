<template>
  <div class="box">
      <div class="left">
          <ul>
              <!-- 原始写法 -->
              <!-- <li><a href="./haixian.html" class="active_a">生鲜页</a></li>
              <li><a href="./yifu.html">衣服页</a></li> -->

            <!-- 利用vue router来实现路由 -->
            <!-- 传参方式1 -->
           <li><router-link :to="`/haixian?name=${haixian.name}&price=${haixian.price}`" active-class="active_a">生鲜页</router-link></li>
            <!-- 传参方式2 -->
            <li><router-link :to="{
              path:'/yifu',
              query:{
                name:yifu.name,
                price:yifu.price
              }
            }" active-class="active_a">衣服页</router-link></li>
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
        haixian:{
          name:'鱿鱼',
          price: 50
        },
        yifu:{
          name:'阿迪达斯',
          price:999
        }
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