
import Vue from 'vue'
/* 引入Router */
import VueRouter from 'vue-router'
/* 引入路由配置 */
import routes from './routes'

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
    routes,
    scrollBehavior(to, from, savedPosition) {
        // 始终滚动到顶部
        return { y: 0 }
    },
})
