<template>
  <div>
    <div id="div3">
      <ul>
        <li v-for="el in arr" :key="el.id">
            <input type="checkbox"  :checked="el.isSelect" @change="changeMethod(el.id)"/><span>{{el.name}}</span><button @click="deleteArr(el.id)">删除</button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
//export Vue.extend({})
import {nanoid} from 'nanoid'

export default {
  name: "UserBody",
  props:['transOpratorArrObjToApp'],
  data() {
    const _this = this
    const opratorArrObj={
      addArrEL(value){
        console.log('添加数组元素',value)
        const elObj ={
          id:nanoid(),
          name:value,
          isSelect:false
        }
        _this.arr.unshift(elObj)
      },
      getArrLength(){
        console.log('获取数组长度')
        return _this.arr.length
      },
      getArrSelectSize(){
        console.log('获取状态为true的数据')
        const result = _this.arr.reduce((pre,el)=>{
            return el.isSelect?pre+1:pre
        },0)
        return result
      },
      updateAllState(state){
        console.log('修改所有数据状态')
        _this.arr.forEach(el =>{
          el.isSelect = state
        })
      }
    }
    this.transOpratorArrObjToApp(opratorArrObj)
    const arr = [{id:'001',name:'写作业',isSelect:true},{id:'002',name:'打游戏',isSelect:false},{id:'003',name:'烧烤',isSelect:true}]
    return {
      arr
    };
  },
  methods: {
    deleteArr(id){
      console.log('删除数组元素',id)
      this.arr = this.arr.filter(element => element.id !== id )
    },
    changeMethod(id){
      this.arr.forEach(element => {
        if(element.id === id){
          element.isSelect= !element.isSelect
        }
      });
    }
  },
};
</script>

<style lang="css" scoped>
/* <style lang="less"> */
  #div3 ul {
    list-style: none;
    padding-left: 0;
  }
  #div3 li {
    border: solid rgba(17, 17, 17, 0.623);
  }
</style>