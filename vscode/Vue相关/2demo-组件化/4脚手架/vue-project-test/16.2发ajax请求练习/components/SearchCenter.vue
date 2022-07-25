<template>
    <div class="center">
        <input type="text" placeholder="请输入查询" v-model="keyworkd">
        <button @click="getUserInfo">查询</button>
    </div>
</template>


<script>
  import axios from 'axios'
  export default {
    name:'SearchCenter',
    data(){
      return {
        keyworkd:''
      }
    },
    methods: {
      getUserInfo(){
        this.$bus.$emit('getUserInfo',{errorMsg:'',isQuering:true,userInfos:[]})
        axios.get(`https://api.github.com/search/users?q=${this.keyworkd}`)
        .then(
            response =>{
              this.$bus.$emit('getUserInfo',{isQuering:false,userInfos:response.data.items})
            },
            error =>{
              this.$bus.$emit('getUserInfo',{errorMsg:error.message,isQuering:false,userInfos:[]})
            }
        )
      }
    },
  }
</script>

<style lang='less' scoped>

  .center{
      box-sizing: border-box;
      flex: 2;
      /* background-color: hotpink; */
      padding-top: 10px;
      text-align: center;
      input{
          outline: none;
          border: 0;
          width: 180px;
          height: 30px;
          padding-left: 20px;
          border: 2px solid antiquewhite;
      }
      button{
          width: 50px;
          height: 30px;
          vertical-align: middle;
          border: 0;
          cursor: pointer;
          background-color: antiquewhite;
          &:hover{
              background-color: rgb(130, 125, 119);
          }
      }
  }

</style>