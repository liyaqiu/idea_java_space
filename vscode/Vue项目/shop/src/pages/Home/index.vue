<template>
    <div>
        <TypeNav/>
        <ListContainer/>
        <Recommend/>
        <Rank/>
        <Like/>
        <Floor v-for="floor in floorList" :key="floor.id" :list="floor"/>
        <Brand/>
    </div>
</template>

<script>

    import {mapState} from 'vuex'

    import ListContainer from '@/pages/Home/ListContainer'
    import Recommend from '@/pages/Home/Recommend'
    import Rank from '@/pages/Home/Rank'
    import Like from '@/pages/Home/Like'
    import Floor from '@/pages/Home/Floor'
    import Brand from '@/pages/Home/Brand'


    export default {
        name:'Home',
        components:{ListContainer,Recommend,Rank,Like,Floor,Brand,},
        computed: {
            ...mapState('home',['floorList']),
        },
        mounted() {
            this.$store.dispatch('home/getFloorList')
        },
    }
</script>

<style scoped lang='less'>

</style>