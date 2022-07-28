<template>
  <div class="right yifu_right">
      <ul>
          <!-- 嵌套需要使用完成路径 -->
          <li><router-link to='/yifu/YiFu_2'>2</router-link></li>
          <li><router-link to='/yifu/YiFu_3'>3</router-link></li>
          <li><router-link to='/yifu/YiFu_4'>4</router-link></li>
          <li><router-link to='/yifu/YiFu_5'>5</router-link></li>
          <li><router-link to='/yifu/YiFu_6'>6</router-link></li>
      </ul>
      <ol >
          <li><router-view></router-view></li>
      </ol>
      
  </div>
</template>

<script>
  
  export default {
    name:'YiFu',
    mounted() {
      console.log('YiFu组件挂载',this.$route,this.$router)
    },
    beforeDestroy() {
      console.log('YiFu组件销毁')
    },
  }
</script>

<style lang='less' scoped>
  .right{
      box-sizing: border-box;
      float: left;
      width: 400px;
      height: 100%;
      img{
          width: 100%;
          height: 100%;
      }
  }
  .yifu_right{
        display: flex;
        flex-direction: column;
        ul{
            display: flex;
            flex: 5%;
        }
        ul li{
            flex: 20%;
            float: left;
            margin: 0 5px 0;
            list-style: none;
            background-color: pink;
            text-align: center;
            cursor: pointer;
            
        }
        ul li a{
            display: block;
            width: 100%;
            height: 100%;
        }
        ul li:hover{
            background: hotpink;
        }
        
        ol{
            width: 100%;
            height: 100%;
            flex: 95%;
        }
        ol li{
            list-style: none;
            margin-top: 20px;
        }
    }
</style>