
import Vue from 'vue'
/* 引入Router */
import VueRouter from 'vue-router'

/* 引入需要路由的相关组件 */
import Home from '@/pages/Home'
import Login from '@/pages/Login'
import Register from '@/pages/Register'
import Search from '@/pages/Search'

//编程式导航重复路由控制台报错问题，重写原型上的方法
const originalPush = VueRouter.prototype.push
const originalReplace = VueRouter.prototype.replace
// push
VueRouter.prototype.push = function (location, onResolve, onReject) {
    if (onResolve || onReject) {
        return originalPush.call(this, location, onResolve, onReject)
    }
    return originalPush.call(this, location,(result)=>{result},(error)=>{error})
}
// replace
VueRouter.prototype.replace = function (location, onResolve, onReject) {
    if (onResolve || onReject) {
        return originalReplace.call(this, location, onResolve, onReject)
    }
    return originalReplace.call(this, location,(result)=>{result},(error)=>{error})
}

/* 注册Router */
Vue.use(VueRouter)

/* 配置相关路由信息 */
export default new VueRouter({
    routes: [
        {
            //路由重定向
            path: '*',
            redirect: "/home",
        },
        {
            path: '/home',
            component: Home,
            meta: {
                footerIsShow: true
            }
        },
        {
            path: '/login',
            component: Login,
            meta: {
                footerIsShow: false
            }
        },
        {
            path: '/register',
            component: Register,
            meta: {
                footerIsShow: false
            }
        },
        {
            name: 'search',
            path: '/search/:keyword?',
            component: Search,
            meta: {
                footerIsShow: true
            }
        },
    ],
})
