<template>
  <div>
    <div class="box">
      <a href="#">京东</a>
      <a href="#">头条</a>
      <a href="#">百度</a>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      msg: "eric",
      isShow: true,
    };
  },
};
</script>

<style scoped lang="less">
/* npm i less-loader */
.box {
  width: 500px;
  height: 500px;
  border: 1px solid red;
  margin: 0 auto;
  a {
    text-decoration: none;
    margin: 0 10px;
    &:hover{
      color: gray;
      text-decoration:underline;
    }
  }
}
</style>