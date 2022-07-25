<template>
    <div class="bottom">
        <ul v-show="dataObj.userInfos.length">
            <li v-for="userInfo of this.dataObj.userInfos" :key="userInfo.id">
                <a target="_blank" :href="userInfo.html_url"><img :src="userInfo.avatar_url"><p>{{userInfo.login}}</p></a>
            </li>
        </ul>
        <h1 v-show="dataObj.isQuering">查询中，请稍后。。。</h1>
        <h1 v-show="dataObj.errorMsg">{{dataObj.errorMsg}}</h1>
    </div>
</template>


<script>
  export default {
    name:'SearchButtom',
    data() {
        return {
            dataObj:{
                userInfos:[],
                isQuering:false,
                errorMsg:''
            }
        }
    },
    mounted() {
        this.$bus.$on('getUserInfo',(dataObj) =>{
            this.dataObj = {...this.dataObj,...dataObj}
        })
    },
    beforeDestroy() {
        this.$bus.$off(['getUserInfo'])
    }
  }
</script>

<style lang='less' scoped>

  .bottom{
        flex: 18;
        /* background-color: pink; */
        li{
            box-sizing: border-box;
            width: 100px;
            height: 100px;
            border-top: 1px solid red;
            border-right: 1px solid red;
            list-style: none;
            float: left;
            text-align: center;
            &:nth-child(6n+1){
                border-left: 1px solid red;
            }
            &:nth-child(n+25){
                border-bottom: 1px solid red;
            }
            a{
                text-decoration: none;
                color: gray;
            }
            img{
                float: left;
                width: 100%;
                height: 80%;
                margin-bottom: 2px;
            }
            p{
                font-size: 12px;
                
            }
        }
    }

</style>