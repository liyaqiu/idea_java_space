<template>
  <div>
    <Header></Header>
    <router-view></router-view>
    <Footer v-show='$route.meta.footerIsShow'></Footer>
  </div>
</template>

<script>
import Header from "./components/Header";
import Footer from "./components/Footer";

import {mapActions} from 'vuex'

export default {
  name: "App",
  components: { Header, Footer },
  methods: {
    ...mapActions('home',['categoryList']),
  },
  mounted() {
    //console.log('App',this)
    this.categoryList()//只对列表数据初始化一次
  },
};
</script>

<style>
</style>
