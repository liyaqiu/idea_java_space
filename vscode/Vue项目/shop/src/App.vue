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

export default {
  name: "App",
  components: { Header, Footer },
  mounted() {
    //console.log('app',this)
  },
};
</script>

<style>
</style>
