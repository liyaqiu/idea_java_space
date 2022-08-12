<template>
    <div class="swiper-container" ref="carousel">
        <div class="swiper-wrapper">
            <div class="swiper-slide" v-for="caro in list" :key="caro.id">
                <img :src="caro.imgUrl" />
            </div>
        </div>
        <!-- 如果需要分页器 -->
        <div class="swiper-pagination"></div>

        <!-- 如果需要导航按钮 -->
        <div class="swiper-button-prev"></div>
        <div class="swiper-button-next"></div>
    </div>
</template>

<script>
    import Swiper from 'swiper'
    export default {
        name: 'Carousel',
        props:['list'],
        watch:{
            //监听props属性的变化
            list:{
                immediate: true, //上来就执行一次
                handler(newData,oldData){
                    //console.log(newData,oldData)
                    this.$nextTick(()=>{
                        new Swiper(this.$refs.carousel, {
                            loop: true, // 循环模式选项
                            autoplay: true,
                            pagination: {// 分页器
                                el: '.swiper-pagination',
                                clickable :true,
                            },
                            // 如果需要前进后退按钮
                            navigation: {
                                nextEl: '.swiper-button-next',
                                prevEl: '.swiper-button-prev',
                            },
                        });
                    })
                }
            }
        },
    }
</script>

<style>

</style>