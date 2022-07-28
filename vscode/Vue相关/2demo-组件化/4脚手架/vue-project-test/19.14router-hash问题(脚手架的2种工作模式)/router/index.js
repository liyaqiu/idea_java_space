
/* 引入Router */
import VueRouter from 'vue-router'

/* 引入需要路由的相关组件 */
import HaiXian from '../pages/HaiXian'
import YiFu from '../pages/YiFu'

/* 配置相关路由信息 */
export default new VueRouter({
    /* 
        路由器的两种工作模式
            mode:'hash',（默认）
            mode:'history',
        1.对于一个url来说，什么是hash值？  井号(#)后面的内容就是hash值，并且井号(#)后面的内容不会发送给服务器
                http://192.168.0.109:8899/index.html#/haixian
                http://192.168.0.109:8899/index.html?name=123#/haixian
        
        2.hash模式:
            2.1地址中永远带着#号，不美观
            2.2若以后将通过第三方手机app分析，若app校验严格，有可能地址会被标记为不合法
            2.3兼容性好
        3.hastory模式:
            3.1地址干净，美观
            3.2兼容性相对差
            3.3应该部署上线时需要后端人员支持，解决刷新页面服务器404问题
    
    */
    mode:'history',
    routes:[
        {
            path:'/haixian',
            component:HaiXian
        },
        {
            path:'/yifu',
            component:YiFu
        }
    ],
})