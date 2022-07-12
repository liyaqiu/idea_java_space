<template>
  <div>
    <div id="div4">
      <input type="checkbox" @change="selectAll" />已完成{{getArrSelectSize()}}/全部{{arrSize()}}
      <button>删除已完成任务</button>
    </div>
  </div>
</template>

<script>
//export Vue.extend({})

export default {
  name: "UserFooter",
  props:['getArrLenth','getArrSelectSize','updateAllState'],
  data() {
    return {
    }
  },
  methods:{
    arrSize(){
      return this.getArrLenth()
    },
    selectAll(e){
      this.updateAllState(e.target.checked)
    }
  }
};
</script>

<style lang="css" scoped>
/* <style lang="less"> */
  #div4 {
    border: solid rgb(17, 17, 17);
    width: 380px;
    height: 90px;
  }
</style>