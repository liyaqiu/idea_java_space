
/* 引入Router */
import VueRouter from 'vue-router'

/* 引入需要路由的相关组件 */
import HaiXian from '../pages/HaiXian'
import YiFu from '../pages/YiFu'
import YiFu_2 from '../pages/YiFu_2'
import YiFu_3 from '../pages/YiFu_3'
import YiFu_4 from '../pages/YiFu_4'
import YiFu_5 from '../pages/YiFu_5'
import YiFu_6 from '../pages/YiFu_6'

import Details from '../pages/Details'


/* 配置相关路由信息 */
export default new VueRouter({
    routes:[
        {
            path:'/haixian',
            component:HaiXian
        },
        {
            path:'/yifu',
            component:YiFu,
            children:[
                {
                    /* 嵌套使用，不需要写斜杠 */
                    path:'YiFu_2',
                    component:YiFu_2,
                    children:[
                        {
                            path:'details',
                            component:Details,
                        }
                    ]
                },
                {
                    path:'YiFu_3',
                    component:YiFu_3,
                },
                {
                    path:'YiFu_4',
                    component:YiFu_4,
                },
                {
                    path:'YiFu_5',
                    component:YiFu_5,
                },
                {
                    path:'YiFu_6',
                    component:YiFu_6,
                },
            ]
        }
    ],
})