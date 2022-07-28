
/* 引入Router */
import VueRouter from 'vue-router'

/* 引入需要路由的相关组件 */
import HaiXian from '../pages/HaiXian'
import YiFu from '../pages/YiFu'

/* 配置相关路由信息 */
const router =  new VueRouter({
    routes:[
        {
            path:'/haixian',
            component:HaiXian,
            meta:{title:'海鲜页'},
            beforeEnter(to,from,next){
                console.log('独享路由守卫')
                next()
            }
        },
        {
            path:'/yifu',
            component:YiFu,
            meta:{isAuth:true,title:'衣服页'}
        }
    ],
})

/* 全局路由守卫-前置路由守卫 */
router.beforeEach((to,form,next)=>{
    console.log('全局路由守卫-前置路由守卫',to,form)
    if(to.meta.isAuth){
        //此处应该向服务器发送ajax请求，看看权限是否放行
        if(localStorage.getItem('token')==='123456'){
            next()
        }else{
            console.log('权限不足')
        }
    }else{
        next()
    }
    
})


/* 
    全局路由守卫-后置路由守卫
    前置不放行，不会触发后置
 */
router.afterEach((to,form)=>{
    console.log('全局路由守卫-后置路由守卫',to,form)
    document.title = to.meta.title|| '系统'
})

export default router