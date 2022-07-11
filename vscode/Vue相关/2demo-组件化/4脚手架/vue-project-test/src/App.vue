<template>
  <div>
    
    <div id="div1">
        <UserHeader :transValueToApp='transValueToApp'/>
        <UserBody :transOpratorArrObjToApp='transOpratorArrObjToApp'/>
        <UserFooter :getArrLenth='getArrLenth' :getArrSelectSize='getArrSelectSize' :updateAllState='updateAllState'/>
    </div>
  </div>
</template>

<script>
    import UserHeader from './components/UserHeader'
    import UserBody from './components/UserBody'
    import UserFooter from './components/UserFooter'

    export default {
        name:'App',
        components:{UserHeader,UserBody,UserFooter},
        data() {
          return {
            opratorArrObj:null
          }
        },
        methods: {
          transValueToApp(value){
            this.opratorArrObj.addArrEL(value)
          },
          transOpratorArrObjToApp(opratorArrObj){
              this.opratorArrObj = opratorArrObj
          },
          getArrLenth(){
            return this.opratorArrObj.getArrLength()
          },
          getArrSelectSize(){
            return this.opratorArrObj.getArrSelectSize()
          },
          updateAllState(state){
              this.opratorArrObj.updateAllState(state)
          }
        },
    }


</script>

<style>
  #div1{
        border: solid rgb(17, 17, 17);
        width: 400px;
        height: 320px;
    }
</style>