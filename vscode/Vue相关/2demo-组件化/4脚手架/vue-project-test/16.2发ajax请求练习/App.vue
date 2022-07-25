<template>
  <div class="search_m">
        <SearchTop/>
        <SearchCenter/>
        <SearchBottom/>
   </div>
</template>


<script>
  import SearchTop from './components/SearchTop'
  import SearchCenter from './components/SearchCenter'
  import SearchBottom from './components/SearchBottom'
  export default {
    name:'App',
    components:{SearchTop,SearchCenter,SearchBottom}
  }
</script>

<style lang='less'>
  *{
    margin: 0;
    padding: 0;
  }
  .search_m{
      display: flex;
      flex-direction:column;
      width: 600px;
      height: 700px;
      /* border: 1px solid red; */
      margin: 20px auto 0;
      overflow: hidden;
  }
</style>