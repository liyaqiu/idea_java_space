
/* 引入Router */
import VueRouter from 'vue-router'

/* 引入需要路由的相关组件 */
import HaiXian from '../pages/HaiXian'
import YiFu from '../pages/YiFu'

/* 配置相关路由信息 */
export default new VueRouter({
    routes:[
        {
            path:'/haixian/:name/:price',
            component:HaiXian,
            /* 路由中的props，可以将参数传递给组件中的props属性 */
            /* 方式1 */
            /* props:{
                name:'未知',
                price:0
            }, */

            /* 方式2，次方式只支持params传参 */
            /* props:true, */

            /* 方式3，函数，比较灵活，支持params和query */
            props($router){
                return {
                    /* name:$router.query.name,
                    price:$router.query.price */
                    name:$router.params.name,
                    price:$router.params.price
                }
            },
        },
        {
            name:'yifuyifu',
            path:'/yifu/:name/:price',
            component:YiFu
        }
    ],
})