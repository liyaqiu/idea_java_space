<template>
  <div class="user"> 
    <h1>用户信息</h1>
    <h3>用户名:{{userName}}</h3>
    <h3>性别:{{userSex}}</h3>
    <h4>地址:{{userAddress}}</h4>
    <button @click="updateUserName('ERIC1')">更新用户名</button>
    <button @click="UPDATE_USER_NAME('ERIC2')">更新用户名</button>
  </div>
</template>

<script>
  import {mapState,mapGetters,mapActions,mapMutations} from 'vuex'
  export default {
    name:'User',
    methods: {
      ...mapActions('userOptions',['updateUserName']),
      ...mapMutations('userOptions',['UPDATE_USER_NAME']),
    },
    computed: {
      ...mapState('userOptions',['userName','userSex']),
      ...mapGetters('userOptions',['userAddress']),
    },
    mounted() {
      console.log('User',this)
    },
  }
</script>

<style>

</style>