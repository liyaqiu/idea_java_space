
/* 引入Router */
import VueRouter from 'vue-router'

/* 引入需要路由的相关组件 */
import HaiXian from '../pages/HaiXian'
import YiFu from '../pages/YiFu'

/* 配置相关路由信息 */
export default new VueRouter({
    routes:[
        {
            path:'/haixian',
            component:HaiXian
        },
        {
            name:'yifuyifuyifu',
            path:'/yifu',
            component:YiFu
        }
    ],
})